/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package handlers;

import domain.model.Conversation;
import domain.model.Message;
import domain.model.Person;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.parser.ParseException;
import domain.service.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
/**
 *
 * @author Alsvid
 */
public abstract class RequestHandler {
    
    private static ArrayList<Conversation> conversations = new ArrayList<>();
    
    public abstract void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
    
    public static ArrayList<Conversation> getConversations() {
        return conversations;
    }
    
    public Conversation checkIfConversationAvailable(Person person1, Person person2) {
        for (Conversation c : getConversations()) {
                if (person1.equals(c.getPerson1()) && person2.equals(c.getPerson2())) {
                    return c;
                }
                if (person1.equals(c.getPerson2()) && person2.equals(c.getPerson1())) {
                    return c;
                }
            }
            return null;
    }
    
    public void addMessageToList(Conversation convo, Message m) {
        for (Conversation c : getConversations()) {
            if (c.equals(convo)) {
            c.getMessagelist().add(m);
            }
        }
    }
    
    public ArrayList<Message> getConversationMessageList(Conversation convo) {
        for (Conversation c : getConversations()) {
            if (c.equals(convo)) {
                return c.getMessagelist();
            }
        }
        ArrayList<Message> defaultlist = new ArrayList<>();
        return defaultlist;
    }
    
    
}
