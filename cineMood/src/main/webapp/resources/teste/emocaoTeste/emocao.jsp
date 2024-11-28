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
<h2>Emocões</h2>

<form action="/emocao" method="post">
    <div>
        <label>Nome da emoção</label>
        <input type="text" name="nome" id="nome" value="${param.name}">
        <input  id="id_emocao" name="id_emocao" value="${param.idEmocao}">
        <label>Descrição da emoção</label>
        <input type="text" name="genre" id="genre" value="${param.genre}">

        <button type="submit">Salvar</button>
    </div>
</form>

<div>
    <table class="table table-striped">
        <thead>
        <tr>
            <th>id_emocao</th>
            <th>nome</th>
            <th>genre</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="emocao" items="${emocoes}">
            <tr>
                <td>${emocao.idEmocao}</td>
                <td>${emocao.nome}</td>
                <td>${emocao.genre}</td>
            </tr>
            <td>
                <form action="/delete-emocao" method="post">
                    <input id="id_emocao_delete" name="id_emocao_delete" value="${emocao.idEmocao}">
                    <button type="submit">Delete</button>
                    <span> | </span>
                    <a href="/emocao?id_emocao=${emocao.idEmocao}&name=${emocao.nome}&descricao=${emocao.genre}">Update</a>
                </form>
            </td>
        </c:forEach>
        </tbody>
    </table>
</div>

</body>
</html>