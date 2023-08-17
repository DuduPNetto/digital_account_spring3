# Digital Account

Projeto feito como base para um aplicativo de conta digital.

Para este projeto foram utilizados a linguagem Java e o framework Springboot3. <br>
É necessária conexão com o banco de dados MySQL.

É possível buscar, cadastrar, atualizar e deletar usuários por meio de requisições. <br>
Também é possível realizar transações entre usuários, por meio de uma requisição passando o id do pagador, recebedor e a quantidade.

# Cadastro / Update / Transaction

Para cadastrar e atualizar um usuário é necessário informar os dados por meio de um JSON. <br>
O tipo do usuário pode ser "Simple" ou "Merchant" para lojistas. <br>

Exemplo: <br>
{ <br>
    "name": "Name", <br>
    "cpf": "111.111.111-11", <br>
    "email": "email@email.com", <br>
    "password": "12345678", <br>
    "type": "Merchant" <br>
} <br>

Para realizar uma transação é necessário informar também os dados por meio de um JSON. <br>
Os usuário do tipo "Merchant" só podem receber, não podem efetuar transferências. <br>

Exemplo: <br>
{ <br>
	"payerId": 1, <br>
	"receiverId": 2, <br>
	"amount": 50.0 <br>
} <br>

# URLs para requisições

GET http://localhost:8080/user/ -> Busca todos os usuários. <br>
GET http://localhost:8080/user/3 -> Busca o usuário de id 3. <br>

POST http://localhost:8080/user/ -> Registra um novo usuário. <br>

PUT http://localhost:8080/user/3 -> Atualiza o usuário de id 3. <br>

DELETE http://localhost:8080/user/3 -> Deleta o usuário de id 3. <br>

POST http://localhost:8080/transaction/ -> Efetua uma transação. <br>