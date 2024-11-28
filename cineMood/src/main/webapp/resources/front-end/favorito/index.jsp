<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="br.com.project.cineMood.model.Favorito" %>

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
    <h1 class="mb-4">Filmes Favoritados</h1>
    <table class="table table-striped">
        <thead>
        <tr>
            <th>Filme</th>
            <th>Status</th>
            <th>Avaliação</th>
            <th>Gênero</th>
        </tr>
        </thead>
        <tbody>
        <%
            List<Favorito> favoritos = (List<Favorito>) request.getAttribute("favoritos");
            if (favoritos != null && !favoritos.isEmpty()) {
                for (Favorito favorito : favoritos) {
        %>
        <tr>
            <td><%= favorito.getIdFilme() %></td>
            <td><%= favorito.getStatus() %></td>
            <td><%= favorito.getAvaliacao() %>/10</td>
            <td><%= favorito.getGenero() %></td>
            <td>
                <form method="post" action="/deleteFavorito">
                    <input type="hidden" name="idFavorito" value="<%= favorito.getIdFavorito() %>">
                    <button type="submit" class="btn btn-danger btn-sm">Excluir</button>
                </form>
                <button
                    class="btn btn-warning btn-sm"
                    data-bs-toggle="modal"
                    data-bs-target="#editModal<%= favorito.getIdFavorito() %>">
                    Editar
                </button>
            </td>
        </tr>

        <!-- Modal para editar o favorito -->
        <div class="modal fade" id="editModal<%= favorito.getIdFavorito() %>" tabindex="-1" aria-labelledby="editModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <form method="post" action="/updateFavorito">
                    <input type="hidden" name="idFavorito" value="<%= favorito.getIdFavorito() %>">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="editModalLabel">Editar Favorito</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                            <div class="mb-3">
                                <label for="status" class="form-label">Status</label>
                                <select class="form-select" name="status" required>
                                    <option value="Assistido" <%= "Assistido".equals(favorito.getStatus().name()) ? "selected" : "" %>>Assistido</option>
                                    <option value="Assistindo" <%= "Assistindo".equals(favorito.getStatus().name()) ? "selected" : "" %>>Assistindo</option>
                                    <option value="Quero_Assistir" <%= "Quero_Assistir".equals(favorito.getStatus().name()) ? "selected" : "" %>>Quero Assistir</option>
                                </select>
                            </div>
                            <div class="mb-3">
                                <label for="avaliacao" class="form-label">Avaliação</label>
                                <input type="number" class="form-control" name="avaliacao" value="<%= favorito.getAvaliacao() %>" min="0" max="10" required>
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

        <%
                }
            } else {
        %>
        <tr>
            <td colspan="5" class="text-center">Nenhum favorito encontrado.</td>
        </tr>
        <% } %>
        </tbody>
    </table>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
