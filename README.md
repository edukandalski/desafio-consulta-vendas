# DevSuperior - Java Spring Professional

## Desafio 04 - Consulta vendas
Neste desafio trabalhamos conceitos de implementar cláusulas de consultas customizadas.

O que utilizamos no backend:
- Java
- Framework Spring Boot

O desafio consiste em usar os conceitos de JPA com Hibernate para obter dados mais específicos com consultas customizadas ao Banco de Dados.

As APIs implementadas foram:
- /sales/report
- /sales/summary

A consulta customizada para obter uma paginação do **SalesReport** foi feita com JPQL com o uso de `JOIN FETCH` e `countQuery`.
A consulta customizada para obter uma lista do **SalesSummary** foi feita utilizando uma query nativa do SQL.

## Como executar
Clonar este repositório, importar na IDE de preferência e executar a classe principal `DsmetaApplication`.

O banco de dados H2 poderá ser consultado pelo client web do H2 no [link](http://localhost:8080/h2-console) e os dados de conexão ao banco H2 estão no arquivo `src/main/resources/application.properties`.

A API vai estar disponível no [link](http://localhost:8080) mais especificamente o recurso `/sales`.