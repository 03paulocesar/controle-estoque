# Controle de Estoque

Sistema simples de controle de estoque em Java, integrado com banco de dados MySQL.

## Como rodar o projeto

1. **Instale o MySQL** e crie o banco de dados com as tabelas abaixo (ou use o arquivo `tabelas.sql` se disponível).

2. **Configure a conexão**  
   No arquivo `src/conexao/Conexao.java`, ajuste:
   - nome do banco
   - usuário
   - senha

3. **Compile o projeto**  
   No terminal, dentro da pasta `src`, rode:
   ```
   javac -cp ".;../lib/mysql-connector-j-9.2.0.jar" entidades\Produto.java entidades\Usuario.java entidades\Venda.java repositorios\ProdutoRepository.java repositorios\UsuarioRepository.java repositorios\VendaRepository.java interfaces\IProdutoRepository.java interfaces\IUsuarioRepository.java interfaces\IVendaRepository.java conexao\Conexao.java view\Main.java
   ```

4. **Execute o sistema**
   ```
   java -cp ".;../lib/mysql-connector-j-9.2.0.jar" view.Main
   ```

## Script das tabelas MySQL

```sql
CREATE TABLE usuarios (
    id INT AUTO_INCREMENT PRIMARY KEY,
    login VARCHAR(50) NOT NULL UNIQUE,
    senha VARCHAR(100) NOT NULL
);

CREATE TABLE produtos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    descricao VARCHAR(255),
    quantidade INT DEFAULT 0,
    valor DECIMAL(10,2) NOT NULL
);

CREATE TABLE vendas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_produto INT NOT NULL,
    quantidade INT NOT NULL,
    tipo VARCHAR(10) NOT NULL,
    data DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (id_produto) REFERENCES produtos(id)
);
```

---

## Observações

- O driver JDBC do MySQL (`mysql-connector-j-9.2.0.jar`) deve estar na pasta `lib`.
- O sistema roda via terminal/console.
- Qualquer dúvida, consulte o código ou abra uma issue.
