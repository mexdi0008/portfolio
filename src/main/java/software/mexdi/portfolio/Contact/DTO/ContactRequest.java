package software.mexdi.portfolio.Contact.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class ContactRequest{
	@NotNull(message = "Please provide a name")
//	@Min(value = 3, message = "Name must contain at least 3 letters.")
	private String firstName;
	@NotNull(message = "Please provide a lastname.")
//	@Min(value = 3, message = "Surname must contain at least 3 letters.")
	private String lastName;
	@NotNull(message = "Please provide a email")
//	@Email(message = "Please provide a valid e-mail address")
	private String email;
	@NotNull(message = "Please provide a subject")
//	@Min(value = 5, message = "Subject must contain at least 5 letters.")
	private String subject;
	@NotNull(message = "Please provide a message")
//	@Min(value = 10, message = "Message must contain at least 10 letters.")
	private String message;
}
