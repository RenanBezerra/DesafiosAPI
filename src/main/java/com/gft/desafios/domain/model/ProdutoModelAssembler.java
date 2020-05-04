package com.gft.desafios.domain.model;

import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.gft.desafios.api.controller.ProdutoController;

@Component
public class ProdutoModelAssembler extends RepresentationModelAssemblerSupport<Produto, ProdutoModel> {

//	@Autowired
//	private ModelMapper modelMapper;

	public ProdutoModelAssembler() {
		super(ProdutoController.class, ProdutoModel.class);
	}

	public ProdutoModel toModel(Produto entity) {
		// TODO Auto-generated method stub
		return null;
	}

//	@Override
//	public ProdutoModel toModel(Produto produto) {
//		ProdutoModel produtoModel = createModelWithId(produto.getId(),produto);
//		modelMapper.map(produto, ProdutoModel.class);
//
////		produtoModel.add(Link(ProdutoController.class).withRel("produtos"));
//		
//		return produtoModel;
//	}
}
