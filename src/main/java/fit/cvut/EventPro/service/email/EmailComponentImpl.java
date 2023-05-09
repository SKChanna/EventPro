package fit.cvut.EventPro.service.email;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Map;

@Service
public class EmailComponentImpl {
	@Autowired
	private JavaMailSender sender;

	private void sendHtmlEmail(Email eParams) throws MessagingException {
		boolean isHtml = true;
		MimeMessage message = sender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);
		helper.setTo(eParams.getTo().toArray(new String[eParams.getTo().size()]));
		helper.setReplyTo(eParams.getFrom());
		helper.setFrom(eParams.getFrom());
		helper.setSubject(eParams.getSubject());
		helper.setText(eParams.getMessage(), isHtml);
		if (eParams.getCc().size() > 0) {
			helper.setCc(eParams.getCc().toArray(new String[eParams.getCc().size()]));
		}
		sender.send(message);
	}


	public void send(Email email, String templateName, Map<String, String> replacements) throws MessagingException {
		EmailTemplate template = new EmailTemplate(templateName);
		String message = template.getTemplate(replacements);
		email.setMessage(message);
		sendHtmlEmail(email);
	}

	public void send(Email email, String templateName) throws MessagingException {
		EmailTemplate template = new EmailTemplate(templateName);
		String message = template.getTemplate();
		email.setMessage(message);
		sendHtmlEmail(email);

	}

	private String createJsonToString(Object t) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(t);
		return json;
	}
}
