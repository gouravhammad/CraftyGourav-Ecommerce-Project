<%@page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!doctype html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    
    <title>Crafty-Gourav</title>   
    
    <link rel='icon' href="<c:url value='images/logo.jpg' />" />
    <link rel="stylesheet" href="<c:url value='css/bootstrap.min.css' />">
    <link rel="stylesheet" href="<c:url value='customCSS/home.css' />">
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
            <a class="nav-link active" href="#">Home</a>
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

  <header>
      <div id="carouselExampleIndicators" class="carousel slide" data-ride="carousel">
        <ol class="carousel-indicators">
          <li data-target="#carouselExampleIndicators" data-slide-to="0" class="active"></li>
          <li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
          <li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
          <li data-target="#carouselExampleIndicators" data-slide-to="3"></li>
        </ol>
        <div class="carousel-inner" role="listbox">
          <!-- Slide One - Set the background image for this slide in the line below -->
          <div class="carousel-item active" style="background-image: url('./images/1.jpg')">
            <div class="carousel-caption d-none d-md-block">
              <span  class="h3 text-dark shadow-lg bg-white rounded" style="font-family: 'Kaushan Script';">&nbsp Handmade Products &nbsp</span>
              <p class="font-weight-bold" >All Products are 100% Handmade!</p>
            </div>
          </div>
          <!-- Slide Two - Set the background image for this slide in the line below -->
          <div class="carousel-item" style="background-image: url('./images/2.jpg')">
            <div class="carousel-caption d-none d-md-block">
              <span  class="h3 text-dark shadow-lg bg-white rounded" style="font-family: 'Kaushan Script';">&nbsp Greatdeals on Products &nbsp</span>
              <p class="font-weight-bold">Great prices available for all products!</p>
            </div>
          </div>
          <!-- Slide Three - Set the background image for this slide in the line below -->
          <div class="carousel-item" style="background-image: url('./images/3.jpg')">
            <div class="carousel-caption d-none d-md-block">
               <span  class="h3 text-dark shadow-lg bg-white rounded" style="font-family: 'Kaushan Script';">&nbsp Customizable Products &nbsp</span>
              <p class="font-weight-bold" >Customize Product as per your demand!</p>
            </div>
          </div>
          <!-- Slide Four - Set the background image for this slide in the line below -->
          <div class="carousel-item" style="background-image: url('./images/4.jpg')">
            <div class="carousel-caption d-none d-md-block">
               <span  class="h3 text-dark shadow-lg bg-white rounded" style="font-family: 'Kaushan Script';">&nbsp Free Delivery &nbsp</span>
              <p class="font-weight-bold" >No extra cost for delivery*!</p>
            </div>
          </div>
        </div>
        <a class="carousel-control-prev" href="#carouselExampleIndicators" role="button" data-slide="prev">
          <span class="carousel-control-prev-icon" aria-hidden="true"></span>
          <span class="sr-only">Previous</span>
        </a>
        <a class="carousel-control-next" href="#carouselExampleIndicators" role="button" data-slide="next">
          <span class="carousel-control-next-icon" aria-hidden="true"></span>
          <span class="sr-only">Next</span>
        </a>
      </div>
    </header>

  <div class="container">

      <h1 class="h1 mt-4 font-weight-normal text-center shadow-lg p-2 rounded bg-dark text-light" style="font-family: 'Kaushan Script';">Welcome &nbsp ${email}</h1><br>
  
      
      <!-- Features Section -->
      <div class="row">
        <div class="col-lg-6">
          <h2 class="shadow-lg bg-white rounded text-center p-1" style="font-family: 'Kaushan Script';">Crafty-Gourav Features:</h2>
          <p>The site deals with all Handmade Art & Craft Products</p>
          <ul>
            <li>
              <strong class="shadow bg-white rounded p-1">Customizable Products</strong>
            </li>
            <li class="mt-2">
              <strong class="shadow bg-white rounded p-1">Pure Handmade Products</strong>
            </li>
            <li class="mt-2">
              <strong class="shadow bg-white rounded p-1">Free Delivery*</strong>
            </li>
            <li class="mt-2">
              <strong class="shadow bg-white rounded p-1">Easy Return Policies</strong>
            </li>
            <li class="mt-2">
              <strong class="shadow bg-white rounded p-1">Great Deals and COD available</strong>
            </li>
          </ul>
          <p class="text-justify">This website is fully responsive so you must know that this website will look great on any device, whether it's a phone, tablet, or desktop the website will remain responsive!
             This website is user friendly. Simple navigation provided to all the users throughout the website. Incase of any query, there is a 'About' section where user can simply mail us.
          </p>
        </div>
        <div class="col-lg-6">
          <img class="img-fluid rounded shadow-lg bg-white rounded p-2" src="<c:url value='images/aboutus.jpg' />" alt="">
        </div>
      </div>
      <!-- /.row -->     
      
   
      <hr>

      <!-- Call to Action Section -->
      <div class="row mb-4">
        <div class="col-md-8 shadow-lg bg-white rounded pt-2">
          <p>Any Problem ? Feel free to contact us and we will tell you all the details and overall processing of your desired products. We don't share your personal details with anyone :)</p>
        </div>
        <div class="col-md-4">
          <a class="small btn btn-lg btn-dark btn-block font-weight-bold disabled" href="#">Mail us on gouravhammad477@gmail.com</a>
        </div>
      </div>
  
    </div>
    <!-- /.container -->
         
     
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
