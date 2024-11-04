<
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<head>
    <meta charset="UTF-8">
    <meta content="width=device-width, initial-scale=1">
    <title>Dashboard</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
</head>
<body>
<h2>Adicionar à minha lista de favoritos</h2>

<form action="/favoritos" method="post">
    <div>
        <label>Usuário</label>
        <input type="number" name="usuario" id="usuario">
        <label>Filme</label>
        <input type="number" name="filme" id="filme">
        <label>Data Favoritado</label>
        <input type="text" name="data_favoritado" id="data_favoritado">

        <button type="submit">Register</button>
    </div>
</form>

<div>
    <table class="table table-striped">
        <thead>
        <tr>
            <th>id_favorito</th>
            <th>id_usuario</th>
            <th>id_filme</th>
            <th>data_favoritado</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="favorito" items="${favoritos}">
            <tr>
                <td>${favorito.id_favorito}</td>
                <td>${favorito.id_usuario}</td>
                <td>${favorito.id_filme}</td>
                <td>${favorito.data_favoritado}</td>
                <td>
                    <form action="/favorito" method="post">
                        <input type="hidden" id="id_favorito_delete" name="id_favorito_delete" value="${favorito.id_favorito}">
                        <button type="submit">Delete</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

</body>
</html>