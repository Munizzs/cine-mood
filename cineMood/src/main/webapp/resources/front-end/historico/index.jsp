<!-- <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> -->
<!DOCTYPE html>
<html lang="pt-BR">
<head>

    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>CineMood</title>
    <link rel="stylesheet" href="/resources/front-end/nao_logada/style.css">
<%--    <link rel="stylesheet" href="style.css">--%>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Arimo:ital,wght@0,400..700;1,400..700&family=Inter:wght@500&family=Roboto+Slab:wght@100..900&display=swap" rel="stylesheet">
</head>
<body>
    <header id="header">
        <ul class="nav">
                        
            <div class="opcoes_nav">
                <a  href="/inicio"><img class="logo" src="/resources/front-end/area_logada/image/logoFeliz.png" alt="Logo do site"></a>
<%--            <a  href="/inicio"><img class="logo" src="image/logoFeliz.png" alt="Logo do site"></a>--%>
                <li><a href="/inicio">Inicio</a></li>
                <li><a href="/filme-emocao">Emoções</a></li>
                <li><a href="/filmes_salvos">Filmes Salvos</a></li>
                <li><a href="/historico_filme">Histórico de filmes</a></li>
                <a class="icon_lupa" href="#"><img src="/resources/front-end/area_logada/image/lupa.png" alt="Icone de lupa"></a>
<%--                <a class="icon_lupa" href="#"><img src="image/lupa.png" alt="Icone de lupa"></a>--%>
                <li><a href="/perfil"></a>
                    <button id="showButtons">perfil</button>
                    <style>
                        #buttons-container {
                            display: none;
                            margin-top: 10px;
                        }
                    </style>
                
                
                   
                
                    <div id="buttons-container">

                        <button id = "botao1">Botão 1</button>
                        <button id = "botao2">Botão 2</button>
                    </div>
                
                    <script>
                        // Obtém o botão e o contêiner dos botões adicionais
                        const showButtons = document.getElementById('showButtons');
                        const buttonsContainer = document.getElementById('buttons-container');
                
                        
                        showButtons.addEventListener('click', () => {
                            
                            buttonsContainer.style.display = buttonsContainer.style.display === 'none' ? 'block' : 'none';
                        });
                    </script>
                    </li>
            </div>    
        </ul>
    </header>
    <main>
        <section class="tela_filme">
        <section class="container_filmes">
            <div class="box_filmes">
                <p class="titulo">Lançamento</p>
                <div class="filmes">
                    <c:forEach var="movie" items="${movies}">
                        <div class="filme">
                            <img src="${movie.poster}" alt="${movie.title}" style="width: 150px; height: 200px; object-fit: cover;">
                            <p class="titulo_filme">${movie.title}</p>
                        </div>
                    </c:forEach>
                </div>
            </div>
            <div class="box_filmes">
                <p class="titulo">Recomendados</p>
                <div class="filmes">
                    <c:forEach var="movie" items="${movies}">
                        <div class="filme">
                            <img src="${movie.poster}" alt="${movie.title}" style="width: 150px; height: 200px; object-fit: cover;">
                            <p class="titulo_filme">${movie.title}</p>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </section>
        <section class="container_grafico">
            <p class="titulo">Histórico de Filme</p>
            <p>Tristeza: ${tristeza}</p>
            <p>Raiva: ${raiva}</p>
            <p>Alegria: ${alegria}</p>
            <p>Curiosidade: ${curiosidade}</p>
            <p>Amor: ${amor}</p>
        </section >
    </section>
    </main>
    <footer></footer>
</body>
</html>