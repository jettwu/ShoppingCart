$(document).on("click", "#btn_login", function() {
	window.location.href = "login.jsp";
});

$(document).on("click", "#btn_logout", function() {
	window.location.href = "logout.jsp";
});

$(document).on("click", "#btn_register", function() {
	window.location.href = "register.jsp";
});

$(document).on("click", "#btn_cart", function() {
	window.location.href = "cartview.jsp";
});

$(document).on("click", "#btn_wishlist", function() {
	window.location.href = "wishlistview.jsp";
});

$(document).on(
		"click",
		"#btn_add_cart",
		function() {
			var pname = $(this).parent().prev().prev().html();
			var price = $(this).parent().prev().html().slice(1);
			alert("Add 1 Qty "+pname +" $"+ price+" to the cart!");

			window.location.href = "add_to_cart.jsp?pname=" + pname + "&price="
					+ price + "&amount=" + "1";
		});

$(document).on("click","#btn_add_wishlist",function(){
			var pname = $(this).parent().prev().prev().html();
			var price = $(this).parent().prev().html().slice(1);
			alert("Add 1 Qty " + pname + " $" + price + " to the wish list!");

			window.location.href = "move_add_to_wishlist.jsp?pname=" + pname
					+ "&price=" + price + "&amount=" + "1";
	
});






