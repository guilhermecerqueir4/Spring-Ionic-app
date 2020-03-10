package com.guilherme.projSpring;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.guilherme.projSpring.domain.Categoria;
import com.guilherme.projSpring.domain.Cidade;
import com.guilherme.projSpring.domain.Cliente;
import com.guilherme.projSpring.domain.Endereco;
import com.guilherme.projSpring.domain.Estado;
import com.guilherme.projSpring.domain.ItemPedido;
import com.guilherme.projSpring.domain.Pagamento;
import com.guilherme.projSpring.domain.PagamentoComBoleto;
import com.guilherme.projSpring.domain.PagamentoComCartao;
import com.guilherme.projSpring.domain.Pedido;
import com.guilherme.projSpring.domain.Produto;
import com.guilherme.projSpring.domain.enums.EstadoPagamento;
import com.guilherme.projSpring.domain.enums.TipoCliente;
import com.guilherme.projSpring.repositories.CategoriaRepository;
import com.guilherme.projSpring.repositories.CidadeRepository;
import com.guilherme.projSpring.repositories.ClienteRepository;
import com.guilherme.projSpring.repositories.EnderecoRepository;
import com.guilherme.projSpring.repositories.EstadoRepository;
import com.guilherme.projSpring.repositories.ItemPedidoRepository;
import com.guilherme.projSpring.repositories.PagamentoRepository;
import com.guilherme.projSpring.repositories.PedidoRepository;
import com.guilherme.projSpring.repositories.ProdutoRepository;

@SpringBootApplication
public class ProjSpringApplication implements CommandLineRunner  {

	//dependencias dos Repositories
	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private EstadoRepository estadoRepository;
	@Autowired
	private CidadeRepository cidadeRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;
	@Autowired
	private PedidoRepository pedidoRepository;
	@Autowired
	private PagamentoRepository pagamentoRepository;
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(ProjSpringApplication.class, args);
	}

	
	
	@Override
	public void run(String... args) throws Exception {
		
	
		
		
		
	}

}
