<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Movie Dashboard</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <style>
        body {
            background: linear-gradient(to right, #141e30, #243b55);
            color: #f8f9fa;
            min-height: 100vh;
        }
        .card {
            background-color: rgba(0, 0, 0, 0.8);
            border: none;
            border-radius: 10px;
        }
        .btn-primary {
            background: #ff5c5c;
            border: none;
        }
        .btn-primary:hover {
            background: #ff4040;
        }
        .custom-header {
            color: #ff5c5c;
            font-size: 2.5rem;
            font-weight: bold;
        }
        .movie-info img {
            max-height: 400px;
            object-fit: cover;
            border-radius: 10px;
        }
        .movie-info h3 {
            color: #ff5c5c;
        }
        .alert-warning {
            background-color: rgba(255, 193, 7, 0.2);
            border: 1px solid rgba(255, 193, 7, 0.5);
            color: #ffc107;
        }
    </style>
</head>
<body>
<div class="container py-5">
    <div class="card p-4 shadow-lg">
        <h1 class="text-center custom-header mb-4">🎬 Movie Dashboard</h1>

        <c:if test="${sessionScope.loggedUser != null}">
            <div class="d-flex justify-content-between align-items-center mb-4">
                <span class="fw-bold">Welcome, ${sessionScope.loggedUser}!</span>
                <a href="/deslogar" class="btn btn-danger btn-sm">Logout</a>
            </div>
        </c:if>

        <form action="searchMovie" method="get" class="mb-5">
            <div class="input-group">
                <input type="text" name="title" class="form-control" placeholder="Enter movie title" required>
                <button type="submit" class="btn btn-primary">🔍 Search</button>
            </div>
        </form>

        <%-- Movie information section --%>
        <c:if test="${not empty title}">
            <div class="movie-info">
                <div class="row">
                    <div class="col-md-4 mb-3">
                        <img src="${posterPath}" alt="Movie Poster" class="img-fluid shadow">
                    </div>
                    <div class="col-md-8">
                        <h3>${title}</h3>
                        <p><strong>Original Title:</strong> ${originalTitle}</p>
                        <p><strong>Synopsis:</strong> ${overview}</p>
                        <p><strong>Release Date:</strong> ${releaseDate}</p>
                        <p><strong>Original Language:</strong> ${originalLanguage}</p>
                        <p><strong>Average Rating:</strong> ${voteAverage} ⭐</p>
                        <p><strong>Total Votes:</strong> ${voteCount}</p>
                        <p><strong>Popularity:</strong> ${popularity}</p>
                        <p><strong>Adult Content:</strong> ${adult ? "Yes" : "No"}</p>
                    </div>
                </div>
                <div class="mt-4">
                    <h3>Backdrop</h3>
                    <img src="${backdropPath}" alt="Movie Backdrop" class="img-fluid rounded shadow">
                </div>
            </div>
        </c:if>

        <%-- Message for no data available --%>
        <c:if test="${empty title}">
            <div class="alert alert-warning text-center" role="alert">
                No movie data available. Please enter a title to search.
            </div>
        </c:if>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-skAcpId7UcLFQphseVVrPWCpXdE+S9xg1pv37j5G5tmS8Bwdvo+1Qd8B74STaCTj" crossorigin="anonymous"></script>
</body>
</html>
