package ao.gov.minfin.agt.ms_email.ms_email.consumers;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import ao.gov.minfin.agt.ms_email.ms_email.dtos.EmailDto;
import ao.gov.minfin.agt.ms_email.ms_email.models.EmailModel;
import ao.gov.minfin.agt.ms_email.ms_email.services.EmailService;

@Component
public class EmailConcumer {

	
	private @Autowired EmailService emailService;
	
	@RabbitListener(queues = "ms.email")
	public void listen(@Payload EmailDto emailDto) {
		EmailModel emailModel = new EmailModel();
		
		BeanUtils.copyProperties(emailDto, emailModel);
		
		this.emailService.sendEmail(emailModel);
		
		System.err.println("Email status: "+emailModel.getStatusEmail().toString());
	}
}
