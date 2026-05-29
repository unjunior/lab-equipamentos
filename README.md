# Lab — Sistema de Controle de Equipamentos

API REST para controle de equipamentos de clientes em laboratório de manutenção. Permite gerenciar clientes, equipamentos e rastrear o status de cada equipamento (no laboratório ou devolvido).

---

## Tecnologias

- Java 21
- Spring Boot 4.0
- Spring Data JPA
- Spring Security + JWT
- PostgreSQL
- H2 (ambiente de desenvolvimento)
- Maven

---

## Modelo de Domínio

```
Cliente (1) ──────── (0..N) Equipamento
```

- Um cliente pode ter zero ou vários equipamentos no laboratório
- Cada equipamento pertence a um cliente
- Equipamentos têm status rastreável: `NO_LABORATORIO` ou `DEVOLVIDO`

---

## Estrutura do Projeto

```
src/main/java/br/com/empresa/lab/
    ├── controller/
    │   ├── ClienteController.java
    │   └── EquipamentoController.java
    ├── service/
    │   ├── ClienteService.java
    │   └── EquipamentoService.java
    ├── repository/
    │   ├── ClienteRepository.java
    │   └── EquipamentoRepository.java
    ├── entidades/
    │   ├── Cliente.java
    │   └── Equipamento.java
    ├── dto/
    │   ├── ClienteDTO.java
    │   └── EquipamentoDTO.java
    └── enums/
        ├── Tipo.java
        └── StatusEquipamento.java
```

---

## Configuração

### `application.properties` — PostgreSQL

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/lab
spring.datasource.username=postgres
spring.datasource.password=suasenha

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

### `application.properties` — H2 (desenvolvimento)

```properties
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driver-class-name=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=

spring.jpa.hibernate.ddl-auto=create-drop
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console
```

---

## Rotas da API

### Cliente

| Método | Rota | Descrição |
|--------|------|-----------|
| GET | `/cliente` | Lista todos os clientes com equipamentos |
| GET | `/cliente/{id}` | Busca cliente por ID |
| GET | `/cliente/{id}/equipamentos` | Lista equipamentos de um cliente |
| POST | `/cliente` | Cadastra novo cliente |
| PUT | `/cliente/{id}` | Atualiza dados do cliente |
| DELETE | `/cliente/{id}` | Remove cliente |

### Equipamento

| Método | Rota | Descrição |
|--------|------|-----------|
| GET | `/equipamentos` | Lista todos os equipamentos |
| GET | `/equipamentos?statusEquipamento=NO_LABORATORIO` | Filtra por status |
| GET | `/equipamentos/{id}` | Busca equipamento por ID |
| GET | `/equipamentos/tipo/{tipo}` | Filtra por tipo |
| POST | `/cliente/{id}/equipamento` | Cadastra equipamento para um cliente |
| PUT | `/equipamentos/{id}` | Atualiza dados do equipamento |
| PATCH | `/equipamentos/{id}/entrada` | Registra entrada no laboratório |
| PATCH | `/equipamentos/{id}/saida` | Registra saída (devolução) |
| DELETE | `/equipamentos/{id}` | Remove equipamento |

---

## Enums

### `Tipo`
```
DESKTOP | NOTEBOOK | IMPRESSORA | SERVIDOR
```

### `StatusEquipamento`
```
NO_LABORATORIO | DEVOLVIDO
```

---

## Exemplos de Requisições

### Listar todos os clientes
```
GET /cliente?page=0&size=10
```

### Buscar equipamentos no laboratório
```
GET /equipamentos?statusEquipamento=NO_LABORATORIO
```

### Filtrar por tipo
```
GET /equipamentos/tipo/NOTEBOOK
```

### Listar equipamentos de um cliente
```
GET /cliente/1/equipamentos
```

---

## Observações

- Equipamentos sem código recebem `null` — o código é atribuído na primeira entrada no laboratório
- Equipamentos nunca são deletados permanentemente — o status é alterado para `DEVOLVIDO` para manter histórico
- Todas as listagens suportam paginação via `Pageable`
