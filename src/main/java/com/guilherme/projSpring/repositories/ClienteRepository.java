package com.guilherme.projSpring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.guilherme.projSpring.domain.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

	//spring data reconhece que voce quer buscar email usando 'find'
	@Transactional(readOnly=true)
	Cliente findByEmail(String email) ;
}
