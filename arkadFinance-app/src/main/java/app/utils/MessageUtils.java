package app.utils;

import app.service.ArkadBotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import user.User;

public class MessageUtils {

    private static ArkadBotService service;
    public static void sendMessage(User user, String text){
        SendMessage message = new SendMessage();
        message.setChatId(user.getChatId());
        message.setText(text);

        service.sendMessage(message);
    }
}
