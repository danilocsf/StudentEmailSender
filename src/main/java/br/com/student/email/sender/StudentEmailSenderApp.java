package br.com.student.email.sender;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Aplicação server responsável por receber uma lista de alunos e enviar 
 * notificações por e-mail para os mesmos.
 * @author Danilo Ferreira
  */
@SpringBootApplication
public class StudentEmailSenderApp {
	
	public static void main(String[] args) {
		SpringApplication.run(StudentEmailSenderApp.class, args);
	}
}
