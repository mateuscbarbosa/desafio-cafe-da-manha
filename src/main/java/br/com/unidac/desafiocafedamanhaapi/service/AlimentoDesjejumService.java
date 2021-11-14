package br.com.unidac.desafiocafedamanhaapi.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.unidac.desafiocafedamanhaapi.dto.AlimentoDesjejumFormDto;
import br.com.unidac.desafiocafedamanhaapi.dto.AlimentoDesjejumOutputDto;
import br.com.unidac.desafiocafedamanhaapi.modelo.AlimentoDesjejum;
import br.com.unidac.desafiocafedamanhaapi.modelo.Colaborador;
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

	public void cadastrar(List<AlimentoDesjejumFormDto> alimentosForm,Colaborador colaborador){
		List<AlimentoDesjejum> alimentos = alimentosForm.stream()
															.map(a -> modelMapper.map(a, AlimentoDesjejum.class))
															.collect(Collectors.toList()); 
		if(!verificaAlimentosCadastrados(alimentos)) {
			colaborador.setAlimentos(alimentos);
			alimentos.stream().forEach(a -> a.setColaborador(colaborador));
		}
	}
	
	public boolean verificaAlimentosCadastrados(List<AlimentoDesjejum> alimentos) {
		List<String> respostas = new ArrayList<>();
		for (AlimentoDesjejum alimentoDesjejum : alimentos) {
			boolean existe = alimentoRepository
					.existePorNomeSemEspacos(alimentoDesjejum.getNome().toLowerCase().trim().replaceAll("\\s+", ""));
			if (existe) {
				respostas.add("Alguém já irá trazer " + alimentoDesjejum.getNome());
				return true;
			}
		}

		return false;
	}

}