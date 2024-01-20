<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
  if (session.getAttribute("is_auth") == null) {
    response.sendRedirect("login.jsp");
  }
%>

<html lang="en" data-bs-theme="light">

<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Blog | Posts</title>
  <!--favicon-->
  <link rel="icon" href="${pageContext.request.contextPath}/resources/assets/images/favicon-32x32.png"
        type="image/png">
  <!--bootstrap css-->
  <link href="${pageContext.request.contextPath}/resources/assets/css/bootstrap-extended.css" rel="stylesheet">
  <link href="${pageContext.request.contextPath}/resources/assets/css/bootstrap.min.css" rel="stylesheet">
  <!--fonts-->
  <link href="https://fonts.googleapis.com/css2?family=Noto+Sans:wght@300;400;500;600&display=swap" rel="stylesheet">
  <link href="https://fonts.googleapis.com/css?family=Material+Icons+Outlined" rel="stylesheet">
</head>

<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
  <div class="container-fluid">
    <a class="navbar-brand" href="#">Blog</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav"
            aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNav">
      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
        <li class="nav-item active">
          <a class="nav-link" href="blogs">Posts</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="#">Users</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="#">My Posts</a>
        </li>
      </ul>
      <ul class="navbar-nav ms-auto">
        <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button"
             data-bs-toggle="dropdown" aria-expanded="false">
            <img src="${sessionScope.picture}" alt="user photo"
                 style="width: 30px; height: 30px; border-radius: 50%;">
          </a>
          <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="navbarDropdown">
            <li><span class="dropdown-item">${sessionScope.username}</span></li>
            <li>
              <hr class="dropdown-divider">
            </li>
            <li><a class="dropdown-item" href="logout">Logout</a></li>
          </ul>
        </li>
      </ul>

    </div>

  </div>
</nav>
<div class="container mt-4">
  <div class="position-relative">
    <div style="display: inline-block; margin-bottom: 10px;">
      <div class="btn-group " role="group" aria-label="Basic example">
        <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#addCardModal">
          Add Post
        </button>
      </div>
    </div>

  </div>
</div>
<div class="container mt-4">
  <div class="row">
    <c:forEach var="post" items="${posts}">
      <div class="col-md-4">
        <div class="card">
          <a href="post?id=${ post.getId()}">
            <img src="${post.getBanner()}" class="card-img-top" alt="..."></a>
          <div class="card-body">
            <h5 class="card-title">${post.getTitle()}</h5>
            <p class="card-text">${ post.getContent() } </p>
          </div>
        </div>
      </div>
    </c:forEach>

    <!-- End of card block -->
  </div>
</div>

<div class="modal fade" id="addCardModal" tabindex="-1" aria-labelledby="addCardModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="addCardModalLabel">Create A New Post</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        <!-- Contenu du formulaire pour ajouter une carte -->
        <form method="post" action="postCreate" enctype="multipart/form-data" id="addPostForm">
          <div class="mb-3">
            <label for="card-title" class="form-label">Title</label>
            <input type="text" class="form-control" name="title" id="card-title">
          </div>
          <div class="mb-3">
            <label for="card-content" class="form-label">Content</label>
            <textarea class="form-control" name="content" id="card-content" rows="3"></textarea>
          </div>
          <div class="mb-3">
            <label for="card-content" class="form-label">Banner</label>
            <input type="file" class="form-control" name="banner" id="card-banner">
          </div>
        </form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
        <button type="submit" class="btn btn-primary" id="savePost">Add Post</button>
      </div>
    </div>
  </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>

<script>
document.getElementById('savePost').addEventListener('click', function() {
    document.getElementById('addPostForm').submit();
});
</script>
</body>
</html>