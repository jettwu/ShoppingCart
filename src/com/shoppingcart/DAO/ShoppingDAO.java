package com.shoppingcart.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.shoppingcart.model.CustomerBean;

public class ShoppingDAO {
	Connection conn;

	public ShoppingDAO() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dec2016", "root", "");
			if (conn != null) {
				System.out.println("Connected!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// login method
	public boolean login(CustomerBean cb) {
		try {
			PreparedStatement ps = conn.prepareStatement("select * from customer where cname = ? and cpwd = ?");
			ps.setString(1, cb.getCname());
			ps.setString(2, cb.getCpwd());
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	// register method
	public boolean register(CustomerBean newcb) {
		try {
			PreparedStatement ps = conn.prepareStatement("insert into customer(cname, cpwd) value (?,?)");
			ps.setString(1, newcb.getCname());
			ps.setString(2, newcb.getCpwd());
			int rowAffected = ps.executeUpdate();
			if (rowAffected == 1) {
				System.out.println("Registered new customer");
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	// showlist method
	public String showList(boolean islogin) {
		String output = "";
		try {
			PreparedStatement ps = conn.prepareStatement("select pname, price from product");
			ResultSet rs = ps.executeQuery();
			if (islogin) {
				while (rs.next()) {
					output += "<tr>" + "<td>" + rs.getString(1) + "</td>" + "<td>$" + rs.getFloat(2) + "</td>" + "<td>"
							+ "<input id='btn_add_cart' type='button' value='add to Cart' />"
							+ "<input id='btn_add_wishlist' type='button' value='add to WishList' />" + "</td>"
							+ "</tr>";
				}
			} else {
				while (rs.next()) {
					output += "<tr>" + "<td>" + rs.getString(1) + "</td>" + "<td>$" + rs.getFloat(2) + "</td>" + "<td>"
							+ "<input id='btn_add_cart_GUEST' type='button' value='add to Cart' />" + "</td>" + "</tr>";
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return output;
	}

	// show cartlist method
	public String showCartList(String cname) {
		String output = "";
		try {
			PreparedStatement ps = conn.prepareStatement(
					"select p.pname, p.price, l.amount from list as l inner join product as p on l.pid = p.pid where l.cid = (select c.cid from customer as c where c.cname = ?) and l.cow = ?");
			ps.setString(1, cname);
			ps.setString(2, "c");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				output += "<tr>" + "<td>" + rs.getString(1) + "</td>" + "<td>" + "$ " + rs.getFloat(2) + "</td>"
				// + "<td>" + rs.getInt(3) + "</td>"
						+ "<td><input id='text_amount' type='text' value='" + rs.getInt(3) + "' /></td>"
						+ "<td><input id='btn_move_to_wishlist' type='button' value='MoveToWishList' /><input id='btn_update_from_cart' type='button' value='Update' /><input id='btn_delete_from_cart' type='button' value='Delete' /></td>"
						+ "</tr>";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return output;
	}

	// show wishlist method
	public String showWishList(String cname) {
		String output = "";
		try {
			PreparedStatement ps = conn.prepareStatement(
					"select p.pname, p.price from list as l inner join product as p on l.pid = p.pid where l.cid = (select c.cid from customer as c where c.cname = ?) and l.cow = ?");
			ps.setString(1, cname);
			ps.setString(2, "w");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				output += "<tr>" + "<td>" + rs.getString(1) + "</td>" + "<td>" + "$ " + rs.getFloat(2) + "</td>"
						+ "<td><input id ='btn_move_to_cart' type='button' value='MoveToCart' /><input id='btn_delete' type='button' value='Delete' /></td>"
						+ "</tr>";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return output;
	}

	// add to cart method
	public boolean moveAddToCart(Boolean islogin, String cname, String pname) {
		ArrayList<String> clist = new ArrayList<>();
		ArrayList<String> wlist = new ArrayList<>();
		if (islogin) {
			try {
				PreparedStatement ps = conn.prepareStatement(
						"select p.pname, p.price, l.amount from list as l inner join product as p on l.pid = p.pid where l.cid = (select c.cid from customer as c where c.cname = ?) and l.cow = ?");
				ps.setString(1, cname);
				ps.setString(2, "w");
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					wlist.add(rs.getString(1));
				}
				if (wlist.contains(pname)) {
					PreparedStatement ps1 = conn.prepareStatement(
							"update list set amount =1, cow = 'c' where cid = (select cid from customer where cname = ?) and pid = (select pid from product where pname = ?)");
					ps1.setString(1, cname);
					ps1.setString(2, pname);
					int rowModified = ps1.executeUpdate();
					if (rowModified == 1) {
						System.out.println("Move product from wish list to cart");
						return true;
					} else {
						System.out.println("Error to move product from wish list to cart");
						return false;
					}
				} else {
					PreparedStatement ps2 = conn.prepareStatement(
							"select p.pname, p.price, l.amount from list as l inner join product as p on l.pid = p.pid where l.cid = (select c.cid from customer as c where c.cname = ?) and l.cow = ?");
					ps2.setString(1, cname);
					ps2.setString(2, "c");
					ResultSet rs2 = ps2.executeQuery();
					while (rs2.next()) {
						clist.add(rs2.getString(1));
					}
					if (clist.contains(pname)) {
						PreparedStatement ps3 = conn.prepareStatement(
								"update list set amount = amount+1 where cid= (select cid from customer where cname=?) and pid = (select pid from product where pname = ?)");
						ps3.setString(1, cname);
						ps3.setString(2, pname);
						int rowModify = ps3.executeUpdate();
						if (rowModify == 1) {
							System.out.println("Product in cart amount+1");
							return true;
						} else {
							System.out.println("Error in updating existing product!");
							return false;
						}
					} else {
						PreparedStatement ps3 = conn.prepareStatement(
								"insert into list(cid, pid, amount, cow) value ((select cid from customer where cname = ?),(select pid from product where pname = ?),?,?)");
						ps3.setString(1, cname);
						ps3.setString(2, pname);
						ps3.setInt(3, 1);
						ps3.setString(4, "c");
						int rowModify = ps3.executeUpdate();
						if (rowModify == 1) {
							System.out.println("Add new product to cart");
							return true;
						} else {
							System.out.println("Error to add new product to cart!");
							return false;
						}
					}

				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("NOT login!");
			return false;
		}
		return false;
	}

	// add to wish list
	public boolean moveAddToWishList(String cname, String pname) {
		ArrayList<String> clist = new ArrayList<>();
		ArrayList<String> wlist = new ArrayList<>();
		try {
			PreparedStatement ps = conn.prepareStatement(
					"select p.pname, p.price, l.amount from list as l inner join product as p on l.pid = p.pid where l.cid = (select c.cid from customer as c where c.cname = ?) and l.cow = ?");
			ps.setString(1, cname);
			ps.setString(2, "c");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				clist.add(rs.getString(1));
			}
			if (clist.contains(pname)) {
				PreparedStatement ps1 = conn.prepareStatement(
						"update list set amount =1, cow = 'w' where cid = (select cid from customer where cname = ?) and pid = (select pid from product where pname = ?)");
				ps1.setString(1, cname);
				ps1.setString(2, pname);
				int rowModified = ps1.executeUpdate();
				if (rowModified == 1) {
					System.out.println("Move product from cart to wish list");
					return true;
				} else {
					System.out.println("Error to move product from cart to wish list");
					return false;
				}
			} else {
				PreparedStatement ps2 = conn.prepareStatement(
						"select p.pname, p.price, l.amount from list as l inner join product as p on l.pid = p.pid where l.cid = (select c.cid from customer as c where c.cname = ?) and l.cow = ?");
				ps2.setString(1, cname);
				ps2.setString(2, "w");
				ResultSet rs2 = ps2.executeQuery();
				while (rs2.next()) {
					wlist.add(rs2.getString(1));
				}
				if (!wlist.contains(pname)) {
					PreparedStatement ps3 = conn.prepareStatement(
							"insert into list(cid, pid, amount, cow) value ((select cid from customer where cname = ?),(select pid from product where pname = ?),?,?)");
					ps3.setString(1, cname);
					ps3.setString(2, pname);
					ps3.setInt(3, 1);
					ps3.setString(4, "w");
					int rowModify = ps3.executeUpdate();
					if (rowModify == 1) {
						System.out.println("Add new product to wish list!");
						return true;
					} else {
						System.out.println("Error to add new product to wish list!");
						return false;
					}
				} else {
					System.out.println("Already in the wish list!");
					return false;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	// Delete product from cart or wish list
	public boolean delete(String cname, String pname) {
		try {
			PreparedStatement ps = conn.prepareStatement(
					"delete from list where cid = (select cid from customer where cname = ?) and pid = (select pid from product where pname = ?)");
			ps.setString(1, cname);
			ps.setString(2, pname);
			int rowModified = ps.executeUpdate();
			if (rowModified == 1) {
				System.out.println("Delete product successfully!");
				return true;
			} else {
				System.out.println("Error to delete product!");
				return false;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	// Update product amount in cart
	public boolean update(String cname, String pname, int amount) {
		ArrayList<String> clist = new ArrayList<>();
		try {
			PreparedStatement ps = conn.prepareStatement(
					"select p.pname, p.price, l.amount from list as l inner join product as p on l.pid = p.pid where l.cid = (select c.cid from customer as c where c.cname = ?) and l.cow = ?");
			ps.setString(1, cname);
			ps.setString(2, "c");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				clist.add(rs.getString(1));
			}
			if (clist.contains(pname)) {
				PreparedStatement ps1 = conn.prepareStatement(
						"update list set amount = ? where cid = (select cid from customer where cname = ?) and pid = (select pid from product where pname = ?)");
				ps1.setInt(1, amount);
				ps1.setString(2, cname);
				ps1.setString(3, pname);
				int rowModified = ps1.executeUpdate();
				if (rowModified == 1) {
					System.out.println("Update " + pname + " amount to " + amount);
					return true;
				} else {
					System.out.println("Failed to update product!");
					return false;
				}
			} else {
				System.out.println("Did not find product to update");
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
