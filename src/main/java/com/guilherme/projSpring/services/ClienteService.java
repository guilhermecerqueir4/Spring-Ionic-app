package com.guilherme.projSpring.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.guilherme.projSpring.domain.Cidade;
import com.guilherme.projSpring.domain.Cliente;
import com.guilherme.projSpring.domain.Endereco;
import com.guilherme.projSpring.domain.enums.TipoCliente;
import com.guilherme.projSpring.dto.ClienteDTO;
import com.guilherme.projSpring.dto.ClienteNewDTO;
import com.guilherme.projSpring.repositories.CidadeRepository;
import com.guilherme.projSpring.repositories.ClienteRepository;
import com.guilherme.projSpring.repositories.EnderecoRepository;
import com.guilherme.projSpring.services.exceptions.DataIntegrityException;
//import com.guilherme.projSpring.services.exceptions;
import com.guilherme.projSpring.services.exceptions.ObjNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repo;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	public Cliente find(Integer id) {
		Optional<Cliente> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
		}
	
	
	
	//update no banco. chama o metodo de buscar o ID e ver se existe
		public Cliente update (Cliente obj) {
			Cliente newObj = find(obj.getId());
			updateData(newObj, obj);
			return repo.save(newObj);
		}
		
		//Delete no banco
			public void  delete (Integer id) {
				find(id);
				try {
					repo.deleteById(id);
					}
			catch(DataIntegrityViolationException e){
					throw new DataIntegrityException("Não é possivel excluir porque há pedidos relacionadas");
				}
			}
			
			public List<Cliente> findAll() {
				return repo.findAll();
			}
			//paginação
			public Page<Cliente> findPage(Integer page, Integer linesPerPage,  String orderBy, String direction) { 
				PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
				return repo.findAll(pageRequest);
			}
			
			public Cliente fromDto (ClienteDTO objDto) {
				return new Cliente(objDto.getId(), objDto.getNome(), objDto.getEmail(), null, null);
			}
			
			public Cliente fromDto (ClienteNewDTO objDto) {
				Cliente cli = new Cliente(null, objDto.getNome(), objDto.getEmail(), objDto.getCpfOuCnpj(), TipoCliente.toEnum( objDto.getTipo()));
				Cidade cid = new Cidade(objDto.getCidadeId(), null , null);
				//Cidade cid = cidadeRepository.findById(objDto.getCidadeId());
				Endereco end = new Endereco(null, objDto.getLogradouro(), objDto.getNumero(), objDto.getComplemento(), objDto.getBairro(), objDto.getCep(), cli,  cid);
				cli.getEnderecos().add(end);
				cli.getTelefones().add(objDto.getTelefone1());
				if (objDto.getTelefone2() != null) {
					cli.getTelefones().add(objDto.getTelefone2());
				}
				if (objDto.getTelefone3() != null) {
					cli.getTelefones().add(objDto.getTelefone3());
				}
				return cli;
			}
			
			
			
			//inserindo no banco
			@Transactional
			public Cliente insert(Cliente obj) {
				obj.setId(null);
				
				enderecoRepository.saveAll(obj.getEnderecos());
				
				obj = repo.save(obj);
				
				
				
				return obj;
			}
			
			
			private void updateData(Cliente newObj, Cliente obj) {
				newObj.setNome(obj.getNome());
				newObj.setEmail(obj.getEmail());
			}
			
	}
	

