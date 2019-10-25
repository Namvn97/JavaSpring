package com.example.demo.com.example.demo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository
public class ProductDAO {
	private static final Map<String, Product> productRepo = new HashMap<String, Product>();
	private static List<Product> listProduct = new ArrayList<Product>();

	static {
		initEmps();
	}
	
    public static List<Product> listProducts() {
		return listProduct;	
    }
    
	private static void initEmps() {
		Product honey = new Product();
		honey.setId("1");
		honey.setName("Honey");
		honey.setSalary(300.0);
		honey.setGrade(1);
		productRepo.put(honey.getId(), honey);
		listProduct.add(honey);

		Product almond = new Product();
		almond.setId("2");
		almond.setName("Almond");
		almond.setSalary(200.0);
		almond.setGrade(2);
		productRepo.put(almond.getId(), almond);
		listProduct.add(almond);


		Product milk = new Product();
		milk.setId("3");
		milk.setName("Milk");
		milk.setSalary(100.5);
		milk.setGrade(1);
		productRepo.put(milk.getId(), milk);
		listProduct.add(milk);
		
		Product candy = new Product();
		candy.setId("4");
		candy.setName("Candy");
		candy.setSalary(250.25);
		candy.setGrade(5);
		productRepo.put(candy.getId(), candy);
		listProduct.add(candy);

	}
	
	  public Product getEmployee(String productID) {
	        return productRepo.get(productID);
	    }
	 
	    public Product addEmployee(Product product) {
	    	productRepo.put(product.getId(), product);
	        return product;
	    }
	 
	    public Product updateEmployee(Product product) {
	    	productRepo.put(product.getId(), product);
	        return product;
	    }
	 
	    public void deleteEmployee(String productId) {
	    	productRepo.remove(productId);
	    }
	    
	    public List<Product> getAllProduct() {
	        Collection<Product> c = productRepo.values();
	        List<Product> listProduct = new ArrayList<Product>();
	        listProduct.addAll(c);
	        return listProduct;
	    }

}
