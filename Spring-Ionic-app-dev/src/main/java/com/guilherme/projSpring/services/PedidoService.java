package com.guilherme.projSpring.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.guilherme.projSpring.domain.Pedido;
import com.guilherme.projSpring.repositories.PedidoRepository;
//import com.guilherme.projSpring.services.exceptions;
import com.guilherme.projSpring.services.exceptions.ObjNotFoundException;

import javassist.tools.rmi.ObjectNotFoundException;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository repo;
	
	public Pedido buscar(Integer id) {
		Optional<Pedido> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName()));
		}
	}
	

