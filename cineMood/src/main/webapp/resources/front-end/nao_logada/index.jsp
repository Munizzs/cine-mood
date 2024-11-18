<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fn" prefix="fn" %>

<!DOCTYPE html>
<html lang="pt-BR">
<head>

    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>CineMood</title>
    <link rel="stylesheet" href="/resources/front-end/nao_logada/style.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Arimo:ital,wght@0,400..700;1,400..700&family=Inter:wght@500&family=Roboto+Slab:wght@100..900&display=swap" rel="stylesheet">
</head>
<body>
    <header id="header">
        <ul class="nav">
                        
            <div class="opcoes_nav">
                <a  href="/inicio"><img class="logo" src="/resources/front-end/nao_logada//image/logoFeliz.png" alt="Logo do site"></a>
                <li><a href="/inicio">Inicio</a></li>
                <li><a href="/filme-emocao">Emoção</a></li>
                <li><a href="/sobre">Sobre</a></li>
                <li><a href="/contato">Contato</a></li>
                <li><a href="/login">Entrar</a></li>
                <a class="icon_lupa" href="#"><img src="/resources/front-end/nao_logada//image/lupa.png" alt="Icone de lupa"></a>
            </div>    
        </ul>
    </header>
    <main>
        <section class="container_filmes">
            <div class="box_filmes">
                    <p class="titulo">Lançamento</p>
                    <div class="filmes">
                        <c:forEach var="movie" items="${movies}">
                            <div class="filme">
                                <img src="${movie.poster}" alt="${movie.title}" width="200" height="300">
                                <p class="titulo_filme">${movie.title}</p>
                            </div>
                        </c:forEach>
                    </div>
            </div>
            <div class="box_filmes">
                <p class="titulo">Recomendados</p>
                <div class="filmes">
                    <div class="filme">
                        <img src="/image/laçamento1.jpg" alt="Filme 1">
                        <p class="titulo_filme">Filme 1</p>
                    </div>
                    <div class="filme">
                        <img src="/image/laçamento2.jpg" alt="Filme 2">
                        <p class="titulo_filme">Filme 2</p>
                    </div>
                    <div class="filme">
                        <img src="/image/laçamento3.jpg" alt="Filme 3">
                        <p class="titulo_filme">Filme 3</p>
                    </div>
                    <div class="filme">
                        <img src="/image/laçamento3.jpg" alt="Filme 4">
                        <p class="titulo_filme">Filme 4</p>
                    </div>
                    <div class="filme">
                        <img src="/image/laçamento5.jpg" alt="Filme 5">
                        <p class="titulo_filme">Filme 5</p>
                    </div>
                    <div class="filme">
                        <img src="/image/laçamento6.jpg" alt="Filme 6">
                        <p class="titulo_filme">Filme 6</p>
                    </div>
                    <div class="filme">
                        <img src="/image/laçamento7.jpg" alt="Filme 7">
                        <p class="titulo_filme">Filme 7</p>
                    </div>
                </div>
            </div>
        </section>
    </main>
    <footer></footer>
</body>
</html>