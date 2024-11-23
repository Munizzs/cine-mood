<!-- <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> -->

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
          <a class="navbar-brand" href="/a">
             <img src="/resources/front-end/nao_logada/image/logoFeliz.png" alt="Logo do site" style="height: 40px;">
            <!-- <img src="image/logoFeliz.png" alt="Logo do site" style="height: 40px;"> -->
          </a>
          <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
          </button>
          <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav mx-auto">
              <li class="nav-item"><a class="nav-link" href="/a">Início</a></li>
              <li class="nav-item"><a class="nav-link" href="/filme-emocao">Emoção</a></li>
              <li class="nav-item"><a class="nav-link" href="/sobre">Sobre</a></li>
              <li class="nav-item"><a class="nav-link" href="/contato">Contato</a></li>
              <li class="nav-item"><a class="nav-link" href="/login">Entrar</a></li>
            </ul>
             <a class="nav-link" href="#"><img src="/resources/front-end/nao_logada/image/lupa.png" alt="Ícone de lupa" style="height: 25px;"></a>
            <!-- <a class="nav-link" href="#"><img src="image/lupa.png" alt="Ícone de lupa" style="height: 25px;"></a> -->
          </div>
        </div>
      </nav>
    </header>
    <main>
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
                    <c:forEach var="movie" items="${recommendedMovies}">
                        <div class="filme">
                            <img src="${movie.poster}" alt="${movie.title}" style="width: 150px; height: 200px; object-fit: cover;">
                            <p class="titulo_filme">${movie.title}</p>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </section>
    </main>
    <footer></footer>
</body>
</html>