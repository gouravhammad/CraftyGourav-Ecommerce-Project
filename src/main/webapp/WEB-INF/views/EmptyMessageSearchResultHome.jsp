<%@ page language="java" isELIgnored="false" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
            <li class="nav-item">
            <a class="nav-link" href="adminLogin">Admin</a>
            </li>    
          </ul>
          <form class="form-inline my-2 my-lg-0" action="searchProductHome">
            <input class="form-control mr-sm-2" type="search" name="searchKeyword" placeholder="Search Product,Category,etc" aria-label="Search">
            <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
          </form>
        </div>
  </nav>  
    
  <div class="container">
       <div class="row align-items-end">
		    <div class="col col-2">
		    </div>
		    <div class="col col-8">
		       <br><br><br><br><br>
		      <h1 class="h1 mb-3 font-weight-normal text-center" style="font-family: 'Kaushan Script';">Oops....</h1>
		        <br>
		       <p class="text-center">${errorSearchResult}!</p>
		    </div>
		    <div class="col col-2">
		    </div>
  		</div>
     
  </div>
  
  <footer class="py-5 bg-dark fixed-bottom">
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