package software.mexdi.portfolio.Contact;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import software.mexdi.portfolio.Bot.PortfolioTelegramBot;
import software.mexdi.portfolio.Contact.DTO.ContactRequest;
import software.mexdi.portfolio.Contact.Mapper.ContactMapper;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Properties;

@Service
@RequiredArgsConstructor
public class ContactServiceImpl implements ContactService {
	private final ContactRepository contactRepository;
	private final ContactMapper contactMapper;
	private final PortfolioTelegramBot bot;

	@Value("${spring.mail.host}")
	private String mailHost;
	@Value("${spring.mail.port}")
	private String mailPort;
	@Value("${spring.mail.username}")
	private String mailUsername;
	@Value("${spring.mail.password}")
	private String mailPassword;

	@Override
	public Map<String, String> save(ContactRequest contactRequest) {
		Map<String, String> status = checkContactIsFake(contactRequest);
		if (status != null) return status;

		contactRepository.save(contactMapper.toEntity(contactRequest));
		sendMailAsync(contactRequest.getEmail(), "Your request accepted successfully", "Your contact request accepted successfully.\n" +
				"Please wait until we contact with you. thanks for contact.\n" +
				"Regards, Mehti Sadixov");

		bot.sendMessageToBot(contactRequest.getFirstName() + " " + contactRequest.getLastName() + " sizinlə əlaqə saxlamağa çalışdı.\n" +
				"Subject: " + contactRequest.getSubject() + "\n" +
				"Message: " + contactRequest.getMessage() + "\n" +
				"Email: " + contactRequest.getEmail());
		return Map.of("status", "Your request sent successfully");
	}

	@Async
	public void sendMailAsync(String to, String subject, String content) {


		Properties properties = new Properties();
		properties.put("mail.smtp.host", mailHost);
		properties.put("mail.smtp.port", mailPort);
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");

		Session session = Session.getInstance(properties, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(mailUsername, mailPassword);
			}
		});

		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(mailUsername));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
			message.setSubject(subject);
			MimeBodyPart mimeBodyPart = new MimeBodyPart();
			mimeBodyPart.setContent(content, "text/html");
			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(mimeBodyPart);

			message.setContent(multipart);

			Transport.send(message);
			System.out.println("E-posta başarıyla gönderildi.");
		} catch (MessagingException e) {
			System.out.println("E-posta gönderme hatası: " + e.getMessage());
		}
	}

	private Map<String, String> checkContactIsFake(ContactRequest contactRequest) {
		LocalDateTime startOfDay = LocalDateTime.now().toLocalDate().atStartOfDay();

		int contactCountToday = contactRepository.countByEmailAndCreatedAtAfter(contactRequest.getEmail(), startOfDay);

		if (contactCountToday >= 3) {
			return Map.of("status", "You have already sent 3 requests today. Please try again tomorrow.");
		}
		return null;
	}
}
