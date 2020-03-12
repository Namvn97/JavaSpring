package com.example.demo.com.example.demo;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductServiceController {
//	private static Map<String, Product> productRepo = new HashMap<>();
//	   static {
//	      Product honey = new Product();
//	      honey.setId("1");
//	      honey.setName("Honey");
//	      productRepo.put(honey.getId(), honey);
//	      
//	      Product almond = new Product();
//	      almond.setId("2");
//	      almond.setName("Almond");
//	      productRepo.put(almond.getId(), almond);
//	      
//	      Product milk = new Product();
//	      milk.setId("3");
//	      milk.setName("Milk");
//	      productRepo.put(milk.getId(), milk);
//	   }
//	  
//	   @RequestMapping(value = "/products/{id}", method = RequestMethod.DELETE)
//	   public ResponseEntity<Object> delete(@PathVariable("id") String id) { 
//	      productRepo.remove(id);
//	      return new ResponseEntity<>("Product is deleted successsfully", HttpStatus.OK);
//	   }
//	   
//	   @RequestMapping(value = "/products/{id}", method = RequestMethod.PUT)
//	   public ResponseEntity<Object> updateProduct(@PathVariable("id") String id, @RequestBody Product product) { 
//	      productRepo.remove(id);
//	      product.setId(id);
//	      productRepo.put(id, product);
//	      return new ResponseEntity<>("Product is updated successsfully", HttpStatus.OK);
//	   }
//	   
//	   @RequestMapping(value = "/products", method = RequestMethod.POST)
//	   public ResponseEntity<Object> createProduct(@RequestBody Product product) {
//	      productRepo.put(product.getId(), product);
//	      return new ResponseEntity<>("Product is created successfully", HttpStatus.CREATED);
//	   }
//	   
//	   @RequestMapping(value = "/products")
//	   public ResponseEntity<Object> getProduct() {
//	      return new ResponseEntity<>(productRepo.values(), HttpStatus.OK);
//	   }

	@Autowired
	private ProductDAO productDAO;
	@Autowired
	private UpdateExcelDemo excel;

	@RequestMapping(value = "/products", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	@ResponseBody
	public List<Product> getEmployees() {
		List<Product> list = productDAO.getAllProduct();
		return list;
	}

	@RequestMapping(value = "/product", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	@ResponseBody
	public Product addEmployee(@RequestBody Product product) {
		System.out.println("(Service Side) Creating employee: " + product.getName());
		return productDAO.addEmployee(product);
	}

	@RequestMapping(value = "/products", method = RequestMethod.PUT, produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	@ResponseBody
	public Product updateEmployee(@RequestBody Product product) {
		System.out.println("(Service Side) Editing employee: " + product.getName());
		return productDAO.updateEmployee(product);
	}

	@RequestMapping(value = "/product/{empNo}", method = RequestMethod.DELETE, produces = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	@ResponseBody
	public void deleteEmployee(@PathVariable("empNo") String productId) {
		productDAO.deleteEmployee(productId);
		System.out.println("Deleting employee: " + productId);
	}

	@RequestMapping(value = "/createProduct", method = RequestMethod.POST, produces = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	@ResponseBody
	public ResponseEntity<Object> addProductExcel(@RequestBody Product product) throws IOException {
		System.out.println("(Service Side) Creating product excel: " + product.getName());
		excel.createRow(product);
		System.out.println("Kết thúc tạo mới ");
		return new ResponseEntity<Object>("Product is created successsfully", HttpStatus.OK);
	}

	@RequestMapping(value = "/setStyleExcel/{empNo}", method = RequestMethod.POST, produces = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	@ResponseBody
	public ResponseEntity<Object> setStyleExcel(@PathVariable("empNo") int indexRow) throws IOException {
		excel.setStyleForOneCell(indexRow);
		System.out.println("Set style successsfully");
		return new ResponseEntity<Object>("Set style successsfully", HttpStatus.OK);
	}

	@RequestMapping(value = "/mergeCell", method = RequestMethod.POST, produces = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	@ResponseBody
	public void mergeCell() throws IOException {
		excel.mergeCell();
	}

}
