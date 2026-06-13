package br.com.empresa.lab.service;

import br.com.empresa.lab.dto.EquipamentoDTO;
import br.com.empresa.lab.entidades.Cliente;
import br.com.empresa.lab.entidades.Equipamento;
import br.com.empresa.lab.enums.StatusEquipamento;
import br.com.empresa.lab.enums.Tipo;
import br.com.empresa.lab.repository.ClienteRepository;
import br.com.empresa.lab.repository.EquipamentoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EquipamentoService {

    private EquipamentoRepository equipamentoRepository;
    private ClienteRepository clienteRepository;


    public EquipamentoService(EquipamentoRepository equipamentoRepository,
                              ClienteRepository clienteRepository){
        this.equipamentoRepository = equipamentoRepository;
        this.clienteRepository = clienteRepository;
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

    @Transactional
    public EquipamentoDTO insert(Long clienteId, EquipamentoDTO dto){

        Cliente cliente = clienteRepository.findById(clienteId)
                .orElseThrow( () -> new RuntimeException("Cliente não encontrado"));


        Equipamento equipamento = new Equipamento();
        equipamento.setCodigo(dto.getCodigo());
        equipamento.setTipo(dto.getTipo());
        equipamento.setDataEntrada(dto.getDataEntrada());
        equipamento.setCliente(cliente);
        equipamento.setStatusEquipamento(dto.getStatusEquipamento());

        Equipamento novoEquipamento = equipamentoRepository.save(equipamento);
        return new EquipamentoDTO(novoEquipamento);
    }

    @Transactional
    public EquipamentoDTO atualizaEquipamento(Long id, EquipamentoDTO dto){
        Equipamento equipamento = equipamentoRepository.getReferenceById(id);
        equipamento.setCodigo(dto.getCodigo());
        equipamento.setTipo(dto.getTipo());
        equipamento.setDataEntrada(dto.getDataEntrada());
        equipamento.setStatusEquipamento(dto.getStatusEquipamento());

        Equipamento novo = equipamentoRepository.save(equipamento);
        return new EquipamentoDTO(novo);
    }

    @Transactional
    public EquipamentoDTO atualizaStatusEquipamento(Long id, EquipamentoDTO dto){
        Equipamento equipamento = equipamentoRepository.getReferenceById(id);
        equipamento.setStatusEquipamento(dto.getStatusEquipamento());

        Equipamento novo = equipamentoRepository.save(equipamento);
        return new EquipamentoDTO(novo);
    }

    @Transactional
    public void deletaEquipamento(Long id){

        if(!equipamentoRepository.existsById(id)){
            throw new RuntimeException("Equipamento inexistente");
        }

        equipamentoRepository.deleteById(id);
    }
}

