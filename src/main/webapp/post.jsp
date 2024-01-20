<%@ page import="com.blog.beans.Post" %>
<%@ page import="com.blog.beans.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
  if (session.getAttribute("is_auth") == null) {
    response.sendRedirect("login.jsp");
  }
%>
<html>
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Blog | Post</title>
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
<!-- Banner -->


<!-- Content -->

<div class="container mt-4">
  <%
    if (request.getAttribute("post") != null) {
      Post post = (Post) request.getAttribute("post");
      User author = (User) request.getAttribute("author");
      System.out.println(post.getAuthorId() + "|" + session.getAttribute("id") + "");
      if ((post.getAuthorId() + "").equals(session.getAttribute("id") + "")) {
  %>
  <div class="position-relative">
    <div style="display: inline-block; margin-bottom: 10px;">
      <div class="btn-group " role="group" aria-label="Basic example">
        <button class="btn btn-danger mt-2 me-3" type="button" data-bs-toggle="modal"
                data-bs-target="#deleteConfirmationModal">Delete
        </button>
        <button class="btn btn-primary mt-2 me-3" type="button" data-bs-toggle="modal"
                data-bs-target="#editCardModal">Edit
        </button>
      </div>
    </div>

  </div>
  <div class="modal fade" id="deleteConfirmationModal" tabindex="-1" aria-labelledby="deleteConfirmationModalLabel"
       aria-hidden="true">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="deleteConfirmationModalLabel">Confirm Delete</h5>
          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
        </div>
        <form method="post" action="postDelete" id="deletePostForm">
          <input type="hidden" name="id" value="${post.id}">
        </form>
        <div class="modal-body">
          Are you sure you want to delete this post?
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
          <button type="button" class="btn btn-danger" id="deletePost">Delete</button>
        </div>
      </div>
    </div>
  </div>
  <div class="modal fade" id="editCardModal" tabindex="-1" aria-labelledby="addCardModalLabel" aria-hidden="true">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="addCardModalLabel">Edit A New Post</h5>
          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
        </div>
        <div class="modal-body">
          <!-- Contenu du formulaire pour ajouter une carte -->
          <form method="post" action="postUpdate" enctype="multipart/form-data" id="modifyPostForm">
            <input type="hidden" name="id" value="${post.id}">
            <div class="mb-3">
              <label for="card-title" class="form-label">Title</label>
              <input type="text" class="form-control" name="title" id="card-title" value="${post.title}">
            </div>
            <div class="mb-3">
              <label for="card-content" class="form-label">Content</label>
              <textarea class="form-control" name="content" id="card-content" rows="3">
                ${post.content}
              </textarea>
            </div>
            <div class="mb-3">
              <label for="card-content" class="form-label">Banner</label>
              <input type="file" class="form-control" name="banner" id="card-banner">
            </div>
          </form>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
          <button type="submit" class="btn btn-primary" id="modifyPost">Modify Post</button>
        </div>
      </div>
    </div>
  </div>
  <%
      }
    }
  %>

  <div class="position-relative">
    <img src="${post.banner}" class="img-fluid" alt="Banner Image">
  </div>
  <h2>${post.title}</h2>
  <h4>Author: ${author.username}</h4>
  <p>${post.content}</p>
</div>

<div class="container mt-5">
  <h2>Comments</h2>

  <!-- Comments List -->
  <div class="mb-3">

    <%--        <c:if test="${not empty comments}">--%>
    <c:forEach var="comment" items="${comments}">
      <div class="card">
        <div class="card-body">
          <h5 class="card-title">${comment.getAuthorUsername()}</h5>
          <h6 class="card-subtitle mb-2 text-muted">Commented on: ${comment.getTimestamp()}</h6>
          <p class="card-text">${comment.getContent()}</p>
        </div>
      </div>
    </c:forEach>
    <%--        </c:if>--%>
    <c:if test="${empty comments}">
      <p>No comments yet!</p>
    </c:if>

  </div>

  <!-- Repeat the above div for more comments -->

  <!-- New Comment Form -->
  <form method="post" action="commentAdd">
    <h4>Add a Comment</h4>
    <input type="hidden" name="postId" value="${post.id}">

    <div class="mb-3">
      <label for="newComment" class="form-label">Comment</label>
      <textarea class="form-control" id="newComment" name="content" rows="3"
                placeholder="Type your comment here"></textarea>
    </div>
    <button type="submit" class="btn btn-primary">Post Comment</button>
  </form>
</div>


<!-- Bootstrap Bundle with Popper -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>

</body>

<script>
document.getElementById('modifyPost').addEventListener('click', function() {
    document.getElementById('modifyPostForm').submit();
});

document.getElementById('deletePost').addEventListener('click', function() {
    document.getElementById('deletePostForm').submit();
});


</script>
</html>
