/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package handlers;

/**
 *
 * @author Alsvid
 */

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import domain.model.*;
import domain.db.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@ServerEndpoint("/subjectendpoint")
public class SubjectEndpoint {
    
    private static final Set<Session> sessions = Collections.synchronizedSet(new HashSet<Session>());
    private static final SubjectRepositoryInMemory subjects = new SubjectRepositoryInMemory();
    
    @OnOpen
    public void onOpen(Session session) {
        System.out.println(session.getId() + " opened connection");
        sessions.add(session);
    }
    
    @OnMessage
    public void onMessage(String message, Session session) throws JsonProcessingException {
        processMessage(message, session.getId());
        sendUpdatedMessage();
    }

    @OnClose
    public void onClose(Session session) {
        System.out.println(session.getId() + " has been closed");
        sessions.remove(session);
    }
    
    private void processMessage(String message, String id) {
        List<String> processedmessage = Arrays.asList(message.split("::"));
        System.out.println("Message: " + processedmessage.toString() + " from " + id);
        String writer = processedmessage.get(0);
        System.out.println(writer);
        String content = processedmessage.get(1);
        System.out.println(content);
        int subjectid = Integer.parseInt(processedmessage.get(2));
        System.out.println(subjectid);
        Comment comment = new Comment(content, writer, subjectid);
        System.out.println(comment.toString() + " has been successfully created");
        subjects.getSubjectWithId(subjectid).addCommentObjectToList(comment);
        System.out.println("Done processing message");
    }

    private void sendUpdatedMessage() throws JsonProcessingException {
        for (Session s : sessions) {
            try  {
                s.getBasicRemote().sendText(new ObjectMapper().writeValueAsString(subjects.getSubjectComments()));
            }
            catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
    
}
