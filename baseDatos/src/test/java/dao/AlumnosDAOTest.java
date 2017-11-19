/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import config.ConfigListener;
import config.Configuration;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Alumno;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

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
        MockitoAnnotations.initMocks(this);
//        mockEvent = mock(ServletContextEvent.class);
//        mockServletContext = mock(ServletContext.class);
//        mockResponse = mock(HttpServletResponse.class);
//        mockRequest = mock(HttpServletRequest.class);
//        when(mockEvent.getServletContext()).thenReturn(mockServletContext);
//
//        when(mockServletContext.getResourceAsStream("/WEB-INF/config.yml")).thenReturn(Configuration.class.getResourceAsStream("/config/config.yml"));
//
//        ConfigListener listen = new ConfigListener();
//        listen.contextInitialized(mockEvent);
    }

    @After
    public void tearDown() {
    }

    @Mock
    ServletContextEvent mockEvent;
    @Mock
    ServletContext mockServletContext;
    @Mock
    HttpServletResponse mockResponse;
    @Mock
    HttpServletRequest mockRequest;
 @Mock
 HttpSession session;

    /**
     * Test of getAllAlumnosDBUtils method, of class AlumnosDAO.
     */
    @Test
    public void testGetAllAlumnosDBUtils() {
//        AlumnosDAO instance = new AlumnosDAO();
//        List<Alumno> result = instance.getAllAlumnosDBUtils();
//        result.get(0).getFecha_nacimiento();
//        result.get(0).getId();
//        assertTrue(result.size() > 0);
    }

    @Test
    public void testServletAllAlumnosDBUtils() throws IOException {
//        
//  StringWriter sw = new StringWriter();
//  PrintWriter pw = new PrintWriter(sw);
//  
//  when(mockResponse.getWriter()).thenReturn(pw);
//  
//  //new Login().doPost(request, response);
//  
//  //Verify the session attribute value
//  verify(session).setAttribute("user", "abhinav");
//    String result = sw.getBuffer().toString().trim();
//
//  System.out.println("Result: "+result);
//  
//  assertEquals("Login successfull...", result);      
        
    }
    
    
}
