package br.com.empresa.lab.dto;

import br.com.empresa.lab.entidades.Equipamento;
import br.com.empresa.lab.enums.StatusEquipamento;
import br.com.empresa.lab.enums.Tipo;

public class EquipamentoDTO {

    private String codigo;
    private Tipo tipo;
    private Long clienteId;
    private StatusEquipamento statusEquipamento;

    public EquipamentoDTO(String codigo, Tipo tipo, Long clienteId, StatusEquipamento statusEquipamento) {
        this.codigo = codigo;
        this.tipo = tipo;
        this.clienteId = clienteId;
        this.statusEquipamento = statusEquipamento;
    }

    public EquipamentoDTO(Equipamento equipamento){
        this.codigo = equipamento.getCodigo();
        this.tipo = equipamento.getTipo();
        this.clienteId = equipamento.getCliente().getId();
        this.statusEquipamento = equipamento.getStatusEquipamento();
    }

    public String getCodigo() {
        return codigo;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public Long getClienteId() {
        return clienteId;
    }

    public StatusEquipamento getStatusEquipamento() {
        return statusEquipamento;
    }
}
