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

import br.com.matrix.lancamento.model.Empresa;
import br.com.matrix.lancamento.repository.EmpresaRepository;
import br.com.matrix.lancamento.service.EmpresaService;

@RestController
@RequestMapping("empresas")
public class EmpresaResource {
	
	@Autowired
	private EmpresaRepository empresaRepository;
	@Autowired
	private EmpresaService empresaService;
	
	@GetMapping()
	public ResponseEntity<?> listarEmpresas() {
		return new ResponseEntity<>(empresaRepository.findAll(), HttpStatus.OK);
	}
	
	@GetMapping(path = "/pesquisarPorNome/{nome}")
	public ResponseEntity<?> pesquisarPorNome(@PathVariable String nome) {
		return new ResponseEntity<>(empresaService.pesquisarPorNome(nome), HttpStatus.OK);
	}
	
	@GetMapping(path = "/pesquisarPorCnpj/{cnpj}")
	public ResponseEntity<?> pesquisarPorCnpj(@PathVariable String cnpj) {
		return new ResponseEntity<>(empresaService.pesquisarPorCnpj(cnpj), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<?> cadastrar(@Valid @RequestBody Empresa empresa) {
		return new ResponseEntity<>(empresaService.cadastrar(empresa), HttpStatus.CREATED);
	}
	
	@DeleteMapping(path = "/{id}")
	public ResponseEntity<?> remover(@PathVariable("id") Long id) {
		empresaService.remover(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PutMapping
	public ResponseEntity<?> atualizar(@RequestBody Empresa empresa) {
		empresaService.atualizar(empresa);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
}
