package com.pms.test.service;

import java.util.List;

import br.com.caelum.stella.ValidationMessage;
import br.com.caelum.stella.validation.CPFValidator;

public class ClientService {
	
 public static boolean validateCPF(String cpf){ 
	 CPFValidator cpfValidator = new CPFValidator(); 

	 List<ValidationMessage> erros = cpfValidator.invalidMessagesFor(cpf); 
	 if(erros.size() > 0){ 
		 return false; 
		 }else{
	     return true; } 
	}

}
