package com.example.webchatserver;


import jakarta.websocket.*;
import jakarta.websocket.server.PathParam;
import jakarta.websocket.server.ServerEndpoint;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * This class represents a web socket server, a new connection is created and it receives a roomID as a parameter
 **/
@ServerEndpoint(value = "/ws/{roomID}")
public class ChatServer {

    private static Map<String, ChatRoom> chatRooms = new HashMap<>();
    private  Map<String, String> users = new HashMap<>();
    private static Map<String, String> roomHistory = new HashMap<>();
    public ChatRoom room, chatRoom;
    private Map<String, String> usernames = new HashMap<String, String>();




    @OnOpen
    public void open(@PathParam("roomID") String roomID, Session session) throws IOException, EncodeException {

        String userId = session.getId();
        ChatRoom currentRoom = chatRooms.get(roomID);

        if(!chatRooms.containsKey(roomID)){
            room = new ChatRoom(roomID, session.getId());
            chatRooms.put(roomID, room);

        }else{

            chatRooms.get(roomID).setUserName(session.getId(), "");

        }

        String messageFormat = "{\"type\": \"chat\", \"message\":\"(Server %s): Welcome. Please enter your name please\"}";
        String message = String.format(messageFormat, roomID);
        session.getBasicRemote().sendText(message);

    }




    @OnClose
    public void close(@PathParam("roomID") String roomID, Session session) throws IOException, EncodeException {

        String userId = session.getId();
        room = chatRooms.get(roomID); chatRoom = chatRooms.get(roomID);
        String leaveMessageFormat = "{\"type\": \"chat\", \"message\":\" %s has left the room.\"}";
        String username = chatRoom.getUsers().get(userId);
        room.removeUser(userId);

        for (Session peer : session.getOpenSessions()) {
            if (chatRoom.inRoom(peer.getId())) {
                String leaveMessage = String.format(leaveMessageFormat, username);
                peer.getBasicRemote().sendText(leaveMessage);
            }}

    }










    @OnMessage
    public void handleMessage( @PathParam("roomID") String roomID,String comm, Session session) throws IOException, EncodeException {
       // Map<String,String> allUsers = currentRoom.getUsers();
        String userID = session.getId();
        JSONObject jsonmsg = new JSONObject(comm);
        String message = (String) jsonmsg.get("msg");
        chatRoom = chatRooms.get(roomID);
        Map<String,String> allUsers = chatRoom.getUsers();
        if(!Objects.equals(allUsers.get(userID), "")){
            String name = allUsers.get(userID);
            String messageFormat = "{\"type\": \"chat\", \"message\":\"(%s): %s\"}";

            for (Session peer : session.getOpenSessions()) {
                if (chatRoom.inRoom(peer.getId())) {
                    String formattedMessage = String.format(messageFormat, name, message);
                    peer.getBasicRemote().sendText(formattedMessage);
                }
            }}



        else{
            chatRoom.setUserName(session.getId(), message);
            String welcomeMessageFormat = "{\"type\": \"chat\", \"message\":\"(Server %s): Welcome, %s!\"}";
            String welcomeMessage = String.format(welcomeMessageFormat, roomID, message);
            session.getBasicRemote().sendText(welcomeMessage);
            String joinMessageFormat = "{\"type\": \"chat\", \"message\":\"(Server): %s joined the chat room.\"}";

            for (Session peer : session.getOpenSessions()) {
                if ((!peer.getId().equals(userID)) && chatRoom.inRoom(peer.getId())) {
                    String joinMessage = String.format(joinMessageFormat, message);
                    peer.getBasicRemote().sendText(joinMessage);
                }
            }
        }

    }




}




