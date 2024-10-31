package app.utils;


import finance.Expense;
import finance.Income;
import finance.category.Category;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import user.User;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

@Component
public class MessageService {
    public SendMessage sendMessage(Long chatId, String messageText, boolean withMainMenu) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(messageText);
        if (withMainMenu) message.setReplyMarkup(getMainMenu());

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
        String actualMoney = calculateActualInfo(user);
        SendMessage message = new SendMessage();
        message.setChatId(user.getChatId());
        message.setText(MessageFormat.format("""
                Ваш текущий баланс: {0}
                                
                                
                """, actualMoney));
        message.setReplyMarkup(getMainMenu());

        return message;
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

    private ReplyKeyboardMarkup getMainMenu() {
        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        keyboardMarkup.setResizeKeyboard(true);
        keyboardMarkup.setSelective(true);

        KeyboardRow firstRow = new KeyboardRow();
        firstRow.add(new KeyboardButton("💰 Добавить доход"));
        firstRow.add(new KeyboardButton("💸 Добавить расход"));

        List<KeyboardRow> keyboard = new ArrayList<>();
        keyboard.add(firstRow);

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
