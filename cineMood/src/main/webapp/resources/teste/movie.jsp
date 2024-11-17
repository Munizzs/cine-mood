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
</head>
<body>
<div class="container mt-5">
    <h1 class="text-center mb-4">Movie Dashboard</h1>
    <c:if test="${sessionScope.loggedUser != null}">
            <span>${sessionScope.loggedUser}</span>
            <a href="/deslogar">Logout</a>
    </c:if>
    <form action="searchMovie" method="get" class="mb-4">
        <div class="input-group">
            <input type="text" name="title" class="form-control" placeholder="Enter movie title" required>
            <button type="submit" class="btn btn-primary">Search</button>
        </div>
    </form>

    <%-- Verifica se o título do filme existe para garantir que os dados foram carregados corretamente --%>
    <c:if test="${not empty title}">
        <h2>Informações do Filme</h2>
        <p><strong>Título:</strong> ${title}</p>
        <p><strong>Ano:</strong> ${year}</p>
        <p><strong>Classificação:</strong> ${rated}</p>
        <p><strong>Data de Lançamento:</strong> ${released}</p>
        <p><strong>Duração:</strong> ${runtime}</p>
        <p><strong>Gênero:</strong> ${genre}</p>
        <p><strong>Diretor:</strong> ${director}</p>
        <p><strong>Escritor:</strong> ${writer}</p>
        <p><strong>Atores:</strong> ${actors}</p>
        <p><strong>Enredo:</strong> ${plot}</p>
        <p><strong>Idioma:</strong> ${language}</p>
        <p><strong>País:</strong> ${country}</p>
        <p><strong>Prêmios:</strong> ${awards}</p>
        <p><strong>Poster:</strong> <img src="${poster}" alt="Poster do Filme" /></p>
        <p><strong>IMDb Rating:</strong> ${imdbRating}</p>
        <p><strong>Bilheteria:</strong> ${boxOffice}</p>
    </c:if>

    <c:if test="${empty movie}">
        <p class="text-center">No movie data available. Please enter a title to search.</p>
    </c:if>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-skAcpId7UcLFQphseVVrPWCpXdE+S9xg1pv37j5G5tmS8Bwdvo+1Qd8B74STaCTj" crossorigin="anonymous"></script>
</body>
</html>
