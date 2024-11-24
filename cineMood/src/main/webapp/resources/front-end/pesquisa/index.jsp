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
    <form action="/pesquisa" method="GET" class="mb-4">
        <div class="input-group">
            <input type="text" name="query" class="form-control" placeholder="Pesquisar filmes..." required>
            <button type="submit" class="btn btn-primary">Pesquisar</button>
        </div>
    </form>

    <!-- Resultados da pesquisa -->
    <div id="resultados" class="row">
        <%
            // Recuperar os resultados enviados pela servlet
            List<Filme> resultados = (List<Filme>) request.getAttribute("resultados");
            if (resultados != null && !resultados.isEmpty()) {
                for (Filme filme : resultados) {
        %>
        <div class="col-12 mb-3">
            <div class="card">
                <div class="row g-0">
                    <!-- Poster do filme -->
                    <div class="col-md-3">
                        <img src="<%= filme.getPosterUrl() %>" class="img-fluid rounded-start" alt="Poster do filme">
                    </div>
                    <!-- Informações do filme -->
                    <div class="col-md-9">
                        <div class="card-body">
                            <h5 class="card-title"><%= filme.getTitulo() %></h5>
                            <p class="card-text"><strong>IMDb Rating:</strong> <%= filme.getRating() %></p>
                            <p class="card-text"><strong>Enredo:</strong> <%= filme.getEnredo() %></p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <%
                }
            } else {
        %>
        <p class="text-center">Nenhum filme encontrado.</p>
        <%
            }
        %>
    </div>
</div>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
