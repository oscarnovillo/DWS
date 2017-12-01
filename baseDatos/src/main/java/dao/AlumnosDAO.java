/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import model.Alumno;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import org.apache.commons.dbutils.DbUtils;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

/**
 *
 * @author oscar
 */
public class AlumnosDAO {

    //Select DBUtils
    public List<Alumno> getAllAlumnosDBUtils() {
        List<Alumno> lista = null;
       
        Connection con = null;
        try {
            con = DBConnection.getInstance().getConnection();

            QueryRunner qr = new QueryRunner();
            ResultSetHandler<List<Alumno>> handler
              = new BeanListHandler<Alumno>(Alumno.class);
            lista = qr.query(con, "select * FROM ALUMNOS", handler);

        } catch (Exception ex) {
            Logger.getLogger(AlumnosDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            
            DBConnection.getInstance().cerrarConexion(con);
        }
        return lista;
    }

    // Select JDBC
    public List<Alumno> getAllAlumnosJDBC() {
        List<Alumno> lista = new ArrayList<>();
        Alumno nuevo = null;
       
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            con = DBConnection.getInstance().getConnection();
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

            DBConnection.getInstance().cerrarConexion(con);
        }
        return lista;

    }

    // Con datasource
    public Alumno getUserById(int id) {
        Alumno alumno = null;


        Connection con = null;
        try {
            con = DBConnection.getInstance().getDataSourceFromServer().getConnection();
            QueryRunner qr = new QueryRunner();
            ResultSetHandler<Alumno> h
              = new BeanHandler<>(Alumno.class);
            alumno = qr.query(con, "select * FROM ALUMNOS where ID = ?",
              h, id);
        } catch (Exception ex) {
            Logger.getLogger(AlumnosDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBConnection.getInstance().cerrarConexion(con);
        }
        return alumno;
    }

    //inser JDBC
    public Alumno insertAlumnoJDBC(Alumno a) {
        
        Connection con = null;
        try {
            con = DBConnection.getInstance().getConnection();
            PreparedStatement stmt = con.prepareStatement("INSERT INTO ALUMNOS (NOMBRE,FECHA_NACIMIENTO,MAYOR_EDAD) VALUES(?,?,?)", Statement.RETURN_GENERATED_KEYS);

            stmt.setString(1, a.getNombre());
            stmt.setDate(2, new java.sql.Date(a.getFecha_nacimiento().getTime()));
            stmt.setBoolean(3, a.getMayor_edad());
            int filas = stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                a.setId(rs.getInt(1));
            }

        } catch (Exception ex) {
            Logger.getLogger(AlumnosDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBConnection.getInstance().cerrarConexion(con);
        }

        return a;
    }

    public List<Alumno> getAllAlumnosJDBCTemplate() {
     
        JdbcTemplate jtm = new JdbcTemplate(
          DBConnection.getInstance().getDataSource());
        List<Alumno> alumnos = jtm.query("Select * from ALUMNOS",
          new BeanPropertyRowMapper(Alumno.class));
        
        
        return alumnos;
    }

    public Alumno getUser(Alumno userOriginal) {
        Alumno user = null;
       
        Connection con = null;
        try {
            con = DBConnection.getInstance().getConnection();
            QueryRunner qr = new QueryRunner();
            ResultSetHandler<Alumno> h
              = new BeanHandler<>(Alumno.class);
            user = qr.query(con, "select * FROM LOGIN where USER = ?", h, userOriginal.getNombre());
        } catch (Exception ex) {
            Logger.getLogger(AlumnosDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBConnection.getInstance().cerrarConexion(con);
        }
        return user;
    }

    //insert spring jdbc template
    public Alumno addUserSimpleJDBCTemplate(Alumno a) {

        TransactionDefinition txDef = new DefaultTransactionDefinition();
        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager(DBConnection.getInstance().getDataSource());
        TransactionStatus txStatus = transactionManager.getTransaction(txDef);
        
        try {
            
            SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(DBConnection.getInstance().getDataSource()).withTableName("ALUMNOS").usingGeneratedKeyColumns("ID");
            Map<String, Object> parameters = new HashMap<String, Object>();

            parameters.put("NOMBRE", a.getNombre());
            parameters.put("FECHA_NACIMIENTO", a.getFecha_nacimiento());
            parameters.put("MAYOR_EDAD", a.getMayor_edad());
            a.setId(jdbcInsert.executeAndReturnKey(parameters).longValue());
            transactionManager.commit(txStatus);

        } catch (Exception e) {

            transactionManager.rollback(txStatus);

            throw e;

        }

        return a;
    }

    //insert spring jdbc template
    public Alumno addUserJDBCTemplate(Alumno a) {
 
       
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(
          DBConnection.getInstance().getDataSource()).withTableName("ALUMNOS").usingGeneratedKeyColumns("ID");
        Map<String, Object> parameters = new HashMap<String, Object>();

        parameters.put("NOMBRE", a.getNombre());
        parameters.put("FECHA_NACIMIENTO", a.getFecha_nacimiento());
        parameters.put("MAYOR_EDAD", a.getMayor_edad());
        a.setId(jdbcInsert.executeAndReturnKey(parameters).longValue());
        return a;
    }

    // insert DBUTILS
    public Alumno addUserDBUtils(Alumno alumno, String activacion) {
   
        Connection con = null;

        try {
            con = DBConnection.getInstance().getConnection();
            con.setAutoCommit(false);
            QueryRunner qr = new QueryRunner();

            BigInteger id = qr.insert(con,
              "INSERT INTO LOGIN (USER,PASSWORD,MAIL,ACTIVACION,"
              + "ACTIVO,FECHA_RENOVACION) VALUES(?,?,?,?,?,now())",
              new ScalarHandler<BigInteger>(),
              "", "", "", activacion, 0);

            alumno.setId(id.longValue());
            con.commit();

        } catch (Exception ex) {
            try {
                Logger.getLogger(AlumnosDAO.class.getName()).log(Level.SEVERE, null, ex);
                if (con!= null)
                    con.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(AlumnosDAO.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } finally {
            DBConnection.getInstance().cerrarConexion(con);
        }
        return alumno;

    }

    public Alumno updateUserDBUtils(Alumno alumno, String activacion) {

        Connection con = null;

        try {
            con = DBConnection.getInstance().getConnection();

            QueryRunner qr = new QueryRunner();

           int filas = qr.update(con,
              "UPDATE ALUMNOS SET NOMBRE = ? WHERE ID = ?",
              alumno.getNombre(),alumno.getId());



        } catch (Exception ex) {
            Logger.getLogger(AlumnosDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBConnection.getInstance().cerrarConexion(con);
        }
        return alumno;

    }
}
