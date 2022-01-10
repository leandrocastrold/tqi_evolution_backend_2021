# Desafio *TQI Evolution* - TQI BANK
<hr/>
<br/>  
<img src="demo-tqi.gif" alt="" width=700 height=600>

## Sobre o Sistema
O sistema roda em CLI, com menus interativos.

## MySQL com Docker Compose

Para persistência de dados, foi utilizado o banco de dados **MySQL**, em um container **Docker** iniciado por um arquivo *docker-compose.yml*. Essa abordagem foi escolhida por já ter sido utilizada em alguns outros projetos do meu portfolio.

## Validação e Testes Unitários

Inicialmente, tentei usar o *Bean Validation*, mas ocorreram alguns problemas. Então decidi fazer um *validator* customizado, que checa os formulários, tanto de clientes como os dos empréstimos. Foram usando algumas expressões regulares para checagem de CPF e RG.
Nessa etapa, os testes unitários foram cruciais.

## Encriptação de senha (BCrypt)
A primeira versão do login tinha um método simples com checagem de strings, mas fiquei incomodado pelo fato da senha ficar aberta na tabela do banco de dados. Então o usei o encriptador *BCrypt*, da dependência *Spring Security*.  A senha passa por uma validação de números mínimos de caracteres, depois é encriptada e só então salva no DB. O *Bcrypt* se encarrega da parte de login.


## Execução
Para executar o projeto:
`docker compose up`  para baixar as dependência e rodar o MySQL no Docker. Após subir o conteiner, é só dar play no projeto.
### Se preferir: 
Gerar arquivo *.jar*: `mvn clean package`.

Rodar a applicação pelo *jar*:
`java -jar target/[NOME_ARQUIVO]` 

## Notas

A versão original desse projeto utilizava Kafka, na parte de Cadastro de clientes. Infelizmente, por problemas técnicos, não foi possível completar essa versão.






