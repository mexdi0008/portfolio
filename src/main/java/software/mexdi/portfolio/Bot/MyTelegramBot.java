package software.mexdi.portfolio.Bot;

import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class MyTelegramBot extends TelegramLongPollingBot {
	private final String botName;

	public MyTelegramBot(String botToken, String botName) {
		super(botToken);
		this.botName = botName;
	}

	@Override
	public void onUpdateReceived(Update update) {
		if (update.hasMessage() && update.getMessage().hasText()) {
			Message message = update.getMessage();
			String chatId = message.getChatId().toString();
			SendMessage responseMessage = new SendMessage();
			responseMessage.setChatId(chatId);
			responseMessage.setText("Xoş gəlmişsiniz.");
			try {
				execute(responseMessage);
			} catch (TelegramApiException e) {
				log.error("error during processing telegram api: {}", e.getMessage());
			}
		}
	}

	public void sendMessageToBot(String message, long id, boolean isOtp) {
		List<String> chatIds = new ArrayList<>();
		chatIds.add("1752627456");
		for (String chatId : chatIds) {
			SendMessage sendMessage = new SendMessage();
			sendMessage.setChatId(chatId);
			sendMessage.setText(message);
		}
	}

	@Override
	public String getBotUsername() {
		return this.botName;
	}
}
