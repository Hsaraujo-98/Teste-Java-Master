package com.rns.testes.java.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rns.testes.java.dao.IProdutoFilialDao;
import com.rns.testes.java.model.Filial;
import com.rns.testes.java.model.Produto;
import com.rns.testes.java.model.ProdutoFilial;
import com.rns.testes.java.service.AbstractGenericServicePersistence;
import com.rns.testes.java.service.IFilialService;
import com.rns.testes.java.service.IProdutoFilialService;
import com.rns.testes.java.service.IProdutoService;

@Service
public class ProdutoFilialService extends AbstractGenericServicePersistence<IProdutoFilialDao, ProdutoFilial, Long>
		implements IProdutoFilialService {

	@Autowired
	IProdutoFilialDao dao;

	@Autowired
	IFilialService iFilialService;

	@Autowired
	IProdutoService iProdutoService;

	@Override
	public ProdutoFilial findByIdFilialAndIdProduto(Long idFilial, String idProduto) {
		Filial filial = iFilialService.findById(idFilial);
		Produto produto = iProdutoService.findById(idProduto);
		ProdutoFilial resultado = dao.findByFilialAndProduto(filial, produto);
		return resultado;
	}

}
