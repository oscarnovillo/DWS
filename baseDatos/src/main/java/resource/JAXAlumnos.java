/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import model.Alumno;

/**
 *
 * @author oscar
 */
@Path("/alumnos")
public class JAXAlumnos {
    
    /**
     * Retrieves representation of an instance of resource.ItemsResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Alumno getJson() {
        //TODO return proper representation object
        return new Alumno();
    }
    
    
}
