/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package init;

import config.Configuration;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author oscar
 */
public class CrearModelo {
    
    
    public void insertarDatosPrueba()
    {
        Connection con = null;
        PreparedStatement stmt = null;
        int filas = -1;
        try {
            Class.forName(Configuration.getInstance().getDriverDB());

            con = DriverManager.getConnection(
              Configuration.getInstance().getUrlDB(),
              Configuration.getInstance().getUserDB(),
              Configuration.getInstance().getPassDB());
           
            stmt = con.prepareStatement("INSERT INTO USUARIOS (NOMBRE, PASSWORD,VECES_LOGIN,LIBROS_PRESTADOS)  VALUES ('PEPE','PEPE',0,2)");
            filas = stmt.executeUpdate();
            stmt = con.prepareStatement("INSERT INTO USUARIOS (NOMBRE, PASSWORD,VECES_LOGIN,LIBROS_PRESTADOS)  VALUES ('JUAN','JUAN',0,0)");
            filas = stmt.executeUpdate();

        } catch (Exception ex) {
            Logger.getLogger(CrearModelo.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {

                if (stmt != null) {
                    stmt.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(CrearModelo.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
       
    }
    
    
     public void crearModeloJDBC() {
        Connection con = null;
        PreparedStatement stmt = null;
        int filas = -1;
        try {
            Class.forName(Configuration.getInstance().getDriverDB());

            con = DriverManager.getConnection(
              Configuration.getInstance().getUrlDB(),
              Configuration.getInstance().getUserDB(),
              Configuration.getInstance().getPassDB());

            stmt = con.prepareStatement("CREATE TABLE USUARIOS (\n"
              + "  ID INTEGER IDENTITY PRIMARY KEY ,\n"
              + "  NOMBRE varchar(250)  DEFAULT NULL,\n"
              + "  PASSWORD varchar(250)  DEFAULT NULL,\n"
              + "  VECES_LOGIN INTEGER NOT NULL,\n"
              + "  LIBROS_PRESTADOS INTEGER NOT NULL\n"
              + ");");
            filas = stmt.executeUpdate();
            

        } catch (Exception ex) {
            Logger.getLogger(CrearModelo.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {

                if (stmt != null) {
                    stmt.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(CrearModelo.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
       

    }
    
    
}
