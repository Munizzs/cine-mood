<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="br.com.project.cineMood.model.Favorito" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>CineMood</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="/resources/front-end/favorito/style.css?v=1.0">
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
            <img src="/resources/front-end/favorito/image/logoFeliz.png" alt="Logo do site" style="height: 40px;">
          </a>
          <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
          </button>
          <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav mx-auto">
              <li class="nav-item"><a class="nav-link" href="/user/inicio">Início</a></li>
              <li class="nav-item"><a class="nav-link" href="/mood">Emoção</a></li>
              <li class="nav-item"><a class="nav-link" href="/user/favorito-filme">Filmes Salvos</a></li>
              <li class="nav-item"><a class="nav-link" href="/user/historico-filme">Historico de Filme</a></li>
              <a class="nav-link" href="/user/pesquisa">
                <img src="/resources/front-end/favorito/image/lupa.png" alt="Ícone de lupa" style="height: 25px;" class="me-2">
              </a>
            </ul>

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
    <div class="mb-4">
        <form method="get" action="/user/favorito-filme" class="d-flex align-items-center">
            <label for="status" class="me-2 text-white">Filtrar por Status:</label>
            <select name="status" id="status" class="form-select me-2" style="width: auto;">
                <option value="">Todos</option>
                <option value="Assistido" <%= "Assistido".equals(request.getParameter("status")) ? "selected" : "" %>>Assistido</option>
                <option value="Assistindo" <%= "Assistindo".equals(request.getParameter("status")) ? "selected" : "" %>>Assistindo</option>
                <option value="Quero_Assistir" <%= "Quero_Assistir".equals(request.getParameter("status")) ? "selected" : "" %>>Quero Assistir</option>
            </select>
            <button type="submit" class="btn btn-primary">Filtrar</button>
        </form>
    </div>



        <div class="container mt-2 py-5 mb-4">
            <h1 class="mb-4 fs-3 fw-bold text-white">Filmes Favoritados</h1>
            <div class="row gy-4">
                <c:forEach var="filme" items="${filmesFavoritos}">
                    <div class="col-lg-3 col-md-4 col-sm-6">
                        <div class="card shadow-sm h-100">
                            <img src="${filme.backdrop_path}" alt="${filme.title}" class="card-img-top rounded" style="height: 180px; object-fit: cover;">
                            <div class="card-body">
                                <h5 class="card-title text-white">${filme.title}</h5>
                                <c:forEach var="favorito" items="${favoritos}">
                                    <c:if test="${favorito.idFilme == filme.id}">
                                        <p class="card-text text-white"><strong>Status:</strong> ${favorito.status}</p>
                                        <p class="card-text text-white"><strong>Avaliação:</strong> ${favorito.avaliacao}/5</p>
                                        <p class="card-text text-white"><strong>Gênero:</strong> ${favorito.genero}</p>
                                        <div class="d-flex justify-content-between">
                                            <form method="post" action="/delete-favorito" class="d-inline">
                                                <input type="hidden" name="id_favorito_delete" value="${favorito.idFavorito}">
                                                <button type="submit" class="btn btn-outline-danger btn-sm">Excluir</button>
                                            </form>
                                            <button class="btn btn-outline-warning btn-sm"
                                                data-bs-toggle="modal"
                                                data-bs-target="#editModal${favorito.idFavorito}">
                                                Editar
                                            </button>
                                        </div>
                                    </c:if>
                                </c:forEach>
                            </div>
                        </div>
                    </div>
                </c:forEach>
                <!-- Modal para editar o favorito -->
                        <c:forEach var="favorito" items="${favoritos}">
                            <div class="modal fade" id="editModal${favorito.idFavorito}" tabindex="-1" aria-labelledby="editModalLabel" aria-hidden="true">
                                <div class="modal-dialog">
                                    <form method="post" action="/user/favorito-filme?action=updateFavorito">
                                        <input type="hidden" name="idFavorito" value="${favorito.idFavorito}">
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <h5 class="modal-title" id="editModalLabel">Editar Favorito</h5>
                                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                            </div>
                                            <div class="modal-body">
                                                <div class="mb-3">
                                                    <label for="status" class="form-label">Status</label>
                                                    <select class="form-select" name="status" required>
                                                        <option value="Assistido" <c:if test="${'Assistido' == favorito.status}">selected</c:if>>Assistido</option>
                                                        <option value="Assistindo" <c:if test="${'Assistindo' == favorito.status}">selected</c:if>>Assistindo</option>
                                                        <option value="Quero_Assistir" <c:if test="${'Quero_Assistir' == favorito.status}">selected</c:if>>Quero Assistir</option>
                                                    </select>
                                                </div>
                                                <div class="mb-3">
                                                    <label for="avaliacao" class="form-label">Avaliação</label>
                                                    <input type="number" class="form-control" name="avaliacao" value="${favorito.avaliacao}" min="0" max="5" required>
                                                </div>
                                            </div>
                                            <div class="modal-footer">
                                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
                                                <button type="submit" class="btn btn-primary">Salvar</button>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </c:forEach>
            </div>
            <c:if test="${empty favoritos}">
                <div class="alert alert-info text-center mt-4">Nenhum favorito encontrado.</div>
            </c:if>
        </div>
    </main>

    <footer class="footer text-white text-center py-3">
            <p class="mb-0">© 2024 CineMood. Todos os direitos reservados.</p>
    </footer>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
