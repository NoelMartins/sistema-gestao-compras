# Sistema de Gestão de Compras - API Documentation

## Visão Geral

Esta API RESTful foi desenvolvida para gerenciar o processo de compras de uma organização, desde o cadastro de fornecedores e produtos até a criação de pedidos de compra e geração de relatórios.

## Tecnologias Utilizadas

- **Java 17**
- **Spring Boot 3.2.0**
- **Spring Data JPA**
- **Spring Security**
- **MySQL 8.0**
- **Maven**

## Configuração do Banco de Dados

A aplicação está configurada para usar MySQL. As configurações estão no arquivo `application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/gestao_compras?createDatabaseIfNotExist=true&useSSL=false&allowPublicKeyRetrieval=true
spring.datasource.username=root
spring.datasource.password=password
```

## Endpoints da API

### 1. Fornecedores (`/api/fornecedores`)

#### GET `/api/fornecedores`
Lista todos os fornecedores cadastrados.

**Resposta:**
```json
[
  {
    "id": 1,
    "nome": "Fornecedor ABC Ltda",
    "contato": "contato@fornecedorabc.com",
    "endereco": "Rua das Flores, 123, São Paulo, SP",
    "informacoesBancarias": "Banco: 001, Agência: 1234, Conta: 56789-0"
  }
]
```

#### GET `/api/fornecedores/{id}`
Busca um fornecedor específico pelo ID.

**Parâmetros:**
- `id` (Long): ID do fornecedor

**Resposta:**
```json
{
  "id": 1,
  "nome": "Fornecedor ABC Ltda",
  "contato": "contato@fornecedorabc.com",
  "endereco": "Rua das Flores, 123, São Paulo, SP",
  "informacoesBancarias": "Banco: 001, Agência: 1234, Conta: 56789-0"
}
```

#### GET `/api/fornecedores/buscar?nome={nome}`
Busca fornecedores por nome (busca parcial, case-insensitive).

**Parâmetros:**
- `nome` (String): Nome ou parte do nome do fornecedor

#### POST `/api/fornecedores`
Cadastra um novo fornecedor.

**Corpo da Requisição:**
```json
{
  "nome": "Novo Fornecedor Ltda",
  "contato": "contato@novofornecedor.com",
  "endereco": "Av. Principal, 456, Rio de Janeiro, RJ",
  "informacoesBancarias": "Banco: 237, Agência: 5678, Conta: 12345-6"
}
```

#### PUT `/api/fornecedores/{id}`
Atualiza um fornecedor existente.

**Parâmetros:**
- `id` (Long): ID do fornecedor

**Corpo da Requisição:**
```json
{
  "nome": "Fornecedor Atualizado Ltda",
  "contato": "novo@contato.com",
  "endereco": "Nova Rua, 789, Belo Horizonte, MG",
  "informacoesBancarias": "Banco: 341, Agência: 9876, Conta: 54321-0"
}
```

#### DELETE `/api/fornecedores/{id}`
Remove um fornecedor.

**Parâmetros:**
- `id` (Long): ID do fornecedor

### 2. Produtos/Serviços (`/api/produtos-servicos`)

#### GET `/api/produtos-servicos`
Lista todos os produtos/serviços cadastrados.

**Resposta:**
```json
[
  {
    "id": 1,
    "nome": "Notebook Dell Inspiron",
    "descricao": "Notebook para uso corporativo com 8GB RAM e SSD 256GB",
    "unidadeMedida": "unidade",
    "precoReferencia": 2500.00
  }
]
```

#### POST `/api/produtos-servicos`
Cadastra um novo produto/serviço.

**Corpo da Requisição:**
```json
{
  "nome": "Mouse Óptico USB",
  "descricao": "Mouse óptico com conexão USB para uso em escritório",
  "unidadeMedida": "unidade",
  "precoReferencia": 25.90
}
```

### 3. Pedidos de Compra (`/api/pedidos-compra`)

#### GET `/api/pedidos-compra`
Lista todos os pedidos de compra.

**Resposta:**
```json
[
  {
    "id": 1,
    "fornecedor": {
      "id": 1,
      "nome": "Fornecedor ABC Ltda"
    },
    "dataCriacao": "2025-06-08T10:30:00",
    "status": "PENDENTE"
  }
]
```

#### GET `/api/pedidos-compra/status/{status}`
Busca pedidos por status.

