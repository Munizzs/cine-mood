<!-- <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> -->
<%@ page contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html>
<html lang="pt-BR">
<head>

    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>CineMood</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="/resources/front-end/contato/style.css?v=1.0">
   <!--  <link rel="stylesheet" href="style.css">-->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Arimo:ital,wght@0,400..700;1,400..700&family=Inter:wght@500&family=Roboto+Slab:wght@100..900&display=swap" rel="stylesheet">
</head>
<body>
    <header>
      <nav class="navbar navbar-expand-lg navbar-dark bg-primary">
        <div class="container-fluid">
          <a class="navbar-brand" href="/a">
             <img src="/resources/front-end/contato/image/logoFeliz.png" alt="Logo do site" style="height: 40px;">
            <!-- <img src="image/logoFeliz.png" alt="Logo do site" style="height: 40px;"> -->
          </a>
          <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
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
<h2><br></h2>
   <div class="row row-cols-1 row-cols-md-3 row-cols-lg-3 g-3 text-center" style="border-radius: 2.5%;background-color: #6a1b9a; color: #ffffff; margin-right: 20px; margin-left: 20px; padding-bottom: 250px;">
        <div class = "titulo1">
            <h1>Nos siga nas redes sociais!</h1>
        </div>
        <div class = "insta">
             <img src="/resources/front-end/contato/image/instagramLogo.png" alt="Logo do site" style="height: 35px;">
             <p id = "instamsg">@Cinemood<p>
        </div>
        <div class = "zap">
            <img src="/resources/front-end/contato/image/zapLogo.png" alt="Logo do site" style="height: 35px;">
            <p id = "zapmsg">+11 99999-9999<p>
        </div>
        <div class = "link">
             <img src="/resources/front-end/contato/image/linkLogo.png" alt="Logo do site" style="height: 35px;">
             <p id = "linkmsg">CineMood<p>
        </div>

        <div class = "trabalha">
            <h2 id = "trampo">Trabalhe conosco!</h2>
            <p id = "id1">Mande seu currículo para:</p>
            <p id = "id2">rhrecrutamento@cinemood.com.br</p>
        </div>

    <div class = "opine">
        <h2 id = "opine1"> Opinião de um funcionário!</h2>
        <p id = "opine2">Adoro trabalhar no CineMood porque fazemos a diferença na vida das pessoas! - Anônimo.</p>
    </div>

        <div class = "curiosidade">
            <img src="/resources/front-end/contato/image/curiosidadeLogo.png" alt="Logo do site" style="height: 250px;" class="float-end custom-img">
        </div>
   </div>
</main>



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