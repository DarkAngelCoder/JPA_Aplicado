# Sistema de Gerenciamento de Cursos

Este projeto é uma aplicação Spring Boot com persistência de dados via JDBC e PostgreSQL, utilizando Docker para facilitar a execução local. Ele permite o cadastro, listagem, filtragem e remoção de cursos e instrutores por meio de uma API REST.

---

## 🐳 Instruções para iniciar com Docker

1. Certifique-se de ter o Docker instalado e em execução.
2. No terminal, execute:

```bash
docker-compose up -d
```

Isso irá subir um container com PostgreSQL na porta `5432`.

---

## 🚀 Iniciando o projeto Spring Boot

Com o Docker em execução:

1. No IntelliJ ou sua IDE de preferência, execute a aplicação `SpringBootApplication`.
2. O backend estará disponível em: [http://localhost:8081](http://localhost:8081)

---

## 📮 Testando os endpoints com Postman

### 🎓 Cursos

- **Criar curso**
    - `POST /cursos`
    - Body (JSON):
      ```json
      {
        "titulo": "Java Básico",
        "duracaoHoras": 40,
        "instrutorId": 1
      }
      ```

- **Buscar todos os cursos**
    - `GET /cursos`

- **Buscar com filtros**
    - `GET /cursos/filtro?titulo=java&duracaoMin=10&duracaoMax=100&instrutorId=1`

- **Deletar curso**
    - `DELETE /cursos/{id}`

---

### 👨‍🏫 Instrutores

- **Criar instrutor**
    - `POST /instrutores`
    - Body (JSON):
      ```json
      {
        "nome": "Juliana Antusa",
        "especialidade": "Java"
      }
      ```

- **Buscar todos os instrutores**
    - `GET /instrutores`

- **Buscar por ID**
    - `GET /instrutores/{id}`

- **Deletar instrutor**
    - `DELETE /instrutores/{id}`

---

## ✅ Boas práticas aplicadas

- Organização em camadas: `controller`, `repository`, `model`.
- Uso de anotações como `@RestController`, `@RequestMapping`, `@Repository`.
- Injeção de dependência via construtor.
- Filtros dinâmicos com `StringBuilder` no JDBC.
- Separação de responsabilidades para facilitar manutenção e testes.

---

## 🔄 JDBC vs JPA (Resumo comparativo)

| Característica     | JDBC                                      | JPA                                             |
|--------------------|-------------------------------------------|-------------------------------------------------|
| Nível de controle  | Muito alto (escreve SQL manualmente)      | Mais abstrato (usa anotações e entidades)       |
| Performance        | Alta performance com SQL otimizado        | Pode ter overhead com geração automática de SQL |
| Curva de aprendizado | Mais simples para quem já conhece SQL   | Mais complexa, porém mais produtiva a longo prazo |
| Flexibilidade      | Total controle sobre consultas             | Abstrai muito, mas permite uso de JPQL e native queries |
| Manutenção         | Pode ser mais difícil em sistemas grandes | Mais organizado com mapeamento objeto-relacional |

---

## 📁 Estrutura base do projeto

```
├── controller
│   ├── CursoController.java
│   └── InstrutorController.java
├── model
│   ├── Curso.java
│   └── Instrutor.java
├── repository
│   ├── CursoRepository.java
│   └── InstrutorRepository.java
├── application.properties
├── docker-compose.yml
└── README.md
```

---

## 👩‍💻 Desenvolvido por

Juliana Antusa – Projeto de estudo com Spring Boot, Docker e JDBC.
