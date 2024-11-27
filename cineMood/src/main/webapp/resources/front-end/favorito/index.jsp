<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Favoritos</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-5">
        <h1 class="text-center">Seus Favoritos</h1>

        <!-- Mensagem de erro -->
        <c:if test="${not empty error}">
            <div class="alert alert-danger">${error}</div>
        </c:if>

        <table class="table table-striped mt-4">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Filme</th>
                    <th>Status</th>
                    <th>Avaliação</th>
                    <th>Ações</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="favorito" items="${favoritos}">
                    <tr>
                        <td>${favorito.idFavorito}</td>
                        <td>${favorito.idFilme}</td>
                        <td>${favorito.status}</td>
                        <td>${favorito.avaliacao}</td>
                        <td>
                            <!-- Formulário para editar -->
                            <form action="favorito-filme" method="post" class="d-inline">
                                <input type="hidden" name="action" value="edit">
                                <input type="hidden" name="idFavorito" value="${favorito.idFavorito}">
                                <select name="status" class="form-select form-select-sm" required>
                                    <option value="ASSISTIDO">Assistido</option>
                                    <option value="ASSISTINDO">Assistindo</option>
                                    <option value="QUERO_ASSISTIR">Quero Assistir</option>
                                </select>
                                <input type="number" name="avaliacao" min="0" max="5" value="${favorito.avaliacao}" class="form-control form-control-sm d-inline-block w-50">
                                <button type="submit" class="btn btn-warning btn-sm">Salvar</button>
                            </form>

                            <!-- Formulário para excluir -->
                            <form action="favorito-filme" method="post" class="d-inline">
                                <input type="hidden" name="action" value="delete">
                                <input type="hidden" name="idFavorito" value="${favorito.idFavorito}">
                                <button type="submit" class="btn btn-danger btn-sm">Excluir</button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
