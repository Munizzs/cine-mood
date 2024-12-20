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
<h2>Adicionar Usuario a minha lista</h2>

<form action="/usuario" method="post">
    <div>
        <label>Nome</label>
        <input type="text" name="nome" id="nome" value="${param.name}">
        <input  id="id_usuario" name="id_usuario" value="${param.idUsuario}">
        <label>Email</label>
        <input type="text" name="email" id="email" value="${param.email}">
        <label>senha</label>
        <input type="text" name="senha" id="senha" value="${param.senha}">
        <label>Ativo</label>
        <input type="text" name="ativo" id="ativo" value="${param.ativo}">

        <button type="submit">Salvar</button>

    </div>


<div>
    <table class="table table-striped">
        <thead>
        <tr>
            <th>Id</th>
            <th>Nome</th>
            <th>Email</th>
            <th>Senha</th>
            <th>Ativo</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="usuario" items="${usuarios}">
            <tr>
                <td>${usuario.idUsuario}</td>
                <td>${usuario.nome}</td>
                <td>${usuario.email}</td>
                <td>${usuario.senha}</td>
                <td>${usuario.ativo}</td>
                <td>
                    <form action="/delete-usuario" method="post">
                        <input  id="idUsuario_delete" name="idUsuario_delete" value="${usuario.idUsuario}">
                        <button type="submit">Delete</button>
                        <span> | </span>
                        <a href="/usuario?idUsuario=${usuario.idUsuario}&name=${usuario.nome}&email=${usuario.email}&senha=${usuario.senha}&ativo=${usuario.ativo}">Update</a>
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