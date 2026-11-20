package software.mexdi.portfolio.Contact.Mapper;

import org.springframework.stereotype.Component;
import software.mexdi.portfolio.Contact.Contact;
import software.mexdi.portfolio.Contact.DTO.ContactRequest;

@Component
public class ContactMapper {
	public Contact toEntity(ContactRequest contactRequest) {
		Contact contact = new Contact();
		contact.setFirstName(contactRequest.getFirstName());
		contact.setLastName(contactRequest.getLastName());
		contact.setEmail(contactRequest.getEmail());
		contact.setSubject(contactRequest.getSubject());
		contact.setMessage(contactRequest.getMessage());
		return contact;
	}
}
