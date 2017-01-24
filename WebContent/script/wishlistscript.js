$(document).on("click", "#btn_home", function() {
	window.location.href = "view.jsp";
});

$(document).on("click", "#btn_cart", function() {
	window.location.href = "cartview.jsp";
});

$(document).on(
		"click",
		"#btn_delete",
		function() {
			var pname = $(this).parent().prev().prev().html();
			var price = $(this).parent().prev().html().slice(1);

			window.location.href = "delete_from_wishlist.jsp?pname=" + pname
					+ "&price=" + price + "&amount=" + "1";
		});

$(document).on(
		"click",
		"#btn_move_to_cart",
		function() {
			var pname = $(this).parent().prev().prev().html();
			var price = $(this).parent().prev().html().slice(1);

			window.location.href = "move_from_wishlist_to_cart.jsp?pname="
					+ pname + "&price=" + price + "&amount=" + "1";
		});