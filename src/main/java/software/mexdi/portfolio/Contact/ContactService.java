package software.mexdi.portfolio.Contact;

import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import software.mexdi.portfolio.Contact.DTO.ContactRequest;

import java.util.Map;

public interface ContactService {
	Map<String, String> save(@Valid ContactRequest contactRequest);
}
