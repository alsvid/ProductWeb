/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.controller;
import domain.db.*;
import domain.model.*;
import handlers.ProductOverviewHandler;
import handlers.*;
import java.security.NoSuchAlgorithmException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 *
 * @author Alsvid
 */
public class HandlerFactory {
    ProductRepositoryInMemory products = new ProductRepositoryInMemory();
    PersonRepositoryInMemory persons = new PersonRepositoryInMemory();
    
    public HandlerFactory() {
      
    }
    
    public RequestHandler getHandler(String action, HttpServletRequest request, HttpServletResponse response) {
        if (action == null) {
            action = "index";
        }
        request.setAttribute("action", action);
        switch (action) {
            case "showProducts":
                return new ProductOverviewHandler(products);
            case "login":
                return new loginHandler(persons);
            case "register":
                return new registerHandler(persons);
            case "updateProduct":
                return new productUpdateHandler(products);
            case "updateProductContinued":
                return new ProductUpdateContinuedHandler(products);
            case "addProduct":
                return new AddProductHandler(products);
            case "refreshProductlist":
                return new RefreshProductlistHandler(products);
            default:
                return new IndexHandler();
        }
        }
}

