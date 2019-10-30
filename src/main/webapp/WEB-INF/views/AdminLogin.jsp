<%@taglib uri="http://www.springframework.org/tags/form" prefix="spring" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!doctype html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    
    <title>Crafty-Gourav</title>   
    
    <link rel='icon' href="<c:url value='images/logo.jpg' />" />
    <link rel="stylesheet" href="<c:url value='css/bootstrap.min.css' />">
    <link rel="stylesheet" href="<c:url value='customCSS/login.css' />">
    <link rel="stylesheet" href="<c:url value='customCSS/common.css' />">
  
  </head>
    
<body>
   <nav class="navbar fixed-top navbar-expand-lg navbar-dark bg-dark">
        <a class="navbar-brand" href="#" style="color: #fed136;"><img src="<c:url value='images/logo.svg' />" width="30" height="30" class="d-inline-block align-top" alt=""> Crafty-Gourav</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
          <ul class="navbar-nav mr-auto">
            <li class="nav-item">
            <a class="nav-link" href="home">Home</a>
            </li>
            <li class="nav-item">
            <a class="nav-link" href="customerLogin">Login</a>
            </li>
            <li class="nav-item">
            <a class="nav-link" href="customerSignup">Signup</a>
            </li>
            <li class="nav-item">
            <a class="nav-link" href="showAllProductsHome">All Products</a>
            </li>
            <li class="nav-item">
            <a class="nav-link" href="aboutHome">About</a>
            </li>
            <li class="nav-item active">
            <a class="nav-link" href="#">Admin</a>
            </li>    
          </ul>
          <form class="form-inline my-2 my-lg-0" action="searchProductHome">
            <input class="form-control mr-sm-2" type="search" name="searchKeyword" placeholder="Search Product,Category,etc" aria-label="Search">
            <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
          </form>
        </div>
  </nav>  
    
  <div class="container">
      <br><br><br>
      <spring:form action="saveAdmin" modelAttribute="admin" class="text-center form-signin shadow-lg p-3 bg-white rounded">
          <img class="mb-4" src="<c:url value='images/logo.svg' />" alt="" width="72" height="72">
          <h1 class="h1 mb-3 font-weight-normal" style="font-family: 'Kaushan Script';">Admin Login</h1>
          <label for="inputEmail" class="sr-only">Email address</label>
          <spring:input type="email" path="email" class="form-control shadow bg-white rounded" placeholder="@Email"/>
          <span class="text-center small"><spring:errors path="email"/></span>
          <label for="inputPassword" class="sr-only">Password</label>
          <spring:input type="password" path="password" class="form-control shadow mt-1 bg-white rounded" placeholder="@Password"/>
          <span class="text-center small"><spring:errors path="password"/></span>
          <button class="btn btn-lg btn-primary btn-block mt-2" type="submit">Sign in</button>
          <p class="mt-3 mb-2 text-muted">&copy; 2017-2019</p>
        </spring:form>  
  </div>
  
  <br>
  <footer class="py-5 bg-dark">
    <div class="container">
      <p class="m-0 text-center text-white">Copyright &copy; Crafty Gourav 2019</p>
    </div>
  </footer>

  <script src="<c:url value='js/bootstrap.min.js' />"></script>
  <script src="<c:url value='js/jquery-3.4.1.min.js' />"></script>
  <script src="<c:url value='js/popper.min.js' />"></script>
  <script src="<c:url value='js/bootstrap.bundle.min.js' />"></script>
    
</body>
</html>


  

