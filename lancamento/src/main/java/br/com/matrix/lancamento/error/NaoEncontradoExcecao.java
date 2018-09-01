package br.com.matrix.lancamento.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NaoEncontradoExcecao extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	public NaoEncontradoExcecao(String message) {
		super(message);
	}
}
