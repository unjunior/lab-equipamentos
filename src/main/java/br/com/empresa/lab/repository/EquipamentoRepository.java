package br.com.empresa.lab.repository;

import br.com.empresa.lab.entidades.Equipamento;
import br.com.empresa.lab.enums.StatusEquipamento;
import br.com.empresa.lab.enums.Tipo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EquipamentoRepository extends JpaRepository<Equipamento, Long> {

    /* método que busque equipamentos pelo clienteId: */
     List<Equipamento> findByClienteId(Long clienteId);

     List<Equipamento> findByTipo(Tipo tipo);

     Page<Equipamento> findByStatusEquipamento(StatusEquipamento statusEquipamento, Pageable pageable);
}


