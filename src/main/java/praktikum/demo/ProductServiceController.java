/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package praktikum.demo;

import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Lenovo
 */
@RestController
public class ProductServiceController {
    private static Map<String, product> productRepo = new HashMap<>();
    static {
      product honey = new product();
      honey.setId("1");
      honey.setName("Honey");
      honey.setQuantity("5");
      honey.setPrice("Rp. 50.000");
      productRepo.put(honey.getId(), honey);
      
      product almond = new product();
      almond.setId("2");
      almond.setName("Almond");
      almond.setQuantity("4");
      almond.setPrice("Rp. 25.000");
      productRepo.put(almond.getId(), almond);
      
      product peanut = new product();
      peanut.setId("3");
      peanut.setName("Peanut");
      peanut.setQuantity("6");
      peanut.setPrice("Rp. 15.000");
      productRepo.put(peanut.getId(), peanut);
    }
    @RequestMapping(value = "/product/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> delete(@PathVariable("id") String id){
        productRepo.remove(id);
        return new ResponseEntity<>("Product is deleted successfully", HttpStatus.OK);
    }
    
    @RequestMapping(value = "/product/ {id}", method = RequestMethod.PUT)
    public ResponseEntity<Object> updateproduct (@PathVariable("id") String id, @RequestBody product product){
      if(!productRepo.containsKey(id)){
          return new ResponseEntity<>("Product Not Found, Please check again", HttpStatus.NOT_FOUND);
      }
      else{
      productRepo.remove(id);
      product.setId(id);
      productRepo.put(id, product);
      return new ResponseEntity<>("Product is updated succesfully", HttpStatus.OK);
      }
    }
    @RequestMapping(value = "/product", method = RequestMethod.POST)
    public ResponseEntity<Object> createproduct(@RequestBody product product){
        if(productRepo.containsKey(product.getId())){
            return new ResponseEntity<>("Failed to post, Product ID cannot be the same ", HttpStatus.OK);
        }else{
        productRepo.put(product.getId(), product);
        return new ResponseEntity<>("Product is created succesfully", HttpStatus.CREATED);
        }
    }
    
    @RequestMapping(value = "/product")
    public ResponseEntity<Object> getProducts(){
        return new ResponseEntity<>(productRepo.values(), HttpStatus.OK);
    }
}

