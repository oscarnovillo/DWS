/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright (c) 2013 Oracle and/or its affiliates. All rights reserved.
 *
 * The contents of this file are subject to the terms of either the GNU
 * General Public License Version 2 only ("GPL") or the Common Development
 * and Distribution License("CDDL") (collectively, the "License").  You
 * may not use this file except in compliance with the License.  You can
 * obtain a copy of the License at
 * https://glassfish.dev.java.net/public/CDDL+GPL_1_1.html
 * or packager/legal/LICENSE.txt.  See the License for the specific
 * language governing permissions and limitations under the License.
 *
 * When distributing the software, include this License Header Notice in each
 * file and include the License file at packager/legal/LICENSE.txt.
 *
 * GPL Classpath Exception:
 * Oracle designates this particular file as subject to the "Classpath"
 * exception as provided by Oracle in the GPL Version 2 section of the License
 * file that accompanied this code.
 *
 * Modifications:
 * If applicable, add the following below the License Header, with the fields
 * enclosed by brackets [] replaced by your own identifying information:
 * "Portions Copyright [year] [name of copyright owner]"
 *
 * Contributor(s):
 * If you wish your version of this file to be governed by only the CDDL or
 * only the GPL Version 2, indicate your decision by adding "[Contributor]
 * elects to include this software in this distribution under the [CDDL or GPL
 * Version 2] license."  If you don't indicate a single choice of license, a
 * recipient has the option to distribute your version of this file under
 * either the CDDL, the GPL Version 2 or to extend the choice of license to
 * its licensees as provided above.  However, if you add GPL Version 2 code
 * and therefore, elected the GPL Version 2 license, then the option applies
 * only if the new code is made subject to such option by the copyright
 * holder.
 */
package dam.chatserver;

import com.datoshttp.Mensaje;
import com.datoshttp.MetaMensajeWS;
import com.datoshttp.OrdenRoomsWS;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;
import javax.websocket.CloseReason;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import model.UserWS;

/**
 * @author Arun Gupta
 */
@ServerEndpoint(value = "/websocket", configurator = ServletAwareConfig.class)
public class MyEndpoint {

    @OnOpen
    public void onOpen(Session session,
            EndpointConfig config) {

        HttpSession httpSession = (HttpSession) config.getUserProperties().get("httpsession");

        
        UserWS u = new UserWS();
        u.setUser((String) httpSession.getAttribute("user"));
        session.getUserProperties().put("user",
                u);

        session.getUserProperties().put("httpsession", httpSession);

        httpSession.setAttribute("perro", "pitbull");

    }

    @OnClose
    public void onClose(Session session) {
        for (Session s : session.getOpenSessions()) {
            try {
                System.out.println(s.getUserProperties().get("user"));
                s.getBasicRemote().sendText("SALIDO " + session.getUserProperties().get("user").toString());
            } catch (IOException ex) {
                Logger.getLogger(MyEndpoint.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @OnMessage
    public void echoText(String mensaje, Session sessionQueManda) {
        try {

            // descencriptar mensaje
            HttpSession httpSession = (HttpSession) sessionQueManda.getUserProperties().get("httpsession");

            String key = (String) httpSession.getAttribute("key");
                       
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            MetaMensajeWS meta = mapper.readValue(mensaje,
                    new TypeReference<MetaMensajeWS>() {
            });

            switch (meta.getTipo()) {
                case MENSAJE:
                    for (Session sesionesMandar : sessionQueManda.getOpenSessions()) {
                        try {
                            Mensaje mensajeCliente = mapper.readValue(
                                    (String) meta.getContenido(),
                                    new TypeReference<Mensaje>() {
                            });

                             httpSession = (HttpSession) sesionesMandar.getUserProperties().get("httpsession");

                             key = (String) httpSession.getAttribute("key");
                            mensajeCliente.setRoom(key);
                            UserWS userMandar = (UserWS) sesionesMandar.getUserProperties().get("user");

                           // if (userMandar.buscaRoom(mensajeCliente.getRoom())) {
                                sesionesMandar.getBasicRemote().sendText(mapper.writeValueAsString(mensajeCliente));
                            //}
                        } catch (IOException ex) {
                            Logger.getLogger(MyEndpoint.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    break;
                case ORDEN:
                    OrdenRoomsWS orden = mapper.readValue(mensaje,
                            new TypeReference<OrdenRoomsWS>() {
                    });
                    if (orden.getOrden().equals("ADD")) {
                        UserWS u = (UserWS) sessionQueManda.getUserProperties().get("user");
                        u.getRoom().add(orden.getRoom());
                    }
                    break;
            }
        } catch (IOException ex) {
            Logger.getLogger(MyEndpoint.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @OnMessage
    public void echoBinary(byte[] data, Session session) throws IOException {
        System.out.println("echoBinary: " + data);
        StringBuilder builder = new StringBuilder();
        for (byte b : data) {
            builder.append(b);
        }
        System.out.println(builder);

        for (Session s : session.getOpenSessions()) {
            System.out.println(s.getUserProperties().get("nombre"));
            s.getBasicRemote().sendBinary(ByteBuffer.wrap(data));
        }

    }

//    @WebSocketMessage
//    public void echoBinary(ByteBuffer data, Session session) throws IOException {
//        System.out.println("echoBinary: " + data);
//        StringBuilder builder = new StringBuilder();
//        for (byte b : data.array()) {
//            builder.append(b);
//        }
//        System.out.println(builder);
//        session.getRemote().sendBytes(data);
//    }
}
