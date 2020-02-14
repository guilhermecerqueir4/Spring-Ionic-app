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
	
	public static void main(String[] args) {
		SpringApplication.run(ProjSpringApplication.class, args);
	}

	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
	
	@Override
	public void run(String... args) throws Exception {
		
		//Criando as instanciacoes das classes de dominio
		Categoria cat1 = new Categoria(null, "informatica");
		Categoria cat2 = new Categoria(null, "escritorio");
		
		Produto prod1 = new Produto(null, "Computador", 2000.00);
		Produto prod2 = new Produto(null, "Impressora", 800.00);
		Produto prod3 = new Produto(null, "Mouse", 80.00);
		
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");
		
		Cidade c1 = new Cidade(null, "Uberlandia", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);
		
		Cliente cli1 = new Cliente(null, "Maria Silva", "maria@gmaail.com", "47004511721", TipoCliente.PESSOAFISICA);
		
		Endereco e1 = new Endereco(null, "Rua Flores","300","Sala 800","Centro","04938023", cli1, c1);
		Endereco e2 = new Endereco(null, "Av. Matos","105","Sala 801","Jardim Angela","04938024", cli1, c2);
		
		Pedido ped1 = new Pedido(null, sdf.parse("13/02/2020 10:32"), cli1, e1);
		Pedido ped2 = new Pedido(null, sdf.parse("14/02/2020 10:32"), cli1, e2);
		
		Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2017 00:00"), null);
		
		ped1.setPagamento(pagto1); //atribuindo um pagamento ao pedido 1
		ped2.setPagamento(pagto2); //atribuindo um pagamento ao pedido 2
		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));
		
		
		//Atribuindo as associacoes
		cat1.getProdutos().addAll(Arrays.asList(prod1,prod2,prod3));
		cat2.getProdutos().addAll(Arrays.asList(prod2));
		
		prod1.getCategorias().addAll(Arrays.asList(cat1));
		prod2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		prod3.getCategorias().addAll(Arrays.asList(cat1));
	
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2, c3));
		
		cli1.getTelefones().addAll(Arrays.asList("58319384","584127384"));
		cli1.getEnderecos().addAll(Arrays.asList(e1,e2));
		
		//Salvando as instanciacoes no banco de dados utilizando classes Repositories.
		//Salvar por ultimo as classes que sao dependentes de outras classes.
		categoriaRepository.saveAll(Arrays.asList(cat1,cat2));
		produtoRepository.saveAll(Arrays.asList(prod1,prod2,prod3));
		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(c1,c2,c3));	
		clienteRepository.saveAll(Arrays.asList(cli1));					
		enderecoRepository.saveAll(Arrays.asList(e1,e2));	
		pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
		pagamentoRepository.saveAll(Arrays.asList(pagto1, pagto2));
		
		
		
	}

}
