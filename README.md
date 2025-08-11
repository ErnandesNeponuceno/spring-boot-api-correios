# 📦 API Correios - Rastreamento de Objetos

O **API Correios** é um serviço desenvolvido em **Java** com **Spring Boot**, que permite realizar o **rastreamento de objetos** através da **API oficial dos Correios**.  
O sistema implementa autenticação via **Token Bearer**, obtido automaticamente a partir das credenciais e dados de contrato fornecidos no `application.properties`.

---


## 📢 Observação Importante

Para utilizar a **API restrita dos Correios**, é necessário possuir um **contrato ativo** com os Correios. As credenciais de acesso (usuário, código de acesso, número de contrato, cartão de postagem e DR) devem ser fornecidas pelo próprio Correios mediante contrato.

---

## 🛠️ Funcionalidades

- **Rastreamento de Objetos**:
    - Endpoint REST para consultar o status de um objeto pelos Correios.
    - Retorna os dados diretamente da API oficial no formato JSON.

- **Autenticação Automática**:
    - Geração de token de acesso através de credenciais fornecidas nas configurações.
    - Renovação automática do token quando estiver próximo da expiração.

- **Configuração Centralizada**:
    - Utilização de `@ConfigurationProperties` para carregar parâmetros como URL base, usuário, código de acesso, contrato e cartão de postagem.

---

## 📁 Estrutura do Projeto

```plaintext
correios/
├── src/main/java/api/correios/
│   ├── config/
│   │   └── CorreiosConfigProperties.java   # Carrega as configurações do application.properties
│   ├── controller/
│   │   └── CorreiosController.java         # Endpoint REST para rastreamento
│   ├── dto/
│   │   └── TokenResponseDTO.java           # Representa a resposta da API de autenticação
│   ├── service/
│   │   ├── CorreiosService.java            # Lógica de rastreamento e uso do token
│   │   └── TokenService.java               # Serviço para solicitar novo token
│   └── CorreiosApplication.java            # Classe principal do Spring Boot
│
├── src/main/resources/
│   └── application.properties              # Configurações do projeto
│
└── pom.xml                                 # Dependências Maven
```

---

## 💻 Tecnologias Utilizadas

<div style="display: inline_block">
  <img alt="Java" src="https://img.shields.io/badge/Java-21-007396?style=for-the-badge&logo=java&logoColor=white">
  <img alt="Spring Boot" src="https://img.shields.io/badge/Spring_Boot-3.x-6DB33F?style=for-the-badge&logo=springboot&logoColor=white">
  <img alt="Maven" src="https://img.shields.io/badge/Maven-3.x-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white">
</div>

---

## 🚀 Como Rodar o Projeto?

### ✅ Pré-requisitos
- Java 21+
- Maven 3.9+
- Conta e credenciais válidas na API dos Correios.

### ▶️ Passos
1. Clone o repositório:
   ```bash
   git clone https://github.com/ErnandesNeponuceno/spring-boot-api-correios.git
   ```

2. Configure o `application.properties`:
   ```properties
   correios.url=https://api.correios.com.br
   correios.usuario=SEU_USUARIO
   correios.codigoAcesso=SEU_CODIGO_ACESSO
   correios.cartaoPostagem=NUMERO_CARTAO
   correios.contrato.numero=NUMERO_CONTRATO
   correios.contrato.dr=10
   ```

3. Compile e execute o projeto:
   ```bash
   mvn spring-boot:run
   ```

4. Acesse o endpoint de rastreamento:
   ```
   GET http://localhost:8080/api/correios/rastreio/{CODIGO_RASTREIO}
   ```

---

## 📌 Endpoints

| Método | Endpoint                              | Descrição                                |
|--------|---------------------------------------|------------------------------------------|
| GET    | `/api/correios/rastreio/{codigo}`     | Retorna informações de rastreamento      |

---

## ✨ Créditos

- API oficial dos **Correios**.
- Desenvolvido para fins de integração e estudos com **Spring Boot**.

---

## 📜 Licença

Este projeto é de uso livre para estudos e aprendizado, sem fins comerciais.
