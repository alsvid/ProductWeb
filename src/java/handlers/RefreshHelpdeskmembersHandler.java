/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package handlers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import domain.db.PersonRepositoryInMemory;
import domain.model.Person;
import java.util.ArrayList;
/**
 *
 * @author Alsvid
 */
public class RefreshHelpdeskmembersHandler extends RequestHandler {

    private PersonRepositoryInMemory persons = new PersonRepositoryInMemory();
    private ArrayList<Person> helpdesks = persons.getAllHelpdeskMembers();
            
    public RefreshHelpdeskmembersHandler(PersonRepositoryInMemory persons) {
        this.persons = persons;
    }

    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.getWriter().write(new ObjectMapper().writeValueAsString(helpdesks));
    }
    
}
