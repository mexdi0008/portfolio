package software.mexdi.portfolio.Bot;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@Configuration
@Slf4j
public class TelegramConfig {

	@Bean
	public MyTelegramBot telegramBot(@Value("${bot.token}") String botToken,
	                                 @Value("${bot.name}") String botName) {
		MyTelegramBot telegramBot = new MyTelegramBot(botToken, botName);
		try {
			var telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
			telegramBotsApi.registerBot(telegramBot);
		} catch (TelegramApiException e) {
			log.error("Error during registration: {}", e.getMessage());
		}
		return telegramBot;
	}
}
