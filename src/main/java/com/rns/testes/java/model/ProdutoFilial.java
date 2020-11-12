package com.rns.testes.java.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "PRODUTO_FILIAL")
@Data
public class ProdutoFilial extends GenericEntity<Long> {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "idFilial")
	private Filial filial;

	@ManyToOne
	@JoinColumn(name = "idProduto")
	private Produto produto;

	@Column
	private Integer quantidade;

}
