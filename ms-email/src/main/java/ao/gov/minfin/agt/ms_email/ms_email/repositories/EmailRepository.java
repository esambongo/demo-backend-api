package ao.gov.minfin.agt.ms_email.ms_email.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import ao.gov.minfin.agt.ms_email.ms_email.models.EmailModel;

public interface EmailRepository extends JpaRepository<EmailModel, UUID>{

}
