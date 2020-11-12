package com.rns.testes.java.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "PRODUTO")
@Data
public class Produto extends GenericEntity<String> {

	@Id
	private String id;

	@Column
	private String nome;

}
