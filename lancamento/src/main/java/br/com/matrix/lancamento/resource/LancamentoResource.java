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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.matrix.lancamento.model.Lancamento;
import br.com.matrix.lancamento.service.LancamentoService;

@RestController
@RequestMapping("lancamentos")
public class LancamentoResource {
	
	@Autowired
	private LancamentoService lancamentoService;
	
	@GetMapping()
	public ResponseEntity<?> listarLancamentoPorVencimento(@RequestParam("dataInicio") String dataInicio,
			@RequestParam("dataFim") String dataFim) {
		LocalDate dtInicio = LocalDate.parse(dataInicio);
		LocalDate dtFim = LocalDate.parse(dataFim);
		
		return new ResponseEntity<>(lancamentoService.listarLancamentoPorVencimento(dtInicio, dtFim), HttpStatus.OK);
	}
	
	@PostMapping()
	public ResponseEntity<?> cadastrar(@RequestParam("id") Long idEmpresa, @RequestParam("id2") Long idCategoria,
			@RequestBody Lancamento lancamento) {
		
		return new ResponseEntity<>(lancamentoService.cadastrar(idEmpresa, idCategoria, lancamento),
				HttpStatus.CREATED);
	}
	
	@DeleteMapping(path = "/{id}")
	public ResponseEntity<?> remover(@PathVariable("id") Long id) {
		lancamentoService.remover(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PutMapping
	public ResponseEntity<?> atualizar(@RequestBody Lancamento lancamento) {
		lancamentoService.atualizar(lancamento);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
}
