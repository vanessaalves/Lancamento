package br.com.matrix.lancamento.service;

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
	
}
