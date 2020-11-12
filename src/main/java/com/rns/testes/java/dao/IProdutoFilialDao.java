package com.rns.testes.java.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rns.testes.java.model.Filial;
import com.rns.testes.java.model.Produto;
import com.rns.testes.java.model.ProdutoFilial;

public interface IProdutoFilialDao extends JpaRepository<ProdutoFilial, Long> {

	public ProdutoFilial findByFilialAndProduto(Filial filial, Produto Produto);
}
