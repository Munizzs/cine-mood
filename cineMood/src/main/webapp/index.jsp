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
<h2>Adicionar Filme aminha lista</h2>

<form action="/create-cineMood" method="post">
    <div>
        <label>Nome do Filme</label>
        <input type="text" name="titulo" id="titulo">
        <label>Tipo</label>
        <input type="text" name="tipo" id="tipo">

        <button type="submit">Register</button>

    </div>


<div>
    <table class="table table-striped">
        <thead>
        <tr>
            <th>Id</th>
            <th>Título</th>
            <th>Tipo</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="film" items="${films}">
            <tr>
                <td>${film.id_filme}</td>
                <td>${film.titulo}</td>
                <td>${film.tipo}</td>
                <td>
                    <form action="/delete-film" method="post">
                        <input type="hidden" id="id_delFilm" name="id_delFilm" value="${film.id_filme}">
                        <button type="submit">Delete</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

</form>
</body>
</html>