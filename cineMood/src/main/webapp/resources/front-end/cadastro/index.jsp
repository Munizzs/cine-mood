<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Registrar</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="/resources/front-end/login/style.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Arimo:ital,wght@0,400..700;1,400..700&family=Inter:wght@500&family=Roboto+Slab:wght@100..900&display=swap" rel="stylesheet">
</head>
<body>
    <main class="container-fluid vh-100 d-flex align-items-center">
        <section id="login" class="row w-100">
            <!-- Left Section -->
            <div class="col-md-6 p-5 d-flex flex-column justify-content-center">
                <h1 class="titulo text-center" style="color: white;">Criar Conta</h1>
                <form class="formulario_login" action="/registrar" method="post">
                    <div class="mb-1">
                        <label class="form-label fw-bold texto_input" for="nome">Nome:</label>
                        <input class="form-control input" type="text" id="nome" name="nome" required>
                    </div>
                    <div class="mb-1">
                        <label class="form-label fw-bold texto_input" for="email">Email:</label>
                        <input class="form-control input" type="email" id="email" name="email" required>
                    </div>
                    <div class="mb-1">
                        <label class="form-label fw-bold texto_input" for="senha">Senha:</label>
                        <input class="form-control input" type="password" id="senha" name="senha" required>
                    </div>
                    <button class="btn btn-primary py-2 botao_entrar" type="submit">Criar</button>
                </form>
                <div class="text-center mt-3">
                    <a class="texto_link" href="/login">Já tem conta? Acessar</a>
                </div>
            </div>
            <!-- Right Section -->
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
    <!-- Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
