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
<h2>Adicionar Usuario aminha lista</h2>

<form action="/create-usuario" method="post">
    <div>
        <label>Nome</label>
        <input type="text" name="nome" id="nome">
        <label>Email</label>
        <input type="text" name="email" id="email">
        <label>senha</label>
        <input type="text" name="senha" id="senha">
        <label>Data de Nascimento</label>
        <input type="text" name="data_nascimento" id="data_nascimento">

        <button type="submit">Register</button>

    </div>


<div>
    <table class="table table-striped">
        <thead>
        <tr>
            <th>Id</th>
            <th>Nome</th>
            <th>Email</th>
            <th>Senha</th>
            <th>Data de Nascimento</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="usuario" items="${usuarios}">
            <tr>
                <td>${usuario.id_usuario}</td>
                <td>${usuario.nome}</td>
                <td>${usuario.email}</td>
                <td>${usuario.senha}</td>
                <td>${usuario.data_nascimento}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

</form>
</body>
</html>