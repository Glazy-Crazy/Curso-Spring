package com.curso.services;

import com.curso.domains.Pedido;
import com.curso.domains.dtos.PedidoDTO;
import com.curso.repositories.PedidoRepository;
import com.curso.services.exceptions.DataIntegrityViolationException;
import com.curso.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepo;

    public List<PedidoDTO> findAll(){

        return pedidoRepo.findAll().stream()
                .map(obj -> new PedidoDTO(obj))
                .collect(Collectors.toList());
    }

    public Pedido findById(Long id){
        Optional<Pedido> obj = pedidoRepo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Pedido não encontrado! Id:" + id));
    }


    public Pedido create(PedidoDTO dto){
        dto.setId(null);
        Pedido obj = new Pedido(dto);
        return pedidoRepo.save(obj);
    }


    public Pedido update(Long id,PedidoDTO objDto){
        objDto.setId(id);
        Pedido oldObj = findById(id);
        oldObj = new Pedido(objDto);
        return pedidoRepo.save(oldObj);
    }

    public Pedido pagar(Long id) {
        Pedido obj = findById(id);
        if (obj == null) {
            throw new DataIntegrityViolationException("Pedido não encontrado");
        }
        obj.pagar();
        return pedidoRepo.save(obj);
    }

    public Pedido enviar(Long id) {
        Pedido obj = findById(id);
        if (obj == null) {
            throw new DataIntegrityViolationException("Pedido não encontrado");
        }
        obj.enviar();
        return pedidoRepo.save(obj);
    }


    public Pedido cancelar(Long id) {
        Pedido obj = findById(id);
        if (obj == null) {
            throw new DataIntegrityViolationException("Pedido não encontrado");
        }
        obj.cancelar();
        return pedidoRepo.save(obj);
    }

    public void delete(Long id){
        Pedido obj = findById(id);
        pedidoRepo.deleteById(id);
    }
}
