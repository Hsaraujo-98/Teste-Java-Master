package com.rns.testes.java.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.rns.testes.java.dto.ProdutoFilialInsertDto;
import com.rns.testes.java.dto.ProdutoFilialTransferDto;
import com.rns.testes.java.dto.ProdutoFilialUpdateDto;
import com.rns.testes.java.model.ProdutoFilial;
import com.rns.testes.java.service.IFilialService;
import com.rns.testes.java.service.IProdutoFilialService;
import com.rns.testes.java.service.IProdutoService;

@CrossOrigin
@RestController
@RequestMapping("produtoFilial/")
public class ProdutoFilialController {

	@Autowired
	IProdutoFilialService iProdutoFilialService;

	@Autowired
	IFilialService iFilialService;

	@Autowired
	IProdutoService iProdutoService;

	@GetMapping(value = "find-all", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(value = HttpStatus.OK)
	public ResponseEntity<List<ProdutoFilial>> findAll() {
		return ResponseEntity.ok(iProdutoFilialService.findAll());
	}

	@GetMapping(value = "find-by-id", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(value = HttpStatus.OK)
	public ResponseEntity<ProdutoFilial> findById(@RequestParam(name = "id") Long id) {
		return ResponseEntity.ok(iProdutoFilialService.findById(id));
	}

	@DeleteMapping(value = "delete", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(value = HttpStatus.OK)
	public void delete(@RequestParam(name = "id") Long id) {
		iProdutoFilialService.delete(id);
	}

	@PostMapping(value = "insert", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(value = HttpStatus.OK)
	public ResponseEntity<ProdutoFilial> add(@RequestBody ProdutoFilialInsertDto dto) {
		ProdutoFilial produtoFilial = new ProdutoFilial();
		produtoFilial.setFilial(iFilialService.findById(dto.getIdFilial()));
		produtoFilial.setProduto(iProdutoService.findById(dto.getIdProduto()));
		produtoFilial.setQuantidade(dto.getQuantidade());
		return ResponseEntity.ok(iProdutoFilialService.save(produtoFilial));
	}

	@PutMapping(value = "update", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(value = HttpStatus.OK)
	public ResponseEntity<ProdutoFilial> update(@RequestParam(name = "id") Long id,
			@RequestBody ProdutoFilialUpdateDto dto) {
		ProdutoFilial produtoFilial = iProdutoFilialService.findById(id);
		produtoFilial.setQuantidade(dto.getQuantidade());
		return ResponseEntity.ok(iProdutoFilialService.save(produtoFilial));
	}

	@PutMapping(value = "transfer", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(value = HttpStatus.OK)
	public ResponseEntity<ProdutoFilial> transfer(@RequestBody ProdutoFilialTransferDto dto) {
		ProdutoFilial produtoFilialOrigem = iProdutoFilialService.findByIdFilialAndIdProduto(dto.getIdFilialOrigem(),
				dto.getIdProduto());
		ProdutoFilial produtoFilialFinal = iProdutoFilialService.findByIdFilialAndIdProduto(dto.getIdFilialDestino(),
				dto.getIdProduto());
		if (produtoFilialOrigem != null && produtoFilialFinal != null) {
			produtoFilialFinal.setQuantidade(produtoFilialFinal.getQuantidade() + produtoFilialOrigem.getQuantidade());
		}
		if (produtoFilialOrigem != null && produtoFilialFinal == null) {
			produtoFilialFinal = produtoFilialOrigem;
		}
		iProdutoFilialService.delete(produtoFilialOrigem);
		return ResponseEntity.ok(iProdutoFilialService.save(produtoFilialFinal));
	}
}
