/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dam.chatserver;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;
import javax.websocket.EndpointConfig;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import model.UserWS;

/**
 *
 * @author user
 */
@ServerEndpoint("/chat/{user}")
public class ChatEndPoint {

    @OnOpen
    public void onOpen(Session session, 
      @PathParam("user") String user) {
        // si es con query string
        //user = session.getRequestParameterMap().get("user").get(0);

        session.getUserProperties().put("user",
          user);
//        try {
//            session.close();
//        } catch (IOException ex) {
//            Logger.getLogger(ChatEndPoint.class.getName()).log(Level.SEVERE, null, ex);
//        }

    }

    @OnMessage
    public void echoText(String mensaje, Session sessionQueManda) {
        for (Session s : sessionQueManda.getOpenSessions()) {
            try {
                String user = (String) sessionQueManda.getUserProperties().get("user");
                if (!s.equals(sessionQueManda)) {
                    s.getBasicRemote().sendText(user + "::" + mensaje);
                }
            } catch (IOException ex) {
                Logger.getLogger(MyEndpoint.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
