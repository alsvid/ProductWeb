/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package handlers;

import domain.db.SubjectRepositoryInMemory;
import java.io.IOException;
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
    
    public IndexHandler(SubjectRepositoryInMemory subjects) {
        this.subjects = subjects;
    }

    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("subjects", subjects.getSubjectlist());
        RequestDispatcher view = request.getRequestDispatcher("index.jsp");
        view.forward(request, response);
    }
    
    
}
