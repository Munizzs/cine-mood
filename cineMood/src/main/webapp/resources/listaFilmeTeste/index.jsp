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
        <label>Lista</label>
        <input type="number" name="lista" id="lista">
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

</body>
</html>