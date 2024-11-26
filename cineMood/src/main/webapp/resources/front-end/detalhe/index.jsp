<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Detalhes do Filme</title>
    <link rel="stylesheet" href="<c:url value='/resources/styles.css' />">
</head>
<body>
    <div class="detalhes-container">
        <c:if test="${not empty filme}">
            <h1>${filme.title}</h1>
            <img src="${filme.poster}" alt="Poster do filme">
            <p><strong>Gênero:</strong> </p>
            <p><strong>Sinopse:</strong> ${filme.overview}</p>
            <p><strong>Avaliação:</strong> ${filme.voteAverage}</p>
            <a href="/user/pesquisa">Voltar à pesquisa</a>
        </c:if>

        <c:if test="${not empty error}">
            <p class="error">${error}</p>
            <a href="/user/pesquisa">Voltar à pesquisa</a>
        </c:if>
    </div>
</body>
</html>
