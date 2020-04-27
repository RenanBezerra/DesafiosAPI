package com.gft.desafios.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gft.desafios.domain.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long>{

}
