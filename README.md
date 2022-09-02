## API RESTful para possibilitar a leitura da lista de indicados e vencedores da categoria Pior Filme do Golden Raspberry Awards.
    Lê o arquivo CSV dos filmes e inseri os dados em uma base de dados(h2) ao iniciar a aplicação

## Subir a aplicação utilizando o Docker
	Utilize os seguintes comandos na pasta raiz do projeto:
	Faz o build(gera o .jar) da aplicação para gerar a imagem: docker build -t golden-raspberry-awards .
	Gera o container com base na imagem gerada: docker run -p 8080:8080 golden-raspberry-awards
	Acessar: http://localhost:8080/swagger-ui.html

## Subir a aplicação local utilizando H2 banco de dados em memória
	Foi utilizado um banco de dados H2 que funciona em memória com um console acessível pelo browser dentro do contexto da aplicação.
	Após subir a aplicação o acesso é feito por este endereço: http://localhost:8080/console/
	Preencher esta url no campo JDBC URL: jdbc:h2:mem:testdb
	User Name e Password não precisam ser preenchidas
	Clique no botão connect.