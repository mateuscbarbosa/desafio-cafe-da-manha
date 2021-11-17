# Desafio Café da Manhã
![Badge Back-End](http://img.shields.io/static/v1?label=BACK-END&message=COMPLETO&color=GREEN&style=for-the-badge)
![Badge Front-End](https://img.shields.io/static/v1?label=FRONT-END&message=N%C3%83O%20INICIADO&color=critical&style=for-the-badge)

## Descição do Projeto
Desafio proposto pela empresa para um Café da Manhã com os *COLABORADORES* que deveriam se cadastrar com um CPF válido e único, assim como os alimentos que fossem levar. Dessa forma não podendo haver nada repetido nesse aspecto.
Também foi solicitada a parte de Front-End, mas não foi implementada.

## Funcionalidades
`Colaboradores`
- Listar: Lista com todos os **colaboradores** registrados e os **alimentos** que trarão;
- Cadastrar: Cadastra um **colaborador** pedindo o nome, CPF válido, e **alimentos** que pretende trazer;
- Atualizar: Atualiza o nome, e CPF do **colaborador** informado;
- Remover: Remove o **colaborador** selecionado junto com todos os **alimentos** que traria;
- Detalhar: Detalha um **colaborador** selecionado mostrando seu nome e alimentos que trará, ocultando o CPF;

`Alimentos Desjejum`
- Listar: Lista todos os **alimentos** já informados pelos **colaboradores**;
- Cadastrar: Insere um novo **alimento** relacionado ao **colaborador** informado, e não pode ser um dos que já foram informados anteriormente;
- Atualizar: Atualiza um **alimento** selecionado se ele pertencer ao **colaborador**; É necessário informar o CPF para atualização;
- Remover: Remover um **alimento** informado, porém é necessário declarar o CPF para que só seja removido se pertencer ao **colaborador**;

## Acesso ao Projeto
Para acessar a documentação do projeto e fazer as interações é necessário navegar através do Swagger-UI por não haver o front-end implementado. Para isso é só acessar esse [link](https://desafio-cafe-da-manha.herokuapp.com/swagger-ui.html)

## Utilização do Sistema
Por causa da falta do Front-End a utilização é pouco amigável, sendo necessário o uso da documentação Swagger para informar os campos e fazer as inserções;

## Abrir e Rodar
Para abrir o projeto é necessário fazer o pull daqui do [meu repisotório](https://github.com/mateuscbarbosa/desafio-cafe-da-manha) do GitHub e executá-lo na sua IDE de ```Java```.
Alguns pontos precisam ser vistos antes disso:
* O Lombok precisa estar habilitado na IDE;
* As dependencias do Maven precisam ser baixadas automático ao tentar rodar o projeto;
* O banco de dados MySql precisa estar instalado;
* O banco ***cafedamanha*** precisa ser criado;

## Tecnologias Usadas
* `Java`
* `SpringBoot`
* `SpringMVC`
* `Maven`
* `MySql`
* `FlyWay`
* `ModemMapper`
* `Swagger`
* `Lombok`

## Autor
[Mateus C Barbosa](https://github.com/mateuscbarbosa)
