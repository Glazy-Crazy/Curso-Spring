package com.curso.controller;

import com.curso.domains.Pedido;
import com.curso.domains.dtos.PedidoDTO;
import com.curso.services.PedidoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/pedido")
@Tag(name = "Pedido", description = "API para gerenciamento de Pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @GetMapping
    @Operation(summary = "Listar todos os Pedidos",
            description = "Retorna uma lista com todos os Pedidos cadastrados")
    public ResponseEntity<List<PedidoDTO>> findAll(){
        return ResponseEntity.ok().body(pedidoService.findAll());
    }

    @GetMapping(value = "/{id}")
    @Operation(summary = "Buscar um Pedido por id",
            description = "Realizar a busca de um Pedido cadastrado por id")
    public ResponseEntity<PedidoDTO> findById(@PathVariable Long id){
        Pedido obj = this.pedidoService.findById(id);
        return  ResponseEntity.ok().body(new PedidoDTO(obj));
    }

    @PostMapping
    @Operation(summary = "Criar um novo Pedido",
            description = "Criar um novo Pedido com base nos dados fornecidos")
    public ResponseEntity<PedidoDTO> create(@Valid @RequestBody PedidoDTO dto){
        Pedido pedido = pedidoService.create(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(pedido.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
    @Operation(summary = "Altera um Pedido",
            description = "Altera um Pedido existente")
    public ResponseEntity<PedidoDTO> update(@PathVariable Long id, @Valid @RequestBody PedidoDTO objDto){
        Pedido Obj = pedidoService.update(id, objDto);
        return ResponseEntity.ok().body(new PedidoDTO(Obj));
    }

    @PutMapping(value = "/{id}/pagar")
    @Operation(summary = "pagar um Pedido",
            description = "pagar um Pedido existente")
    public ResponseEntity<PedidoDTO> pagar(@PathVariable Long id, @Valid @RequestBody PedidoDTO objDto) {
        Pedido obj = pedidoService.pagar(id);
        return ResponseEntity.ok(new PedidoDTO(obj));
    }

    @PutMapping(value = "/{id}/enviar")
    @Operation(summary = "enviar um Pedido",
            description = "enviar um Pedido existente")
    public ResponseEntity<PedidoDTO> enviar(@PathVariable Long id, @Valid @RequestBody PedidoDTO objDto) {
        Pedido obj = pedidoService.enviar(id);
        return ResponseEntity.ok(new PedidoDTO(obj));
    }

    @PutMapping(value = "/{id}/cancelar")
    @Operation(summary = "cancelar um Pedido",
            description = "cancelar um Pedido existente")
    public ResponseEntity<PedidoDTO> cancelar(@PathVariable Long id, @Valid @RequestBody PedidoDTO objDto) {
        Pedido obj = pedidoService.cancelar(id);
        return ResponseEntity.ok(new PedidoDTO(obj));
    }

    @DeleteMapping(value = "/{id}")
    @Operation(summary = "Deletar um Pedido",
            description = "Remove um Pedido apatir do seu id")
    public ResponseEntity<PedidoDTO> delete(@PathVariable Long id){
        pedidoService.delete(id);
        return ResponseEntity.noContent().build();
    }


}
