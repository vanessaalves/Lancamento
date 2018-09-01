package br.com.matrix.lancamento.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.matrix.lancamento.error.NaoEncontradoExcecao;
import br.com.matrix.lancamento.model.Lancamento;
import br.com.matrix.lancamento.repository.LancamentoRepository;

@Service
public class LancamentoService {
	
	@Autowired
	private LancamentoRepository lancamentoRepository;
	
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
}