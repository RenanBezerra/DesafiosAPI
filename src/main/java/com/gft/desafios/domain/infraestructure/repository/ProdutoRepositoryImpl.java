package com.gft.desafios.domain.infraestructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.gft.desafios.domain.model.Produto;
import com.gft.desafios.domain.repository.ProdutoRepository;

@Repository
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

	@Override
	public List<Produto> consultaPorCategoria(String categoria) {
		return manager.createQuery("from Produto where categoria like :categoria", Produto.class)
				.setParameter("categoria","%" + categoria + "%")
				.getResultList();
	}

}
