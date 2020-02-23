package com.guilherme.projSpring.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.guilherme.projSpring.domain.Categoria;
import com.guilherme.projSpring.repositories.CategoriaRepository;
//import com.guilherme.projSpring.services.exceptions;
import com.guilherme.projSpring.services.exceptions.ObjNotFoundException;

import javassist.tools.rmi.ObjectNotFoundException;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repo;
	
	//buscando no banco
	public Categoria buscar(Integer id) {
		Optional<Categoria> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));
		}
	//inserindo no banco
	public Categoria insert (Categoria obj) {
		obj.setId(null);
		return repo.save(obj);
	}
	
	}
	

