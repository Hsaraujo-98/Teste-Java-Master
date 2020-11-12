package com.rns.testes.java.service.impl;

import org.springframework.stereotype.Service;

import com.rns.testes.java.dao.IProdutoDao;
import com.rns.testes.java.model.Produto;
import com.rns.testes.java.service.AbstractGenericServicePersistence;
import com.rns.testes.java.service.IProdutoService;

@Service
public class ProdutoService extends AbstractGenericServicePersistence<IProdutoDao, Produto, String>
		implements IProdutoService {
}
