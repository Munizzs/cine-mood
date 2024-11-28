<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Detalhes do Filme</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.0/css/bootstrap.min.css">
    <style>
        .detalhes-container {
            position: relative;
            min-height: 100vh;
            background-size: cover;
            background-position: center;
            background-repeat: no-repeat;
            color: #fff;
        }

        .overlay {
            position: absolute;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            background-color: rgba(0, 0, 0, 0.7); /* Overlay escuro */
            z-index: 1;
        }

        .conteudo {
            position: relative;
            z-index: 2;
            padding: 50px;
        }

        .poster {
            max-width: 100%;
            border-radius: 10px;
        }

        .titulo {
            font-size: 2rem;
            font-weight: bold;
        }

        .genero, .sinopse, .info {
            margin-top: 10px;
        }

        .botoes {
            margin-top: 30px;
        }

        .botoes button {
            margin-right: 10px;
        }
    </style>
</head>
<body>
    <div class="detalhes-container" style="background-image: url('https://image.tmdb.org/t/p/original${filme.backdrop_path}')">
        <div class="overlay"></div>
        <div class="conteudo container">
            <c:if test="${not empty filme}">
                <div class="row align-items-center">
                    <!-- Coluna para o poster -->
                    <div class="col-md-4 text-center">
                        <img src="https://image.tmdb.org/t/p/w500${filme.poster_path}" alt="Poster do filme" class="poster">
                    </div>
                    <!-- Coluna para os detalhes -->
                    <div class="col-md-8">
                        <h1 class="titulo">${filme.title}</h1>
                        <p class="genero"><strong>Gênero:</strong> ${filme.genres}</p>
                        <p class="sinopse"><strong>Sinopse:</strong> ${filme.overview}</p>
                        <p class="info"><strong>Título Original:</strong> ${filme.originalTitle}</p>
                        <p class="info"><strong>Avaliação:</strong> ${filme.voteAverage}</p>
                        <p class="info"><strong>Data de Lançamento:</strong> ${filme.releaseDate}</p>

                        <!-- Botões -->
                        <div class="botoes">
                            <a href="/user/inicio" class="btn btn-secondary">Inicio</a>
                            <a href="/user/pesquisa" class="btn btn-secondary">Pesquisar</a>
                            <form action="/favoritar" method="post">
                                <input type="hidden" name="idFilme" value="${filme.id}">
                                <input type="hidden" name="genero" value="${filme.genres}">
                                <button type="submit" class="btn btn-primary">Favoritar</button>
                            </form>

                        </div>
                    </div>
                </div>
            </c:if>

            <c:if test="${not empty error}">
                <p class="alert alert-danger">${error}</p>
                <a href="/user/pesquisa" class="btn btn-secondary">Voltar à pesquisa</a>
            </c:if>
        </div>
    </div>

    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.0/js/bootstrap.bundle.min.js"></script>
</body>
</html>
