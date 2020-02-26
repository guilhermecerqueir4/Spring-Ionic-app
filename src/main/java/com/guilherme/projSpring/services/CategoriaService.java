package com.guilherme.projSpring.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.guilherme.projSpring.domain.Categoria;
import com.guilherme.projSpring.dto.CategoriaDTO;
import com.guilherme.projSpring.repositories.CategoriaRepository;
import com.guilherme.projSpring.services.exceptions.DataIntegrityException;
//import com.guilherme.projSpring.services.exceptions;
import com.guilherme.projSpring.services.exceptions.ObjNotFoundException;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repo;
	
	//buscando no banco
	public Categoria find(Integer id) {
		Optional<Categoria> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));
		}
	//inserindo no banco
	public Categoria insert (Categoria obj) {
		obj.setId(null);
		return repo.save(obj);
	}
	//update no banco. chama o metodo de buscar o ID e ver se existe
	public Categoria update (Categoria obj) {
		find(obj.getId());
		return repo.save(obj);
	}
	
	//Delete no banco
		public void  delete (Integer id) {
			find(id);
			try {
				repo.deleteById(id);
				}
		catch(DataIntegrityViolationException e){
				throw new DataIntegrityException("Não é possivel excluir uma categoria que possui produtos");
			}
		}
		
		public List<Categoria> findAll() {
			return repo.findAll();
		}
		//paginação
		public Page<Categoria> findPage(Integer page, Integer linesPerPage,  String orderBy, String direction) { 
			PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
			return repo.findAll(pageRequest);
		}
		
		public Categoria fromDto (CategoriaDTO objDto) {
			return new Categoria(objDto.getId(), objDto.getNome());
		}
		
	}
	
