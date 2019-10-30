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
            <a class="nav-link" href="home">Home</a>
            </li>
            <li class="nav-item">
            <a class="nav-link" href="customerLogin">Login</a>
            </li>
            <li class="nav-item">
            <a class="nav-link active" href="#">Signup</a>
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
        <br><br>
            <div class="cotainer my-form">
              <div class="row justify-content-center">
           		 <div class="col-md-8">
                    <div class="card shadow-lg p-1 bg-white rounded">
                        <div class="card-header h2 text-center">
                        	<img class="mb-4" src="<c:url value='images/logo.svg' />" alt="" width="72" height="72">
        					  <h1 class="h1 mb-3 font-weight-normal" style="font-family: 'Kaushan Script';">User Signup</h1>
                        </div>
                        <div class="card-body">
                            <spring:form action="addCustomer" modelAttribute="customer" method="POST" enctype="multipart/form-data" name="my-form">
                                <div class="form-group row">
                                    <label for="full_name" class="col-md-4 col-form-label text-md-right">Username</label>
                                    <div class="col-md-6">
                                        <spring:input class="form-control" type="text" path="userName" />
                                        <span class="text-center small"><spring:errors path="userName"/></span>
                                    </div>
                                </div>

                                <div class="form-group row">
                                    <label for="email_address" class="col-md-4 col-form-label text-md-right">Password</label>
                                    <div class="col-md-6">
                                        <spring:input class="form-control" type="password" path="password"/>
                                        <span class="text-center small"><spring:errors path="password"/></span>
                                    </div>
                                </div>

                                <div class="form-group row">
                                    <label for="user_name" class="col-md-4 col-form-label text-md-right">Email</label>
                                    <div class="col-md-6">
                                        <spring:input class="form-control" type="email" path="email"/>
                                        <span class="text-center small"><spring:errors path="email"/>${statusError}</span>
                                    </div>
                                </div>

                                <div class="form-group row">
                                    <label for="phone_number" class="col-md-4 col-form-label text-md-right">Mobile No.</label>
                                    <div class="col-md-6">
                                        <spring:input class="form-control" type="number" path="mobileNumber"/>
                                        <span class="text-center small"><spring:errors path="mobileNumber"/></span>
                                    </div>
                                </div>
                                

                                <div class="form-group row">
                                    <label for="present_address" class="col-md-4 col-form-label text-md-right">Address</label>
                                    <div class="col-md-6">
                                        <spring:textarea class="form-control" rows="3" cols="23" path="address"/>
                                        <span class="text-center small"><spring:errors path="address"/></span>
                                    </div>
                                </div>

                                <div class="form-group row">
                                    <label for="permanent_address" class="col-md-4 col-form-label text-md-right">State</label>
                                    <div class="col-md-6">
                                        <spring:select class="form-control" path="state" items="${states}"/> <spring:errors path="state" />
                                    </div>
                                </div>

                                <div class="form-group row">
                                    <label for="permanent_address" class="col-md-4 col-form-label text-md-right">City</label>
                                    <div class="col-md-6">
                                        <spring:select class="form-control" path="city" items="${cities}"/> <spring:errors path="city" />
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="permanent_address" class="col-md-4 col-form-label text-md-right">Profile Pic</label>
                                    <div class="col-md-6">
										<div class="custom-file">
										    <input type="file" class="custom-file-input" name="profilePicture">
										    <label class="custom-file-label">Choose file</label>
										    <span class="text-center small">${error}</span>
										 </div>
									</div>
                                </div>


                                    <div class="col-md-6 offset-md-4">
                                        <button type="submit" class="btn btn-primary btn-block">
                                        Register
                                        </button>
                                    </div>
 
                                 <div class="form-group row">
                                 <label class="col-md-4 col-form-label text-md-right"></label>
                                   <div class="col-md-6"> 
                                   <br>
                                   <a href="customerLogin">Already have an account ? Click here!</a>
                                   </div>
                                    
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


  

