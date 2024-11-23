<!DOCTYPE html>
<html lang="pt-BR">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Pesquisar Filmes</title>
  <!-- Bootstrap CSS -->
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
  <div class="container mt-5">
    <!-- Barra de Pesquisa -->
    <form action="/pesquisa-filmes" method="GET" class="mb-4">
      <div class="input-group">
        <input type="text" name="query" class="form-control" placeholder="Pesquisar filmes..." required>
        <button type="submit" class="btn btn-primary">Pesquisar</button>
      </div>
    </form>

    <!-- Resultados da pesquisa -->
    <div id="resultados" class="row">
      <!-- Os cards dos filmes serão inseridos aqui -->

      <div class="col-12 mb-3">
        <div class="card">
          <div class="row g-0">
            <!-- Poster do filme -->
            <div class="col-md-3">
              <img src="URL_DO_POSTER" class="img-fluid rounded-start" alt="Poster do filme">
            </div>
            <!-- Informações do filme -->
            <div class="col-md-9">
              <div class="card-body">
                <h5 class="card-title">Título do Filme</h5>
                <p class="card-text"><strong>IMDb Rating:</strong> RATING</p>
                <p class="card-text"><strong>Enredo:</strong> ENREDO</p>
              </div>
            </div>
          </div>
        </div>
      </div>

    </div>
  </div>

  <!-- Bootstrap JS -->
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
