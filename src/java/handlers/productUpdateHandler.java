/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package handlers;

import domain.db.ProductRepositoryInMemory;
import domain.model.Product;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Alsvid
 */
public class productUpdateHandler extends RequestHandler {
    
    ProductRepositoryInMemory products = new ProductRepositoryInMemory();

    public productUpdateHandler(ProductRepositoryInMemory products) {
        this.products = products;
    }

    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("productIdToUpdate"));
        Product product = products.findProductWithId(id);
        request.setAttribute("productPlaceHolder", product);
        RequestDispatcher view = request.getRequestDispatcher("productupdate.jsp");
        view.forward(request, response);
    }
    
}
