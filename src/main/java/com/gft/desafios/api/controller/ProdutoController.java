package com.gft.desafios.api.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.gft.desafios.domain.repository.ProdutoRepository;

@RestController
@RequestMapping("/listaProdutos")
public class ProdutoController {

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private ProdutoService produtoService;

	@GetMapping("/diretoBanco")
	public List<Produto> buscar(){
		return produtoRepository.findAll();

	}
	
	@GetMapping
	public Page<Produto> getProdutos(Pageable pageable) {
		return produtoService.getProdutos(pageable);

	}

	@GetMapping("/maiorQue")
	public Page<Produto> getProdutoIdMaiorQue(@RequestParam("id") Long id, Pageable pageable) {
		return produtoService.findProdutoByIdGreaterThan(id, pageable);
	}

	@GetMapping("/{ProdutoId}")
	public Optional<Produto> buscaPorId(@PathVariable Long ProdutoId) {
		return produtoRepository.findById(ProdutoId);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Produto adicionar(@Valid @RequestBody Produto produto) {
		

		return produtoService.saveProduto(produto);
	}

	@PutMapping("/{ProdutoId}")
	public Produto atualiza(@PathVariable Long ProdutoId, @RequestBody Produto produto) {
		Produto atualizar = new Produto();
		atualizar = produtoRepository.save(produto);

		return atualizar;
	}

	@DeleteMapping("/{ProdutoId}")
	public ResponseEntity<Void> excluir(@PathVariable Long ProdutoId) {

		if(!produtoRepository.existsById(ProdutoId)) {
			return ResponseEntity.notFound().build();
		}
		
		
		produtoService.excuirProduto(ProdutoId);
		return ResponseEntity.noContent().build();
	}
}
