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
            <a class="nav-link" href="adminHome">Home</a>
            </li>
             <li class="nav-item">
            	<a class="nav-link" href="showAddProduct">Add Product</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="manageProducts">Manage Products</a>
            </li>
            <li class="nav-item">
           	   <a class="nav-link" href="manageCustomers">Manage Customers</a>
            </li>
            <li class="nav-item active">
                <a class="nav-link" href="#">Manage Orders</a>
            </li>
            <li class="nav-item">
           	   <a class="nav-link" href="logoutConfirmationAdmin">Logout</a>
            </li>
          </ul>
          <form class="form-inline my-2 my-lg-0" action="searchProductAdmin">
            <input class="form-control mr-sm-2" type="search" name="searchKeyword" placeholder="Search Product,Category,etc" aria-label="Search">
            <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
          </form>
        </div>
  </nav> 
    
  <div class="container">
     <div class="row">
        <div class="col">
            <br><br><br>
            <h1 class="h1 mb-3 font-weight-normal text-center" style="font-family: 'Kaushan Script';">Current Orders</h1>
            <div class="table-responsive-xl  shadow-lg p-1 bg-white rounded">
	           <table class="table table-striped ">
				   <thead class="thead-dark">
				     <tr class="small text-center">
				        <th class="align-top" scope="col">PRODUCT</th>
						<th class="align-top" scope="col">RECIPIENT NAME</th>
						<th class="align-top" scope="col">RECIPIENT ADDRESS</th>
						<th class="align-top" scope="col">RECIPIENT CITY</th>
						<th class="align-top" scope="col">RECIPIENT STATE</th>
						<th class="align-top" scope="col">RECIPIENT MOBILE NO</th>
						<th class="align-top" scope="col">DATE & TIME</th>
						<th class="align-top" scope="col">PAYMENT MODE</th>
						<th class="align-top" scope="col">QUANTITY</th>
						<th class="align-top" scope="col">TOTAL AMOUNT</th>
						<th class="align-top" scope="col">ORDER STATUS</th>
						<th class="align-top" scope="col">CANCEL ORDER</th>
					 </tr>
					</thead>
					
					<tbody>
						<c:forEach var="ordered" items="${orderedOrders}">
							<tr class="small font-weight-bold text-center ">
								<td><a href="productDetailsAdmin?productId=${ordered.productId}">Product Details</a></td>
								<td>${ordered.recipientName}</td>
								<td>${ordered.recipientAddress}</td>
								<td>${ordered.recipientCity}</td>
								<td>${ordered.recipientState}</td>
								<td>${ordered.mobileNumber}</td>
								<td>${ordered.dateAndTime}</td>
								<td>${ordered.paymentMode}</td>
								<td>${ordered.quantity}</td>
								<td>${ordered.total}</td>
								<td>${ordered.orderStatus}</td>
								<td>[<a href="showSetOrderStatus?orderId=${ordered.orderId}">Set</a>]</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
        </div>
     </div>
     
    <div class="row">
        <div class="col">
            <br>
            <h1 class="h1 mb-3 font-weight-normal text-center" style="font-family: 'Kaushan Script';">Delivered Orders</h1>
            <div class="table-responsive-xl  shadow-lg p-1 bg-white rounded">
	           <table class="table table-striped">
				<thead class="thead-dark">
				  <tr class="small text-center">
				  	    <th class="align-top" scope="col">PRODUCT</th>
						<th class="align-top" scope="col">RECIPIENT NAME</th>
						<th class="align-top" scope="col">RECIPIENT ADDRESS</th>
						<th class="align-top" scope="col">RECIPIENT CITY</th>
						<th class="align-top" scope="col">RECIPIENT STATE</th>
						<th class="align-top" scope="col">RECIPIENT MOBILE NO</th>
						<th class="align-top" scope="col">DATE & TIME</th>
						<th class="align-top" scope="col">PAYMENT MODE</th>
						<th class="align-top" scope="col">QUANTITY</th>
						<th class="align-top" scope="col">TOTAL AMOUNT</th>
						<th class="align-top" scope="col">ORDER STATUS</th>
				</thead>
					
				<tbody>
					<c:forEach var="delivered" items="${deliveredOrders}">
						<tr class="small font-weight-bold text-center">
							<td><a href="productDetailsAdmin?productId=${delivered.productId}">Product Details</a></td>
							<td>${delivered.recipientName}</td>
							<td>${delivered.recipientAddress}</td>
							<td>${delivered.recipientCity}</td>
							<td>${delivered.recipientState}</td>
							<td>${delivered.mobileNumber}</td>
							<td>${delivered.dateAndTime}</td>
							<td>${delivered.paymentMode}</td>
							<td>${delivered.quantity}</td>
							<td>${delivered.total}</td>
							<td>${delivered.orderStatus}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
        </div>
       </div>
     </div>
     
     <div class="row">
        <div class="col">
            <br>
            <h1 class="h1 mb-3 font-weight-normal text-center" style="font-family: 'Kaushan Script';">Canceled Orders</h1>
            <div class="table-responsive-xl  shadow-lg p-1 bg-white rounded">
	           <table class="table table-striped ">
				<thead class="thead-dark">
				  <tr class="small text-center">
				       <th class="align-top" scope="col">PRODUCT</th>
						<th class="align-top" scope="col">RECIPIENT NAME</th>
						<th class="align-top" scope="col">RECIPIENT ADDRESS</th>
						<th class="align-top" scope="col">RECIPIENT CITY</th>
						<th class="align-top" scope="col">RECIPIENT STATE</th>
						<th class="align-top" scope="col">RECIPIENT MOBILE NO</th>
						<th class="align-top" scope="col">DATE & TIME</th>
						<th class="align-top" scope="col">PAYMENT MODE</th>
						<th class="align-top" scope="col">QUANTITY</th>
						<th class="align-top" scope="col">TOTAL AMOUNT</th>
						<th class="align-top" scope="col">ORDER STATUS</th>
				</thead>
					
				<tbody>
					<c:forEach var="cancel" items="${canceledOrders}">
						<tr class="small font-weight-bold text-center ">
							<td><a href="productDetailsAdmin?productId=${cancel.productId}">Product Details</a></td>
							<td>${cancel.recipientName}</td>
							<td>${cancel.recipientAddress}</td>
							<td>${cancel.recipientCity}</td>
							<td>${cancel.recipientState}</td>
							<td>${cancel.mobileNumber}</td>
							<td>${cancel.dateAndTime}</td>
							<td>${cancel.paymentMode}</td>
							<td>${cancel.quantity}</td>
							<td>${cancel.total}</td>
							<td>${cancel.orderStatus}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
        </div>
       </div>
     </div>
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