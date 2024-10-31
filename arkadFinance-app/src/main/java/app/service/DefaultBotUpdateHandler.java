package app.service;

import app.handler.UpdateHandler;
import app.utils.MessageService;
import finance.Expense;
import finance.Income;
import finance.category.CategoryType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import services.FinanceService;
import services.UserService;
import user.User;

import java.text.MessageFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
@Primary
public class DefaultBotUpdateHandler implements UpdateHandler {

    @Autowired
    private UserService userService;

    @Autowired
    private FinanceService financeService;
    @Autowired
    private MessageService messageService;

    private Map<Long, UserState> userStates = new HashMap<>();
    private Map<Long, Integer> userAmounts = new HashMap<>();

    @Override
    public SendMessage handleUpdate(Update update) {
        if(update.hasMessage() && update.getMessage().hasText()){
            UserState userState = userStates.getOrDefault(update.getMessage().getChatId(), UserState.NOTHING);
            Message message = update.getMessage();
            if(userState != UserState.NOTHING){
                return switch (userState) {
                    case UserState.WAITING_FOR_INCOME_AMOUNT -> recordIncomeCategory(message);
                    case UserState.WAITING_FOR_INCOME_CATEGORY -> addIncome(message);
                    case UserState.WAITING_FOR_EXPENSE_AMOUNT -> recordExpenseCategory(message);
                    case UserState.WAITING_FOR_EXPENSE_CATEGORY -> addExpense(message);
                    default -> null;
                };
            }
            String messageText = update.getMessage().getText();

            return switch (messageText) {
                case "/start" -> registerUser(message);
                case "💰 Добавить доход" -> recordIncomeAmount(message);
                case "💸 Добавить расход" -> recordExpenseAmount(message);
                default -> null;
            };
        }
        else return null;
    }

    private SendMessage registerUser(Message message){
        User user = userService.getUserByChatId(message.getChatId());

        if(user == null){
            user = new User();
            user.setChatId(message.getChatId());
            user.setUsername(message.getFrom().getUserName());
            user.setFirstName(message.getFrom().getFirstName());
            user.setLastName(message.getFrom().getLastName());
            userService.postUser(user);
        }

        String messageText = MessageFormat.format("{0} добро пожаловать в Arkad Finance! Давайте сделаем первую запись! 💰", user.getFirstName());

        return messageService.sendMessage(user.getChatId(), messageText, true);
    }

    private SendMessage recordIncomeAmount(Message message){
        userStates.put(message.getChatId(), UserState.WAITING_FOR_INCOME_AMOUNT);
        String messageText = "💰 Введите сумму дохода: ";
        return messageService.sendMessage(message.getChatId(), messageText, false);
    }

    private SendMessage recordIncomeCategory(Message message){
        userStates.put(message.getChatId(), UserState.WAITING_FOR_INCOME_CATEGORY);
        userAmounts.put(message.getChatId(), Integer.parseInt(message.getText()));
        String messageText = "💰 Выберите категорию дохода на вашей клавиатуре: ";
        var categories = financeService.getCategoriesByCategoryTypeSysName(CategoryType.INCOMES_SYS_NAME);
        return messageService.sendMessageWithCategory(message.getChatId(), messageText, categories);
    }

    private SendMessage addIncome(Message message){
        userStates.put(message.getChatId(), UserState.NOTHING);
        Income income = new Income();
        income.setCategory(financeService.getCategoryByName(message.getText()));
        income.setAmount(userAmounts.get(message.getChatId()));
        income.setUser(userService.getUserByChatId(message.getChatId()));
        income.setIncomeDate(new Date());

        User user = financeService.postIncome(income);
        return messageService.sendActualInfo(user);
    }

    private SendMessage recordExpenseAmount(Message message){
        userStates.put(message.getChatId(), UserState.WAITING_FOR_EXPENSE_AMOUNT);
        String messageText = "💸 Введите сумму расхода: ";
        return messageService.sendMessage(message.getChatId(), messageText, false);
    }

    private SendMessage recordExpenseCategory(Message message){
        userStates.put(message.getChatId(), UserState.WAITING_FOR_EXPENSE_CATEGORY);
        userAmounts.put(message.getChatId(), Integer.parseInt(message.getText()));
        String messageText = "💸 Выберите категорию расхода на вашей клавиатуре: ";
        var categories = financeService.getCategoriesByCategoryTypeSysName(CategoryType.EXPENSE_SYS_NAME);
        return messageService.sendMessageWithCategory(message.getChatId(), messageText, categories);
    }

    private SendMessage addExpense(Message message){
        userStates.put(message.getChatId(), UserState.NOTHING);
        Expense expense = new Expense();
        expense.setCategory(financeService.getCategoryByName(message.getText()));
        expense.setAmount(userAmounts.get(message.getChatId()));
        expense.setUser(userService.getUserByChatId(message.getChatId()));
        expense.setExpenseDate(new Date());

        User user = financeService.postExpense(expense);
        return messageService.sendActualInfo(user);
    }
}
