package com.example.demo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository
public class ProductDao {
	private static final Map<String, Product> productRepo = new HashMap<String, Product>();

	static {
		initEmps();
	}

	private static void initEmps() {
		Product honey = new Product();
		honey.setId("1");
		honey.setName("Honey");
		productRepo.put(honey.getId(), honey);

		Product almond = new Product();
		almond.setId("2");
		almond.setName("Almond");
		productRepo.put(almond.getId(), almond);

		Product milk = new Product();
		milk.setId("3");
		milk.setName("Milk");
		productRepo.put(milk.getId(), milk);
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
