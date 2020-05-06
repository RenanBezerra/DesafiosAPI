package com.gft.desafios.api.controller;

import java.util.ArrayList;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.gft.desafios.api.service.ProdutoService;
import com.gft.desafios.domain.model.Produto;
import com.gft.desafios.domain.repository.ProdutoRepository;

@RestController
@RequestMapping("/listaProdutos")
public class ProdutoController {

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private ProdutoService produtoService;

	@GetMapping("/diretoBanco")
	public @ResponseBody ArrayList<Produto> listaProdutos() {
		Iterable<Produto> listaProdutos = produtoRepository.findAll();
		ArrayList<Produto> produtos = new ArrayList<Produto>();
		for (Produto produto : listaProdutos) {
			produto.add(WebMvcLinkBuilder.linkTo(ProdutoController.class).slash(produto.getId()).withSelfRel());
			produtos.add(produto);
		}

		return produtos;

	}

	@GetMapping
	public Page<Produto> getProdutos(Pageable pageable) {
		return produtoService.getProdutos(pageable);

	}

	@GetMapping("/maiorQue")
	public Page<Produto> getProdutoIdMaiorQue(@RequestParam("id") Long id, Pageable pageable) {
		return produtoService.findProdutoByIdGreaterThan(id, pageable);
	}

//	@GetMapping(value = "/{id}", produces = "application/json")
//	public @ResponseBody Produto produto(@PathVariable(value = "id") Long id) {
//		Produto produto = produtoRepository.findByCodigo(id);
//		produto.add(linkTo(methodOn(ProdutoController.class).listaProdutos()).withRel("Lista de Produtos");
//		
//		return produto;
//	}

	@GetMapping("/{ProdutoId}")
	public ResponseEntity<Produto> buscaPorId(@PathVariable Long ProdutoId) {
		// Optional<Produto> produto = produtoRepository.findById(ProdutoId);
//			produto.add(WebMvcLinkBuilder.linkTo(ProdutoController.class)
//				.withSelfRel());
		Optional<Produto> produto = produtoRepository.findById(ProdutoId);

		if (produto.isPresent()) {
			return ResponseEntity.ok(produto.get());
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

		if (!produtoRepository.existsById(produtoId)) {
			return ResponseEntity.notFound().build();
		}
		produto.setId(produtoId);
		produto = produtoService.saveProduto(produto);
		return ResponseEntity.ok(produto);

	}

	@DeleteMapping("/{ProdutoId}")
	public ResponseEntity<Void> excluir(@PathVariable Long ProdutoId) {

		if (!produtoRepository.existsById(ProdutoId)) {
			return ResponseEntity.notFound().build();
		}

		produtoService.excuirProduto(ProdutoId);
		return ResponseEntity.noContent().build();
	}
}
