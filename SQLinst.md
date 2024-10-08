# Instructions SQL Querys
## Querys para criação do banco 
### Criação de usuario feito a paritr do Postgre
  * "CREATE ROLE "cineGodness" WITH 
    LOGIN NOSUPERUSER CREATEDB CREATEROLE INHERIT
    NOREPLICATION
    NOBYPASSRLS
    CONNECTION LIMIT -1
    PASSWORD 'xxxxxx';
    COMMENT ON ROLE "cineGodness" IS 'usuario da aplicação'";"
### Criação de database
    * "CREATE DATABASE "CineFilmes"
      WITH
      OWNER = "cineGodness"
      ENCODING = 'UTF8'
      LOCALE_PROVIDER = 'libc'
      CONNECTION LIMIT = -1
      IS_TEMPLATE = False;"
