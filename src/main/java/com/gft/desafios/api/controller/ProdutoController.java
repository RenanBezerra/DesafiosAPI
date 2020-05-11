package com.gft.desafios.api.controller;

import java.util.ArrayList;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.gft.desafios.api.service.ProdutoService;
import com.gft.desafios.domain.model.Produto;

@RestController
@RequestMapping("/listaProdutos")
public class ProdutoController {

	@Autowired
	private ProdutoService produtoService;

//	@GetMapping("/direto")
//	public @ResponseBody ArrayList<Produto> listaProdutos(Pageable pageable) {
//		Iterable<Produto> listaProdutos = produtoService.getProdutos(pageable);
//		ArrayList<Produto> produtos = new ArrayList<Produto>();
//		for (Produto produto : listaProdutos) {
//			produto.add(WebMvcLinkBuilder.linkTo(ProdutoController.class).slash(produto.getId()).withSelfRel());
//			produtos.add(produto);
//		}
//
//		return produtos;
//
//	}

//	@GetMapping("/diretoBanco")
//	public @ResponseBody ArrayList<Produto> listaProdutos() {
//		Iterable<Produto> listaProdutos = produtoRepository.findAll();
//		ArrayList<Produto> produtos = new ArrayList<Produto>();
//		for (Produto produto : listaProdutos) {
//			produto.add(WebMvcLinkBuilder.linkTo(ProdutoController.class).slash(produto.getId()).withSelfRel());
//			produtos.add(produto);
//		}
//
//		return produtos;
//
//	}

	@GetMapping
	public ArrayList<Produto> getProdutos(Pageable pageable) {
		return produtoService.getProdutos(pageable);

	}

	@GetMapping("/maiorQue")
	public Page<Produto> getProdutoIdMaiorQue(@RequestParam("id") Long id, Pageable pageable) {
		return produtoService.findProdutoByIdGreaterThan(id, pageable);
	}

	@GetMapping("/{ProdutoId}")
	public ResponseEntity<Produto> buscaPorId(@PathVariable Long ProdutoId) {
		Produto produto = produtoService.buscaOuFalha(ProdutoId);

		if (produto != null) {
			return ResponseEntity.ok(produto);
		}

		return ResponseEntity.notFound().build();
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Produto adicionar(@Valid @RequestBody Produto produto) {

		return produtoService.saveProduto(produto);
	}

	@PutMapping("/{produtoId}")
	public ResponseEntity<Produto> atualizar(@Valid @PathVariable Long produtoId, @RequestBody Produto produto) {

		if (produtoService.existsById(produtoId) == null) {
			return ResponseEntity.notFound().build();
		}
		produto.setId(produtoId);
		produto = produtoService.saveProduto(produto);
		return ResponseEntity.ok(produto);

	}

	@DeleteMapping("/{produtoId}")
	public ResponseEntity<Void> excluir(@PathVariable Long produtoId) {

		try {

			if (produtoService.existsById(produtoId) == null) {
				return ResponseEntity.notFound().build();
			}

			produtoService.excuirProduto(produtoId);
			return ResponseEntity.noContent().build();
		} catch (DataIntegrityViolationException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
	}

}
