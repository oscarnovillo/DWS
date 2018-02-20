/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dam.chatserver;

import com.datoshttp.MetaMensajeWS;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;
import javax.websocket.EndpointConfig;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import model.MensajeCifrado;
import model.MessageDecoder;
import model.MessageEncoder;
import model.UserWS;
import util.AesUtil;

/**
 *
 * @author user
 */
@ServerEndpoint(
  value = "/chat/{user}/{pass}",
  decoders = MessageDecoder.class,
  encoders = MessageEncoder.class)
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
    public void echoText(MensajeCifrado mensaje, Session sessionQueManda) {
        if (!sessionQueManda.getUserProperties().get("login").equals("OK")) {
            try {
                // comprobar login
                String idToken = mensaje.getContenido();
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

            try {
//                ObjectMapper mapper = new ObjectMapper();
//                mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//                MensajeCifrado meta = mapper.readValue(mensaje,
//                  new TypeReference<MensajeCifrado>() {
//                  });
                AesUtil aes = new AesUtil(128, 1000);
                switch (mensaje.getTipo()) {
                    case "texto":
                        //descifrar contenido del mensaje.

                        mensaje.setContenido(aes.encrypt(mensaje.getSalt(), mensaje.getIv(), mensaje.getKey(), "mensaje del servidor"));

                        for (Session s : sessionQueManda.getOpenSessions()) {
                            try {
                                String user = (String) sessionQueManda.getUserProperties().get("user");
                                mensaje.setUser(user);
                                //if (!s.equals(sessionQueManda)) {
                                s.getBasicRemote().sendObject(mensaje);
                                //}
                            } catch (IOException ex) {
                                Logger.getLogger(MyEndpoint.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                        break;
                    case "canales":
                        //descifrar contenido del mensaje.
                        ArrayList<String> canales = new ArrayList<>();
                        canales.add("canal2");
                        canales.add("to lo bueno");
                        ObjectMapper mapper = new ObjectMapper();
                        mensaje.setContenido(aes.encrypt(mensaje.getSalt(), mensaje.getIv(), mensaje.getKey(), mapper.writeValueAsString(canales)));
                        sessionQueManda.getBasicRemote().sendObject(mensaje);
                        break;
                }

            } catch (Exception ex) {
                Logger.getLogger(ChatEndPoint.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
