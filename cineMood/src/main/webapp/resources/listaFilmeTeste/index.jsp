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

<form action="/lista_filme" method="post">
    <div>
        <label>Usuário</label>
        <input type="number" name="usuario" id="usuario" value="${param.usuario}">
        <input  id="id_lista" name="id_lista" value="${param.id_lista}">
        <label>Filme</label>
        <input type="number" name="filme" id="filme" value="${param.filme}">
        <label>Status</label>
        <input type="text" name="status" id="status" value="${param.status}">
        <label>Avaliação</label>
        <input type="number" name="avaliacao" id="avaliacao" value="${param.avaliacao}">
        <label>Data de adição</label>
        <input type="text" name="data_adicao" id="data_adicao" value="${param.data_adicao}">

        <button type="submit">Salvar</button>
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
        <c:forEach var="lista_filme" items="${lista_filmes}">
            <tr>
                <td>${lista_filme.id_lista}</td>
                <td>${lista_filme.id_usuario}</td>
                <td>${lista_filme.id_filme}</td>
                <td>${lista_filme.status}</td>
                <td>${lista_filme.avaliacao}</td>
                <td>${lista_filme.data_adicao}</td>
                <td>
                    <form action="/lista_filme" method="post">
                        <input type="hidden" id="id_lista_filme_delete" name="id_lista_filme_delete" value="${lista_filme.id_lista}">
                        <button type="submit">Delete</button>
                        <span> | </span>
                        <a href="/lista_filme?id_lista=${emocao.id_lista}&usuario=${lista_filme.usuario}&filme=${lista_filme.filme}&status=${lista_filme.status}&avaliacao=${lista_filme.avaliacao}&data_adicao=${lista_filme.data_adicao}">Update</a>
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

</body>
</html>