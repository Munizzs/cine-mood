<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Movie Dashboard</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <style>
        body {
            background: linear-gradient(90deg, #ba58f9, #8d2cc7, #7616ad);
            color: #f8f9fa;
            min-height: 100vh;
        }
        .card {
            background-color: rgba(0, 0, 0, 0.8);
            border: none;
            border-radius: 10px;
            margin-bottom: 20px;
        }
        .btn-primary {
            background: #ff5c5c;
            border: none;
        }
        .btn-primary:hover {
            background: #ff4040;
        }
        .custom-header {
            color: #fff;
            font-size: 2.5rem;
            font-weight: bold;
        }
        .movie-info img {
            max-height: 400px;
            object-fit: cover;
            border-radius: 10px;
        }
        .movie-info h3 {
            color: #fff;
        }
        .alert-warning {
            background-color: rgba(255, 193, 7, 0.2);
            border: 1px solid rgba(255, 193, 7, 0.5);
            color: #ffc107;
        }
        /* Estilo para separar os filmes da barra de pesquisa */
        .movies-section {
            margin-top: 50px; /* Adiciona espaço entre a barra de pesquisa e a lista de filmes */
        }
    </style>
</head>
<body>
<div class="container py-5">
    <div class="card p-4 shadow-lg">
        <h1 class="text-center custom-header mb-4">🎬 Pesquisa de Filmes</h1>

        <c:if test="${sessionScope.loggedUser != null}">
            <div class="d-flex justify-content-between align-items-center mb-4">
                <span class="fw-bold ">pesquise um filme, ${sessionScope.loggedUser}!</span>
            </div>
        </c:if>

        <!-- Barra de Pesquisa -->
        <form action="/user/pesquisa" method="get" class="mb-5">
            <div class="input-group">
                <input type="text" name="title" class="form-control" placeholder="Perquise o filme" required>
                <button type="submit" class="btn btn-primary">🔍 Procurar</button>
            </div>
        </form>

        <!-- Filmes Encontrados -->
        <c:if test="${not empty movies}">
            <div class="movies-section">
                <h2 class="mt-5 custom-header">Filmes Encontrados</h2>
                <div class="row">
                    <c:forEach var="movie" items="${movies}">
                        <div class="col-md-3 col-sm-6 mb-3">
                            <div class="card">
                                <img src="${movie.poster}" class="card-img-top" alt="${movie.title}">
                                <div class="card-body">
                                    <h5 class="card-title">${movie.title}</h5>
                                    <a href="movieDetails?id=${movie.id}" class="btn btn-primary">View Details</a>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </c:if>

        <!-- Mensagem de erro se não houver filme encontrado -->
        <c:if test="${empty movies}">
            <div class="alert alert-warning text-center" role="alert">
                Nenhum filme encontrado com o título pesquisado.
            </div>
        </c:if>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-skAcpId7UcLFQphseVVrPWCpXdE+S9xg1pv37j5G5tmS8Bwdvo+1Qd8B74STaCTj" crossorigin="anonymous"></script>
</body>
</html>
