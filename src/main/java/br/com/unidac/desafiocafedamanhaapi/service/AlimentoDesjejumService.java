package br.com.unidac.desafiocafedamanhaapi.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.unidac.desafiocafedamanhaapi.dto.AlimentoDesjejumOutputDto;
import br.com.unidac.desafiocafedamanhaapi.modelo.AlimentoDesjejum;
import br.com.unidac.desafiocafedamanhaapi.repository.AlimentoDesjejumRepository;

@Service
public class AlimentoDesjejumService {
	
	@Autowired
	private AlimentoDesjejumRepository alimentoRepository;
	
	@Autowired
	private ModelMapper modelMapper;

	public Page<AlimentoDesjejumOutputDto> listar(Pageable paginacao) {
		Page<AlimentoDesjejum> alimentos = alimentoRepository.findAll(paginacao);
		return alimentos.map(a -> modelMapper.map(alimentos, AlimentoDesjejumOutputDto.class));
	}
	
}
