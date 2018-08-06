package co.uk.jasdeepaulakh.resttest.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import co.uk.jasdeepaulakh.resttest.entity.Product;

@RestController
public class ProductController {
	
	@RequestMapping("/product")
	public Product getProduct(@RequestParam(value="id") int id) {
		
		Product product = null;
		
		String url = "jdbc:postgresql://ec2-23-21-129-50.compute-1.amazonaws.com:5432/d8jm11dddo7ds2?sslmode=require";
		Properties props = new Properties();
		props.setProperty("user", "grlahjqfhsjkkl");
		props.setProperty("password", "514d109b01329f487e1948e488276be6943069bc2e61e9a51d682856b1f6f322");
		props.setProperty("ssl","true");
		
		try {
			Class.forName("org.postgresql.Driver");
			Connection conn = DriverManager.getConnection(url,props);
			System.out.println(conn.isClosed());
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM products WHERE id=?");
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				int id2 = rs.getInt("id");
				String name = rs.getString("name");
				String desc = rs.getString("description");
				double price = rs.getDouble("price");
				System.out.println(name + desc);
				
				product = new Product(id2, name, desc, price);
			}
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println(e.getMessage());
		}
		
		
		return product;
		
	}
	
	@PostMapping("/addproduct")
	public @ResponseBody ResponseEntity<String> addProduct(){
		return new ResponseEntity<String>("POST Response", HttpStatus.OK);
	}

}
