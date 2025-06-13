# Refatoração e Melhoria da API Todolist

## Descrição
Foram realizadas diversas melhorias e refatorações no projeto, visando padronização, segurança, clareza e melhor manutenção do código. Esta issue documenta as principais mudanças e serve como referência para futuras revisões.

## Principais Alterações
- Reescrita e detalhamento do README.md, incluindo exemplos de uso, estrutura de pastas, instruções de execução e testes.
- Introdução de DTOs para tasks e users, padronizando entrada e saída de dados.
- Refatoração dos controllers para uso de DTOs e responses padronizadas.
- Criação das camadas de serviço (`TaskService` e `UserService`) para centralizar regras de negócio e validações.
- Atualização dos repositórios para uso de `Optional` e melhor tratamento de ausência de dados.
- Ajustes nas entidades para padronização de nomes e uso correto de anotações JPA/Lombok.
- Implementação do filtro `TaskAuthFilter` para autenticação Basic Auth, com tratamento de erros e integração com o repositório de usuários.
- Remoção de código antigo e duplicado, como o filtro `FilterTaskAuth.java`.
- Validações aprimoradas nos DTOs e services, incluindo validação de datas e unicidade de usuário.
- Tratamento global de exceções com mensagens amigáveis.
- Atualização do `pom.xml` para dependências de validação, actuator e Lombok.

## Justificativa Técnica
- Adoção de DTOs melhora a segurança e desacoplamento da API.
- Service layer centraliza regras de negócio, facilitando manutenção e testes.
- Filtro de autenticação garante segurança e padronização.
- Documentação detalhada facilita onboarding e uso do projeto.

## Próximos Passos
- Avaliar cobertura de testes automatizados.
- Considerar implementação de autenticação JWT para maior segurança.
- Melhorar mensagens de erro e internacionalização.

---

_Para dúvidas ou sugestões, comentar nesta issue._
