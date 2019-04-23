<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	    <%@ include file="parts/links.jsp" %>
	    <link rel="stylesheet" type="text/css" href="/css/PackageDetails.css">
	    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
	    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.2/css/all.css" integrity="sha384-fnmOCqbTlWIlj8LyTjo7mOUStjsKC4pOpQbqyi7RrhN7udi9RwhKkMHpvLbHG9Sr" crossorigin="anonymous">
	    <link href="https://fonts.googleapis.com/css?family=Nunito:400,700i" rel="stylesheet">
	    <link href="https://fonts.googleapis.com/css?family=Kanit" rel="stylesheet">
	    	    
	    <%@ include file="parts/scripts.jsp" %>
	    <script src="js/PackageDetails.js"></script>
	    
	    <script>
			$(document).ready(function(){
				$().UItoTop({ easingType: 'easeOutQuart' });
			});
		</script>
	    
<meta charset="ISO-8859-1">
<title>Package</title>
</head>
	<% 
	String packId = request.getParameter("packageId"); 
	%>
<body onload="packageDetail(<%=packId %>);">
	<%@ include file="parts/header.jsp" %>
	<div id="idenifier" style="display:none"><%=packId %></div>
	<div class="container main-container">
		<div class="row">
			<div class="col-7">
			<div class="feature">
				<figure class="featured-item image-holder r-3-2 transition"></figure>
			</div>
		
			<div class="gallery-wrapper">
				<div class="gallery">
						<div class="item-wrapper">
							<figure class="gallery-item image-holder r-3-2 active transition"></figure>
						</div>
						<div class="item-wrapper">
							<figure class="gallery-item image-holder r-3-2 transition"></figure>
						</div>
						<div class="item-wrapper">
							<figure class="gallery-item image-holder r-3-2 transition"></figure>
						</div>
						<div class="item-wrapper">
							<figure class="gallery-item image-holder r-3-2 transition"></figure>
						</div>
		
				</div>
			</div>
		
			<div class="controls">
				<button class="move-btn left">&larr;</button>
				<button class="move-btn right">&rarr;</button>
			</div>
		</div>
			<div class="col-5">
				<section class="site-section py-lg">
			      <div class="container">
			        <div class="row blog-entries element-animate">
			          <div class="main-content">
			            <p id="PkgName" class="mb-4">Package Name</p>
			            <div id="PkgPrice">Price</div>
			            <!-- Two hidden inputs -->
			            <input type="hidden" id="PkgId" value="" />
			            <input type="hidden" id="PkgImageArray" value="" />
			            <!-- ******************* -->
						<div class="post-content-body">
			              <div class="row">
							<label class="col-2">
								From:
							</label>
							<label id="fromlabel" class="col-10">
								DDDD-MM-YY:
							</label>
						</div>
						<div class="row">	
							<label class="col-2">
								To:
							</label>
							<label id="tolabel" class="col-10">
								DDDD-MM-YY:
							</label>
						</div>
			            </div>
			
			            <div class="post-content-body">
			              <p id="PkgDesc">Package Description</p>
			            </div>
						</div>
						
						<button type="button" class="btn btn-info">Add to Cart</button>
			          </div>
			      </div>
		    </section>
			</div>
		</div>
		
		</div>

	<%@ include file="parts/footer.jsp" %> 
	<script src="js/PackageDetails.js"></script>
</body>
</html>