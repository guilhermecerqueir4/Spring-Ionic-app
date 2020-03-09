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

	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
	
	@Override
	public void run(String... args) throws Exception {
		
		//Criando as instanciacoes das classes de dominio
		Categoria cat1 = new Categoria(null, "Placas-mãe");
		Categoria cat2 = new Categoria(null, "Placas de vídeo");
		Categoria cat3 = new Categoria(null, "Disco Rígido (HD)");
		Categoria cat4 = new Categoria(null, "Memória RAM");
		Categoria cat5 = new Categoria(null, "Processadores");
		Categoria cat6 = new Categoria(null, "Teclado/Mouse");
		Categoria cat7 = new Categoria(null, "Outros");
		
		
		Produto prod1 = new Produto(null, "Placa-Mãe ASRock A320M-HD, AMD AM4, mATX, DDR4", 319.90);
		Produto prod2 = new Produto(null, "Placa-Mãe Asus EX-A320M-Gaming, AMD AM4, mATX, DDR4", 465.90);
		Produto prod3 = new Produto(null, "Placa-mãe Gigabyte B450 Aorus M, AMD AM4, mATX, DDR4", 589.90);
		Produto prod4 = new Produto(null, "Placa de Vídeo Galax NVIDIA GeForce GTX 1660 1-Click OC, 6GB, GDDR5 - 60SRH7DSY91C", 899.00);
		Produto prod5 = new Produto(null, "Placa de Vídeo Asus AMD Radeon RX 580 OC 8GB, GDDR5 - DUAL-RX580-O8G", 679.00);
		Produto prod6 = new Produto(null, "Placa de Vídeo Galax NVIDIA RTX 2060 EX White (1-Click OC) 6GB, GDDR6 - 26NRL7HPY3EW", 2133.90);
		Produto prod7 = new Produto(null, "HD Seagate BarraCuda, 1TB, 3.5´, SATA - ST1000DM010", 259.60);
		Produto prod8 = new Produto(null, "HD Seagate Externo Portátil Expansion USB 3.0 2TB Preto - STEA2000400", 339.00);
		Produto prod9 = new Produto(null, "Memória HyperX Fury, 8GB, 2666MHz, DDR4, CL16, Preto - HX426C16FB3/8", 229.00);
		Produto prod10 = new Produto(null, "Memória Kingston 8GB, 2400MHz, DDR4, CL17 - KVR24N17S8/8", 205.00);
		Produto prod11 = new Produto(null, "Processador AMD Ryzen 5 1600, Cache 19MB, 3.2GHz (3.6GHz Max Turbo), AM4 - YD1600BBAFBOX", 629.00);
		Produto prod12 = new Produto(null, "Processador Intel Core i5-9400F Coffee Lake, Cache 9MB, 2.9GHz (4.1GHz Max Turbo), LGA 1151, Sem Vídeo - BX80684I59400F", 810.00);
		
		
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
		
		ItemPedido ip1 = new ItemPedido(ped1, prod1, 0.00, 1, 2000.0);
		ItemPedido ip2 = new ItemPedido(ped1, prod3, 0.00, 2, 80.0);
		ItemPedido ip3 = new ItemPedido(ped2, prod2, 100.00, 1, 80.0);
		
		//Atribuindo as associacoes
		
		ped1.getItens().addAll(Arrays.asList(ip1,ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));
		
		ped1.setPagamento(pagto1); //atribuindo um pagamento ao pedido 1
		ped2.setPagamento(pagto2); //atribuindo um pagamento ao pedido 2
		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));
		
		
		
		cat1.getProdutos().addAll(Arrays.asList(prod1,prod2,prod3));
		cat2.getProdutos().addAll(Arrays.asList(prod4, prod5, prod6));
		cat3.getProdutos().addAll(Arrays.asList(prod7, prod8));
		cat4.getProdutos().addAll(Arrays.asList(prod9, prod10));
		cat4.getProdutos().addAll(Arrays.asList(prod11, prod12));

		prod1.getCategorias().addAll(Arrays.asList(cat1));
		prod2.getCategorias().addAll(Arrays.asList(cat1));
		prod3.getCategorias().addAll(Arrays.asList(cat1));
		prod4.getCategorias().addAll(Arrays.asList(cat2));
		prod5.getCategorias().addAll(Arrays.asList(cat2));
		prod6.getCategorias().addAll(Arrays.asList(cat2));
		prod7.getCategorias().addAll(Arrays.asList(cat3));
		prod8.getCategorias().addAll(Arrays.asList(cat3));
		prod9.getCategorias().addAll(Arrays.asList(cat4));
		prod10.getCategorias().addAll(Arrays.asList(cat4));
		prod11.getCategorias().addAll(Arrays.asList(cat5));
		prod12.getCategorias().addAll(Arrays.asList(cat5));
		
		prod1.getItens().addAll(Arrays.asList(ip1));
		prod2.getItens().addAll(Arrays.asList(ip3));
		prod3.getItens().addAll(Arrays.asList(ip2));
	
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2, c3));
		
		cli1.getTelefones().addAll(Arrays.asList("58319384","584127384"));
		cli1.getEnderecos().addAll(Arrays.asList(e1,e2));
		
		//Salvando as instanciacoes no banco de dados utilizando classes Repositories.
		//Salvar por ultimo as classes que sao dependentes de outras classes.
		categoriaRepository.saveAll(Arrays.asList(cat1,cat2, cat3, cat4, cat5, cat6 ,cat7));
		produtoRepository.saveAll(Arrays.asList(prod1,prod2,prod3, prod4, prod4, prod5, prod6, prod7, prod8, prod9, prod10,prod11,prod12));
		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(c1,c2,c3));	
		clienteRepository.saveAll(Arrays.asList(cli1));					
		enderecoRepository.saveAll(Arrays.asList(e1,e2));	
		pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
		pagamentoRepository.saveAll(Arrays.asList(pagto1, pagto2));
		itemPedidoRepository.saveAll(Arrays.asList(ip1, ip2, ip3));
		
		
		
	}

}
