			package com.guilherme.projSpring.services.validation;
			
			import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import com.guilherme.projSpring.domain.Cliente;
import com.guilherme.projSpring.dto.ClienteDTO;
import com.guilherme.projSpring.repositories.ClienteRepository;
import com.guilherme.projSpring.resources.exceptions.FieldMessage;
			
			
			public class ClienteUpdateValidator implements ConstraintValidator<ClienteUpdate, ClienteDTO> {
				
				@Autowired
				private HttpServletRequest  request;
				
				@Autowired
				private ClienteRepository repo;
				
				
			 @Override
			 public void initialize(ClienteUpdate ann) {
			 }
			 @Override
			 public boolean isValid(ClienteDTO objDto, ConstraintValidatorContext context) {
			
				 @SuppressWarnings("unchecked")
				 //Mapeando o ID que foi requisitado na URI ( Json ) em metódo PUT(update) 
				Map <String, String> map = (Map <String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
				Integer uriId = Integer.parseInt(map.get("id"));
				
				 List<FieldMessage> list = new ArrayList<>();
			
			 Cliente aux = repo.findByEmail(objDto.getEmail());
			 
			 if (aux != null && !aux.getId().equals(uriId)) {
				 list.add(new FieldMessage("email", "E-mail existente em nossa base de dados."));
			 }
			
			 for (FieldMessage e : list) {
			 context.disableDefaultConstraintViolation();
			 context.buildConstraintViolationWithTemplate(e.getMessage())
			 .addPropertyNode(e.getFieldName()).addConstraintViolation();
			 }
			 return list.isEmpty();
			 }
 
}