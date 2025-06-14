package com.curso.services;

import com.curso.enums.MetodoEnvio;
import com.curso.domains.Pedido;
import com.curso.repositories.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class DBService {

    @Autowired
    private PedidoRepository pedidoRepo;

    public void initDB(){

        Pedido pedido01 = new Pedido(null,"Panela Delux", BigDecimal.valueOf(255),MetodoEnvio.TERRESTRE,BigDecimal.valueOf(0));

        Pedido pedido02 = new Pedido(null,"Ferro de Passar Max",BigDecimal.valueOf(255),MetodoEnvio.AEREO,BigDecimal.valueOf(0));

        pedidoRepo.save(pedido01);
        pedidoRepo.save(pedido02);
    }
}
