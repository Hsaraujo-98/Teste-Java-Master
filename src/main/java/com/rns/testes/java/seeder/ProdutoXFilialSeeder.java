package com.rns.testes.java.seeder;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.rns.testes.java.model.ProdutoFilial;
import com.rns.testes.java.service.IFilialService;
import com.rns.testes.java.service.IProdutoFilialService;
import com.rns.testes.java.service.IProdutoService;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ProdutoXFilialSeeder {

	@Autowired
	private IProdutoFilialService iProdutoFilialService;

	@Autowired
	private IProdutoService iProdutoService;

	@Autowired
	private IFilialService iFilialService;

	@EventListener
	public void seedProdutoFilial(ContextRefreshedEvent event) {
		try {
			log.info("Criando associação de Filial e Produto");
			criandoProdutoFilial();
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}

	private void criandoProdutoFilial() {
		Random random = new Random();
		int totalFiliais = iFilialService.findAll().size();
		int totalProdutos = iProdutoService.findAll().size();
		for (int i = 1; i <= totalFiliais; i++) {
			for (int j = 1; j <= totalProdutos; j++) {
				ProdutoFilial produtoFilial = new ProdutoFilial();
				produtoFilial.setFilial(iFilialService.findById((long) i));
				produtoFilial.setProduto(iProdutoService.findById("Cod-Produto-" + j));
				produtoFilial.setQuantidade(random.nextInt(10) + 1);
				iProdutoFilialService.save(produtoFilial);
			}
		}
	}

}
