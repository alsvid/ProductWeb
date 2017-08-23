/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package handlers;

import domain.db.PersonRepositoryInMemory;
import domain.db.SubjectRepositoryInMemory;
import domain.model.Person;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Alsvid
 */
public class IndexHandler extends RequestHandler {
    
    private SubjectRepositoryInMemory subjects = new SubjectRepositoryInMemory();
    private PersonRepositoryInMemory persons = new PersonRepositoryInMemory();
    private ArrayList<Person> helpdesks = persons.getAllHelpdeskMembers();
    
    public IndexHandler() {

    }

    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Person accountholder = (Person) request.getSession().getAttribute("user");
        if (accountholder != null) {
        request.setAttribute("helpdeskmembers", helpdesks);
        request.setAttribute("subjects", subjects.getSubjectlist());
        RequestDispatcher view = request.getRequestDispatcher("index.jsp");
        view.forward(request, response);
        }
        else {
            request.setAttribute("subjects", subjects.getSubjectlist());
            RequestDispatcher view = request.getRequestDispatcher("index.jsp");
            view.forward(request, response);
        }
    }
    
    
}
