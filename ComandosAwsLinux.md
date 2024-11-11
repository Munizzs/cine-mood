# Configurando e excutando AWS Linux

## Configurações da instância em nuvem
* Tipo de instância: T2.micro
## Comando para verificar updates do sistema
    * sudo yum update -y
## Comando para instalar o Java
    * sudo yum install java-devel -y
## Comando para instalar o GIT
    * sudo yum install git -y
## Comando para instalar Nguinx(serviço utilizado como proxy para redirecionar portas)
    * sudo yum install nginx -y
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
## Redirecionando Porta 80 para 8080 com Nginx
### Navegue até o diretório
    *  cd /etc/nginx/conf.d
### Crie um arquivo "redirecionamento.conf"
    * sudo nano redirecionamento.conf
### Configuração a seguir inserida
    * server {
        listen 80;
        server_name localhost;
    
        location / {
            proxy_pass http://127.0.0.1:8080;
            proxy_set_header Host $host;
            proxy_set_header X-Real-IP $remote_addr;
            proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
            proxy_set_header X-Forwarded-Proto $scheme;
        }
    }
### Reiniciando o Serviço
    * sudo systemctl restart nginx
## Iniciando o Serviço de server Tumcat7 
### Navegar até o repositorio do projeto maven
    *  cd cine-mood/cineMood/ 
### Configurando o chmod para tornar o script execuavel
    * chmod +x mvnw
### Execute o serviço server tumcat7
    * ./mvnw tomcat7:run-war
### Configurando Tomcat
## Crie o Usuário Tomcat
	* sudo groupadd tomcat
	* sudo useradd -s /bin/false -g tomcat -d /opt/tomcat tomcat

## Baixar tomcat na /opt
	* cd /opt 

	#Baixar Documentação do tomcat 
	* wget https://dlcdn.apache.org/tomcat/tomcat-9/v9.0.97/bin/apache-tomcat-9.0.97-fulldocs.tar.gz
	#Baixar binário do tomcat
	* wget https://dlcdn.apache.org/tomcat/tomcat-9/v9.0.97/bin/apache-tomcat-9.0.97.tar.gz

	#descompactar binário do tomcat 
	* tar -zvxf apache-tomcat-9.0.97.tar.gz
	* tar -zvxf apache-tomcat-9.0.97-fulldocs.tar.gz

## Adicionar permissão de execução para startup.sh e shutdown.sh
	* cd apache-tomcat-9.0.97

	* cd bin 

	* sudo chmod +x bin/startup.sh
	* sudo chmod +x bin/shutdown.sh

## Para iniciar o Tomcat
	* sudo ./bin/startup.sh

## Alterar configurações para gerenciar o Tomcat
	* cd apache-tomcat-9.0.97 

	* find -name context.xml 

	* sudo ./conf/context.xml 
	* sudo ./webapps/examples/META-INF/context.xml 
	* sudo ./webapps/host-manager/META-INF/context.xml 
	* sudo ./webapps/manager/META-INF/context.xml 

	#comment value tag seções abaixo de todos os arquivos 
	* vi ./webapps/examples/META-INF/context.xml 
	* vi ./webapps/host-manager/META-INF/context.xml  
	* vi ./webapps/manager/META-INF/context.xml

## Atualizar informações do usuário em tomcat-users.xml
	* cd conf 

	vi tomcat-users.xml 

	#Adicione as linhas abaixo entre a tag <tomcat-users> 

	<role rolename="manager-gui"/> 
	<role rolename="manager-script"/> 
	<role rolename="manager-jmx"/> 
	<role rolename="manager-status"/>    
	<usuário nome de usuário="godness" senha="adaoeva11" funções="manager-gui,manager-script,manager-jmx,manager-status"/> 
	<usuário nome de usuário="deployer" senha="testingtomcat" funções="manager-script"/> 
	<usuário nome de usuário="tomcat7" senha="cinemood" funções="manager-gui"/>

## Criando serviço
	* sudo nano /etc/systemd/system/tomcat.service
	* 
## Configurando o serviço
	* [Unit]
	Description=Apache Tomcat Web Application Container
	After=network.target

	[Service]
	Type=forking

	# Caminho para o script de inicialização do Tomcat
	Environment=JAVA_HOME=/usr/lib/jvm/java-22-openjdk
	Environment=CATALINA_PID=/opt/apache-tomcat-9.0.97/temp/tomcat.pid
	Environment=CATALINA_HOME=/opt/apache-tomcat-9.0.97
	Environment=CATALINA_BASE=/opt/apache-tomcat-9.0.97
	Environment='CATALINA_OPTS=-Xms512M -Xmx1024M -server -XX:+UseParallelGC'
	Environment='JAVA_OPTS=-Djava.awt.headless=true -	Djava.security.egd=file:/dev/./urandom'
	
	ExecStart=/opt/apache-tomcat-9.0.97/bin/startup.sh
	ExecStop=/opt/apache-tomcat-9.0.97/bin/shutdown.sh

	User=tomcat
	Group=tomcat
	UMask=0007
	RestartSec=10
	Restart=always

	[Install]
	WantedBy=multi-user.target

## Confirmar Permissões nos Scripts de Inicialização
	* sudo chown -R tomcat:tomcat /opt/apache-tomcat-9.0.97/

## Recarregar o systemd e Reiniciar o Serviço
	* sudo systemctl daemon-reload
	* sudo systemctl start tomcat

## Copiar arquivo .war
	cp /path/to/your/project/target/your-application.war /opt/tomcat/webapps/



    
    