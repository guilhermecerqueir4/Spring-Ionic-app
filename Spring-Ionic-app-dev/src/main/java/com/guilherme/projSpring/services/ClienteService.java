package com.guilherme.projSpring.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.guilherme.projSpring.domain.Cliente;
import com.guilherme.projSpring.repositories.ClienteRepository;
//import com.guilherme.projSpring.services.exceptions;
import com.guilherme.projSpring.services.exceptions.ObjNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repo;
	
	public Cliente buscar(Integer id) {
		Optional<Cliente> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
		}
	}
	

