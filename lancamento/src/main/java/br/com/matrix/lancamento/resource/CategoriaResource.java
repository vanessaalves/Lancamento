package br.com.matrix.lancamento.resource;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.matrix.lancamento.model.Categoria;
import br.com.matrix.lancamento.repository.CategoriaRepository;
import br.com.matrix.lancamento.service.CategoriaService;

@RestController
@RequestMapping("categorias")
public class CategoriaResource {
	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private CategoriaService categoriaService;
	
	@GetMapping()
	public ResponseEntity<?> listarCategorias() {
		return new ResponseEntity<>(categoriaRepository.findAll(), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<?> cadastrar(@Valid @RequestBody Categoria categoria) {
		categoriaService.verificarCategoriaDuplicada(categoria);
		return new ResponseEntity<>(categoriaRepository.save(categoria), HttpStatus.CREATED);
	}
	
	@DeleteMapping(path = "/{id}")
	public ResponseEntity<?> remover(@PathVariable("id") Long id) {
		categoriaService.verificarCategoriaExiste(id);
		categoriaRepository.deleteById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PutMapping
	public ResponseEntity<?> atualizar(@RequestBody Categoria categoria) {
		categoriaService.verificarCategoriaExiste(categoria.getId());
		categoriaRepository.save(categoria);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
