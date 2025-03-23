# 📌 Plataforma de Gestão de Projetos - Microsoft

Este projeto foi desenvolvido como parte do **Desafio Técnico para Desenvolvedores** da Microsoft. O objetivo é criar uma plataforma moderna de **gestão de projetos** e **acompanhamento de times** utilizando **Java + Spring Boot** no backend e **Vue.js** no frontend.

## 🚀 Tecnologias Utilizadas

### **Backend**
- **Java 17**
- **Spring Boot** (Spring Web, Spring Data JPA, Spring Security)
- **Hibernate** (ORM para PostgreSQL)
- **JUnit 5 + Mockito** (Testes unitários)
- **Swagger** (Documentação da API)
- **Jakarta Validation** (Bean Validation)

### **Banco de Dados**
- **PostgreSQL**

## 📂 Funcionalidades Implementadas
✅ **CRUD 'Customers'**
✅ **CRUD 'Tasks'**
✅ **Relacionamento customer ↔ Projeto ↔ Atividade**  
✅ **Listagem de projetos em aberto**  
✅ **Autenticação JWT**  
✅ **Testes unitários**  
✅ **Swagger para documentação da API (http://localhost:8080/swagger-ui.html)**

## ⚙️ Como Rodar o Projeto
🧪 Rodando os Testes
Testes Unitários (JUnit + Mockito)
```sh
mvn test
```

##
🧪 Cobertura de Código
```sh
mvn verify
```

### **1️⃣ Clonar o Repositório**
```sh
git clone https://github.com/Lucas-Caldas/manage-project.git
```
### **🏗️ Modelo de Dados**
**📌 Diagrama de Classes**
```sh
+----------------+       +----------------+       +----------------+
|    customer     |       |    Project     |       |   Task    |
+----------------+       +----------------+       +----------------+
| - id: Long     |       | - id: Long     |       | - id: Long     |
| - name: String |       | - name: String |       | - name: String |
| - email: String |       | - description:   |       | - description:   |
| - telephone:    |       |   String       |       |   String       |
|   String       |       | - status:      |       | - status:      |
+----------------+       |   String       |       |   String       |
                         | - customer:     |       | - project:     |
                         |   Customer      |       |   Project      |
                         +----------------+       +----------------+
                             |                         |
                             |                         |
                             | 1                       | 1
                             | *                       | *
                             |                         |
                         +----------------+       +----------------+
                         |  1 Customer     |<---->|  * Projects    |
                         +----------------+       +----------------+
                             |                         |
                             |                         |
                             | 1                       | *
                             | *                       | *
                             |                         |
                         +----------------+       +----------------+
                         |  1 Project     |<---->|  * Task  |
                         +----------------+       +----------------+

```
### 📌 Autor
**Desenvolvido por Lucas Caldas de Oliva Rodrigues**

📧 caldas.oliva@gmail.com

🔗 [Linkedin]: (https://www.linkedin.com/in/lucas-caldas-69869094/)