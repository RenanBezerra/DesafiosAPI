package com.gft.desafios.api.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Service;

import com.gft.desafios.api.controller.ProdutoController;
import com.gft.desafios.domain.model.Produto;
import com.gft.desafios.domain.repository.ProdutoRepository;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;

	public ArrayList<Produto> getProdutos(Pageable pageable) {
		Iterable<Produto> listaProdutos = produtoRepository.listar(pageable);
		ArrayList<Produto> produtos = new ArrayList<Produto>();
		for (Produto produto : listaProdutos) {
			produto.add(WebMvcLinkBuilder.linkTo(ProdutoController.class).slash(produto.getId()).withSelfRel());
			produtos.add(produto);
		}

		return produtos;
	}

//	public Page<Produto> getProdutos(Pageable pageable){
//		return produtoRepository.findAll(pageable);
//	}

	public List<Produto> buscaPorCategoria(String categoria) {

		return produtoRepository.consultaPorCategoria(categoria);
	}

	public Produto saveProduto(Produto produto) {
		return produtoRepository.salvar(produto);
	}

	public void excuirProduto(Long produto) {
		produtoRepository.remover(produto);
	}

	public Produto buscaOuFalha(Long produtoId) {

		return produtoRepository.buscar(produtoId);
	}

	public Produto existsById(Long produtoId) {
		return produtoRepository.buscar(produtoId);
	}

}
