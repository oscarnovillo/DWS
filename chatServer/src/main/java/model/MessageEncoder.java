/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

public class MessageEncoder implements Encoder.Text<MensajeCifrado> {
 
    private static final ObjectMapper gson = new ObjectMapper();
 
    @Override
    public String encode(MensajeCifrado message) throws EncodeException {
        String json = "";
        try {
            json = gson.writeValueAsString(message);
        } catch (JsonProcessingException ex) {
            Logger.getLogger(MessageEncoder.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return json;
    }
 
    @Override
    public void init(EndpointConfig endpointConfig) {
        // Custom initialization logic
    }
 
    @Override
    public void destroy() {
        // Close resources
    }

    

    
}