package br.com.matrix.lancamento.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.matrix.lancamento.error.NaoEncontradoExcecao;
import br.com.matrix.lancamento.model.Categoria;
import br.com.matrix.lancamento.repository.CategoriaRepository;

@Service
public class CategoriaService {
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public void verificarCategoriaDuplicada(Categoria categoria) {
		if (!categoriaRepository.findByDescricaoIgnoreCase(categoria.getDescricao()).isEmpty())
			throw new IllegalArgumentException("Essa categoria já está cadastrada");
	}
	
	public void verificarCategoriaExiste(Long id) {
		if (categoriaRepository.existsById(id) == false)
			throw new NaoEncontradoExcecao("Categoria não encontrada pelo id " + id);
		
	}
}
