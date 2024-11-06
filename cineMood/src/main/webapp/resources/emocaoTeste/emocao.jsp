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
        <input type="text" name="nome" id="nome">
        <label>Descrição da emoção</label>
        <input type="text" name="descricao" id="descricao">

        <button type="submit">Cadastrar</button>
    </div>
</form>

<div>
    <table class="table table-striped">
        <thead>
        <tr>
            <th>id_emocao</th>
            <th>nome</th>
            <th>descricao</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="emocao" items="${emocoes}">
            <tr>
                <td>${emocao.id_emocao}</td>
                <td>${emocao.nome}</td>
                <td>${emocao.descricao}</td>
            </tr>
            <td>
                <form action="/emocao" method="post">
                    <input type="hidden" id="id_emocao_delete" name="id_emocao_delete" value="${emocao.id_emocao}">
                    <button type="submit">Delete</button>
                </form>
            </td>
        </c:forEach>
        </tbody>
    </table>
</div>

</body>
</html>