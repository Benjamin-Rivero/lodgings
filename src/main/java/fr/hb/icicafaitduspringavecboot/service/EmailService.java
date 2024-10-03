package fr.hb.icicafaitduspringavecboot.service;

import fr.hb.icicafaitduspringavecboot.entity.User;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;

@Service
@AllArgsConstructor
public class EmailService {

	private Environment env;

	private JavaMailSender mailSender;

	public void sendVerificationEmail(User user) throws MessagingException, UnsupportedEncodingException {
		String toAddress = user.getEmail();
		String fromAddress = env.getProperty("spring.mail.username");
		String subject = "Veuillez activer votre compte";
		String content = "Cher [[name]],<br>"
				+ "Veuillez cliquer sur le lien ci dessous pour activer votre compte<br>"
				+ "<h3><a href=\"[[URL]]\" target=\"_self\">VERIFY</a></h3><br>"
				+ "Ou coller ce lien dans votre navigateur<br>"
				+ "[[URL]]<br>"
				+ "Merci,<br>"
				+ "Air-ish BNB.";

		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);

		content = content.replace("[[name]]", user.getFullName());
		String verifyURL = "http://localhost:8080/api/user" + "/validate?token=" + user.getActivationToken();

		content = content.replace("[[URL]]", verifyURL);

		helper.setTo(toAddress);
		helper.setFrom(fromAddress);
		helper.setSubject(subject);
		helper.setText(content,true);


		mailSender.send(message);
	}

}
