package br.com.matrix.lancamento.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.matrix.lancamento.error.NaoEncontradoExcecao;
import br.com.matrix.lancamento.model.Empresa;
import br.com.matrix.lancamento.repository.EmpresaRepository;

@Service
public class EmpresaService {
	@Autowired
	private EmpresaRepository empresaRepository;
	
	public void verificarEmpresaExiste(Long id) {
		if (empresaRepository.existsById(id) == false)
			throw new NaoEncontradoExcecao("Empresa não encontrada pelo id " + id);
	}
	
	public void verificarEmpresaDuplicada(Empresa empresa) {
		if (!empresaRepository.findByCnpj(empresa.getCnpj()).isEmpty())
			throw new IllegalArgumentException("Essa empresa já está cadastrada");
	}
	
	public List<Empresa> pesquisarPorNome(String nome) {
		return empresaRepository.findByNomeIgnoreCaseContaining(nome);
	}
	
	public List<Empresa> pesquisarPorCnpj(String cnpj) {
		return empresaRepository.findByCnpj(cnpj);
	}
	public Empresa cadastrar(@Valid Empresa empresa) {
		verificarEmpresaDuplicada(empresa);
		return empresaRepository.save(empresa);
	}
	
	public void remover(Long id) {
		verificarEmpresaExiste(id);
		empresaRepository.deleteById(id);
		
	}
	
	public void atualizar(Empresa empresa) {
		verificarEmpresaExiste(empresa.getId());
		 empresaRepository.save(empresa);
		
	}

	public Empresa findById(Long idEmpresa) {
		return empresaRepository.findById(idEmpresa).get();
	}
	
}
