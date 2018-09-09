package br.com.matrix.lancamento.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.matrix.lancamento.error.NaoEncontradoExcecao;
import br.com.matrix.lancamento.model.Categoria;
import br.com.matrix.lancamento.repository.CategoriaRepository;

@Service
public class CategoriaService {
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	private void verificarCategoriaDuplicada(Categoria categoria) {
		if (!categoriaRepository.findByDescricaoIgnoreCase(categoria.getDescricao()).isEmpty())
			throw new IllegalArgumentException("Essa categoria já está cadastrada");
	}
	
	private void verificarCategoriaExiste(Long id) {
		if (categoriaRepository.existsById(id) == false)
			throw new NaoEncontradoExcecao("Categoria não encontrada pelo id " + id);
		
	}
	
	public List<Categoria> listarCategorias() {
		return categoriaRepository.findAll();
	}
	
	public Categoria cadastrar(@Valid Categoria categoria) {
		verificarCategoriaDuplicada(categoria);
		return categoriaRepository.save(categoria);
	}
	
	public void remover(Long id) {
		verificarCategoriaExiste(id);
		categoriaRepository.deleteById(id);
	}
	
	public void atualizar(@Valid Categoria categoria) {
		verificarCategoriaExiste(categoria.getId());
		categoriaRepository.save(categoria);
	}
	
	public Categoria findById(Long idCategoria) {
		return categoriaRepository.findById(idCategoria).get();
	}
	
}
