package br.com.empresa.lab.service;

import br.com.empresa.lab.dto.EquipamentoDTO;
import br.com.empresa.lab.entidades.Equipamento;
import br.com.empresa.lab.enums.StatusEquipamento;
import br.com.empresa.lab.enums.Tipo;
import br.com.empresa.lab.repository.EquipamentoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EquipamentoService {

    private EquipamentoRepository equipamentoRepository;

    public EquipamentoService(EquipamentoRepository equipamentoRepository){
        this.equipamentoRepository = equipamentoRepository;
    }

    public Page<EquipamentoDTO> buscarTodosEquipamentos(Pageable pageable) {
        Page<Equipamento> result = equipamentoRepository.findAll(pageable);
        return result.map(EquipamentoDTO::new);
    }

    @Transactional
    public EquipamentoDTO pegarEquipamentoPorId(Long id){
        Equipamento result = equipamentoRepository.findById(id)
                .orElseThrow(
                        () -> new RuntimeException("Id do equipamento não foi encontrado!")
                );

        return new EquipamentoDTO(result);
    }

    @Transactional
    public List<EquipamentoDTO> buscarEquipamentoPorTipo(Tipo tipo){
        List<Equipamento> result = equipamentoRepository.findByTipo(tipo);
        return result.stream().map(EquipamentoDTO::new).toList();
    }

   // COLOCANDO VALOR FLEXIVEL O CONTROLADOR VAI LER OS PARAMETROS
    public Page<EquipamentoDTO> buscarEquipamentoPorStatus(StatusEquipamento statusEquipamento, Pageable pageable){
        Page<Equipamento> result = equipamentoRepository.findByStatusEquipamento(statusEquipamento, pageable);
        return result.map(EquipamentoDTO::new);
    }
    // BUSCAR EQUIPAMENTO COLOCANDO VALOR FIXO
//    public List<EquipamentoDTO> buscarEquipamentoPorStatus(StatusEquipamento statusEquipamento){
//        List<Equipamento> result = equipamentoRepository.findByStatusEquipamento(statusEquipamento);
//        return result.stream().map(EquipamentoDTO::new).toList();
//    }


}

