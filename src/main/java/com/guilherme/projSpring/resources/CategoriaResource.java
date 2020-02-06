package com.guilherme.projSpring.resources;


import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.guilherme.projSpring.domain.Categoria;


@RestController
@RequestMapping(value="/categorias")
public class CategoriaResource {

	@RequestMapping(method=RequestMethod.GET)
	public List<Categoria> listarCat() {
		
		Categoria cat3 = new Categoria(1, "oi");
		Categoria cat4 = new Categoria(2, "ola");
		
		List<Categoria> lista1 = new ArrayList<>();
		lista1.add(cat3);
		lista1.add(cat4);
		
		return lista1;
	}
	
}

