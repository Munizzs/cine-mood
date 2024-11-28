<!--
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
-->
<%@ page contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html>
<html lang="pt-BR">
<head>

    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>CineMood</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="/resources/front-end/nao_logada/style.css?v=1.0">
    <!--  <link rel="stylesheet" href="style.css">-->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Arimo:ital,wght@0,400..700;1,400..700&family=Inter:wght@500&family=Roboto+Slab:wght@100..900&display=swap"
          rel="stylesheet">
</head>
<body>
<header>
    <nav class="navbar navbar-expand-lg navbar-dark bg-primary">
        <div class="container-fluid">
            <a class="navbar-brand" href="/a">
                <img src="/resources/front-end/nao_logada/image/logoFeliz.png" alt="Logo do site" style="height: 40px;">
                <!-- <img src="image/logoFeliz.png" alt="Logo do site" style="height: 40px;"> -->
            </a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav"
                    aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav mx-auto">
                    <li class="nav-item"><a class="nav-link" href="/a">Início</a></li>
                    <li class="nav-item"><a class="nav-link" href="/mood">Emoção</a></li>
                    <li class="nav-item"><a class="nav-link" href="/sobre">Sobre</a></li>
                    <li class="nav-item"><a class="nav-link" href="/contato">Contato</a></li>
                    <li class="nav-item"><a class="nav-link" href="/login">Entrar</a></li>
                </ul>
                <p class="fs-5 fw-bold text-white mb-3">CineMood</p>
            </div>
        </div>
    </nav>
</header>
<main>
    <section class="container my-4">
        <div class="container-fluid">
            <h2 class="text-center mb-4" style="color: #ffffff;">Olá <c:out value="${usuario.nome}" />! O que você está sentindo hoje?</h2>
            <div class="row row-cols-1 row-cols-md-3 row-cols-lg-3 g-3 text-center" style="border-radius: 2.5%;background-color: #6a1b9a; color: #ffffff;">
                <c:forEach var="emocao" items="${emocoes}">
                    <div class="col">
                        <div class="card h-100 p-3 shadow-sm rounded" style="background-color: rgba(0, 0, 0, 0.0); color: rgba(0, 0, 0, 0.0);">
                            <a href="/mood/result?idEmocao=${emocao.idEmocao}" class="text-decoration-none">
                                <img src="/resources/front-end/mood/image/${emocao.image}" alt="${emocao.nome}" class="card-img-top mx-3" style="height: 120px; width: 120px; border-radius: 100%;">
                                <div class="card-body" style="background-color: transparent;">
                                    <h5 class="card-title" style="color: #ffffff;">${emocao.nome}</h5>
                                </div>
                            </a>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </section>
</main>

<!-- Modal de Aviso -->
<div class="modal fade" id="loginAlertModal" tabindex="-1" aria-labelledby="loginAlertModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="loginAlertModalLabel">Aviso</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                Para ver o filme, você precisa estar logado. Por favor, faça login para continuar.
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Fechar</button>
            </div>
        </div>
    </div>
</div>
<footer class="footer text-white text-center py-3">
    <p class="mb-0">© 2024 CineMood. Todos os direitos reservados.</p>
</footer>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
<script>
    function showLoginAlert() {
        var myModal = new bootstrap.Modal(document.getElementById('loginAlertModal'));
        myModal.show();
    }
</script>

</body>
</html>
