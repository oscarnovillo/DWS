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


           
            stmt = con.prepareStatement("INSERT INTO LIBROS (NOMBRE, ID_BIBLIOTECA)  VALUES ('QUIJOTE',1)");
            filas = stmt.executeUpdate();
            stmt = con.prepareStatement("INSERT INTO LIBROS (NOMBRE, ID_BIBLIOTECA)  VALUES ('IT',1)");
            filas = stmt.executeUpdate();


            stmt = con.prepareStatement("INSERT INTO LIBROS (NOMBRE, ID_BIBLIOTECA)  VALUES ('CANCION DE FUEGO Y HIELO',2)");

            filas = stmt.executeUpdate();
            
            stmt = con.prepareStatement("INSERT INTO BIBLIOTECAS (NOMBRE, API_KEY)  VALUES ('SAN ROMUALDO','KEY-1')");
            filas = stmt.executeUpdate();

            stmt = con.prepareStatement("INSERT INTO BIBLIOTECAS (NOMBRE, API_KEY)  VALUES ('ARAMDNO GARCIA','KEY-2')");
            filas = stmt.executeUpdate();
            
            stmt = con.prepareStatement("INSERT INTO PRESTAMOS (ID_LIBRO, ID_USUARIO,ID_BIBLIOTECA)  VALUES (1,1,1)");
            filas = stmt.executeUpdate();
            stmt = con.prepareStatement("INSERT INTO PRESTAMOS (ID_LIBRO, ID_USUARIO,ID_BIBLIOTECA)  VALUES (2,1,1)");
            filas = stmt.executeUpdate();
//
//            stmt = con.prepareStatement("INSERT INTO REPARADORES (NOMBRE)  VALUES ('Juan')");
//
//            filas = stmt.executeUpdate();

            
//            stmt = con.prepareStatement("INSERT INTO asignaturas VALUES (83,'damn','damn','damn'),(86,'cabrones','meteos con','EDU'),(94,'yiiha','ahusdau','asdako'),(99,'aaaaa','vvvvv','aaaaa'),(100,'sadasda','asdasdasd','dasdasda'),(111,'hola ','erasto','ketal'),(114,'mu','quien quiera','que seas'),(124,'mu2','que seas','quien quiera'),(129,'peter','peter','peter'),(135,'aa','aa','aa'),(138,'prueba2','2','122'),(142,'pruebba','ba','ba'),(155,'Barsa','Es','Enorme'),(166,'Asignatura','Asignatura','Asignatura'),(175,'asignatura con nota','no tocar','no tocar'),(199,'juan1ssss','juan2','juan1'),(207,'san','tin','valen'),(208,'ingles','tercero','b'),(212,'miau miau','miau miau','miau guau');");
//
//            filas = stmt.executeUpdate();
//             stmt = con.prepareStatement("INSERT INTO notas VALUES (403,83,8),(403,155,0),(412,155,5),(426,166,7),(460,175,10),(530,99,5),(530,100,2),(530,111,3),(530,135,5),(723,208,6);");
//
//            filas = stmt.executeUpdate();
//            
//            stmt = con.prepareStatement("INSERT INTO notas VALUES (403,86,DEFAULT)");
//
//            filas = stmt.executeUpdate();



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


             stmt = con.prepareStatement(
              "CREATE TABLE LIBROS (\n"
              + "  ID INTEGER IDENTITY NOT NULL,\n"
              + "  NOMBRE varchar(250) NOT NULL,\n"
              + "  ID_BIBLIOTECA INTEGER NOT NULL,\n"
              + "  PRIMARY KEY (ID)\n"
              + ");");

            filas = stmt.executeUpdate();
            stmt = con.prepareStatement("CREATE TABLE BIBLIOTECAS (\n"
              + "  ID INTEGER IDENTITY NOT NULL,\n"
              + "  NOMBRE varchar(250) NOT NULL,\n"
              + "  API_KEY varchar(250) NOT NULL,\n"
              + "  PRIMARY KEY (ID),\n"
              + ")");
            filas = stmt.executeUpdate();
            stmt = con.prepareStatement("CREATE TABLE PRESTAMOS (\n"
              + "  ID_LIBRO INTEGER NOT NULL,\n"
              + "  ID_USUARIO INTEGER NOT NULL,\n"
              + "  ID_BIBLIOTECA INTEGER NOT NULL\n"
              + ")");
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
