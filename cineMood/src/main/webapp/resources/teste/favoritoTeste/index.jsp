<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Favoritos</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
</head>
<body>
<div class="container mt-5">
    <h2 class="mb-4">Gerenciar Favoritos</h2>

    <!-- Formulário para adicionar ou atualizar um favorito -->
    <form action="/favorito" method="post" class="mb-4">
        <div class="mb-3">
            <label for="id_favorito" class="form-label">ID Favorito</label>
            <input type="number" id="id_favorito" name="id_favorito" class="form-control"
                   value="${param.id_favorito}" placeholder="Deixe vazio para criar um novo">
        </div>
        <div class="mb-3">
            <label for="usuario" class="form-label">ID Usuário</label>
            <input type="number" id="usuario" name="usuario" class="form-control" value="${param.usuario}" required>
        </div>
        <div class="mb-3">
            <label for="filme" class="form-label">ID Filme</label>
            <input type="text" id="filme" name="filme" class="form-control" value="${param.filme}" required>
        </div>
        <div class="mb-3">
            <label for="status" class="form-label">Status</label>
            <select id="status" name="status" class="form-select" required>
                <option value="">Selecione o status</option>
                <c:forEach var="status" items="${Favorito.Status.values()}">
                    <option value="${status}" ${status == param.status ? 'selected' : ''}>${status}</option>
                </c:forEach>
            </select>
        </div>
        <div class="mb-3">
            <label for="avaliacao" class="form-label">Avaliação (0-5)</label>
            <input type="number" id="avaliacao" name="avaliacao" class="form-control" min="0" max="5"
                   value="${param.avaliacao}" required>
        </div>
        <div class="mb-3">
            <label for="genero" class="form-label">Gênero</label>
            <input type="text" id="genero" name="genero" class="form-control" value="${param.genero}" required>
        </div>
        <button type="submit" class="btn btn-primary">Salvar</button>
    </form>

    <!-- Tabela para listar os favoritos -->
    <table class="table table-striped">
        <thead>
        <tr>
            <th>ID Favorito</th>
            <th>ID Usuário</th>
            <th>ID Filme</th>
            <th>Status</th>
            <th>Avaliação</th>
            <th>Gênero</th>
            <th>Ações</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="favorito" items="${favoritos}">
            <tr>
                <td>${favorito.idFavorito}</td>
                <td>${favorito.idUsuario}</td>
                <td>${favorito.idFilme}</td>
                <td>${favorito.status}</td>
                <td>${favorito.avaliacao}</td>
                <td>${favorito.genero}</td>
                <td>
                    <!-- Botão para excluir -->
                    <form action="/delete-favorito" method="post" style="display: inline;">
                        <input type="hidden" name="id_favorito_delete" value="${favorito.idFavorito}">
                        <button type="submit" class="btn btn-danger btn-sm">Excluir</button>
                    </form>
                    <!-- Link para atualizar -->
                    <a href="/favorito?id_favorito=${favorito.idFavorito}&usuario=${favorito.idUsuario}&filme=${favorito.idFilme}&status=${favorito.status}&avaliacao=${favorito.avaliacao}&genero=${favorito.genero}"
                       class="btn btn-warning btn-sm">Editar</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-rb6j0tEu9VUd6wKRzVzzP6Isw3GdbpbDjFBJvN8KcUAljXDVSXz6AQQ5LgSA2COr" crossorigin="anonymous"></script>
</body>
</html>
