$(document).on("click", "#btn_login", function() {
	window.location.href = "login.jsp";
});

$(document).on("click", "#btn_register", function() {
	window.location.href = "register.jsp";
});

$(document).on("click", "#btn_home", function() {
	window.location.href = "view.jsp";
});

$(document).on("click", "#btn_wishlist", function() {
	window.location.href = "wishlistview.jsp";
});

$(document).on(
		"click",
		"#btn_delete_from_cart",
		function() {
			var pname = $(this).parent().prev().prev().prev().html();
			var price = $(this).parent().prev().prev().html().slice(1);

			window.location.href = "delete_from_cart.jsp?pname=" + pname
					+ "&price=" + price + "&amount=" + "1";
		});

$(document).on(
		"click",
		"#btn_update_from_cart",
		function() {
			var pname = $(this).parent().prev().prev().prev().html();
			var price = $(this).parent().prev().prev().html().slice(1);
			var amount = $(this).parent().prev().children().val();

			window.location.href = "update_from_cart.jsp?pname=" + pname
					+ "&price=" + price + "&amount=" + amount;
		});

$(document).on(
		"click",
		"#btn_move_to_wishlist",
		function() {
			var pname = $(this).parent().prev().prev().prev().html();
			var price = $(this).parent().prev().prev().html().slice(1);

			window.location.href = "move_from_cart_to_wishlist.jsp?pname="
					+ pname + "&price=" + price + "&amount=" + "1";
		});