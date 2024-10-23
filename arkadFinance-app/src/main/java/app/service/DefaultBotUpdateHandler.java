package app.service;

import app.handler.UpdateHandler;
import app.utils.MessageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import services.UserService;
import user.User;

import java.text.MessageFormat;

@Component
@Primary
public class DefaultBotUpdateHandler implements UpdateHandler {

    @Autowired
    private UserService userService;

    @Override
    public void handleUpdate(Update update) {
        if(update.hasMessage() && update.getMessage().hasText()){
            String messageText = update.getMessage().getText();

            switch (messageText){
                case "/start":
                    registerUser(update.getMessage());
                    break;
            }
        }
    }

    private void registerUser(Message message){
        User user = userService.getUserByChatId(message.getChatId());

        if(user == null){
            User newUser = new User();
            newUser.setChatId(message.getChatId());
            newUser.setUsername(message.getFrom().getUserName());
            newUser.setFirstName(message.getFrom().getFirstName());
            newUser.setLastName(message.getFrom().getLastName());
            userService.postUser(newUser);
            System.out.println("user saved!" + newUser.getUsername());
        }
    }
}
