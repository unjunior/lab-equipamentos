package br.com.empresa.lab.controller;

import br.com.empresa.lab.dto.ClienteDTO;
import br.com.empresa.lab.dto.EquipamentoDTO;
import br.com.empresa.lab.service.ClienteService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteController {

    private ClienteService clienteService;

    public ClienteController(ClienteService clienteService){
        this.clienteService = clienteService;
    }

    @GetMapping
    public ResponseEntity <Page<ClienteDTO>> buscarTodosClientes(Pageable pageable){
        Page<ClienteDTO> result = clienteService.pegarTodosClientes(pageable);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ClienteDTO> buscarClientePorId(@PathVariable Long id){
       ClienteDTO dto = clienteService.pegarClientePorId(id);
       return ResponseEntity.ok(dto);
    }

    @GetMapping(value = "/{id}/equipamentos")
    public ResponseEntity<List<EquipamentoDTO>> buscarEquipamentosPorCliente(@PathVariable Long id) {
        List<EquipamentoDTO> result = clienteService.pegarEquipamentosPorCliente(id);
        return ResponseEntity.ok(result);
    }

    @PostMapping
    public ResponseEntity<ClienteDTO> insertCliente(@RequestBody ClienteDTO dto){
        ClienteDTO novoCliente = clienteService.insereCliente(dto);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(dto.getId()).toUri();

        return ResponseEntity.created(uri).body(novoCliente);
    }

}
