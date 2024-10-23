package app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Service
public class ArkadBotService {
    private final ArkadFinanceBot bot;

    @Autowired
    public ArkadBotService(ArkadFinanceBot bot) {
        this.bot = bot;
    }

    public void sendMessage(SendMessage message) {
        try{
            bot.execute(message);
        } catch (TelegramApiException ignored){
        }
    }
}
