package ao.gov.minfin.agt.ms_email.ms_email.services;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import ao.gov.minfin.agt.ms_email.ms_email.enums.StatusEmail;
import ao.gov.minfin.agt.ms_email.ms_email.models.EmailModel;
import ao.gov.minfin.agt.ms_email.ms_email.repositories.EmailRepository;

@Service
public class EmailService {
	
	private @Autowired EmailRepository emailRepository;
	
	private @Autowired JavaMailSender emailSend;

	@SuppressWarnings("finally")
	public EmailModel sendEmail(EmailModel emailModel) {
	    emailModel.setSendDateEmail(LocalDateTime.now());

	    try {
	    	SimpleMailMessage message=new SimpleMailMessage();
	    	message.setFrom(emailModel.getEmailFrom());
	    	message.setTo(emailModel.getEmailTo());
	    	message.setSubject(emailModel.getSubject());
	    	message.setText(emailModel.getText());
	    	emailSend.send(message);
	    	
	    	emailModel.setStatusEmail(StatusEmail.SENT);
	    }catch(MailException e) {
	    	emailModel.setStatusEmail(StatusEmail.ERROR);
	    }finally {
	    	return this.emailRepository.save(emailModel);
	    }
	}

	public Page<EmailModel> findAll(Pageable pageable) {
		return this.emailRepository.findAll(pageable);
	}

	public Optional<EmailModel> findById(UUID emailId) {
		return this.emailRepository.findById(emailId);
	}
}
