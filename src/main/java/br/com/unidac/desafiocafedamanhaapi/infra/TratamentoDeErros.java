package br.com.unidac.desafiocafedamanhaapi.infra;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.unidac.desafiocafedamanhaapi.dto.Erro400OutputDto;
import br.com.unidac.desafiocafedamanhaapi.dto.Erro500OutputDto;

@RestControllerAdvice
public class TratamentoDeErros {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public List<Erro400OutputDto> tratarErro400(MethodArgumentNotValidException ex){
		return ex.getFieldErrors()
				.stream()
				.map(erro -> new Erro400OutputDto(erro.getField(), erro.getDefaultMessage()))
				.collect(Collectors.toList());
	}
	
	@ExceptionHandler(RegrasDeNegocioException.class)
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public String tratarRegraDeNegocio(RegrasDeNegocioException ex) {
		return ex.getMessage();
	}
	
	@ExceptionHandler(Exception.class)
	@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
	public Erro500OutputDto tratarErro500(Exception ex, HttpServletRequest req) {
		return new Erro500OutputDto(LocalDateTime.now(),
									ex.getMessage(),
									req.getRequestURI());
	}
	
	@ExceptionHandler({EntityNotFoundException.class, EmptyResultDataAccessException.class})
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	public String tratarErro404() {
		return "Não há nada aqui!";
	}
}
