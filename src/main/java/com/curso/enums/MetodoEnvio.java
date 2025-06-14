package com.curso.enums;

import java.math.BigDecimal;
import java.math.RoundingMode;

public enum MetodoEnvio {

    TERRESTRE {
        public double calcularFrete(BigDecimal valorPedido) {
            return valorPedido.multiply(new BigDecimal("0.05"))
                    .setScale(2, RoundingMode.HALF_UP)
                    .doubleValue();
        }
    },
    AEREO {
        public double calcularFrete(BigDecimal valorPedido) {
            return valorPedido.multiply(new BigDecimal("0.10"))
                    .setScale(2, RoundingMode.HALF_UP)
                    .doubleValue();

        }
    };

    public abstract double calcularFrete(BigDecimal valorPedido);
}