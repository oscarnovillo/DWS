/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import util.PasswordHash;

/**
 *
 * @author user
 */
@WebServlet(name = "Hash", urlPatterns = {"/secure/hash"})
public class Hash extends HttpServlet {

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
            try (PrintWriter out = response.getWriter()) {
                /* TODO output your page here. You may use following sample code. */
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Servlet Hash</title>");
                out.println("</head>");
                out.println("<body>");
                String password = request.getParameter("password");
                if (password == null) {
                    password = "TEST";
                }
                try {
                  PasswordHash p = new PasswordHash();
                    if (request.getSession().getAttribute("HASH") == null) {
                        String hash = p.createHash(password);
                        out.println("<h1>Servlet Hash at " + p.createHash(password) + "</h1>");
                        out.println("<h1>Servlet Hash at " + p.createHash(password) + "</h1>");
                        request.getSession().setAttribute("HASH", hash);
                    } else {

                        out.println("<h1>Servlet Hash at " + p.validatePassword(password, (String) request.getSession().getAttribute("HASH")) + "</h1>");
                        request.getSession().setAttribute("HASH", null);
                    }
                } catch (NoSuchAlgorithmException ex) {
                    Logger.getLogger(Hash.class.getName()).log(Level.SEVERE, null, ex);
                } catch (InvalidKeySpecException ex) {
                    Logger.getLogger(Hash.class.getName()).log(Level.SEVERE, null, ex);
                }
                out.println("</body>");
                out.println("</html>");
            }
        
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
