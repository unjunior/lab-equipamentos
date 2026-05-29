package br.com.empresa.lab.dto;

import br.com.empresa.lab.entidades.Cliente;

import java.util.ArrayList;
import java.util.List;

public class ClienteDTO {

    private Long id;
    private String codigo;
    private String nome;
    private List<EquipamentoDTO> equipamentos = new ArrayList<>();

    public ClienteDTO(Long id, String codigo, String nome) {
        this.id = id;
        this.codigo = codigo;
        this.nome = nome;
    }

    public ClienteDTO(Cliente cliente){
        this.id = cliente.getId();
        this.codigo = cliente.getCodigo();
        this.nome = cliente.getNome();

        this.equipamentos = cliente.getEquipamentos()
                .stream().map(e -> new EquipamentoDTO(e.getCodigo(), e.getTipo(), e.getCliente().getId(), e.getStatusEquipamento()))
                .toList();

    }

    public Long getId(){
        return id;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public List<EquipamentoDTO> getEquipamentos() {
        return equipamentos;
    }
}
