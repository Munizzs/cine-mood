# Configurando e excutando AWS Linux

## Configurações da instância em nuvem
* Tipo de instância: T2.micro
## Comando para verificar updates do sistema
    * sudo yum update -y
## Comando para instalar o Java
    * sudo yum install java-devel -y
## Comando para instalar o GIT
    * sudo yum install git -y
## Comando para instalar o PostgreSql
    * sudo dnf install postgresql15.x86_64 postgresql15-server -y
## PostgreSQL
### Iniciando o banco de dados
    *  sudo postgresql-setup --initdb
### Habilitando o serviço PostgreSQL para inicio automatico no sistema
    *  sudo systemctl start postgresql
       sudo systemctl enable postgresql
       sudo systemctl status postgresql
### Altere a senha do usuario SSH
    * sudo passwd postgres 
    adaoeva11
### Efutuar login(Postgre)
    * su - postgres
### Altere a senha do banco de dados do administrador
    *  psql -c "ALTER USER postgres WITH PASSWORD 'your-password';"
       exit
### Cria um backup do arquivo de configuração
    * sudo cp /var/lib/pgsql/data/postgresql.conf /var/lib/pgsql/data/postgresql.conf.bck
### Editando o arquivo de configuração 
1    * sudo vi /var/lib/pgsql/data/postgresql.conf
#### itens para alteração
escutat apenas o localhost
* listen_addresses = 'localhost'

escutar todos os endereços de ip
* listen_addresses = '*' # what IP address(es) to listen on;
### Autenticando  pg_hba.conf
    * sudo cp /var/lib/pgsql/data/pg_hba.conf /var/lib/pgsql/data/pg_hba.conf.bck
    * sudo sed -i 's/ident$/md5/' /var/lib/pgsql/data/pg_hba.conf
### Necessario reiniciar o serviço PostgreSQL
    * sudo systemctl restart postgresql
### Criando Usuario e database
    conectando ao serviço(iniciar serviço)
    * sudo -i -u postgres psql 
    query de exemplo para criar usuario
    * CREATE USER yourusername WITH PASSWORD 'password';
    Query de exemplo para criar database
    * CREATE DATABASE database_name;
    Definindo privilegios para usuario definido por database
    * GRANT ALL PRIVILEGES ON DATABASE database_name TO yourusername;
    listar todos os usuários e bancos de dados PostgreSQL disponíveis
    * \l
### Acessar banco de dados
    * psql -h localhost -U nome_de_usuário -d nome_do_banco_de_dados
### Acessar usuario remotos
    * psql -h endereço -ip-do-servidor -U nome_de_usuário -d nome_do_banco_de_dados
### Acessar pelo servidor
    iniciar
    * sudo -i -u postgres
    para executar querys
    * psql
    listar
    * \l
    sair 
    * \q
### Permitindo o ip no postgreSQL 
    Alterando permissões de ip
    * sudo vi  /var/lib/pgsql/data/pg_hba.conf
    Alterar permissões para que fiquem assim
    * # IPv4 local connections:
    host    all             all             all            md5
    Salvar alterações e sair do editor do vi com 
    * "esc" + ":wq"
    Reiniciando o serviço
    * sudo systemctl restart postgresql
### Alterando senha de um usuário 
    acessar pelo terminal
    * psql -U postgres
    
### Desistalar PostgreSQL
    Parar serviço
    * sudo systemctl stop postgresql
    Desabilitar inicialização auto do Serviço
    * sudo systemctl disable postgresql
    Remover pacotes
    * sudo dnf remove postgresql15.x86_64 postgresql15-server
    Completely Remove PostgreSQL
    * sudo rm -rf /var/lib/pgsql /var/log/postgresql /etc/postgresql
## Iniciando o Serviço de server Tumcat7 
### Navegar até o repositorio do projeto maven
    *  cd cine-mood/cineMood/ 
### Configurando o chmod para tornar o script execuavel
    * chmod +x mvnw
### Execute o serviço server tumcat7
    * ./mvnw tomcat7:run

    
    