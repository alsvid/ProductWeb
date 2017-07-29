/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package handlers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import domain.db.ProductRepositoryInMemory;
import domain.model.Product;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Alsvid
 */
public class RefreshProductlistHandler extends RequestHandler {

    private ProductRepositoryInMemory products = new ProductRepositoryInMemory();
    
    public RefreshProductlistHandler(ProductRepositoryInMemory products) {
        this.products = products;
    }
    
    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Product product = products.findProductWithId(Integer.parseInt(request.getParameter("productToRefresh")));
        String productJSON = this.toJSON(product);
        response.getWriter().write(productJSON);
    }
    
    private String toJSON(Product p) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(p);
    }
    
    
    
}
