/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dam.mmiprimeraweb;

import com.fasterxml.jackson.databind.ObjectMapper;
//import dao.ErrorPeso;
//import dao.PesoPersona;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Ejemplo;

/**
 *
 * @author oscar
 */
@WebServlet(name = "JsonExample", urlPatterns = {"/JsonExample"})
public class JsonExample extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
//        
//        ArrayList<PesoPersona> ejemplos = new ArrayList();
//        
//        PesoPersona ej = new PesoPersona();
//        
//        ej.setPeso(10);
//        ErrorPeso error = new ErrorPeso("HOLA MUNDO");
//    
//        ej.setError(error);
//        ej.setLesionado(true);
//        
//        ejemplos.add(ej);
//        ejemplos.add(ej);
//        ObjectMapper mapper = new ObjectMapper();
//        // equivalente a las lineas de abajo. 
//        mapper.writeValue(response.getOutputStream(), ejemplos);
//        
//        String ejemplo = mapper.writeValueAsString(ejemplos);
//        PrintWriter out = response.getWriter();
//        out.println(ejemplo);
    
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
