<%@ page isELIgnored="false" language="java"
	contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
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
            <a class="nav-link" href="home">Home</a>
            </li>
            <li class="nav-item">
            <a class="nav-link" href="customerLogin">Login</a>
            </li>
            <li class="nav-item">
            <a class="nav-link" href="customerSignup">Signup</a>
            </li>
            <li class="nav-item active">
            <a class="nav-link" href="#">All Products</a>
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
  
  <nav class="navbar navbar-expand-lg navbar-light bg-light">
      <ul class="navbar-nav">
      <li class="nav-item active">
        <h3 class="h2 font-weight-normal" style="font-family: 'Kaushan Script';">Latest Products : &nbsp&nbsp</h3>
      </li>
      <li class="nav-item">
        <div class="dropdown">
				  <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
				    Sort by:
				  </button>
				  <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
				    <a class="dropdown-item" href="showAllFilteredProductsHome?filter=productPrice&order=ASC">Price Low</a>
				    <a class="dropdown-item" href="showAllFilteredProductsHome?filter=productPrice&order=DESC">Price High</a>
				    <a class="dropdown-item" href="showAllFilteredProductsHome?filter=picsRequired&order=ASC">Pics Req Low</a>
				    <a class="dropdown-item" href="showAllFilteredProductsHome?filter=picsRequired&order=DESC">Pics Req High</a>
				    <a class="dropdown-item" href="showAllFilteredProductsHome?filter=daysRequired&order=ASC">Days Req Low</a>
				    <a class="dropdown-item" href="showAllFilteredProductsHome?filter=daysRequired&order=DESC">Days Req High</a>
				  </div>
		</div>
      </li>
     </ul>
   </nav>
     

	  <div class="row">
      	
      	<c:forEach var="product" items="${products}">
  
	        <div class="col-lg-4 col-md-6 mb-4">
	           <div class="card h-100 shadow-lg p-1 bg-white rounded">
	              <a href="#"><img class="card-img-top" src="LoadProductImage?productId=${product.productId}" alt=""></a>
	              <div class="card-body">
	                <h4 class="card-title">
	                  <a href="productDetailsHome?productId=${product.productId}">${product.productName}</a>
	                </h4>
	                <h5>${product.productPrice}/-</h5>
	                <p class="card-text">${product.productDesc}</p>
	              </div>
	              <div class="card-footer">
	                  <div class="row">
	                      <div class="col text-center">
	                          <a class="btn btn-success btn-block" href="loginFirst">Buy Now</a>
	                      </div>
	                      <div class="col text-center">
	                          <a class="btn btn-primary btn-block" href="loginFirst">Add To Cart</a>
	                      </div>
	                  </div>
	              </div>
	            </div>
	         </div>
	       
	  	 </c:forEach>
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


  

