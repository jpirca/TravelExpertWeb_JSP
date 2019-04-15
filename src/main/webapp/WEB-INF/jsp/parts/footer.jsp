<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
	<!--==============================footer=================================-->
	<footer>
		<div class="container_12">
			<div class="grid_12">
				<div class="socials">
					<a href="#" class="fab fa-facebook"></a>
					<a href="#" class="fab fa-twitter"></a>
					<a href="#" class="fab fa-google-plus"></a>
				</div>
				<div class="copy">
					2019 Travel Experts || Website Template Designed by <a href="http://www.JAVAmasters.com/" rel="nofollow">JAVAmaster.com</a>
				</div>
			</div>
		</div>
	</footer>
		<script src="js/header-footer.js"></script>
		<script>
			$(function (){
				$('#bookingForm').bookingForm({
					ownerEmail: '#'
				});
			})
			$(function() {
				$('#bookingForm input, #bookingForm textarea').placeholder();
			});
		</script>