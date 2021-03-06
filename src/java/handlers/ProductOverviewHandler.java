/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package handlers;
import domain.db.*;
import domain.model.*;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 *
 * @author Alsvid
 */
public class ProductOverviewHandler extends RequestHandler {
    ProductRepositoryInMemory products = new ProductRepositoryInMemory();
    
    public ProductOverviewHandler(ProductRepositoryInMemory products) {
        this.products = products;
    }
    
    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("products", products.getProductenlijst());
        RequestDispatcher view = request.getRequestDispatcher("productoverview.jsp");
        view.forward(request, response);
    }
}
