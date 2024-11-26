<!-- <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> -->
<%@ page contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html>
<html lang="pt-BR">
<head>

    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>CineMood</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="/resources/front-end/sobre/style.css?v=1.0">
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
             <img src="/resources/front-end/sobre/image/logoFeliz.png" alt="Logo do site" style="height: 40px;">
            <!-- <img src="image/logoFeliz.png" alt="Logo do site" style="height: 40px;"> -->
          </a>
          <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
          </button>
          <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav mx-auto">
              <li class="nav-item"><a class="nav-link" href="/a">Início</a></li>
              <li class="nav-item"><a class="nav-link" href="/filme-emocao">Emoção</a></li>
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
      <div class="container">
           <img src="/resources/front-end/sobre/image/logoAmor.png" alt="Logo do site" style="height: 300px;">
                       <!-- <img src="image/logoAmor.png" alt="Logo do site" style="height: 300px;"> -->
            <div class="text-input">
                <h1>Sobre nós</h1>
                <p class="texto">Você já sentiu que escolher um filme pode ser mais difícil do que parece? Às vezes, tudo o que queremos é encontrar algo que combine exatamente com o nosso humor ou com a emoção que queremos experimentar. Foi pensando nisso que criamos CineMood, um espaço dedicado a ajudar você a encontrar o filme perfeito para cada momento. <br><br> Somos um grupo de quatro amigos apaixonados por cinema e tecnologia: Paulo Fenuchi, Matheus Muniz, Camila Abreu e Lucas Gadelha. Juntos, unimos nossas habilidades para desenvolver uma plataforma que entende o que você sente e transforma emoções em recomendações cinematográficas únicas.<br><br> Nosso objetivo é tornar sua experiência mais intuitiva e pessoal, conectando você com histórias que ressoem com o seu estado de espírito. Porque acreditamos que filmes têm o poder de transformar, inspirar e, acima de tudo, emocionar.<p>
                <p class="mini-texto"><br> Explore, descubra e sinta. Estamos aqui para tornar sua jornada cinematográfica inesquecível!</p>
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