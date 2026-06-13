package br.com.empresa.lab.service;

import br.com.empresa.lab.dto.ClienteDTO;
import br.com.empresa.lab.dto.EquipamentoDTO;
import br.com.empresa.lab.entidades.Cliente;
import br.com.empresa.lab.repository.ClienteRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ClienteService {

    private ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository){
        this.clienteRepository = clienteRepository;
    }

    public Page<ClienteDTO> pegarTodosClientes (Pageable pageable){
        Page<Cliente> result = clienteRepository.findAll(pageable);
        return result.map(ClienteDTO::new);
    }

    @Transactional
    public ClienteDTO pegarClientePorId (Long id){
        Cliente result = clienteRepository.findById(id)
                .orElseThrow(
                        () -> new RuntimeException("Id do cliente não foi encontrado")
                );
        return new ClienteDTO(result);
    }

    @Transactional
    public List<EquipamentoDTO> pegarEquipamentosPorCliente(Long clienteId) {
        ClienteDTO cliente = pegarClientePorId(clienteId);
        return cliente.getEquipamentos();
    }

    @Transactional
    public ClienteDTO insereCliente (ClienteDTO dto){
        Cliente cliente = new Cliente();
        cliente.setNome(dto.getNome());
        cliente.setCodigo(dto.getCodigo());

        cliente = clienteRepository.save(cliente);
        return new ClienteDTO(cliente);
    }

    @Transactional
    public ClienteDTO atualizaCliente(Long id, ClienteDTO dto){
        Cliente cliente = clienteRepository.getReferenceById(id);
        cliente.setNome(dto.getNome());
        cliente.setCodigo(dto.getCodigo());

        Cliente novo = clienteRepository.save(cliente);
        return new ClienteDTO(novo);
    }

//    @Transactional
//    public void deletaCliente(Long id){
//
//        if(!clienteRepository.existsById(id)){
//            throw new RuntimeException("Cliente inexistente");
//        }
//
//        clienteRepository.deleteById(id);
//    }

    @Transactional
    public void deletaCliente(Long id){
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado!"));

        if (!cliente.getEquipamentos().isEmpty()) {
            throw new RuntimeException("Cliente possui equipamentos no laboratório!");
        }

        clienteRepository.deleteById(id);
    }
}