**Parâmetros:**
- `status`: PENDENTE, APROVADO, REJEITADO, CONCLUIDO, CANCELADO

#### POST `/api/pedidos-compra?fornecedorId={fornecedorId}`
Cria um novo pedido de compra.

**Parâmetros:**
- `fornecedorId` (Long): ID do fornecedor

**Corpo da Requisição:**
```json
[
  {
    "produtoServico": {
      "id": 1
    },
    "quantidade": 10,
    "precoNegociado": 2400.00
  },
  {
    "produtoServico": {
      "id": 2
    },
    "quantidade": 5,
    "precoNegociado": 20.00
  }
]
```

#### PUT `/api/pedidos-compra/{id}/status?status={status}`
Atualiza o status de um pedido.

**Parâmetros:**
- `id` (Long): ID do pedido
- `status`: Novo status do pedido

### 4. Cotações (`/api/cotacoes`)

#### GET `/api/cotacoes/produto/{produtoId}/comparar`
Compara cotações de um produto ordenadas por preço.

**Parâmetros:**
- `produtoId` (Long): ID do produto

**Resposta:**
```json
[
  {
    "id": 1,
    "produtoServico": {
      "id": 1,
      "nome": "Notebook Dell Inspiron"
    },
    "fornecedor": {
      "id": 1,
      "nome": "Fornecedor ABC Ltda"
    },
    "preco": 2400.00,
    "dataCotacao": "2025-06-08T09:15:00"
  }
]
```

#### POST `/api/cotacoes?produtoId={produtoId}&fornecedorId={fornecedorId}`
Registra uma nova cotação.

**Parâmetros:**
- `produtoId` (Long): ID do produto
- `fornecedorId` (Long): ID do fornecedor

**Corpo da Requisição:**
```json
{
  "preco": 2350.00
}
```

### 5. Relatórios (`/api/relatorios`)

#### GET `/api/relatorios/pedidos-por-periodo?dataInicio={dataInicio}&dataFim={dataFim}`
Gera relatório de pedidos por período.

**Parâmetros:**
- `dataInicio` (DateTime): Data de início no formato ISO (ex: 2025-06-01T00:00:00)
- `dataFim` (DateTime): Data de fim no formato ISO (ex: 2025-06-30T23:59:59)

#### GET `/api/relatorios/produtos-mais-comprados`
Gera relatório dos produtos/serviços mais comprados.

**Resposta:**
```json
[
  [
    {
      "id": 1,
      "nome": "Notebook Dell Inspiron"
    },
    150
  ]
]
```

#### GET `/api/relatorios/desempenho-fornecedores`
Gera relatório de desempenho dos fornecedores.

**Resposta:**
```json
{
  "pedidosPorFornecedor": {
    "Fornecedor ABC Ltda": 25,
    "Fornecedor XYZ S.A.": 18
  },
  "pedidosConcluidosPorFornecedor": {
    "Fornecedor ABC Ltda": 22,
    "Fornecedor XYZ S.A.": 15
  }
}
```

#### GET `/api/relatorios/estatisticas-gerais`
Gera estatísticas gerais do sistema.

**Resposta:**
```json
{
  "totalPedidos": 43,
  "pedidosPendentes": 8,
  "pedidosAprovados": 12,
  "pedidosConcluidos": 20
}
```

## Códigos de Status HTTP

- `200 OK`: Requisição bem-sucedida
- `201 Created`: Recurso criado com sucesso
- `204 No Content`: Recurso deletado com sucesso
- `400 Bad Request`: Dados inválidos na requisição
- `404 Not Found`: Recurso não encontrado
- `500 Internal Server Error`: Erro interno do servidor

## Validações

A API implementa validações automáticas nos seguintes campos:

- **Nome** (Fornecedor/Produto): Obrigatório, máximo 255 caracteres
- **Preço**: Deve ser maior que zero
- **Quantidade**: Deve ser maior que zero
- **IDs de relacionamento**: Devem existir no banco de dados

## CORS

A API está configurada para aceitar requisições de qualquer origem (`*`) para facilitar o desenvolvimento e integração com frontends.

## Segurança

Atualmente, a API está configurada para permitir acesso sem autenticação para facilitar o desenvolvimento inicial. Para produção, recomenda-se implementar autenticação JWT ou OAuth2.

