<%@ page contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="/resources/front-end/login/style.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Arimo:ital,wght@0,400..700;1,400..700&family=Inter:wght@500&family=Roboto+Slab:wght@100..900&display=swap" rel="stylesheet">
</head>
<body>
    <main class="container-fluid vh-100 d-flex justify-content-center align-items-center">
        <section id="login" class="row w-100">
            <div class="col-lg-6 col-md-12 left_login d-flex flex-column justify-content-center align-items-center">
                <p class="titulo text-center">Faça seu login</p>
                <form class="formulario_login text-center w-75" action="/login" method="post">
                    <label class="texto_input form-label" for="email">Nome:</label>
                    <input class="input form-control" type="text" id="email" name="email">
                    <label class="texto_input form-label" for="senha">Senha:</label>
                    <input class="input form-control" type="password" id="senha" name="senha">
                    <button class="botao_entrar btn btn-primary" type="submit">Entrar</button>
                    <span class="status d-block mt-3">${requestScope.mensagem}</span>
                </form>
                <a class="texto_link text-white text-decoration-underline mt-2" href="/registrar">Ainda não tenho uma conta</a>
            </div>
            <div class="col-lg-6 col-md-12 right_login d-flex justify-content-center align-items-center">
                <div class="circulo d-flex flex-column justify-content-center align-items-center">
                    <img src="/resources/front-end/login/image/logoFeliz.png" alt="Logo feliz" class="img-fluid">
                    <p class="titulo text-center">
                        <span>Bem-Vindo(a)!</span>
                        <span>ao CineMood</span>
                    </p>
                </div>
            </div>
        </section>
    </main>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>