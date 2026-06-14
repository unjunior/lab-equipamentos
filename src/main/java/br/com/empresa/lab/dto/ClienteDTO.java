package br.com.empresa.lab.dto;

import br.com.empresa.lab.entidades.Cliente;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;

public class ClienteDTO {

    private Long id;

    @NotBlank(message = "O nome é requerido")
    private String codigo;

    @Size(min = 3, message = "O nome não pode ter menos que 3 caracteres")
    @NotBlank(message = "O nome é requerido")
    private String nome;
    private List<EquipamentoDTO> equipamentos = new ArrayList<>();

    public ClienteDTO(){}

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
                .stream()
                .map(EquipamentoDTO::new)
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
