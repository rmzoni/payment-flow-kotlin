# Fluxo de Pagamento
Este documento tem como objetivo detalhar o fluxo de pagamento, estratégia de implementação, modelagem das entidades e escolha de tecnologia para o protótipo funcional do fluxo de pagamento.

## Tecnologia Utilizada
Dada a característica do problema em consistir de um desafio de modelagem e boas práticas fazendo uso de Domain Driven Design.

A escolha foi utilizar uma linguagem que faça uso de programação funcional e orientação a objeto. Por isso, a escolha do **Kotlin com runtime da JVM**.

Para o empacotamento da solução foi utilizado **gradle** com objetivo de rodar os testes unitários do protótipo funcional.

### IDE
A IDE utilizada para o desenvolvimento deste protótipo funcional foi o **Intellij**

## Modelagem
Todas as entidades foram modeladas com atributos com a característica **val**, ou seja, **imutáveis**. A escolha deste modelo foi para uso do paradigma puramente funcional, pois assim não há estados mutáveis e conseguimos atingir um modelo de reprocessamento.

Seguem as definições das entidades envolvidas no fluxo de pagamento:

Família de Produtos
- **_Interface Product_**: entidade abstrata para representar um produto e seu respectivo método de pagamento.
    - **_Physical_**: entidade que extende a interface procuct e representa um produto física que emite shipping label.
    - **_Book_**: entidade que extende a interface product e representa um lívro físico que emite shipping label  isento de impostos conforme disposto na Constituição Art. 150, VI, d.
    - **_Digital_**: entidade que extende a interface product e representa um produto digital que deve notificar por email o cliente e conceder um voucher de R$10 ao comprador.
    - **_Menbership_**: entidade que extende a interface product e representa um produto de assinatura que deve notificar o cliente por email.

Família de Meios de Pagamentos
- **_Interface PaymentMethod_**: entidade abstrata de tipo base para representar um meio de pagamento
    - **_CreditCard_**: entidade que extende a interface PaymentMethod e representa o meio de pagamento de um cartão de crédito.

Família de Clientes
- **_Customer_**: Entidade que represente um cliente. Um cliente por meio de suas compras tem associado diretamente a sua conta os produtos digitais, suas respectivas assinaturas e vouchers.
- **_Voucher_**: Entidade que representa um voucher concedido durante uma compra de um produto digital. O voucher está associado diretamente a um cliente.

Família de Pedidos
- **_Order_**: Entidade que representa um pedido. Um pedido é composto dos itens do pedido, cliente, endereço e shipping label emito.
- **_OrderItem_**: Entidade que representa um item do pedido. O item do pedido é composto de um produto, quantidade e preço.
- **_ShippingLabel_**: Entidade que representa o shipping label emitido para um pedido em caso de conter produtos físicos.
- **_Address_**: Entidade que representa um endereço. O endereço é utilizado em um pedido com o endereço de entrega e de cobrança.

Pagamento
- **_Payment_**: Entidade principal do fluxo e representa o pagamento de um pedido. O pagamento é composto por um número de autorização, quantidade a ser paga, endereço de entrega, endereço de cobrança, email e o pedido.

### Fluxo de Pagamento
O fluxo de pagamento acontece da seguinte forma:
1. Um novo objeto pagamento é criado recebendo o pedido; 
2. O método de pagar (pay) é executado;
3. É gerado o número de autorização para o pagamento;
4. O valor total do pagamento é computado a partir do preço de cada item multiplicado pela respectiva quantidade;
5. Endereço de entrega e endereço de cobrança é obtido a partir do endereço do pedido;
6. Executa o pagamento de cada produto de forma iterativa por meio de polimorfismo da interface de produto;
7. Cada tipo de produto realiza a função específica de pagamento, podendo: emitir shipping label no pedido, envio de email para produtos digitais ou ativar assinatura para um cliente;
8. Imprimi shipping label, caso exista um produto físico;
9. Envia email de confirmação de compra digital, caso exista produtos digitais no pedido;
10. Envia um email de ativação de assinatura, caso exista produtos de assinaturas no pedido;
11. Finaliza o pedido com a data atual;
12. Finaliza o pagamento com a data atual.

#### Métodos importantes
Segue a lista dos principais métodos para o fluxo de pagamento:
- Payment.pay
    - Product.pay
        - Digital.pay
        - Physical.pay
        - Book.pay
        - Menbership.pay
    - Order.printShippingLabel
    - Order.sendDigitalPurchaseConfirmation
    - Order.sendMenbershipPurchaseConfirmation

## Como rodar os testes unitárioas
Para rodar os testes unitários, execute o seguinte comando:
```sh
./gradlew test
```

Para maiores informações sobre a execução do teste, pode se acessar o relatório no formato html em:
```
./build/reports/tests/test/index.html
```

## Referência
- [Kotlin](https://kotlinlang.org/)
- [Gradle](https://gradle.org/)
- [Lambda Calculus](https://en.wikipedia.org/wiki/Lambda_calculus)
- [Domain Driven Design](https://en.wikipedia.org/wiki/Domain-driven_design)
