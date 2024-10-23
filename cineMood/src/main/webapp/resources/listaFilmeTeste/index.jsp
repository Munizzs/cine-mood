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
<h2>Adicionar à lista de filmes a minha lista</h2>

<form action="/create-lista_filmes" method="post">
    <div>
        <label>Usuário</label>
        <input type="number" name="usuario" id="usuario">
        <label>Filme</label>
        <input type="number" name="filme" id="filme">
        <label>Status</label>
        <input type="text" name="status" id="status">
        <label>Avaliação</label>
        <input type="number" name="avaliacao" id="avaliacao">
        <label>Data de adição</label>
        <input type="text" name="data_adicao" id="data_adicao">

        <button type="submit">Register</button>
    </div>
</form>

<div>
    <table class="table table-striped">
        <thead>
        <tr>
            <th>id_lista</th>
            <th>id_usuario</th>
            <th>id_filme</th>
            <th>status</th>
            <th>avaliacao</th>
            <th>data_adicao</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="listaFilme" items="${listafilmes}">
            <tr>
                <td>${listaFilme.id_lista}</td>
                <td>${listaFilme.id_usuario}</td>
                <td>${listaFilme.id_filme}</td>
                <td>${listaFilme.status}</td>
                <td>${listaFilme.avaliacao}</td>
                <td>${listaFilme.data_adicao}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

</body>
</html>