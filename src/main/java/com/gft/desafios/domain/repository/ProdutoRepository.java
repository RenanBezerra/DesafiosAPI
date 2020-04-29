package com.gft.desafios.domain.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gft.desafios.domain.model.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long>{

	Page<Produto> findProdutoByIdGreaterThan(Long id,Pageable pageable);
	
}
