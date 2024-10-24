# README Revisado e Otimizado
## Instructions SQL Queries

## **Criação do Banco de Dados**

### **1. Criação de Usuário no PostgreSQL**
    CREATE ROLE "cineGodness" WITH 
    LOGIN NOSUPERUSER CREATEDB CREATEROLE INHERIT
    NOREPLICATION NOBYPASSRLS
    CONNECTION LIMIT -1
    PASSWORD 'adaoeva11';
    COMMENT ON ROLE "cineGodness" IS 'usuario da aplicação';

### Alterando Permisão
    * "ALTER TABLE IF EXISTS filmes
    OWNER TO "cineGodness";"


### CREATE de database
    * "CREATE DATABASE "CineFilmes"
       WITH
       OWNER = "cineGodness"
       ENCODING = 'UTF8'
       LOCALE_PROVIDER = 'libc'
       CONNECTION LIMIT = -1
       IS_TEMPLATE = False;"



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
    data_nascimento VARCHAR(10) NOT NULL
    );"

## Tabela Favorito

## Tabela Emoção

## Tabela Lista de Filme
    "CREATE TABLE Lista_Filmes (
    id_lista SERIAL PRIMARY KEY,
    id_usuario INT NOT NULL,
    id_filme INT NOT NULL,
    status VARCHAR(50) NOT NULL, -- Ex.: "Já assistido", "Assistindo", etc.
    avaliacao NUMERIC(2,1), -- Avaliação pode ser em uma escala de 0 a 10, ex.: 8.5
    data_adicao DATE NOT NULL DEFAULT CURRENT_DATE,
    CONSTRAINT fk_usuario_lista FOREIGN KEY (id_usuario) REFERENCES Usuario (id_usuario),
    CONSTRAINT fk_filme_lista FOREIGN KEY (id_filme) REFERENCES Filmes (id_filme)
    );"
## Tabela Recomendação
    "CREATE TABLE Recomendacao (
    id_recomendacao SERIAL PRIMARY KEY,
    id_usuario INT NOT NULL,
    id_filme INT NOT NULL,
    id_emocao INT NOT NULL,
    data_adicao VARCHAR(10),
    CONSTRAINT fk_usuario_recomendacao FOREIGN KEY (id_usuario) REFERENCES Usuario (id_usuario),
    CONSTRAINT fk_filme_recomendacao FOREIGN KEY (id_filme) REFERENCES Filmes (id_filme),
    CONSTRAINT fk_emocao_recomendacao FOREIGN KEY (id_emocao) REFERENCES Emocoes (id_emocao)
    );"


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