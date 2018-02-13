/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dam.chatserver;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
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
@ServerEndpoint("/chat/{user}/{pass}")
public class ChatEndPoint {

    @OnOpen
    public void onOpen(Session session,
      @PathParam("user") String user,
      @PathParam("pass") String pass) {
        // si es con query string
        //user = session.getRequestParameterMap().get("user").get(0);

        session.getUserProperties().put("user",
          user);
        if (!user.equals("google")) {
            session.getUserProperties().put("login",
              "OK");
        } else {
            session.getUserProperties().put("login",
              "NO");
        }

//        try {
//          if ! login ok 
//            session.close();
//        } catch (IOException ex) {
//            Logger.getLogger(ChatEndPoint.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }

    @OnMessage
    public void echoText(String mensaje, Session sessionQueManda) {
        if (!sessionQueManda.getUserProperties().get("login").equals("OK")) {
            try {
                // comprobar login
                String idToken = mensaje;
                GoogleIdToken.Payload payLoad = IdTokenVerifierAndParser.getPayload(idToken);
                String name = (String) payLoad.get("name");
                sessionQueManda.getUserProperties().put("user", name);
                System.out.println(payLoad.getJwtId());
                String email = payLoad.getEmail();
                sessionQueManda.getUserProperties().put("login", "OK");
            } catch (Exception ex) {
                try {
                    sessionQueManda.close();
                } catch (IOException ex1) {
                    Logger.getLogger(ChatEndPoint.class.getName()).log(Level.SEVERE, null, ex1);
                }
                Logger.getLogger(ChatEndPoint.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {

            for (Session s : sessionQueManda.getOpenSessions()) {
                try {
                    String user = (String) sessionQueManda.getUserProperties().get("user");
                    //if (!s.equals(sessionQueManda)) {
                    s.getBasicRemote().sendText(user + "::" + mensaje);
                    //}
                } catch (IOException ex) {
                    Logger.getLogger(MyEndpoint.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

}
