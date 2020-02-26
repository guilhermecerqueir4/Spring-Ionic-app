package com.guilherme.projSpring.resources;


import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.guilherme.projSpring.domain.Categoria;
import com.guilherme.projSpring.dto.CategoriaDTO;
import com.guilherme.projSpring.services.CategoriaService;


@RestController
@RequestMapping(value="/categorias")
public class CategoriaResource {

	@Autowired
	private CategoriaService service;
	
	// metodo GET
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Categoria> find(@PathVariable Integer id) {
		Categoria obj = service.find(id);
		return ResponseEntity.ok().body(obj);
	}
	
	// metodo POST
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert (@Valid @RequestBody CategoriaDTO objDto){
		Categoria obj = service.fromDto(objDto);
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	// metodo PUT
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> update (@Valid @RequestBody CategoriaDTO objDto, @PathVariable Integer id){
		Categoria obj = service.fromDto(objDto);
		obj.setId(id);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
	}
	
	
	// metodo DELETE
		@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
		public ResponseEntity<Void> delete(@PathVariable Integer id) {
			service.delete(id);
			return ResponseEntity.noContent().build();
		}
	
		// metodo GET (todas as categorias - filtrando objeto DTO)
		@RequestMapping(method=RequestMethod.GET)
		public ResponseEntity<List <CategoriaDTO>> findAll() {
			List <Categoria> list = service.findAll();
			List <CategoriaDTO> listDto = list.stream().map(obj -> new CategoriaDTO(obj)).collect(Collectors.toList());
			return ResponseEntity.ok().body(listDto);
		}
		
		// metodo GET FindPage
				@RequestMapping(value="/page", method=RequestMethod.GET)
				public ResponseEntity<Page <CategoriaDTO>> findPage(
						@RequestParam(name="page", defaultValue="0") Integer page, 
						@RequestParam(name="linesPerPage", defaultValue="24") Integer linesPerPage, 
						@RequestParam(name="orderBy", defaultValue="nome") String orderBy, 
						@RequestParam(name="direction", defaultValue="ASC") String direction) {
					Page <Categoria> list = service.findPage(page, linesPerPage, orderBy, direction);
					Page <CategoriaDTO> listDto = list.map(obj -> new CategoriaDTO(obj));
					return ResponseEntity.ok().body(listDto);
					}
}

