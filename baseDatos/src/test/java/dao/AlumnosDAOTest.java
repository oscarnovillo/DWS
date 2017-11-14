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
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAllAlumnosJDBC method, of class AlumnosDAO.
     */
    @Test
    public void testGetAllAlumnosJDBC() {
        System.out.println("getAllAlumnosJDBC");
        AlumnosDAO instance = new AlumnosDAO();
        List<Alumno> expResult = null;
        List<Alumno> result = instance.getAllAlumnosJDBC();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getUserById method, of class AlumnosDAO.
     */
    @Test
    public void testGetUserById() {
        System.out.println("getUserById");
        int id = 0;
        AlumnosDAO instance = new AlumnosDAO();
        Alumno expResult = null;
        Alumno result = instance.getUserById(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of insertAlumnoJDBC method, of class AlumnosDAO.
     */
    @Test
    public void testInsertAlumnoJDBC() {
        System.out.println("insertAlumnoJDBC");
        Alumno a = null;
        AlumnosDAO instance = new AlumnosDAO();
        Alumno expResult = null;
        Alumno result = instance.insertAlumnoJDBC(a);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAllAlumnosJDBCTemplate method, of class AlumnosDAO.
     */
    @Test
    public void testGetAllAlumnosJDBCTemplate() {
        System.out.println("getAllAlumnosJDBCTemplate");
        AlumnosDAO instance = new AlumnosDAO();
        List<Alumno> expResult = null;
        List<Alumno> result = instance.getAllAlumnosJDBCTemplate();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getUser method, of class AlumnosDAO.
     */
    @Test
    public void testGetUser() {
        System.out.println("getUser");
        Alumno userOriginal = null;
        AlumnosDAO instance = new AlumnosDAO();
        Alumno expResult = null;
        Alumno result = instance.getUser(userOriginal);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addUserSimpleJDBCTemplate method, of class AlumnosDAO.
     */
    @Test
    public void testAddUserSimpleJDBCTemplate() {
        System.out.println("addUserSimpleJDBCTemplate");
        Alumno a = null;
        AlumnosDAO instance = new AlumnosDAO();
        Alumno expResult = null;
        Alumno result = instance.addUserSimpleJDBCTemplate(a);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addUserJDBCTemplate method, of class AlumnosDAO.
     */
    @Test
    public void testAddUserJDBCTemplate() {
        System.out.println("addUserJDBCTemplate");
        Alumno a = null;
        AlumnosDAO instance = new AlumnosDAO();
        Alumno expResult = null;
        Alumno result = instance.addUserJDBCTemplate(a);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addUserDBUtils method, of class AlumnosDAO.
     */
    @Test
    public void testAddUserDBUtils() {
        System.out.println("addUserDBUtils");
        Alumno alumno = null;
        String activacion = "";
        AlumnosDAO instance = new AlumnosDAO();
        Alumno expResult = null;
        Alumno result = instance.addUserDBUtils(alumno, activacion);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateUserDBUtils method, of class AlumnosDAO.
     */
    @Test
    public void testUpdateUserDBUtils() {
        System.out.println("updateUserDBUtils");
        Alumno alumno = null;
        String activacion = "";
        AlumnosDAO instance = new AlumnosDAO();
        Alumno expResult = null;
        Alumno result = instance.updateUserDBUtils(alumno, activacion);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
