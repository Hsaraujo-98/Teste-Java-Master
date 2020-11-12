package com.rns.testes.java.dto;

import lombok.Data;

@Data
public class ProdutoFilialInsertDto {

	private Long idFilial;
	private String idProduto;
	private Integer quantidade;

}
