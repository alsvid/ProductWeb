/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package handlers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import domain.db.ConversationRepositoryInMemory;
import domain.db.PersonRepositoryInMemory;
import domain.model.Conversation;
import domain.model.Person;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

/**
 *
 * @author Alsvid
 */
public class GetConversationHandler extends RequestHandler {

    private PersonRepositoryInMemory persons = new PersonRepositoryInMemory();
    private ConversationRepositoryInMemory conversations = new ConversationRepositoryInMemory();
    
    public GetConversationHandler(PersonRepositoryInMemory persons) {
        this.persons = persons;
    }
    
    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String dataString = request.getParameter("data");
        
        JSONParser parser = new JSONParser();
        Object object = null;
        try {
            object = parser.parse(dataString);
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        JSONObject jsondata = (JSONObject) object;
        
        
        Person helpdeskmember = persons.findPersonWithUserid(jsondata.get("person").toString().trim());
        Person accountholder = (Person) request.getSession().getAttribute("user");
        
        Conversation convo = conversations.createConversation(accountholder, helpdeskmember);
        
        response.setContentType("application/json");
        response.getWriter().write(new ObjectMapper().writeValueAsString(convo.getMessagelist()));
        
    }
    
}
