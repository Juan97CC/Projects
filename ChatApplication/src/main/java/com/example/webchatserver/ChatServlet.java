package com.example.webchatserver;

import java.io.*;
import java.util.HashSet;
import java.util.Set;
import java.util.StringJoiner;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.apache.commons.lang3.RandomStringUtils;

/**
 * This is a class that has services
 * In our case, we are using this to generate unique room IDs**/
@WebServlet(name = "chatServlet", value = {"/chat-servlet", "/chat-servlet/allRooms"})
public class ChatServlet extends HttpServlet {
    private String message;

    //static so this set is unique
    public static Set<String> roomsHashSet = new HashSet<>();



    /**
     * Method generates unique room codes
     * **/
    public String generatingRandomUpperAlphanumericString(int length) {
        String generatedString = RandomStringUtils.randomAlphanumeric(length).toUpperCase();
        // generating unique room code
        while (roomsHashSet.contains(generatedString)){
            generatedString = RandomStringUtils.randomAlphanumeric(length).toUpperCase();
        }
        roomsHashSet.add(generatedString);

        return generatedString;
    }




    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String path = request.getServletPath();


        if (path == null || path.equals("/") || path.equals("/chat-servlet")) {
            response.setContentType("text/plain");
            PrintWriter out = response.getWriter();
            out.println(generatingRandomUpperAlphanumericString(5));


        } else if (path.equals("/chat-servlet/allRooms")) {
            response.setContentType("application/json");
            PrintWriter out = response.getWriter();
            out.println(allRooms());
        }

    }

    public void destroy() {
    }

    public String allRooms() {
        StringJoiner roomsJoiner = new StringJoiner("\",\"", "[\"", "\"]");
        for (String room : roomsHashSet) {
            roomsJoiner.add(room);
        }
        return "{\"rooms\": " + roomsJoiner.toString() + "}";
    }




}