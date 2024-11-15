<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Login</title>
</head>
<body>

<form action="/login" method="post">

    <span>${requestScope.mensagem}</span>

    <br>

    <label for="nome">Username</label>
    <input type="text" id="nome" name="nome">

    <br>

    <label for="senha">Password</label>
    <input type="password" id="senha" name="senha">

    <button type="submit">Login</button>

</form>

</body>
</html>