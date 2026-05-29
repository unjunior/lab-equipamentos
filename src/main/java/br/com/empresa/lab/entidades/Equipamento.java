package br.com.empresa.lab.entidades;

import br.com.empresa.lab.enums.StatusEquipamento;
import br.com.empresa.lab.enums.Tipo;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "tb_equipamento")
public class Equipamento {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = true)
    private String codigo;

    @Enumerated(EnumType.STRING)
    private Tipo tipo;

    private LocalDate dataEntrada;

    @Enumerated(EnumType.STRING)
    private StatusEquipamento statusEquipamento;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    public Equipamento() {
    }

    public Equipamento(Long id, String codigo, Tipo tipo, LocalDate dataEntrada, StatusEquipamento statusEquipamento) {
        this.id = id;
        this.codigo = codigo;
        this.tipo = tipo;
        this.dataEntrada = dataEntrada;
        this.statusEquipamento = statusEquipamento;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public void setDataEntrada(LocalDate dataEntrada) {
        this.dataEntrada = dataEntrada;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public StatusEquipamento getStatusEquipamento() {
        return statusEquipamento;
    }

    public void setStatusEquipamento(StatusEquipamento statusEquipamento) {
        this.statusEquipamento = statusEquipamento;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        Equipamento that = (Equipamento) o;
        return Objects.equals(codigo, that.codigo);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(codigo);
    }
}
