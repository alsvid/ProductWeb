/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package handlers;

import domain.db.PersonRepositoryInMemory;
import domain.model.DomainException;
import domain.model.Person;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Alsvid
 */
public class registerHandler extends RequestHandler {
    
    private PersonRepositoryInMemory persons = new PersonRepositoryInMemory();
    
    public registerHandler(PersonRepositoryInMemory persons) {
        this.persons = persons;
    }

    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Person person = new Person();
        List<String> errors = checkInputValuesPerson(request,person,true);
        
        if (errors.size() > 0) {
            request.setAttribute("errors", errors);
            request.setAttribute("person", person);
            RequestDispatcher view = request.getRequestDispatcher("register.jsp");
            view.forward(request, response);
        }
        else if (persons.findPersonWithUserid(person.getUserid()) != null) {
            errors.add("Username not available - try another one.");
            request.setAttribute("errors", errors);
            RequestDispatcher view = request.getRequestDispatcher("register.jsp");
            view.forward(request, response);
        }
        else {
            person.setRole(Person.ROLE.CLIENT);
            persons.addPerson(person);
            RequestDispatcher view = request.getRequestDispatcher("index.jsp");
            view.forward(request,response);
        }
        
    }

    private List<String> checkInputValuesPerson(HttpServletRequest request, Person person, boolean b) {
        List<String> errors = new ArrayList<>();
        getUserid(person,request,errors);
        getFirstname(person,request,errors);
        getLastName(person,request,errors);
        if (b) {
            getPassword(person, request, errors);
        }
        return errors;
    }

    private void getUserid(Person person, HttpServletRequest request, List<String> errors) {
        String userid = request.getParameter("userid");
		try {
			person.setUserid(userid);
		} catch (DomainException e) {
			errors.add(e.getMessage());
        }
    }

    private void getFirstname(Person person, HttpServletRequest request, List<String> errors) {
        String name = request.getParameter("firstName");
		try {
			person.setFirstname(name);
		} catch (DomainException e) {
			errors.add(e.getMessage());
		}
    }

    private void getLastName(Person person, HttpServletRequest request, List<String> errors) {
        String name = request.getParameter("lastName");
		try {
			person.setLastname(name);
		} catch (DomainException e) {
			errors.add(e.getMessage());
		}
    }

    private void getPassword(Person person, HttpServletRequest request, List<String> errors) {
        String pass = request.getParameter("password");
		try {
			person.setPassword(pass);
		} catch (DomainException e) {
			errors.add(e.getMessage());
		}
    }
    
    
    
}
