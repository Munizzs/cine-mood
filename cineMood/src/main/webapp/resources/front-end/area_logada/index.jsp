<!-- <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> -->
<%@ page contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html>
<html lang="pt-BR">
<head>

    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>CineMood</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="/resources/front-end/nao_logada/style.css?v=1.0">
   <!--  <link rel="stylesheet" href="style.css">-->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Arimo:ital,wght@0,400..700;1,400..700&family=Inter:wght@500&family=Roboto+Slab:wght@100..900&display=swap" rel="stylesheet">
</head>
<body>
    <header>
      <nav class="navbar navbar-expand-lg navbar-dark bg-primary">
        <div class="container-fluid">
          <a class="navbar-brand" href="/user/inicio">
            <img src="/resources/front-end/nao_logada/image/logoFeliz.png" alt="Logo do site" style="height: 40px;">
          </a>
          <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
          </button>
          <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav mx-auto">
              <li class="nav-item"><a class="nav-link" href="/user/inicio">Início</a></li>
              <li class="nav-item"><a class="nav-link" href="/user/filme-emocao">Emoção</a></li>
              <li class="nav-item"><a class="nav-link" href="/user/favorito-filme">Filmes Salvos</a></li>
              <li class="nav-item"><a class="nav-link" href="/user/historico-filme">Historico de Filme</a></li>
            </ul>

            <a class="nav-link" href="/user/pesquisa">
              <img src="/resources/front-end/nao_logada/image/lupa.png" alt="Ícone de lupa" style="height: 25px;" class="me-2">
            </a>

            <c:if test="${sessionScope.loggedUser != null}">
              <div class="dropdown">
                <button class="btn btn-link nav-link dropdown-toggle" id="dropdownMenuLink" data-bs-toggle="dropdown" aria-expanded="false">
                  ${sessionScope.loggedUser}
                </button>
                <ul class="dropdown-menu" aria-labelledby="dropdownMenuLink">
                  <li><a class="dropdown-item" href="/perfil">Ir para o perfil</a></li>
                  <li><a class="dropdown-item" href="/deslogar">Deslogar</a></li>
                </ul>
              </div>
            </c:if>
          </div>
        </div>
      </nav>
    </header>
    <main>
        <section class="container my-4">
            <!-- Lançamento -->
            <div class="mb-5">
                <p class="fs-4 fw-bold text-white mb-3">Lançamento</p>
                <div id="carouselLancamentos" class="carousel slide" data-bs-ride="carousel">
                    <div class="carousel-inner">
                        <c:forEach var="movieChunk" items="${moviesChunks}" varStatus="status">
                            <div class="carousel-item ${status.first ? 'active' : ''}">
                                <div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 row-cols-lg-4 g-4">
                                    <c:forEach var="movie" items="${movieChunk}">
                                        <div class="col">
                                            <div class="card bg-dark text-white w-100 h-100">
                                                <a href="/user/detalhes?id=${movie.id}">
                                                    <img src="${movie.poster_path}" class="card-img-top" alt="${movie.title}" style="height: 350px;">
                                                </a>
                                                <div class="card-body">
                                                    <p class="card-title text-center fw-bold">${movie.title}</p>
                                                </div>
                                            </div>
                                        </div>
                                    </c:forEach>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                    <button class="carousel-control-prev" type="button" data-bs-target="#carouselLancamentos" data-bs-slide="prev">
                        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                        <span class="visually-hidden">Previous</span>
                    </button>
                    <button class="carousel-control-next" type="button" data-bs-target="#carouselLancamentos" data-bs-slide="next">
                        <span class="carousel-control-next-icon" aria-hidden="true"></span>
                        <span class="visually-hidden">Next</span>
                    </button>
                </div>
            </div>

            <!-- Recomendados -->
            <div>
                <p class="fs-4 fw-bold text-white mb-3">Recomendados</p>
                <div id="carouselRecomendados" class="carousel slide" data-bs-ride="carousel">
                    <div class="carousel-inner">
                        <c:forEach var="movieChunk" items="${recommendedMoviesChunks}" varStatus="status">
                            <div class="carousel-item ${status.first ? 'active' : ''}">
                                <div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 row-cols-lg-4 g-4">
                                    <c:forEach var="movie" items="${movieChunk}">
                                        <div class="col">
                                            <div class="card bg-dark text-white w-100 h-100">
                                                <a href="/user/detalhes?id=${movie.id}">
                                                    <img src="${movie.poster_path}" class="card-img-top" alt="${movie.title}" style="height: 350px;">
                                                </a>
                                                <div class="card-body">
                                                    <p class="card-title text-center fw-bold">${movie.title}</p>
                                                </div>
                                            </div>
                                        </div>
                                    </c:forEach>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                    <button class="carousel-control-prev" type="button" data-bs-target="#carouselRecomendados" data-bs-slide="prev">
                        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                        <span class="visually-hidden">Previous</span>
                    </button>
                    <button class="carousel-control-next" type="button" data-bs-target="#carouselRecomendados" data-bs-slide="next">
                        <span class="carousel-control-next-icon" aria-hidden="true"></span>
                        <span class="visually-hidden">Next</span>
                    </button>
                </div>
            </div>
        </section>
    </main>
    <footer class="footer text-white text-center py-3">
            <p class="mb-0">© 2024 CineMood. Todos os direitos reservados.</p>
    </footer>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>