<%@taglib uri="http://www.springframework.org/tags/form" prefix="spring" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page isELIgnored="false" %>

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
        <br><br>
            <div class="cotainer my-form">
              <div class="row justify-content-center">
           		 <div class="col-md-8">
                    <div class="card">
                        <div class="card-header h2 text-center">
                        	<img class="mb-4" src="<c:url value='images/logo.svg' />" alt="" width="72" height="72">
          					<h1 class="h1 mb-3 font-weight-normal" style="font-family: 'Kaushan Script';">Checkout</h1>
                        </div>
                        
                        <div class="card-body">
                           <spring:form action="placeOrderAll" modelAttribute="order" name="my-form">
                                <div class="form-group row">
			    					 <spring:input type="hidden" path="orderStatus"/>
                                    <label class="col-md-4 col-form-label text-md-right">Recipient Name:</label>
                                    <div class="col-md-6">
                                        <spring:input class="form-control" type="text" path="recipientName" />
                                        <span class="text-center small"><spring:errors path="recipientName" /></span>
                                    </div>
                                </div>
                                
                                <div class="form-group row">
                                    <label class="col-md-4 col-form-label text-md-right">Recipient Address:</label>
                                    <div class="col-md-6">
                                        <spring:textarea class="form-control" type="text" path="recipientAddress" />
                                        <span class="text-center small"><spring:errors path="recipientAddress" /></span>
                                    </div>
                                </div>
                                
                                <div class="form-group row">
                                    <label class="col-md-4 col-form-label text-md-right">Recipient City:</label>
                                    <div class="col-md-6">
                                        <spring:select class="form-control" path="recipientCity" items="${cities}"/>
                                        <span class="text-center small"><spring:errors path="recipientCity" /></span>
                                    </div>
                                </div>
                                
                                <div class="form-group row">
                                    <label class="col-md-4 col-form-label text-md-right">Recipient State:</label>
                                    <div class="col-md-6">
                                        <spring:select class="form-control" path="recipientState" items="${states}"/>
                                        <span class="text-center small"><spring:errors path="recipientState" /></span>
                                    </div>
                                </div>
                                
                                <div class="form-group row">
                                    <label class="col-md-4 col-form-label text-md-right">Recipient Mobile No:</label>
                                    <div class="col-md-6">
                                        <spring:input class="form-control" type="number" path="mobileNumber" min="1000000000" max="9999999999"/>
                                        <span class="text-center small"><spring:errors path="mobileNumber" /></span>
                                    </div>
                                </div>
                              
                                <div class="form-group row">
                                    <label class="col-md-4 col-form-label text-md-right">Payment Mode:</label>
                                    <div class="col-md-6">
                                        <spring:select class="form-control" path="paymentMode" items="${paymentModes}"/>
                                        <span class="text-center small"><spring:errors path="paymentMode" /></span>
                                    </div>
                                </div>
                                
                                <div class="form-group row">
                                    <label class="col-md-4 col-form-label text-md-right">Total Amount:</label>
                                   <div class="col-md-6">
                                        <spring:input class="form-control" type="number" path="total" readonly="true"/>
                                       
                                    </div>
                                </div>

                                <div class="col-md-6 offset-md-4">
                                    <button type="submit" class="btn btn-success btn-block">Place Order</button>
                                </div>
                             </spring:form>
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


 

