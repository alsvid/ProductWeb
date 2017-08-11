/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package handlers;

import com.fasterxml.jackson.databind.ObjectMapper;
import domain.db.ConversationRepositoryInMemory;
import domain.db.PersonRepositoryInMemory;
import domain.model.Conversation;
import domain.model.Message;
import domain.model.Person;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
/**
 *
 * @author Alsvid
 */
public class NewConversationHandler extends RequestHandler {
    
    private PersonRepositoryInMemory persons = new PersonRepositoryInMemory();
    
    public NewConversationHandler(PersonRepositoryInMemory persons) {
        this.persons = persons;
    }

    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String dataString = request.getParameter("data");
        JSONParser parser = new JSONParser();
        Object object = new Object();
        try {
            object = parser.parse(dataString);
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        JSONObject jsondata = (JSONObject) object;
        
        Person helpdeskmember = persons.findPersonWithUserid(jsondata.get("person").toString().trim());
        Person accountholder = (Person) request.getSession().getAttribute("user");
        
        
        Message newMessage = new Message(accountholder.getUserid(),jsondata.get("message").toString());
       
        Conversation convo = checkIfConversationAvailable(accountholder,helpdeskmember);
        
        if (convo == null) {
            convo = new Conversation(accountholder, helpdeskmember);
            convo.addMessage(newMessage);
            getConversations().add(convo);
        }
        else {
            addMessageToList(convo,newMessage);
        }
            
        
        
        response.setContentType("application/json");
        response.getWriter().write(new ObjectMapper().writeValueAsString(getConversationMessageList(convo)));
        
    }  
    
}
