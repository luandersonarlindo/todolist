# Todolist

API de Gerenciamento de Tarefas (ToDo List) construída em Java com Spring Boot.

## Sumário
- [Todolist](#todolist)
  - [Sumário](#sumário)
  - [Sobre o Projeto](#sobre-o-projeto)
  - [Funcionalidades](#funcionalidades)
  - [Estrutura de Pastas](#estrutura-de-pastas)
  - [Como Executar](#como-executar)
  - [Coleção Postman](#coleção-postman)
  - [Exemplos de Uso](#exemplos-de-uso)
    - [Cadastro de Usuário](#cadastro-de-usuário)
    - [Cadastro de Tarefa (requer autenticação Basic Auth)](#cadastro-de-tarefa-requer-autenticação-basic-auth)
  - [Testes](#testes)
  - [Detalhes das Pastas e Funcionalidades](#detalhes-das-pastas-e-funcionalidades)
    - [dto/](#dto)
    - [errors/](#errors)
    - [filter/](#filter)
    - [task/](#task)
    - [user/](#user)
    - [utils/](#utils)
  - [Licença](#licença)

---

## Sobre o Projeto
Este projeto é uma API RESTful para gerenciamento de tarefas, permitindo cadastro de usuários, autenticação básica e operações CRUD de tarefas. Desenvolvido como parte de um minicurso gratuito de Java com Spring Boot.

## Funcionalidades
- Cadastro de usuários com validação e senha criptografada
- Autenticação básica (Basic Auth) para acesso às tarefas
- CRUD de tarefas (criar, listar, atualizar, remover)
- Validações de datas e campos obrigatórios
- Tratamento global de exceções

## Estrutura de Pastas
```
src/main/java/br/com/luanderson/todolist/
│
├── dto/                # Objetos de transferência de dados (Request/Response)
│   ├── task/           # DTOs para tarefas
│   └── user/           # DTOs para usuários
│
├── errors/             # Manipulação global de exceções
│   └── ExceptionHandlerController.java
│
├── filter/             # Filtros de autenticação (Basic Auth)
│   └── TaskAuthFilter.java
│
├── task/               # Domínio de tarefas
│   ├── controller/     # Controlador REST de tarefas
│   ├── entity/         # Entidade JPA de tarefas
│   ├── repository/     # Interface de repositório JPA
│   └── service/        # Lógica de negócio de tarefas
│
├── user/               # Domínio de usuários
│   ├── controller/     # Controlador REST de usuários
│   ├── entity/         # Entidade JPA de usuários
│   ├── repository/     # Interface de repositório JPA
│   └── service/        # Lógica de negócio de usuários
│
├── utils/              # Utilitários gerais
│   └── Utils.java
│
└── TodolistApplication.java # Classe principal Spring Boot
```

## Como Executar
1. Clone o repositório
2. Execute o comando:
   ```shell
   ./mvnw spring-boot:run
   ```
   ou no Windows:
   ```shell
   .\mvnw.cmd spring-boot:run
   ```
3. Acesse: http://localhost:8080

O banco de dados H2 é utilizado em memória e pode ser acessado em http://localhost:8080/h2-console

## Coleção Postman
A pasta `postman_collection` contém uma coleção pronta para testar os endpoints da API.

## Exemplos de Uso
### Cadastro de Usuário
`POST /users`
```json
{
  "name": "João Silva",
  "username": "joaosilva",
  "password": "senha123"
}
```

### Cadastro de Tarefa (requer autenticação Basic Auth)
`POST /tasks`
```json
{
  "title": "Estudar Spring Boot",
  "description": "Assistir aulas e praticar exercícios",
  "priority": "Alta",
  "startAt": "2025-06-13T10:00:00",
  "endAt": "2025-06-13T12:00:00"
}
```

## Testes
Para rodar os testes automatizados:
```shell
./mvnw test
```

## Detalhes das Pastas e Funcionalidades
### dto/
- **task/**: Define os dados de entrada e saída das tarefas (ex: `TaskRequest`, `TaskResponse`).
- **user/**: Define os dados de entrada e saída dos usuários (ex: `UserRequest`, `UserResponse`).

### errors/
- **ExceptionHandlerController.java**: Captura exceções globais e retorna mensagens amigáveis ao cliente.

### filter/
- **TaskAuthFilter.java**: Filtro que intercepta requisições para `/tasks` e valida autenticação Basic Auth.

### task/
- **controller/**: Endpoints REST para CRUD de tarefas.
- **entity/**: Entidade JPA que representa a tabela de tarefas.
- **repository/**: Interface para persistência de tarefas.
- **service/**: Lógica de negócio, validações e regras para tarefas.

### user/
- **controller/**: Endpoint REST para cadastro de usuários.
- **entity/**: Entidade JPA que representa a tabela de usuários.
- **repository/**: Interface para persistência de usuários.
- **service/**: Lógica de negócio, validações e regras para usuários.

### utils/
- **Utils.java**: Métodos utilitários para manipulação de propriedades dos objetos.

## Licença
Projeto para fins educacionais.
