package br.com.empresa.lab.controller;

import br.com.empresa.lab.dto.EquipamentoDTO;
import br.com.empresa.lab.enums.StatusEquipamento;
import br.com.empresa.lab.enums.Tipo;
import br.com.empresa.lab.service.EquipamentoService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/equipamentos")
public class EquipamentoController {

    private EquipamentoService equipamentoService;

    public EquipamentoController(EquipamentoService equipamentoService){
        this.equipamentoService = equipamentoService;
    }

//    @GetMapping
//    public ResponseEntity<Page<EquipamentoDTO>> buscarEquipamentos(Pageable pageable){
//        Page<EquipamentoDTO> result = equipamentoService.buscarTodosEquipamentos(pageable);
//        return ResponseEntity.ok().body(result);
//    }HHHJJJ

    @GetMapping(value = "/{id}")
    public ResponseEntity<EquipamentoDTO> pegarEquipamentoPorId(@PathVariable Long id){
       EquipamentoDTO result = equipamentoService.pegarEquipamentoPorId(id);
       return ResponseEntity.ok(result);
    }

    @GetMapping(value = "/tipo/{tipo}")
    public ResponseEntity<List<EquipamentoDTO>> buscarEquipamentoPorTipo(@PathVariable Tipo tipo){
        List<EquipamentoDTO> result = equipamentoService.buscarEquipamentoPorTipo(tipo);
        return ResponseEntity.ok(result);
    }

    // ROTA COM PARAMETRO
    @GetMapping
    public ResponseEntity<Page<EquipamentoDTO>> buscarEquipamento(
            @RequestParam(required = false) StatusEquipamento statusEquipamento,
            Pageable pageable) {

        if (statusEquipamento != null){
            return ResponseEntity.ok(equipamentoService.buscarEquipamentoPorStatus(statusEquipamento, pageable));
        }

        Page<EquipamentoDTO> result = equipamentoService.buscarTodosEquipamentos(pageable);
        return ResponseEntity.ok(result);
    }

    // VALOR FIXO NA URL
//    @GetMapping(value = "/no-laboratorio")
//    public ResponseEntity<List<EquipamentoDTO>> buscarEquipamentoNoLaboratorio(){
//        List<EquipamentoDTO> result = equipamentoService.buscarEquipamentoPorStatus(StatusEquipamento.NO_LABORATORIO);
//        return ResponseEntity.ok(result);
//    }


}
