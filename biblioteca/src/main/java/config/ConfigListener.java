package config;



import config.Configuration;
import init.CrearModelo;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Web application lifecycle listener.
 *
 * @author oscar
 */
public class ConfigListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
      Configuration.getInstance(sce.getServletContext().getResourceAsStream("/WEB-INF/config.yml"),
              sce.getServletContext());
      CrearModelo crear = new CrearModelo();
        crear.crearModeloJDBC();
        crear.insertarDatosPrueba();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
       //((HikariDataSource)DBConnection.getInstance().getDataSource()).close();
        
    }
}
