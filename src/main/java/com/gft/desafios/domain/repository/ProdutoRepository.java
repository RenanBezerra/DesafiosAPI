package com.gft.desafios.domain.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.gft.desafios.domain.model.Produto;

public interface ProdutoRepository {

	Page<Produto> findProdutoByIdGreaterThan(Long id, Pageable pageable);

	List<Produto> listar(Pageable pageable);

	Produto buscar(Long id);
	
	Produto salvar(Produto produto);

	void remover(Long id);

	

}
