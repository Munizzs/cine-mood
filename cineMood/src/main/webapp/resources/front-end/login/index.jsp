<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>
    <link rel="stylesheet" href="/resources/front-end/login/style.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Arimo:ital,wght@0,400..700;1,400..700&family=Inter:wght@500&family=Roboto+Slab:wght@100..900&display=swap" rel="stylesheet">
</head>
<body>
    <main>
        <section id="login">
            <div class="left_login">
                <p class="titulo">Faça seu login</p>
                <form class="formulario_login" action="/login" method="post">
                    <label class="texto_input" for="email">Nome:</label>
                    <input class="input" type="text" id="email" name="email">
                    <label class="texto_input" for="senha">Senha:</label>
                    <input class="input" type="password" id="senha" name="senha">
                    <button class="botao_entrar" type="submit">Entrar</button>
                    <span class="status">${requestScope.mensagem}</span>
                </form>
                <a class="texto_link" href="/registrar">Ainda nâo tenho uma conta</a>
            </div>
            <div class="right_login">
                <div class="circulo">
                    <img src="/resources/front-end/login/image/logoFeliz.png" alt="Logo feliz">
                    <p class="titulo">
                        <span>Bem-Vindo(a)!</span>
                        <span>ao CineMood</span>
                    </p>
                </div>
            </div>
        </section>
    </main>
</body>
</html>