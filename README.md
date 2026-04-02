# 📦 Sistema de Doações - API REST

API desenvolvida com Spring Boot para gerenciamento de doações, permitindo cadastro de usuários, controle de itens e geração de relatórios.

---

## 🚀 Tecnologias utilizadas

- Java 17+
- Spring Boot
- Spring Security
- JWT (JSON Web Token)
- JPA / Hibernate
- MySql (ou H2)
- Swagger (OpenAPI)
- Lombok

---

## 📌 Funcionalidades

- ✅ Cadastro e login de usuários
- ✅ Controle de permissões (ADMIN / DOADOR)
- ✅ Cadastro de itens para doação
- ✅ Registro de doações com múltiplos itens
- ✅ Atualização automática de estoque
- ✅ Consulta de doadores e doações
- ✅ Geração de relatórios:
  - 📄 CSV
  - 📄 PDF

---

## 🏗️ Arquitetura

O projeto segue uma arquitetura em camadas:

- **Controller** → Entrada da API (requisições HTTP)
- **Service** → Regras de negócio
- **Repository** → Acesso ao banco de dados
- **DTOs** → Comunicação entre camadas

📊 Diagrama do sistema:

![Diagrama](./docs/diagrama.png)

---

## 🔐 Autenticação

A API utiliza **JWT (JSON Web Token)**.

### Fluxo:
1. Usuário faz login
2. Recebe um token JWT
3. Envia o token no header:


---

📡 Principais endpoints
🔑 Autenticação
POST /auth/register
POST /auth/login
📦 Itens
GET /itens
POST /itens (ADMIN)
🤝 Doações
POST /doacoes
GET /doacoes (ADMIN)
📊 Relatórios
GET /relatorios/mensal
GET /relatorios/mensal/csv
GET /relatorios/mensal/pdf
🧪 Testes da API

Você pode testar usando:

Swagger:
http://localhost:8080/swagger-ui.html
Postman

📸 Exemplo:

📈 Regras de negócio
Não permite itens duplicados na mesma doação
Quantidade deve ser maior que 0
Atualiza estoque automaticamente ao doar
Apenas ADMIN pode ver relatórios
👨‍💻 Autor

Desenvolvido por Felipe Movio

## ⚙️ Como rodar o projeto

```bash
# Clonar repositório
git clone https://github.com/seu-usuario/seu-repo.git

# Entrar na pasta
cd seu-repo

# Rodar aplicação
./mvnw spring-boot:run
