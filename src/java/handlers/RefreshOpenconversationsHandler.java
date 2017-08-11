/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package handlers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import domain.db.ProductRepositoryInMemory;
import domain.model.Conversation;
import domain.model.Person;
import domain.model.Product;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Alsvid
 */
public class RefreshOpenconversationsHandler extends RequestHandler {

    
    public RefreshOpenconversationsHandler() {
        
    }
    
    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Person accountholder = (Person) request.getSession().getAttribute("user");
        ArrayList<Conversation> conversationlist = new ArrayList<>();
        for (Conversation c : getConversations()) {
            if (c.getPerson2().equals(accountholder)) {
                conversationlist.add(c);
            }
        }
        String conversationJSON = this.toJSON(conversationlist);
        response.setContentType("application/json");
        response.getWriter().write(conversationJSON);
    }
    
    private String toJSON(ArrayList<Conversation> p) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(p);
    }
    
    
    
}
