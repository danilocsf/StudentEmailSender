package br.com.student.email.sender.service;

import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import br.com.student.email.sender.exception.EmailSenderException;
import br.com.student.email.sender.model.StudentEmailInformation;
import br.com.student.email.sender.util.EmailUtil;

/**
 * Responsável pelo envio de e-mail
 * @author Danilo Ferreira
 *
 */
@Service
public class MailService {
	
	private static final Logger LOG = LoggerFactory.getLogger(MailService.class);
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	/**
	 * Envia e-mail para todos os alunos da lista
	 * @param lista de alunos
	 * @return número de e-mails enviados
	 */
	public int sendStudentsEmail(List<StudentEmailInformation> students) {
		
		int emailCount = 0;
		for(StudentEmailInformation student : students) {
			try {
				sendEmail(student);
				emailCount++;
			} catch (EmailSenderException | MessagingException e) {
				LOG.error(e.getMessage());
			}
		}	
		return emailCount;
	}
	
	private void sendEmail(StudentEmailInformation student) throws EmailSenderException, MessagingException {
		LOG.info("Enviando o e-mail do(a) aluno(a) "+student.getName());
		validateEmail(student.getEmail(), student.getName());
		
		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true);		
		helper.setSubject("Atenção com suas notas");
		helper.setTo(student.getEmail());
		helper.setText(student.getMsg());
		javaMailSender.send(message);
	}
	
	private void validateEmail(String email, String studentName) throws EmailSenderException {
		if(StringUtils.isBlank(email)) {
			throw new EmailSenderException("O e-mail do(a) aluno(a) "+studentName+" não foi informado.");
		}
		if(!EmailUtil.isAValidEmail(email)) {
			throw new EmailSenderException("O e-mail do(a) aluno(a) "+studentName+" é inválido: "+email );
		}
	}
}
