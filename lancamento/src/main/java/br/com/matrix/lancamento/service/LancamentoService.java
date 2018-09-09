package br.com.matrix.lancamento.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.matrix.lancamento.error.NaoEncontradoExcecao;
import br.com.matrix.lancamento.model.Categoria;
import br.com.matrix.lancamento.model.Empresa;
import br.com.matrix.lancamento.model.Lancamento;
import br.com.matrix.lancamento.repository.LancamentoRepository;

@Service
public class LancamentoService {
	
	@Autowired
	private LancamentoRepository lancamentoRepository;
	
	@Autowired
	private EmpresaService empresaService;
	@Autowired
	private CategoriaService categoriaService;
	
	public void verificarLancamentoExiste(Long id) {
		if (lancamentoRepository.existsById(id) == false)
			throw new NaoEncontradoExcecao("Lancamento não encontrado pelo id " + id);
	}
	
	public List<Lancamento> buscarVencimento(LocalDate inicio, LocalDate fim) {
		return lancamentoRepository.findByVencimentoBetween(inicio, fim);
		
	}
	
	public BigDecimal somarValor(List<Lancamento> listaCompleta) {
		List<BigDecimal> result = listaCompleta.stream().map(x -> x.getValor()).collect(Collectors.toList());
		BigDecimal total = result.stream().reduce(BigDecimal.ZERO, BigDecimal::add).setScale(2, RoundingMode.HALF_UP);
		return total;
		
	}
	
	public List<Lancamento> listarLancamentoPorVencimento(LocalDate dtInicio, LocalDate dtFim) {
		return lancamentoRepository.findByVencimentoBetween(dtInicio, dtFim);
	}
	
	public Lancamento cadastrar(Long idEmpresa, Long idCategoria, Lancamento lancamento) {
		Empresa empresa = empresaService.findById(idEmpresa);
		Categoria categoria = categoriaService.findById(idCategoria);
		lancamento.setEmpresa(empresa);
		lancamento.setCategoria(categoria);
		return lancamentoRepository.save(lancamento);
	}
	
	public void remover(Long id) {
		verificarLancamentoExiste(id);
		lancamentoRepository.deleteById(id);
	}
	
	public void atualizar(Lancamento lancamento) {
		verificarLancamentoExiste(lancamento.getId());
		lancamentoRepository.save(lancamento);
	}
}