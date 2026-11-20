package software.mexdi.portfolio.Contact;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import software.mexdi.portfolio.Contact.DTO.ContactRequest;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/contact")
public class ContactController {

	private final ContactService contactService;

	@PostMapping("/save")
	public ResponseEntity<Map<String, String>> saveContact(@ModelAttribute @Valid ContactRequest contactRequest) {
		return ResponseEntity.ok(contactService.save(contactRequest));
	}
}
