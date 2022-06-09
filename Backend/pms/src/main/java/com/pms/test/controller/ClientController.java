package com.pms.test.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.pms.test.model.Client;
import com.pms.test.repository.ClientRepository;
import com.pms.test.service.ClientService;


@RestController
@RequestMapping(value = "/client")
public class ClientController {
	
	@Autowired
	private ClientRepository clientRepository;
	

	@CrossOrigin
	@GetMapping
	public List<Client> getClients() {
		
		return clientRepository.findAll();
	}
	
	
	
	@CrossOrigin
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> addClient(@RequestBody Client client) {
		
		try {
			if (ClientService.validateCPF(client.getCpf())) {
				Client findCPF = clientRepository.findBycpf(client.getCpf());

				if (findCPF != null) {

					
					  return ResponseEntity
							     .status(HttpStatus.ACCEPTED)
					             .body("O CPF informado já está registrado");
				}
				
				
				clientRepository.save(client);

				
				  return ResponseEntity
						     .status(HttpStatus.CREATED)
				             .body("Success");
			}else {
			     return ResponseEntity
			             .status(HttpStatus.BAD_REQUEST)
			             .body("Error: CPF Inválido");
			}
				
		
			
		}catch(Exception e) {
		     return ResponseEntity
		             .status(HttpStatus.INTERNAL_SERVER_ERROR)
		             .body("Error: " + e);
		
		}
		

	}
	
	@CrossOrigin
    @PutMapping
    public ResponseEntity<String> updateClient(
    		@RequestParam("id") Long id,		
    @RequestBody Client clientData){
    	
       
         Client client = clientRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        	
       if (clientData.getCpf().length()>0) {
    	   client.setCpf(clientData.getCpf());
       }
       if (clientData.getBirthDate().toString().length()>0) {
    	   client.setBirthDate(clientData.getBirthDate());
       }
       if (clientData.getName().length()>0) {
           client.setName(clientData.getName());
       }
       if (clientData.getAddress().size()>0) {
    	   client.setAddress(clientData.getAddress());   	   
       }
      

     
        final Client updatedClient = clientRepository.save(client);
        return ResponseEntity.ok("Success");
   }
	 @CrossOrigin
	 @DeleteMapping
	    public ResponseEntity<String> deleteClient( @RequestParam("id") Long id) {

		 try {
		        clientRepository.deleteById( id);
		  	  return ResponseEntity
					     .status(200)
			             .body("Success");
		 }catch(Exception e) {
		     return ResponseEntity
		             .status(HttpStatus.INTERNAL_SERVER_ERROR)
		             .body("Erro: " + e);
		 }

	    }
}
