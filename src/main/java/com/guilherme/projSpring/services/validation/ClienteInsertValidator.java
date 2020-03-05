			package com.guilherme.projSpring.services.validation;
			
			import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.guilherme.projSpring.domain.Cliente;
import com.guilherme.projSpring.domain.enums.TipoCliente;
import com.guilherme.projSpring.dto.ClienteNewDTO;
import com.guilherme.projSpring.repositories.ClienteRepository;
import com.guilherme.projSpring.resources.exceptions.FieldMessage;
import com.guilherme.projSpring.services.validation.utils.BR;
			
			
			public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO> {
				
				@Autowired
				private ClienteRepository repo;
				
				
			 @Override
			 public void initialize(ClienteInsert ann) {
			 }
			 @Override
			 public boolean isValid(ClienteNewDTO objDto, ConstraintValidatorContext context) {
			 List<FieldMessage> list = new ArrayList<>();
			
			 // inclua os testes aqui, inserindo erros na lista
			 if (objDto.getTipo().equals(TipoCliente.PESSOAFISICA.getCod()) &&  !BR.isValidCPF(objDto.getCpfOuCnpj())) {
				 list.add(new FieldMessage("cpfOuCnpj", "CPF inválido!"));
			 }
			 
			 if (objDto.getTipo().equals(TipoCliente.PESSOAJURIDICA.getCod()) &&  !BR.isValidCNPJ(objDto.getCpfOuCnpj())) {
				 list.add(new FieldMessage("cpfOuCnpj", "CNPJ inválido!"));
			 }
			 
			 Cliente aux = repo.findByEmail(objDto.getEmail());
			 
			 if (aux != null ) {
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