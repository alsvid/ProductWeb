/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package handlers;

import domain.db.ProductRepositoryInMemory;
import domain.model.DomainException;
import domain.model.Product;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Alsvid
 */
public class AddProductHandler extends RequestHandler {

    private ProductRepositoryInMemory products = new ProductRepositoryInMemory();
    
    public AddProductHandler(ProductRepositoryInMemory products) {
        this.products = products;
    }

    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Product product = new Product();
        List<String> errors = checkProductInputValue(request,product);
        
        if (errors.size() > 0) {
            request.setAttribute("errors", errors);
            request.setAttribute("product", product);
            RequestDispatcher view = request.getRequestDispatcher("addproduct.jsp");
            view.forward(request, response);
        }
        else if (products.checkAvailabilityId(product.getId())) {
            errors.add("Id not available - try another one.");
            request.setAttribute("errors", errors);
            RequestDispatcher view = request.getRequestDispatcher("addproduct.jsp");
            view.forward(request, response);
        }
        else {
            products.addProduct(product);
            response.sendRedirect("Controller?action=showProducts");
        }
    }
    
    private List<String> checkProductInputValue(HttpServletRequest request, Product productToUpdate) {
        List<String> errors = new ArrayList<>();
        getProductnaam(productToUpdate,request,errors);
        getAantal(productToUpdate,request,errors);
        getStatus(productToUpdate,request,errors);
        getId(productToUpdate,request,errors);
        return errors;
    }

    private void getProductnaam(Product product, HttpServletRequest request, List<String> errors) {
        String productnaam = request.getParameter("productnaam");
		try {
			product.setProductnaam(productnaam);
		} catch (DomainException e) {
			errors.add(e.getMessage());
        }
    }

    private void getAantal(Product product, HttpServletRequest request, List<String> errors) {
        String aantal = request.getParameter("aantal");
		try {
			product.setAantal(Integer.parseInt(aantal));
		} catch (DomainException e) {
			errors.add(e.getMessage());
        }
    }

    private void getStatus(Product product, HttpServletRequest request, List<String> errors) {
        String status = request.getParameter("status");
		try {
			product.setStatus(status);
		} catch (DomainException e) {
			errors.add(e.getMessage());
        }
    }

    private void getId(Product product, HttpServletRequest request, List<String> errors) {
        String id = request.getParameter("id");
		try {
			product.setId(Integer.parseInt(id));
		} catch (DomainException e) {
			errors.add(e.getMessage());
        }
    }
}
