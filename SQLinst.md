# README Revisado e Otimizado
## Instructions SQL Queries

## **Criação do Banco de Dados**

### **Criação de Usuário no PostgreSQL**
    CREATE ROLE "cineGodness" WITH 
    LOGIN NOSUPERUSER CREATEDB CREATEROLE INHERIT
    NOREPLICATION NOBYPASSRLS
    CONNECTION LIMIT -1
    PASSWORD 'adaoeva11';
    COMMENT ON ROLE "cineGodness" IS 'usuario da aplicação';

## Permisão

### Alterando permisão para tabelas existentes
    "DO $$
        DECLARE
        table_name RECORD;
        BEGIN
        FOR table_name IN
        SELECT tablename
        FROM pg_tables
        WHERE schemaname = 'public'
        LOOP
        EXECUTE format('ALTER TABLE public.%I OWNER TO "cineGodness";', table_name.tablename);
        END LOOP;
        END $$;"

### Alterando permisão para futuras tabelas criadas
    "ALTER SCHEMA public OWNER TO "cineGodness";
    ALTER DEFAULT PRIVILEGES IN SCHEMA public
    GRANT ALL ON TABLES TO "cineGodness";"

### Alterando Permisão de tabela em tabela
    * "ALTER TABLE IF EXISTS filmes
    OWNER TO "cineGodness";"


### Criação das tabelas no banco de dados
### Usuario:

    CREATE TABLE Usuario (
        id_usuario SERIAL PRIMARY KEY,
        nome VARCHAR(100) NOT NULL,
        email VARCHAR(100) UNIQUE NOT NULL,
        senha VARCHAR(255) NOT NULL,
        ativo BOOLEAN NOT NULL DEFAULT TRUE
    );

### Favorito:

    CREATE TYPE status_favorito AS ENUM ('Assistido', 'Assistindo', 'Quero_Assistir');

    CREATE TABLE Favorito (
        id_favorito SERIAL PRIMARY KEY,
        id_usuario INT NOT NULL,
        status status_favorito,
        avaliacao INT CHECK (avaliacao BETWEEN 0 AND 5),
        id_filme VARCHAR(255) NOT NULL,
        genero VARCHAR(255) NOT NULL,
        CONSTRAINT fk_usuario_favorito FOREIGN KEY (id_usuario) REFERENCES Usuario (id_usuario)
    );

### Emoção:

    CREATE TABLE Emocoes (
        id_emocao SERIAL PRIMARY KEY,
        nome VARCHAR(50) NOT NULL,
        genre TEXT,
        image TEXT
    );