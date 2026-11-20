package software.mexdi.portfolio.Bot;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import software.mexdi.portfolio.Bot.PortfolioTelegramBot;

@Configuration
@Slf4j
public class PortfolioTelegramConfig {
	@Bean
	public PortfolioTelegramBot portfolioTelegramBot(@Value("${portfolio.bot.token}") String botToken,
	                                                 @Value("${portfolio.bot.name}") String botName) {
		PortfolioTelegramBot telegramBot = new PortfolioTelegramBot(botToken, botName);
		try {
			var telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
			telegramBotsApi.registerBot(telegramBot);
		} catch (TelegramApiException e) {
			log.error("Error during registration: {}", e.getMessage());
		}
		return telegramBot;
	}
}
