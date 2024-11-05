package app.utils;


import finance.Expense;
import finance.Income;
import finance.category.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import services.FinanceService;
import user.User;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

@Component
public class MessageService {
    @Autowired
    private FinanceService financeService;

    public SendMessage sendMessage(Long chatId, String messageText, boolean withMainMenu) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(messageText);
        if (withMainMenu) message.setReplyMarkup(createMainMenu());

        return message;
    }

    public SendMessage sendMessageWithCategory(Long chatId, String messageText, List<Category> categories) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(messageText);
        message.setReplyMarkup(getCategoryMenu(categories));

        return message;
    }

    public SendMessage sendActualInfo(User user) {
        SendMessage message = new SendMessage();
        String actualMoney = calculateActualInfo(user);
        message.setChatId(user.getChatId());
        message.setText(MessageFormat.format("""
                Ваш текущий баланс: {0}
                                
                                
                """, actualMoney));
        message.setReplyMarkup(createMainMenu());

        return message;
    }

    public SendMessage sendIncomesHistory(Long chatId){
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setReplyMarkup(createMainMenu());
        List<Income> incomeList = financeService.getIncomes(chatId);
        message.setText(incomeList.isEmpty() ? "Список доходов пуст" : createIncomesListInfo(incomeList));

        return message;
    }

    public SendMessage sendExpenseHistory(Long chatId){
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setReplyMarkup(createMainMenu());
        List<Expense> expenseList = financeService.getExpenses(chatId);
        message.setText(expenseList.isEmpty() ? "Список расходов пуст" : createExpensesListInfo(expenseList));

        return message;
    }

    private String createIncomesListInfo(List<Income> incomeList){
        StringBuilder builder = new StringBuilder();
        for(Income income : incomeList){
            builder.append(MessageFormat.format
                    ("⏳{0} - \uD83D\uDCC3{1} - 💰{2}\n",
                    income.getIncomeDate(),
                    income.getCategory().getName(),
                    income.getAmount()));
        }
        return builder.toString();
    }
    private String createExpensesListInfo(List<Expense> expenseList){
        StringBuilder builder = new StringBuilder();
        for(Expense expense : expenseList){
            builder.append(MessageFormat.format
                    ("⏳{0} - \uD83D\uDCC3{1} - 💸{2}\n",
                            expense.getExpenseDate(),
                            expense.getCategory().getName(),
                            expense.getAmount()));
        }
        return builder.toString();
    }

    private String calculateActualInfo(User user) {
        double totalIncome = user.getIncomes().stream()
                .mapToDouble(Income::getAmount)
                .sum();

        double totalExpense = user.getExpenses().stream()
                .mapToDouble(Expense::getAmount)
                .sum();

        return String.valueOf(totalIncome - totalExpense);
    }

    private ReplyKeyboardMarkup createMainMenu() {
        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        keyboardMarkup.setResizeKeyboard(true);
        keyboardMarkup.setSelective(true);

        KeyboardRow firstRow = new KeyboardRow();
        firstRow.add(new KeyboardButton("💰 Добавить доход"));
        firstRow.add(new KeyboardButton("💸 Добавить расход"));
        KeyboardRow secondRow = new KeyboardRow();
        secondRow.add(new KeyboardButton("📄 История доходов"));
        KeyboardRow thirdRow = new KeyboardRow();
        thirdRow.add(new KeyboardButton("📄 История расходов"));

        List<KeyboardRow> keyboard = new ArrayList<>();
        keyboard.add(firstRow);
        keyboard.add(secondRow);
        keyboard.add(thirdRow);

        keyboardMarkup.setKeyboard(keyboard);
        return keyboardMarkup;
    }

    private ReplyKeyboardMarkup getCategoryMenu(List<Category> categories) {
        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        keyboardMarkup.setResizeKeyboard(true);
        keyboardMarkup.setSelective(true);

        KeyboardRow keyboardRow = new KeyboardRow();
        List<KeyboardRow> keyboard = new ArrayList<>();
        int count = 1;
        for (var category : categories) {
            keyboardRow.add(new KeyboardButton(category.getName()));
            if (count == 3) {
                keyboard.add(keyboardRow);
                keyboardRow = new KeyboardRow();
                count = 1;
            } else {
                count++;
            }
        }
        keyboard.add(keyboardRow);
        keyboardMarkup.setKeyboard(keyboard);

        return keyboardMarkup;
    }
}
