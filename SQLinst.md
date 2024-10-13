# Instructions SQL Querys
## Querys para criação do banco 
### CREATE usuario para banco de dados PostgreSQL
    * "CREATE ROLE "cineGodness" WITH 
       LOGIN NOSUPERUSER CREATEDB CREATEROLE INHERIT
       NOREPLICATION
       NOBYPASSRLS
       CONNECTION LIMIT -1
       PASSWORD 'adaoeva11';
       COMMENT ON ROLE "cineGodness" IS 'usuario da aplicação';"

### CREATE de database
    * "CREATE DATABASE "CineFilmes"
       WITH
       OWNER = "cineGodness"
       ENCODING = 'UTF8'
       LOCALE_PROVIDER = 'libc'
       CONNECTION LIMIT = -1
       IS_TEMPLATE = False;"

### Alterando Permisão
    * "ALTER TABLE IF EXISTS filmes
    OWNER TO "cineGodness";"

### Criação das tabelas no banco de dados
## CREATE  de filmes (teste para verificar a conexão com base de dados)
    * "CREATE TABLE FILMES (
       id_filme SERIAL PRIMARY KEY,
       titulo VARCHAR(255) NOT NULL,
       diretor VARCHAR(255),
       tipo VARCHAR(100),
       genero VARCHAR(100),
       ano_lancamento INTEGER CHECK (ano_lancamento >= 1000 AND ano_lancamento <= 9999),
       sinopse TEXT,
       duracao VARCHAR(50),
       classificacao_indicativa VARCHAR(50)
       );"

## Tabela Usuario
        "CREATE TABLE Usuario(
        id_usuario SERIAL PRIMARY KEY,
        nome VARCHAR(100) NOT NULL,
        email VARCHAR(100) UNIQUE NOT NULL,
        senha VARCHAR(255) NOT NULL,
        data_nacimento DATE NOT NULL
        );"

## Tabela Favorito

## Tabela Emoção

## Tabela Lista de Filme

## Tabela Recomendação



### INSERT de filmes (teste para verificar a conexão com base de dados)
    * "INSERT INTO filmes 
       (titulo, diretor, tipo, genero, ano_lancamento, sinopse, duracao, classificacao_indicativa) 
       VALUES (
       'O Poderoso Chefão',
       'Francis Ford Coppola',
       'Filme',
       'Drama/Crime',
       1972,
       'A história da ascensão e queda da família Corleone, uma das mais poderosas famílias da máfia.',
       '2h 55m',
       '18 anos'
       );"

