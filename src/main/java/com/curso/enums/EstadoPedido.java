package com.curso.enums;

public enum EstadoPedido {

    AGUARDANDOPAGAR {
        @Override
        public EstadoPedido pagar() {
            return PAGO;
        }

        @Override
        public EstadoPedido cancelar() {
            return CANCELADO;
        }

        @Override
        public EstadoPedido enviar() {
            throw new IllegalStateException("Pedido aguardando pagamento não pode ser enviado.");
        }
    },

    PAGO {
        @Override
        public EstadoPedido pagar() {
            throw new IllegalStateException("Pedido já pago não pode ser pago novamente.");
        }

        @Override
        public EstadoPedido cancelar() {
            return CANCELADO;
        }

        @Override
        public EstadoPedido enviar() {
            return ENVIADO;
        }
    },

    ENVIADO {
        @Override
        public EstadoPedido pagar() {
            throw new IllegalStateException("Pedido já enviado não pode ser pago novamente.");
        }

        @Override
        public EstadoPedido cancelar() {
            throw new IllegalStateException("Pedido enviado não pode ser cancelado.");
        }

        @Override
        public EstadoPedido enviar() {
            throw new IllegalStateException("O pedido já foi enviado.");
        }
    },

    CANCELADO {
        @Override
        public EstadoPedido pagar() {
            throw new IllegalStateException("Pedido cancelado não pode ser pago.");
        }

        @Override
        public EstadoPedido cancelar() {
            throw new IllegalStateException("Pedido cancelado não pode ser cancelado novamente.");
        }

        @Override
        public EstadoPedido enviar() {
            throw new IllegalStateException("Pedido cancelado não pode ser enviado.");
        }
    };


    public abstract EstadoPedido pagar();
    public abstract EstadoPedido cancelar();
    public abstract EstadoPedido enviar();
}