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
import domain.db.*;
import domain.model.*;
import java.util.ArrayList;
import javax.servlet.http.HttpSession;
/**
 *
 * @author Alsvid
 */
public class loginHandler extends RequestHandler {
    
    private PersonRepositoryInMemory persons = new PersonRepositoryInMemory();
    private SubjectRepositoryInMemory subjects = new SubjectRepositoryInMemory();
    private ArrayList<Person> helpdesks = persons.getAllHelpdeskMembers();
    
    public loginHandler(PersonRepositoryInMemory persons) {
        this.persons = persons;
    }
    
    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userid = request.getParameter("userid");
        String password = request.getParameter("password");
        Person p = persons.findPersonWithUseridAndPassword(userid, password);
        HttpSession session = null;
        
        if (p != null) {
            session = request.getSession();
            session.setAttribute("loginid", userid);
            session.setMaxInactiveInterval(3600);
            
            request.setAttribute("login", true);
            request.setAttribute("loggedInPerson", p);
            request.setAttribute("subjects", subjects.getSubjectlist());
            request.setAttribute("helpdeskmembers", helpdesks);
            session.setAttribute("user", p);
            request.getRequestDispatcher("Controller?action=index").forward(request, response);
        }
        else {
            request.setAttribute("error", "Wrong username or password");
            request.getRequestDispatcher("Controller?action=index").forward(request,response);
        }
    }
    
}
