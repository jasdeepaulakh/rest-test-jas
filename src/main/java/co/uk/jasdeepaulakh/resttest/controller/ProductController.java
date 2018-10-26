package co.uk.jasdeepaulakh.resttest.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import co.uk.jasdeepaulakh.resttest.entity.Product;
import co.uk.jasdeepaulakh.resttest.repository.ProductRepository;

@RestController
public class ProductController {
	
	@Autowired
	ProductRepository productRepository;
	
	@GetMapping("/product/{id}")
	public Product getProduct(@PathVariable String id) {
		int productId = Integer.parseInt(id);
		return productRepository.findById(productId).get();
	}
	
	@GetMapping("/product")
	public List<Product> getAllProducts(){
		return productRepository.findAll();
	}
	
	@PostMapping("/product")
	public Product createProduct(@RequestBody Map<String, String> body) {
		String name = body.get("name");
		String description = body.get("description");
		double price = Double.parseDouble(body.get("price"));
		String product_image = body.get("product_image");
		return productRepository.save(new Product(name,description, price, product_image));
	}
	
	@PutMapping("/product/{id}")
	public Product updateProduct(@PathVariable String id, @RequestBody Map<String, String> body) {
		int productId = Integer.parseInt(id);
		Product product = productRepository.findById(productId).get();
		product.setName(body.get("name"));
		product.setDescription(body.get("description"));
		product.setPrice(Double.parseDouble(body.get("price")));
		product.setProduct_image(body.get("product_image"));
		return productRepository.save(product);
	}
	
	@GetMapping("/product/search/{searchTerm}")
	public List<Product> searchProduct(@PathVariable String searchTerm){
		return productRepository.findBySearchName(searchTerm);
	}
}

//************OLD Code using JDBC**************

/* @RequestMapping("/product")
public Product getProduct(@RequestParam(value="id") int id) {
	
	Product product = null;
	
	String url = "jdbc:postgresql://ec2-23-21-129-50.compute-1.amazonaws.com:5432/d8jm11dddo7ds2?sslmode=require";
	Properties props = new Properties();
	props.setProperty("user", "grlahjqfhsjkkl");
	props.setProperty("password", "514d109b01329f487e1948e488276be6943069bc2e61e9a51d682856b1f6f322");
	props.setProperty("ssl","true");
	
	Connection conn = null;
	PreparedStatement stmt = null;
	
	try {
		Class.forName("org.postgresql.Driver");
		conn = DriverManager.getConnection(url,props);
		System.out.println(conn.isClosed());
		stmt = conn.prepareStatement("SELECT * FROM products WHERE id=?");
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
	} finally {
		if(stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	return product;
	
} */


/* @PostMapping("/addproduct")
public void addProduct(@RequestBody Map<String, String> body){
	
	String name = body.get("name");
	String description = body.get("description");
	double price = Double.parseDouble(body.get("price"));
	
	String url = "jdbc:postgresql://ec2-23-21-129-50.compute-1.amazonaws.com:5432/d8jm11dddo7ds2?sslmode=require";
	Properties props = new Properties();
	props.setProperty("user", "grlahjqfhsjkkl");
	props.setProperty("password", "514d109b01329f487e1948e488276be6943069bc2e61e9a51d682856b1f6f322");
	props.setProperty("ssl","true");
	
	Connection conn = null;
	PreparedStatement stmt = null;
	
	try {
		Class.forName("org.postgresql.Driver");
		conn = DriverManager.getConnection(url,props);
		stmt = conn.prepareStatement("INSERT INTO products VALUES (DEFAULT, ?, ?, ?);");
		stmt.setString(1,name);
		stmt.setString(2, description);
		stmt.setDouble(3, price);
		stmt.executeUpdate();
	} catch (ClassNotFoundException | SQLException e) {
		System.out.println(e.getMessage());
	} finally {
		if(stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

} */
