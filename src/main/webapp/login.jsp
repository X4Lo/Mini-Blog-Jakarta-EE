<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    if (session.getAttribute("is_auth") != null) {
        response.sendRedirect("blogs.jsp");
    }
%>

<html lang="en" data-bs-theme="light">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Blog | Login</title>
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


<!--authentication-->

<div class="mx-3 mx-lg-0">

    <div class="card my-5 col-xl-9 col-xxl-8 mx-auto rounded-4 overflow-hidden p-4">
        <div class="row g-4">
            <div class="col-lg-6 d-flex">
                <div class="card-body">
                    <h4 class="fw-bold">Login Now And Start Posting</h4>
                    <p class="mb-0">Enter your account credentials here</p>

                    <div class="form-body mt-4">
                        <form class="row g-3" method="post" action="login">
                            <div class="col-12">
                                <label for="username" class="form-label">Username</label>
                                <input type="text" class="form-control" name="username" id="username"
                                       placeholder="Enter Username">
                            </div>
                            <div class="col-12">
                                <label for="password" class="form-label">Password</label>
                                <div class="input-group" id="show_hide_password">
                                    <input type="password" class="form-control border-end-0" name="password"
                                           id="password"
                                           placeholder="Enter Password">
                                    <a href="javascript:;" class="input-group-text bg-transparent"><i
                                            class="bi bi-eye-slash-fill"></i></a>
                                </div>
                            </div>
                            <div class="col-md-6">

                            </div>
                            <div class="col-12">
                                <div class="d-grid">
                                    <button type="submit" class="btn btn-primary">Login</button>
                                </div>
                            </div>
                            <div class="col-12">
                                <div class="text-start">
                                    <p class="mb-0">Don't have an account yet? <a href="register">Sign up here</a>
                                    </p>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
            <div class="col-lg-6 d-lg-flex d-none">
                <div class="p-3 rounded-4 w-100 d-flex align-items-center justify-content-center bg-light">
                    <img src="${pageContext.request.contextPath}/resources/assets/images/blog_logo.png"
                         class="img-fluid" alt="">
                </div>
            </div>

        </div><!--end row-->
    </div>

</div>


<!--authentication-->


<!--plugins-->
<script src="${pageContext.request.contextPath}/resources/assets/js/jquery.min.js"></script>

<script>
    $(document).ready(function () {
      $("#show_hide_password a").on('click', function (event) {
        event.preventDefault();
        if ($('#show_hide_password input').attr("type") == "text") {
          $('#show_hide_password input').attr('type', 'password');
          $('#show_hide_password i').addClass("bi-eye-slash-fill");
          $('#show_hide_password i').removeClass("bi-eye-fill");
        } else if ($('#show_hide_password input').attr("type") == "password") {
          $('#show_hide_password input').attr('type', 'text');
          $('#show_hide_password i').removeClass("bi-eye-slash-fill");
          $('#show_hide_password i').addClass("bi-eye-fill");
        }
      });
    });

</script>

</body>

</html>