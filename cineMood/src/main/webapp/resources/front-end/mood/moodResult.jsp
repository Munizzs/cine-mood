<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>CineMood - Filmes Recomendados</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="/resources/front-end/nao_logada/style.css?v=1.0">
</head>
<body>
<header>
    <nav class="navbar navbar-expand-lg navbar-dark bg-primary">
        <div class="container-fluid">
            <a class="navbar-brand" href="/a">
                <img src="/resources/front-end/nao_logada/image/logoFeliz.png" alt="Logo do site" style="height: 40px;">
            </a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav"
                    aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav mx-auto">
                    <li class="nav-item"><a class="nav-link" href="/a">Início</a></li>
                    <li class="nav-item"><a class="nav-link" href="/mood">Emoção</a></li>
                    <li class="nav-item"><a class="nav-link" href="/sobre">Sobre</a></li>
                    <li class="nav-item"><a class="nav-link" href="/contato">Contato</a></li>
                    <li class="nav-item"><a class="nav-link" href="/login">Entrar</a></li>
                </ul>
                <p class="fs-5 fw-bold text-white mb-3">CineMood</p>
            </div>
        </div>
    </nav>
</header>

<main>
    <section class="container my-4">
        <h2 class="text-center mb-4" style="color: #ffffff;">Filmes Recomendados Baseados na Sua Emoção</h2>
        <div class="row row-cols-1 row-cols-md-3 row-cols-lg-4 g-4 text-center">
            <c:forEach var="filme" items="${recommendedFilmes}">
                <div class="col">
                    <div class="card h-100 p-3 shadow-sm rounded" style="background-color: #6a1b9a; color: #ffffff;">
                        <img src="${filme.poster_path}" alt="${filme.title}" class="card-img-top" style="height: 300px;">
                        <div class="card-body">
                            <h5 class="card-title">${filme.title}</h5>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </section>
</main>

<footer class="footer text-white text-center py-3">
    <p class="mb-0">© 2024 CineMood. Todos os direitos reservados.</p>
</footer>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
