package br.com.student.email.sender.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.student.email.sender.model.StudentEmailInformation;
import br.com.student.email.sender.service.MailService;

@RestController
public class StudentEmailSenderRestController {
	
	@Autowired
	private MailService mailService;
	
	@RequestMapping(value = "/maladireta", method = RequestMethod.POST)
	public ResponseEntity<Integer> sendStudentsEmails(@RequestBody List<StudentEmailInformation> students) {

		return new ResponseEntity<Integer>(mailService.sendStudentsEmail(students), HttpStatus.OK);
	}
	
}
