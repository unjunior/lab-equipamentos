package br.com.empresa.lab.dto;

import br.com.empresa.lab.entidades.Equipamento;
import br.com.empresa.lab.enums.StatusEquipamento;
import br.com.empresa.lab.enums.Tipo;

import java.time.LocalDate;

public class EquipamentoDTO {

    private String codigo;
    private Tipo tipo;
    private LocalDate dataEntrada;
    private Long clienteId;
    private StatusEquipamento statusEquipamento;

    public EquipamentoDTO() {
    }

    public EquipamentoDTO(String codigo, Tipo tipo, LocalDate dataEntrada, Long clienteId, StatusEquipamento statusEquipamento) {
        this.codigo = codigo;
        this.tipo = tipo;
        this.dataEntrada = dataEntrada;
        this.clienteId = clienteId;
        this.statusEquipamento = statusEquipamento;
    }

    public EquipamentoDTO(Equipamento equipamento){
        this.codigo = equipamento.getCodigo();
        this.tipo = equipamento.getTipo();
        this.dataEntrada = equipamento.getDataEntrada();
        this.clienteId = equipamento.getCliente().getId();
        this.statusEquipamento = equipamento.getStatusEquipamento();
    }

    public String getCodigo() {
        return codigo;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public LocalDate getDataEntrada() {
        return dataEntrada;
    }

    public Long getClienteId() {
        return clienteId;
    }

    public StatusEquipamento getStatusEquipamento() {
        return statusEquipamento;
    }
}
