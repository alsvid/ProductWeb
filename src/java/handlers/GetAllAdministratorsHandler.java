/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package handlers;

import com.fasterxml.jackson.databind.ObjectMapper;
import domain.db.AdminPersonAngular;
import domain.db.PersonRepositoryInMemory;
import domain.model.Person;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Alsvid
 */
public class GetAllAdministratorsHandler extends RequestHandler {

    private PersonRepositoryInMemory persons = new PersonRepositoryInMemory();
    
    public GetAllAdministratorsHandler(PersonRepositoryInMemory persons) {
        this.persons = persons;
    }
    
    
    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<Person> temp = persons.getAllAdministrators();
        ArrayList<AdminPersonAngular> out = new ArrayList<>();
        AdminPersonAngular adminangular = null;
        for (Person p : temp) {
            adminangular = new AdminPersonAngular(p.getFirstname(), p.getLastname(), p.getUserid(), p.getStatus());
            out.add(adminangular);
        }
        response.setContentType("application/json");
        System.out.println(new ObjectMapper().writeValueAsString(out));
        response.getWriter().write(new ObjectMapper().writeValueAsString(out));
        
        
    }
    
}
