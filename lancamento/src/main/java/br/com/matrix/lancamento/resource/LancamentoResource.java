package br.com.matrix.lancamento.resource;

import java.time.LocalDate;

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
import br.com.matrix.lancamento.model.Empresa;
import br.com.matrix.lancamento.model.Lancamento;
import br.com.matrix.lancamento.repository.CategoriaRepository;
import br.com.matrix.lancamento.repository.EmpresaRepository;
import br.com.matrix.lancamento.repository.LancamentoRepository;
import br.com.matrix.lancamento.service.LancamentoService;

@RestController
@RequestMapping("lancamentos")
public class LancamentoResource {
	
	@Autowired
	private LancamentoRepository lancamentoRepository;
	@Autowired
	private LancamentoService lancamentoService;
	@Autowired
	private EmpresaRepository empresaRepository;
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@GetMapping()
	public ResponseEntity<?> listarLancamento() {
		
		return new ResponseEntity<>(lancamentoRepository.findAll(), HttpStatus.OK);
	}
	
	@GetMapping(path = "/{dataInicio}/{dataFim}")
	public ResponseEntity<?> listarLancamentoPorVencimento(@PathVariable("dataInicio") String dataInicio,
			@PathVariable("dataFim") String dataFim) {
		LocalDate dtInicio = LocalDate.parse(dataInicio);
		LocalDate dtFim = LocalDate.parse(dataFim);
		
		return new ResponseEntity<>(lancamentoRepository.findByVencimentoBetween(dtInicio, dtFim), HttpStatus.OK);
	}
	
	@PostMapping(path = "/{id}/{id2}")
	public ResponseEntity<?> cadastrarLancamento(@PathVariable("id") Long idEmpresa,
			@PathVariable("id2") Long idCategoria, @RequestBody Lancamento lancamento) {
		Empresa empresa = empresaRepository.getOne(idEmpresa);
		Categoria categoria = categoriaRepository.getOne(idCategoria);
		lancamento.setEmpresa(empresa);
		lancamento.setCategoria(categoria);
		lancamentoRepository.save(lancamento);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@DeleteMapping(path = "/{id}")
	public ResponseEntity<?> remover(@PathVariable("id") Long id) {
		lancamentoService.verificarLancamentoExiste(id);
		lancamentoRepository.deleteById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PutMapping
	public ResponseEntity<?> atualizar(@RequestBody Lancamento lancamento) {
		lancamentoService.verificarLancamentoExiste(lancamento.getId());
		lancamentoRepository.save(lancamento);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
}
