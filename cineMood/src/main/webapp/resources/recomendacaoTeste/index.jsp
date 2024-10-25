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
<h2>Adicionar às recomendações a minha lista</h2>

<form action="/create-recomendacao" method="post">
    <div>
        <label>Usuário</label>
        <input type="number" name="usuario" id="usuario">
        <label>Filme</label>
        <input type="number" name="filme" id="filme">
        <label>Emoção</label>
        <input type="number" name="emocao" id="emocao">
        <label>Data de recomendação</label>
        <input type="text" name="data_recomendacao" id="data_recomendacao">

        <button type="submit">Register</button>
    </div>
</form>

<div>
    <table class="table table-striped">
        <thead>
        <tr>
            <th>id_recomendacao</th>
            <th>id_usuario</th>
            <th>id_filme</th>
            <th>id_emocao</th>
            <th>data_recomendacao</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="recomendacao" items="${recomendacoes}">
            <tr>
                <td>${recomendacao.id_recomendacao}</td>
                <td>${recomendacao.id_usuario}</td>
                <td>${recomendacao.id_filme}</td>
                <td>${recomendacao.id_emocao}</td>
                <td>${recomendacao.data_recomendacao}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

</body>
</html>