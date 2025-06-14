# ControlePedido - Solução para Gestão de Pedidos em E-commerce

## Visão Geral
**ControlePedido** é um sistema backend construído com **Java e Spring Boot**, idealizado para administrar pedidos em plataformas de e-commerce. Entre os principais recursos, destacam-se:

- Registro de novos pedidos  
- Cálculo de frete conforme o tipo de envio selecionado  
- Controle do status dos pedidos com base em regras de negócio  
- API REST exposta e documentada com Swagger  

O projeto adota os seguintes padrões de design:

- **State**: gerenciamento dos estados do pedido  
- **Strategy**: cálculo flexível de frete conforme a estratégia definida  

---

## Funcionalidades Disponíveis
- Criar pedidos com nome do produto, valor e tipo de envio  
- Cálculo automático do frete no momento da criação  
- Visualizar todos os pedidos registrados  
- Consultar um pedido específico por meio do ID  
- Alterar o status do pedido: marcar como pago, enviado ou cancelado  
- Observar o valor do frete calculado e o status atual do pedido  

---

## Regras de Negócio
- Todos os pedidos iniciam com o status **AGUARDANDO_PAGAMENTO**  
- A partir deste estado, o pedido pode ser:
  - **Pago**
  - **Cancelado**
- Um pedido **pago** pode ser:
  - **Enviado**
  - **Cancelado**
- Pedidos **enviados** ou **cancelados** não são mais elegíveis para alterações  
- Toda lógica de transição de status está encapsulada no `enum EstadoPedido`, baseado no padrão **State**

---

## Cálculo de Frete (Strategy)
O valor do frete é calculado automaticamente com base no valor total do pedido e na modalidade de envio definida:

- **Transporte terrestre (caminhão)**: 5% do valor total  
- **Transporte aéreo (avião)**: 10% do valor total  

As regras estão implementadas no `enum MetodoEnvio`, o que permite incluir facilmente novos métodos de entrega futuramente.

---

## Camada de Persistência
- Utiliza **Spring Data JPA** para gerenciamento dos dados  
- A entidade `Pedido` é mapeada com anotações da JPA  
- Suporte a múltiplos bancos de dados: **H2** (ambiente de testes) e **PostgreSQL/MySQL** (ambientes produtivos)

---

## API RESTful
A API está completamente documentada com **Swagger/OpenAPI**, podendo ser facilmente testada com ferramentas como **Postman** e **Insomnia**.

### Endpoints Principais

| Método | Caminho                    | Descrição da Ação             |
|--------|----------------------------|-------------------------------|
| POST   | `/pedidos`                 | Registrar um novo pedido      |
| GET    | `/pedidos`                 | Listar todos os pedidos       |
| GET    | `/pedidos/{id}`            | Obter detalhes de um pedido   |
| POST   | `/pedidos/{id}/pagar`      | Marcar o pedido como pago     |
| POST   | `/pedidos/{id}/cancelar`   | Cancelar o pedido             |
| POST   | `/pedidos/{id}/enviar`     | Enviar o pedido (se já pago)  |