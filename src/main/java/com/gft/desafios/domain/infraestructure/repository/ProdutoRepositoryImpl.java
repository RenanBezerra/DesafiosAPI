package com.gft.desafios.domain.infraestructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.gft.desafios.domain.model.Produto;
import com.gft.desafios.domain.repository.ProdutoRepository;

@Component
public class ProdutoRepositoryImpl implements ProdutoRepository {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public List<Produto> listar(Pageable pageable) {
		return manager.createQuery("from Produto", Produto.class).getResultList();
	}

	@Override
	public Produto buscar(Long id) {
		return manager.find(Produto.class, id);
	}

	@Transactional
	@Override
	public Produto salvar(Produto produto) {
		return manager.merge(produto);
	}

	@Transactional
	@Override
	public void remover(Long id) {
		Produto produto = buscar(id);

		if (produto == null) {
			throw new EmptyResultDataAccessException(1);
		}
		manager.remove(produto);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Page<Produto> findProdutoByIdGreaterThan(Long id, Pageable pageable) {
		return (Page<Produto>) manager.createQuery("from Produto", Produto.class).getResultList();
	}

}
