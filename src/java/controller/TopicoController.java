/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.BO.TopicoBO;
import model.Topico;
import model.Usuario;

/**
 *
 * @author Daniel
 */
@WebServlet(urlPatterns = {"/Topicos",
    "/Topicos/Inserir",
    "/Topicos/Exibir"})
public class TopicoController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String userPath = request.getServletPath();
        if (userPath.contains("Inserir")) {
            request.getRequestDispatcher("/WEB-INF/view/insereTopico.jsp").forward(request, response);
        } else if (userPath.contains("Exibir")) {
            try {
                int topico = Integer.parseInt(request.getParameter("topico"));
                request.setAttribute("topico", TopicoBO.recuperar(topico));
                request.getRequestDispatcher("/WEB-INF/view/exibeTopico.jsp").forward(request, response);
            } catch (Exception ex) {
                request.setAttribute("erro", ex.getMessage());
                request.getRequestDispatcher("/WEB-INF/view/topicos.jsp").forward(request, response);
            }
        } else {
            try {
                request.setAttribute("topicos", TopicoBO.listar());
            } catch (Exception ex) {
                request.setAttribute("erro", ex.getMessage());
            }
            request.getRequestDispatcher("/WEB-INF/view/topicos.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String userPath = request.getServletPath();
        if (userPath.contains("Inserir")) {
            Topico topico = new Topico();
            String titulo = request.getParameter("titulo");
            String conteudo = request.getParameter("conteudo");
            topico.setTitulo(titulo);
            topico.setConteudo(conteudo);
            topico.setLogin(((Usuario) request.getSession().getAttribute("usuario")).getLogin());
            try {
                TopicoBO.inserir(topico);
                response.sendRedirect(request.getContextPath() + "/Topicos");
            } catch (Exception ex) {
                request.setAttribute("erro", ex.getMessage());
                request.getRequestDispatcher("/WEB-INF/view/insereTopico.jsp").forward(request, response);
            }
        }
    }
}
