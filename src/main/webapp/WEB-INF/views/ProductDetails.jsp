<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false" language="java"
	contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<!doctype html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    
    <title>Crafty-Gourav</title>   
    
    <link rel='icon' href="<c:url value='images/logo.jpg' />" />
    <link rel="stylesheet" href="<c:url value='css/bootstrap.min.css' />">
    <link rel="stylesheet" href="<c:url value='customCSS/common.css' />">
    <link rel="stylesheet" href="<c:url value='customCSS/home.css' />">
  
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
            	<a class="nav-link" href="logoutConfirmationCustomer">Logout</a>
            </li>
          </ul>
          <form class="form-inline my-2 my-lg-0" action="searchProduct">
            <input class="form-control mr-sm-2" type="search" name="searchKeyword" placeholder="Search Product,Category,etc" aria-label="Search">
            <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
          </form>
        </div>
  </nav> 
      
  <div class="container">
	  <div class="row">
	        <div class="col">
	        </div> 
	        <div class="col text-center">
	          <h2 class="mt-4 font-weight-normal text-center" style="font-family: 'Kaushan Script';">${product.productName}</h2>
	        </div>  
	        <div class="col">
	        </div> 
	  </div>
	    
      <div class="row">
        <div class="col"></div>
	        <div class="col-lg-4 col-md-6 mb-4">
	            <div class="card h-100 shadow-lg p-1 bg-white rounded">
	              <a href="#"><img class="card-img-top" src="LoadProductImage?productId=${product.productId}" alt=""></a>
	              <div class="card-body">
	               
	                <h5  class="mark">Price : ${product.productPrice}/- Only</h5>
	                <h6>Category      : ${product.productCategory}</h6>
	                <h6>Days Required : ${product.daysRequired}</h6>
	                <h6>Pics Required : ${product.picsRequired}</h6>
	                <h6 class="mark">Description :</h6> 
	                <p class="card-text">${product.productDesc}</p>
	              </div>
	              <div class="card-footer">
	                  <div class="row">
	                      <div class="col text-center">
	                          <a class="btn btn-primary btn-block" href="addToWishList?productId=${product.productId}">Add To Wishlist</a>
	                      </div>
	                      <div class="col text-center">
	                          <a class="btn btn-secondary btn-block" href="addToCart?productId=${product.productId}">Add To Cart</a>
	                      </div>
	                  </div>
	                  <div class="row">
	                      <div class="col text-center">
	                          <br>
	                          <a class="btn btn-success btn-block" href="buyNowSingle?productId=${product.productId}">Buy Now</a>
	                          
	                      </div>
	                  </div>
	                  
	              </div>
	            </div>
	         </div>
	         
	        <div class="col"></div>
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


  

