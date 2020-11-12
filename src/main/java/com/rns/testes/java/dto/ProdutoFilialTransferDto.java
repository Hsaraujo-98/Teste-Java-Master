package com.rns.testes.java.dto;

import lombok.Data;

@Data
public class ProdutoFilialTransferDto {

	private Long idFilialOrigem;
	private Long idFilialDestino;
	private String idProduto;

}
