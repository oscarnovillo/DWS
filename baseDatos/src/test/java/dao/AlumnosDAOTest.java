/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import config.Configuration;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Alumno;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 *
 * @author user
 */
public class AlumnosDAOTest {
    
    public AlumnosDAOTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {

    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Mock
    ServletContextEvent mockEvent;
    @Mock
    ServletContext mockServletContext;
    @Mock
    Configuration mockConfig;
    
    /**
     * Test of getAllAlumnosDBUtils method, of class AlumnosDAO.
     */
    @Test
    public void testGetAllAlumnosDBUtils() {
        Configuration.getInstance(Configuration.class.getResourceAsStream("/config/config.yml"), mockServletContext);
        System.out.println("getAllAlumnosDBUtils");
        AlumnosDAO instance = new AlumnosDAO();
        List<Alumno> expResult = null;
        List<Alumno> result = instance.getAllAlumnosDBUtils();
        assertTrue(result.size() > 0);
        // TODO review the generated test code and remove the default call to fail.
       
    }

   
    

    
}
