/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package handlers;

import com.fasterxml.jackson.databind.ObjectMapper;
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
        ArrayList<Person> out = persons.getAllAdministrators();
        response.setContentType("application/json");
        response.setHeader("Access-Control-Allow-Origin", "*");
        System.out.println(new ObjectMapper().writeValueAsString(out));
        response.getWriter().write(new ObjectMapper().writeValueAsString(out));
        
        
    }
    
}
