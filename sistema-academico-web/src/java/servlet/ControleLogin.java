/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import dao.UsuarioDao;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import negocio.Usuario;

/**
 *
 * @author joao
 */
public class ControleLogin extends HttpServlet {

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
            out.println("<title>Servlet ControleLogin</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ControleLogin at " + request.getContextPath() + "</h1>");
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
        //processRequest(request, response);
        String acao = request.getParameter("acao");
        String login;
        String senha;
        UsuarioDao usuarioDao;
        Usuario usuario;
        switch (acao) {
            case "login":
                usuario = new Usuario();
                login = request.getParameter("login");
                senha = request.getParameter("senha");
                usuario.setLogin(login);
                usuario.setSenha(senha);
                try {
                    usuarioDao = new UsuarioDao();
                    if (usuarioDao.validarUsuario(usuario)) {
                        response.sendRedirect("principal.jsp");
                    } else {
                        PrintWriter out = response.getWriter();
                        out.println("<!DOCTYPE html>");
                        out.println("<html>");
                        out.println("<body>");
                        out.println("<h2>Login e/ou senhas inválidos</h2>");
                        out.println("</body>");
                        out.println("</html>");
                    }

                } catch (Exception ex) {
                    PrintWriter out = response.getWriter();
                    out.println("<!DOCTYPE html>");
                    out.println("<html>");
                    out.println("<body>");
                    out.println("<h2>Não foi possivel incluir o curso</h2>");
                    out.println("</body>");
                    out.println("</html>");
                }
                break;

        }
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
