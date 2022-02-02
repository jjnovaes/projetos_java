/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import dao.CursoDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import negocio.Curso;

/**
 *
 * @author joao
 */
@WebServlet(name = "ControleCurso", urlPatterns = {"/ControleCurso"})
public class ControleCurso extends HttpServlet {

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    //serve para link
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //processRequest(request, response);
        String acao = request.getParameter("acao");
        int id;
        String descricao;
        String modalidade;
        CursoDao cursoDao;
        Curso curso;
        switch (acao) {
            case "excluir":
                curso = new Curso();
                id = Integer.parseInt(request.getParameter("id"));
                curso.setId(id);
                try {
                    cursoDao = new CursoDao();
                    cursoDao.excluir(curso);
                    response.sendRedirect("listar.jsp");
                } catch (Exception ex) {
                    PrintWriter out = response.getWriter();
                    out.println("<!DOCTYPE html>");
                    out.println("<html>");
                    out.println("<body>");
                    out.println("<h2>Não foi possivel excluir o curso</h2>");
                    out.println("</body>");
                    out.println("</html>");
                }
                break;
            case "buscarEditar":
                id = Integer.parseInt(request.getParameter("id"));
                try {
                    cursoDao = new CursoDao();
                    curso = cursoDao.buscarUm(id);
                    request.setAttribute("curso", curso);
                    request.getRequestDispatcher("editar.jsp").forward(request, response);
                    //response.sendRedirect("editar.jsp");
                } catch (Exception ex) {
                    PrintWriter out = response.getWriter();
                    out.println("<!DOCTYPE html>");
                    out.println("<html>");
                    out.println("<body>");
                    out.println("<h2>Não foi possivel buscar o curso</h2>");
                    out.println("</body>");
                    out.println("</html>");
                }
                break;
        }

    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    //sever para formulario
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //processRequest(request, response);
        String acao = request.getParameter("acao");
        int id;
        String descricao;
        String modalidade;
        CursoDao cursoDao;
        Curso curso;
        switch (acao) {
            case "incluir":
                curso = new Curso();
                descricao = request.getParameter("descricao");
                modalidade = request.getParameter("modalidade");
                curso.setDescricao(descricao);
                curso.setModalidade(modalidade);
                try {
                    cursoDao = new CursoDao();
                    cursoDao.incluir(curso);
                    response.sendRedirect("listar.jsp");
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

            case "editar":
                curso = new Curso();
                id = Integer.parseInt(request.getParameter("id"));
                descricao = request.getParameter("descricao");
                modalidade = request.getParameter("modalidade");
                curso.setId(id);
                curso.setDescricao(descricao);
                curso.setModalidade(modalidade);
                try {
                    cursoDao = new CursoDao();
                    cursoDao.alterar(curso);
                    response.sendRedirect("listar.jsp");
                } catch (Exception ex) {
                    PrintWriter out = response.getWriter();
                    out.println("<!DOCTYPE html>");
                    out.println("<html>");
                    out.println("<body>");
                    out.println("<h2>Não foi possivel alterar o curso</h2>");
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
