# 🛒 Gestão Shop

Sistema desktop de gestão de loja desenvolvido em **Java + Swing** com banco de dados **MySQL**.

---

## ✨ Funcionalidades

- 🔐 Login de gerente com autenticação
- 📦 Cadastro e consulta de produtos
- 👤 Cadastro de usuários
- 🛍️ Registro de vendas
- 🖥️ Interface gráfica com Java Swing

---

## 🛠️ Tecnologias

| Tecnologia | Versão |
|---|---|
| Java | 11+ |
| Swing | (nativo JDK) |
| MySQL | 8.0+ |
| Maven | 3.8+ |
| mysql-connector-java | 8.0.33 |

---

## 🚀 Como rodar

### 1. Pré-requisitos

- Java 11 ou superior instalado
- MySQL 8 rodando localmente
- Maven instalado

### 2. Clone o repositório

```bash
git clone https://github.com/seu-usuario/gestao-shop.git
cd gestao-shop
```

### 3. Configure o banco de dados

```bash
# Acesse o MySQL
mysql -u root -p

# Crie o banco
CREATE DATABASE gestao_shop;
USE gestao_shop;
```

Importe o script SQL (se houver na pasta `/sql`):
```bash
mysql -u root -p gestao_shop < sql/gestao_shop.sql
```

### 4. Configure a conexão

Copie o arquivo de exemplo e ajuste com suas credenciais:

```bash
cp src/gestao_shop/ConexaoMySQL.example.java src/gestao_shop/ConexaoMySQL.java
```

Ou configure via variáveis de ambiente (recomendado):

```bash
export DB_HOST=localhost
export DB_PORT=3306
export DB_NAME=gestao_shop
export DB_USER=root
export DB_PASSWORD=suasenha
```

### 5. Compile e execute

```bash
# Compilar e gerar JAR
mvn clean package

# Executar
java -jar target/gestao-shop.jar
```

---

## 📁 Estrutura do projeto

```
gestao-shop/
├── src/
│   └── gestao_shop/
│       ├── Gestao_Shop.java           # Ponto de entrada
│       ├── ConexaoMySQL.java          # Conexão com banco (não versionado)
│       ├── ConexaoMySQL.example.java  # Exemplo de configuração
│       ├── Banco_Main.java
│       ├── Produto.java
│       ├── Tela_venda.java / .form
│       ├── TelaLogin.java / .form
│       ├── TelaPrincipalGerente.java / .form
│       ├── TelaCadastroUsuario.java / .form
│       └── Consultar_Produto.java / .form
├── sql/                               # Scripts SQL (crie esta pasta)
├── IMG/                               # Recursos de imagem
├── pom.xml                            # Configuração Maven
└── .gitignore
```

---

## ⚙️ Instalando MySQL no Linux

```bash
# Ubuntu / Debian
sudo apt update
sudo apt install mysql-server -y
sudo systemctl start mysql
sudo systemctl enable mysql

# Configurar senha do root
sudo mysql_secure_installation

# Acessar
mysql -u root -p
```

---

## 📝 Licença

Este projeto é de uso pessoal / educacional.

---

*Desenvolvido com ☕ Java*
