/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package handlers;

import domain.db.PersonRepositoryInMemory;
import domain.model.Person;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Alsvid
 */
public class CustomHandler extends RequestHandler {

   private PersonRepositoryInMemory persons = new PersonRepositoryInMemory();
    
    public CustomHandler(PersonRepositoryInMemory persons) {
        this.persons = persons;
    }

    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Person accountholder = (Person) request.getSession().getAttribute("user");
        String custom = request.getParameter("customstatus");
        for (Person p : persons.getPersonlist()) {
            if (p.equals(accountholder)) {
                p.setStatus(custom);
            }
        }
        response.sendRedirect("Controller?action=defaulthandler");
    }
    
}
