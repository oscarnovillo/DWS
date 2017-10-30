/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import model.Alumno;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

/**
 *
 * @author oscar
 */
public class AlumnosDAO {

    public List<Alumno> getAllAlumnos() {
        List<Alumno> lista = null;
        DBConnection db = new DBConnection();
        Connection con = null;
        try {
            con = db.getConnection();
            QueryRunner qr = new QueryRunner();
            ResultSetHandler<List<Alumno>> h
                    = new BeanListHandler<Alumno>(Alumno.class);
            lista = qr.query(con, "select * FROM ALUMNOS", h);

        } catch (Exception ex) {
            Logger.getLogger(AlumnosDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            db.cerrarConexion(con);
        }
        return lista;
    }

    public List<Alumno> getAllAlumnosJDBC() {
        List<Alumno> lista = new ArrayList<>();
        Alumno nuevo = null;
        DBConnection db = new DBConnection();
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            con = db.getConnection();
            stmt = con.createStatement();
            String sql;
            sql = "SELECT * FROM ALUMNOS";
            rs = stmt.executeQuery(sql);

            //STEP 5: Extract data from result set
            while (rs.next()) {
                //Retrieve by column name
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                Date fn = rs.getDate("fecha_nacimiento");
                Boolean mayor = rs.getBoolean("mayor_edad");
                nuevo = new Alumno();
                nuevo.setFecha_nacimiento(fn);
                nuevo.setId(id);
                nuevo.setMayor_edad(mayor);
                nuevo.setNombre(nombre);
                lista.add(nuevo);
            }

        } catch (Exception ex) {
            Logger.getLogger(AlumnosDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(AlumnosDAO.class.getName()).log(Level.SEVERE, null, ex);
            }

            db.cerrarConexion(con);
        }
        return lista;

    }

    public Alumno getUserById(int id) {
        Alumno user = null;
        DBConnection db = new DBConnection();

        Connection con = null;
        try {
            Context ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("jdbc/db4free");
            con = ds.getConnection();
            QueryRunner qr = new QueryRunner();
            ResultSetHandler<Alumno> h
                    = new BeanHandler<>(Alumno.class);
            user = qr.query(con, "select * FROM ALUMNOS where ID = ?", h, id);
        } catch (Exception ex) {
            Logger.getLogger(AlumnosDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            db.cerrarConexion(con);
        }
        return user;
    }

    public Alumno getUser(Alumno userOriginal) {
        Alumno user = null;
        DBConnection db = new DBConnection();
        Connection con = null;
        try {
            con = db.getConnection();
            QueryRunner qr = new QueryRunner();
            ResultSetHandler<Alumno> h
                    = new BeanHandler<>(Alumno.class);
            user = qr.query(con, "select * FROM LOGIN where USER = ?", h, userOriginal.getNombre());
        } catch (Exception ex) {
            Logger.getLogger(AlumnosDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            db.cerrarConexion(con);
        }
        return user;
    }

    public void delUser(Alumno u) {
        DBConnection db = new DBConnection();
        Connection con = null;
        try {
            con = db.getConnection();
            QueryRunner qr = new QueryRunner();

            int filas = qr.update(con,
                    "DELETE FROM LOGIN WHERE ID=?",
                    u.getId());

        } catch (Exception ex) {
            Logger.getLogger(AlumnosDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            db.cerrarConexion(con);
        }
    }

    public void updateUser(Alumno u) {
        DBConnection db = new DBConnection();
        Connection con = null;
        try {
            con = db.getConnection();
            QueryRunner qr = new QueryRunner();

            int filas = qr.update(con,
                    "UPDATE LOGIN SET MAIL=? WHERE USER=?",
                    "", "");

        } catch (Exception ex) {
            Logger.getLogger(AlumnosDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            db.cerrarConexion(con);
        }
    }

    public boolean recuperarUser(Alumno u, String activacion) {
        DBConnection db = new DBConnection();
        Connection con = null;
        boolean ok = false;
        try {
            con = db.getConnection();
            QueryRunner qr = new QueryRunner();

            int filas = qr.update(con,
                    "UPDATE LOGIN SET ACTIVACION=?, FECHA_RENOVACION = now() WHERE ID=?",
                    activacion, u.getId());
            if (filas >= 1) {
                ok = true;
            }
        } catch (Exception ex) {
            Logger.getLogger(AlumnosDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            db.cerrarConexion(con);
        }
        return ok;
    }

    public void updateUserPassword(Alumno u) {
        DBConnection db = new DBConnection();
        Connection con = null;
        try {
            con = db.getConnection();
            QueryRunner qr = new QueryRunner();

            int filas = qr.update(con,
                    "UPDATE LOGIN SET PASSWORD=?,MAIL=? WHERE USER=?",
                    "", "", "");

        } catch (Exception ex) {
            Logger.getLogger(AlumnosDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            db.cerrarConexion(con);
        }
    }

    public int cambiarPassUser(String codigo, String password) {
        DBConnection db = new DBConnection();
        Connection con = null;
        int filas = 0;
        try {
            con = db.getConnection();
            QueryRunner qr = new QueryRunner();

            filas = qr.update(con,
                    "UPDATE LOGIN SET PASSWORD=? WHERE ACTIVACION=? "
                    + "AND fecha_renovacion > date_sub(now(),INTERVAL 1 HOUR)",
                    password, codigo);

        } catch (Exception ex) {
            Logger.getLogger(AlumnosDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            db.cerrarConexion(con);
        }
        return filas;

    }

    public int activarUser(String activacion) {
        DBConnection db = new DBConnection();
        Connection con = null;
        int filas = 0;
        try {
            con = db.getConnection();
            QueryRunner qr = new QueryRunner();

            filas = qr.update(con,
                    "UPDATE LOGIN SET ACTIVO=1 WHERE ACTIVACION=? "
                    + "AND fecha_renovacion > date_sub(now(),INTERVAL 1 HOUR)",
                    activacion);

        } catch (Exception ex) {
            Logger.getLogger(AlumnosDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            db.cerrarConexion(con);
        }
        return filas;

    }

    public Alumno addUser(Alumno u, String activacion) {
        DBConnection db = new DBConnection();
        Connection con = null;

        try {
            con = db.getConnection();
            con.setAutoCommit(false);
            QueryRunner qr = new QueryRunner();

            BigInteger id = qr.insert(con,
                    "INSERT INTO LOGIN (USER,PASSWORD,MAIL,ACTIVACION,ACTIVO,FECHA_RENOVACION) VALUES(?,?,?,?,?,now())",
                    new ScalarHandler<BigInteger>(),
                    "", "", "", activacion, 0);

            u.setId(id.longValue());
            con.commit();

        } catch (Exception ex) {
            Logger.getLogger(AlumnosDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            db.cerrarConexion(con);
        }
        return u;

    }

}
