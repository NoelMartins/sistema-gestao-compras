# Sistema de Gestão de Compras

Um sistema completo para gerenciamento de compras desenvolvido em Java com Spring Boot, oferecendo uma API RESTful para automatizar e otimizar o processo de aquisição de produtos e serviços.

## 📋 Funcionalidades

- **Gestão de Fornecedores**: Cadastro, consulta, atualização e remoção de fornecedores
- **Gestão de Produtos/Serviços**: Controle completo do catálogo de produtos e serviços
- **Pedidos de Compra**: Criação e acompanhamento de pedidos com diferentes status
- **Sistema de Cotações**: Registro e comparação de cotações de diferentes fornecedores
- **Relatórios Gerenciais**: Relatórios de pedidos, produtos mais comprados e desempenho de fornecedores
- **API RESTful**: Interface padronizada para integração com sistemas externos

## 🚀 Tecnologias Utilizadas

- **Java 17**
- **Spring Boot 3.2.0**
- **Spring Data JPA**
- **Spring Security**
- **MySQL 8.0**
- **Maven 3.6+**

## 📦 Pré-requisitos

Antes de executar o projeto, certifique-se de ter instalado:

- Java 17 ou superior
- Maven 3.6 ou superior
- MySQL 8.0 ou superior
- Git

## 🔧 Instalação e Configuração

### 1. Clone o repositório

```bash
git clone https://github.com/seu-usuario/sistema-gestao-compras.git
cd sistema-gestao-compras
```

### 2. Configure o banco de dados MySQL

Crie um banco de dados MySQL:

```sql
CREATE DATABASE gestao_compras;
```

### 3. Configure as propriedades da aplicação

Edite o arquivo `src/main/resources/application.properties` se necessário:

```properties
# Configurações do Banco de Dados
spring.datasource.url=jdbc:mysql://localhost:3306/gestao_compras?createDatabaseIfNotExist=true&useSSL=false&allowPublicKeyRetrieval=true
spring.datasource.username=root
spring.datasource.password=password

# Configurações do servidor
server.port=8080
server.address=0.0.0.0
```

### 4. Execute a aplicação

```bash
# Compile o projeto
mvn clean compile

# Execute a aplicação
mvn spring-boot:run
```

Ou compile e execute o JAR:

```bash
# Gere o JAR
mvn clean package

# Execute o JAR
java -jar target/sistema-gestao-compras-1.0-SNAPSHOT.jar
```

### 5. Verifique se a aplicação está funcionando

Acesse: `http://localhost:8080/api/fornecedores`

Se retornar uma lista vazia `[]`, a aplicação está funcionando corretamente.

## 📚 Uso da API

### Exemplos de Requisições

#### Cadastrar um fornecedor

```bash
curl -X POST http://localhost:8080/api/fornecedores \
  -H "Content-Type: application/json" \
  -d '{
    "nome": "Fornecedor Exemplo Ltda",
    "contato": "contato@exemplo.com",
    "endereco": "Rua das Flores, 123, São Paulo, SP",
    "informacoesBancarias": "Banco: 001, Agência: 1234, Conta: 56789-0"
  }'
```

#### Cadastrar um produto

```bash
curl -X POST http://localhost:8080/api/produtos-servicos \
  -H "Content-Type: application/json" \
  -d '{
    "nome": "Notebook Dell Inspiron",
    "descricao": "Notebook para uso corporativo",
    "unidadeMedida": "unidade",
    "precoReferencia": 2500.00
  }'
```

#### Criar um pedido de compra

```bash
curl -X POST "http://localhost:8080/api/pedidos-compra?fornecedorId=1" \
  -H "Content-Type: application/json" \
  -d '[
    {
      "produtoServico": {"id": 1},
      "quantidade": 5,
      "precoNegociado": 2400.00
    }
  ]'
```

#### Registrar uma cotação

```bash
curl -X POST "http://localhost:8080/api/cotacoes?produtoId=1&fornecedorId=1" \
  -H "Content-Type: application/json" \
  -d '{
    "preco": 2350.00
  }'
```

## 📊 Estrutura do Projeto

```
src/
├── main/
│   ├── java/com/gestaocompras/
│   │   ├── config/          # Configurações (CORS, Security)
│   │   ├── controller/      # Controladores REST
│   │   ├── model/          # Entidades JPA
│   │   ├── repository/     # Repositórios de dados
│   │   ├── service/        # Lógica de negócio
│   │   └── SistemaGestaoComprasApplication.java
│   └── resources/
│       └── application.properties
└── test/                   # Testes unitários
```

## 🗄️ Modelo de Dados

O sistema possui as seguintes entidades principais:

- **Fornecedor**: Dados dos fornecedores
- **ProdutoServico**: Catálogo de produtos e serviços
- **PedidoCompra**: Pedidos de compra realizados
- **ItemPedido**: Itens de cada pedido
- **Cotacao**: Cotações de preços dos fornecedores

## 📈 Relatórios Disponíveis

1. **Pedidos por Período**: Lista pedidos em um intervalo de datas
2. **Produtos Mais Comprados**: Ranking dos produtos/serviços mais solicitados
3. **Desempenho de Fornecedores**: Estatísticas de pedidos por fornecedor
4. **Estatísticas Gerais**: Visão geral do sistema

## 🔒 Segurança

A aplicação está configurada com Spring Security, mas atualmente permite acesso sem autenticação para facilitar o desenvolvimento. Para produção, recomenda-se:

- Implementar autenticação JWT
- Configurar roles e permissões
- Habilitar HTTPS
- Configurar rate limiting

## 🧪 Testes

Execute os testes unitários:

```bash
mvn test
```

## 📝 Documentação da API

Para documentação detalhada dos endpoints, consulte o arquivo [API_DOCUMENTATION.md](API_DOCUMENTATION.md).

## 🤝 Contribuição

1. Faça um fork do projeto
2. Crie uma branch para sua feature (`git checkout -b feature/AmazingFeature`)
3. Commit suas mudanças (`git commit -m 'Add some AmazingFeature'`)
4. Push para a branch (`git push origin feature/AmazingFeature`)
5. Abra um Pull Request

## 📄 Licença

Este projeto está sob a licença MIT. Veja o arquivo [LICENSE](LICENSE) para mais detalhes.

## 📞 Suporte

Para dúvidas ou suporte, entre em contato através de:

- Email: juniornoel351@gmail.com
- Issues do GitHub: [https://github.com/seu-usuario/sistema-gestao-compras/issues](https://github.com/sNoelMartins/sistema-gestao-compras/issues)

## 🚀 Próximos Passos

- [ ] Interface web (frontend)
- [ ] Autenticação JWT
- [ ] Notificações por email
- [ ] Integração com sistemas ERP
- [ ] Workflow de aprovação
- [ ] Dashboard com gráficos
- [ ] API de relatórios em PDF
- [ ] Testes de integração

---

**Desenvolvido com ❤️ usando Spring Boot**

