<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false" language="java"
	contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" isErrorPage="false"%>

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
           	   <a class="nav-link active" href="#">Wishlist</a>
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
            <br><br>
            <h1 class="h1 mt-4 font-weight-normal text-center" style="font-family: 'Kaushan Script';">Wishlist</h1>
            <div class="table-responsive-xl  shadow-lg p-1 bg-white rounded">
	           <table class="table table-striped ">
					<thead class="thead-dark">
					  <tr class="text-center">
					    <th scope="col">PRODUCT NAME</th>
						<th scope="col">PRODUCT PRICE</th>
						<th scope="col">PRODUCT PICTURE</th>
						<th scope="col">REMOVE</th>
					</thead>
						
					<tbody>
						<c:forEach var="product" items="${products}">
						  <tr class="font-weight-bold text-center">
							<td class="align-middle"><a href="productDetails?productId=${product.productId}">${product.productName}</a></td>
							<td class="align-middle">${product.productPrice}</td>
							<td class="align-middle"> <img src="LoadProductImage?productId=${product.productId}" height="124" width="124" /></td>
							<td class="align-middle">[<a href="removeFromWishList?productId=${product.productId}">X</a>]</td>
						  </tr>
						</c:forEach>
					</tbody>
				</table>
       	 	</div>
         </div>
     </div>
     
     <div class="row p-3">
        <div class="col">
            <div class="row text-center">
                <div class="col col-6 shadow-lg p-1 bg-dark rounded">
                    <a class=" btn btn-light btn-block disabled text-dark font-weight-bold" >Total Products : ${totalCount}</a>
                </div>
                <div class="col col-6 shadow-lg p-1 bg-white rounded">
               		<a class=" btn btn-success btn-block" href="clearWishList">Clear All</a>
                </div>
            </div>
	    </div>
     </div>
     
     <div class="row">
        <br><br><br><br>
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