package co.uk.jasdeepaulakh.resttest.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="product")
public class Product {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String name;
	private String description;
	private double price;
	private String product_image;
	
	public Product() {}
	
	public Product(String name, String description, double price, String product_image ) {
		this.name = name;
		this.description = description;
		this.price = price;
		this.product_image = product_image;
	}


	public Product(int id, String name, String description, double price, String product_image) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
		this.product_image = product_image;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getProduct_image() {
		return product_image;
	}

	public void setProduct_image(String product_image) {
		this.product_image = product_image;
	}


	
	
	
	

}
