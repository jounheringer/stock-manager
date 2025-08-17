# Controle de Estoque Web

## 📄 Descrição do Projeto

Este projeto consiste em uma aplicação **web** para o **controle de estoque**, desenvolvida para gerenciar produtos, usuários e pagamentos de forma eficiente.
A aplicação permite:

* **Cadastro e listagem de produtos e usuários**
* **Processamento de pagamentos**, garantindo que apenas produtos válidos e com quantidade disponível sejam processados
* **Registro e listagem de pagamentos realizados**

---

## ✅ Funcionalidades Principais

* **Cadastro de Produtos**: Informações como código, descrição, data de entrada, data de validade e quantidade em estoque.
* **Cadastro de Usuários**: Nome e e-mail.
* **Processamento de Pagamentos**:

  * Seleção de usuário e produto
  * Validação de validade e quantidade em estoque
  * Baixa automática no estoque
  * Registro do pagamento
* **Listagem de Pagamentos**: Detalhes incluindo produto, quantidade, data de entrega e usuário responsável.

---

## 📏 Regras de Negócio

* Não é permitido realizar pagamentos para **produtos fora da validade**.
* A quantidade no pagamento não pode **exceder a quantidade disponível em estoque**.
* Todos os campos obrigatórios são validados para **garantir a integridade dos dados**.

---

## 🛠 Tecnologias Utilizadas

### Backend

* **Java 8**
* **JSF (JavaServer Faces)**
* **PrimeFaces**

### Banco de Dados

* **PostgreSQL**

### Orquestração/Containerização

* **Docker**
* **Docker Compose**

---

## ▶️ Como Rodar o Projeto

### ✅ Pré-requisitos

Certifique-se de ter **Docker** e **Docker Compose** instalados em seu sistema.

### 1. Clonar o Repositório

```bash
git clone <URL_DO_SEU_REPOSITORIO>
cd <NOME_DA_PASTA_DO_PROJETO>
```

### 2. Configurar e Iniciar os Contêineres Docker

O arquivo `docker-compose.yml` na raiz do projeto contém:

* Um contêiner **PostgreSQL** (banco de dados)
* Um contêiner **Tomcat 8.5** (servidor de aplicação)

Execute:

```bash
docker-compose up --build -d
```

---

### 3. Acessar o Banco de Dados e Carregar Dados Iniciais

* Os scripts SQL para inicialização estão em **`/src/main/resources/META-INF/sql`**.
* O script **`init-data.sql`** é um exemplo de dados que o usuario pode usar, sua implementação deve ser manual via postgres:

  * Usuários
  * Produtos
  * Pagamentos

---

### 4. Acessar a Aplicação Web

Após o Tomcat iniciar:

```
http://localhost:8080/
```

Para verificar se a aplicação foi implantada com sucesso, confira os logs do contêiner Tomcat.
