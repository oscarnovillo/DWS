/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import com.mysql.jdbc.jdbc2.optional.MysqlConnectionPoolDataSource;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import config.Configuration;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

/**
 *
 * @author oscar
 */
public class DBConnection {

    public DBConnection()
    {
        
    }
    
    public Connection getConnection() throws Exception {
        Class.forName(Configuration.getInstance().getDriverDB());
        Connection connection = null;

        connection = DriverManager.getConnection(
                Configuration.getInstance().getUrlDB(),
                Configuration.getInstance().getUserDB(),
                Configuration.getInstance().getPassDB());

        return connection;
    }

    
    public DataSource getDataSourceFromServer() throws NamingException
    {
        Context ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("jdbc/db4free");
            return ds;
        
    }
     public DataSource getDataSource() {
        // Creates a new instance of DriverManagerDataSource and sets
        // the required parameters such as the Jdbc Driver class,
        MysqlDataSource mysql = new MysqlConnectionPoolDataSource();
        mysql.setUrl(Configuration.getInstance().getUrlDB());
        mysql.setUser(Configuration.getInstance().getUserDB());
        mysql.setPassword(Configuration.getInstance().getPassDB());
        
        // Jdbc URL, database user name and password.
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        dataSource.setDriverClassName(Configuration.getInstance().getDriverDB());
        dataSource.setUrl(Configuration.getInstance().getUrlDB());
        dataSource.setUsername( Configuration.getInstance().getUserDB());
        dataSource.setPassword(Configuration.getInstance().getPassDB());
        return mysql;
    }
    
    public void cerrarConexion(Connection connection) {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
