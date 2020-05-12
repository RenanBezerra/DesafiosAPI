package com.gft.desafios.domain.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.gft.desafios.domain.model.Produto;

@Component
public interface ProdutoRepository {

	List<Produto> consultaPorCategoria(String categoria);

	List<Produto> listar(Pageable pageable);

	Produto buscar(Long id);
	
	Produto salvar(Produto produto);

	void remover(Long id);

	

}
