package br.com.unidac.desafiocafedamanhaapi.infra;

public class RegrasDeNegocioException extends RuntimeException{

	public RegrasDeNegocioException(String mensagem) {
		super(mensagem);
	}
}
