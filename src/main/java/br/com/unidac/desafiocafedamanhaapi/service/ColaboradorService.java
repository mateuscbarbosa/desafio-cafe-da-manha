package br.com.unidac.desafiocafedamanhaapi.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.unidac.desafiocafedamanhaapi.dto.ColaboradorAtualizarFormDto;
import br.com.unidac.desafiocafedamanhaapi.dto.ColaboradorFormDto;
import br.com.unidac.desafiocafedamanhaapi.dto.ColaboradorOutputDto;
import br.com.unidac.desafiocafedamanhaapi.modelo.AlimentoDesjejum;
import br.com.unidac.desafiocafedamanhaapi.modelo.Colaborador;
import br.com.unidac.desafiocafedamanhaapi.repository.AlimentoDesjejumRepository;
import br.com.unidac.desafiocafedamanhaapi.repository.ColaboradorRepository;

@Service
public class ColaboradorService {

	@Autowired
	private ColaboradorRepository colaboradorRepository;
	
	@Autowired
	private AlimentoDesjejumRepository alimentoRepository;
	
	@Autowired
	private ModelMapper modelMapper;

	
	public Page<ColaboradorOutputDto> listar(Pageable paginacao){
		Page<Colaborador> colaboradores = colaboradorRepository.findAll(paginacao);
		return colaboradores.map(c -> modelMapper.map(c, ColaboradorOutputDto.class));
	}

	@Transactional
	public ColaboradorOutputDto cadastrar(ColaboradorFormDto colaboradorForm) {
		Colaborador colaborador = modelMapper.map(colaboradorForm, Colaborador.class);
		colaborador.setId(null);
		
		List<AlimentoDesjejum> alimentos = colaboradorForm.getAlimentos()
														  .stream().map(a -> modelMapper
																  			.map(a, AlimentoDesjejum.class))
														  					.collect(Collectors.toList());
		colaboradorRepository.save(colaborador);
		
		if(!verificaAlimentosCadastrados(alimentos)) { // criar a classe controller e testar
			Colaborador registrado = colaboradorRepository.getByCpf(colaborador.getCpf()).get();
			alimentos.stream().forEach(a -> a.setColaborador(registrado));
			alimentoRepository.saveAll(alimentos);
		}
		
		return modelMapper.map(colaborador, ColaboradorOutputDto.class);
	}

	@Transactional
	public ColaboradorOutputDto atualizar(ColaboradorAtualizarFormDto colaboradorForm) {
		Colaborador colaborador = colaboradorRepository.findById(colaboradorForm.getId()).get();
		
		colaborador.atualizarInformacoes(colaboradorForm.getNome(), colaboradorForm.getCpf(), colaborador.getAlimentos());
		return modelMapper.map(colaborador, ColaboradorOutputDto.class);
	}

	@Transactional
	public void remover(Long id) {
		colaboradorRepository.deleteById(id);
	}
	
	private boolean verificaAlimentosCadastrados(List<AlimentoDesjejum> alimentos){
		List<String> respostas = new ArrayList<>();
		for (AlimentoDesjejum alimentoDesjejum : alimentos) {
			boolean existe = alimentoRepository.existePorNomeSemEspacos(alimentoDesjejum.getNome().toLowerCase().trim().replaceAll("\\s+", ""));
			if(existe) {
				respostas.add("Alguém já irá trazer " + alimentoDesjejum.getNome());
				return true;
			}
		}
		
		return false;
	}
}
