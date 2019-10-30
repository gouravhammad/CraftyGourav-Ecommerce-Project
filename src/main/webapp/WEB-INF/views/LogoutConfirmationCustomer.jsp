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
            <a class="nav-link" href="customerHome">Home</a>
            </li>
             <li class="nav-item">
            	<a class="nav-link" href="showAllProducts">All Products</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="viewCustomer">Profile</a>
            </li>
            <li class="nav-item">
           	   <a class="nav-link" href="updateCustomer">Update Profile</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="viewCart">Cart</a> 
            </li>
            <li class="nav-item">
           	   <a class="nav-link" href="showWishList">Wishlist</a>
            </li>
            <li class="nav-item">
           	   <a class="nav-link" href="viewMyOrders">My Orders</a>
            </li>
            <li class="nav-item">
            	<a class="nav-link" href="showDeleteAccountConfirmation">Delete Account</a>
            </li>    
            <li class="nav-item">
            	<a class="nav-link active" href="#">Logout</a>
            </li>
          </ul>
          <form class="form-inline my-2 my-lg-0" action="searchProduct">
            <input class="form-control mr-sm-2" type="search" name="searchKeyword" placeholder="Search Product,Category,etc" aria-label="Search">
            <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
          </form>
        </div>
  </nav> 
        
    
  <div class="container">
         <div class="container">

     <div class="row">
        <div class="col col-2">
        </div> 
        <div class="col col-8 text-center mt-4">
            <br><br><br><br>
             <h2 class="h2 mb-3 font-weight-normal text-center" style="font-family: 'Kaushan Script';">Are you sure want to Logout?</h2>
        </div> 
        <div class="col col-2">
        </div> 
     </div>  
      <div class="row">
        <div class="col col-2"></div>
        <div class="col col-8 mt-4 mb-3">
                <div class="card h-100">
                  <div class="card-footer bg-white border border-white shadow-lg p-3 bg-white rounded">
                      <div class="row">
                          <div class="col text-center">
                              <a href="logout" class="btn btn-success btn-block btn-lg">Yes</a>
                          </div>
                          <div class="col text-center">
                              <a href="customerHome" class="btn btn-danger btn-block btn-lg">No</a>
                          </div>
                      </div>
                  </div>
                </div>
        </div>
        <div class="col col-2">
        </div>
      </div>
      <div class="row">
         <br><br><br><br><br><br><br>
      </div>
   </div>
  </div>
  
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


  

