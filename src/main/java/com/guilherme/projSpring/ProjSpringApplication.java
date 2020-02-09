package com.guilherme.projSpring;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.guilherme.projSpring.domain.Categoria;
import com.guilherme.projSpring.repositories.CategoriaRepository;

@SpringBootApplication
public class ProjSpringApplication implements CommandLineRunner  {

	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(ProjSpringApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Categoria cat1 = new Categoria(null, "informatica");
		Categoria cat2 = new Categoria(null, "escritorio");
		Categoria cat3 = new Categoria(null, "alright");
		
		categoriaRepository.saveAll(Arrays.asList(cat1,cat2, cat3));
		
	}

}
