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
    SubjectRepositoryInMemory subjects = new SubjectRepositoryInMemory();
    
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
            case "logOut":
                return new LogoutHandler();
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
            case "getHelpdeskmembers":
                return new RefreshHelpdeskmembersHandler(persons);
            case "getConversation":
                return new GetConversationHandler(persons);
            case "newConversation":
                return new NewConversationHandler(persons);
            case "refreshOpenconversations":
                return new RefreshOpenconversationsHandler();
            case "setOnline":
                return new OnlineHandler(persons);
            case "setOffline":
                return new OfflineHandler(persons);
            case "setAway":
                return new AwayHandler(persons);
            case "setCustom":
                return new CustomHandler(persons);
            case "defaulthandler":
                return new IndexHandler();
            default:
                return new IndexHandler();
        }
        }
}

