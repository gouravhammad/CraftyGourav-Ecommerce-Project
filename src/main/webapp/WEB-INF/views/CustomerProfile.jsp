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
    <link rel="stylesheet" href="<c:url value='customCSS/signup.css' />">
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
                <a class="nav-link active" href="#">Profile</a>
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
        <br><br>
            <div class="cotainer my-form">
              <div class="row justify-content-center">
           		 <div class="col-md-8">
                    <div class="card shadow-lg p-1 bg-white rounded">
                        <div class="card-header h2 text-center">
          					<h1 class="h1 mb-3 font-weight-normal" style="font-family: 'Kaushan Script';">Profile</h1>
          					<img  class="rounded-circle shadow-sm p-3 mb-2 bg-white rounded" src="LoadCustomerDP?email=${customer.email}" height="250" width="250"/>
                        </div>
                        <div class="card-body">
                                <div class="form-group row">
                                    <label  class="col-md-4 col-form-label text-md-right h6">Username:</label>
                                    <div class="col-md-6">
                                      <input type="text" class="form-control" value=" ${customer.userName}" disabled>
                                    </div>
                                </div>

                                <div class="form-group row">
                                    <label  class="col-md-4 col-form-label text-md-right h6">Password:</label>
                                    <div class="col-md-6">
                                      <input type="text" class="form-control" value=" ${customer.password}" disabled>
                                    </div>
                                </div>

                                <div class="form-group row">
                                    <label  class="col-md-4 col-form-label text-md-right h6">Email:</label>
                                    <div class="col-md-6">
                                       <input type="text" class="form-control" value=" ${customer.email}" disabled>
                                    </div>
                                </div>

                                <div class="form-group row">
                                    <label  class="col-md-4 col-form-label text-md-right h6">Mobile No:</label>
                                    <div class="col-md-6">
                                       <input type="text" class="form-control" value=" ${customer.mobileNumber}" disabled>
                                    </div>
                                </div>
                                

                                <div class="form-group row">
                                    <label class="col-md-4 col-form-label text-md-right h6">Address:</label>
                                    <div class="col-md-6">
                                      <textarea class="form-control" rows="3" cols="23" disabled>${customer.address}</textarea>
                                    </div>
                                </div>

                                <div class="form-group row">
                                    <label class="col-md-4 col-form-label text-md-right h6">State:</label>
                                    <div class="col-md-6">
                                       <input type="text" class="form-control" value=" ${customer.state}" disabled>
                                    </div>
                                </div>

                                <div class="form-group row">
                                    <label class="col-md-4 col-form-label text-md-right h6">City:</label>
                                    <div class="col-md-6">
                                        <input type="text" class="form-control" value=" ${customer.city}" disabled>
                                    </div>
                                </div>
                               
                             </div>
                             </div>
                        </div>
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


  

