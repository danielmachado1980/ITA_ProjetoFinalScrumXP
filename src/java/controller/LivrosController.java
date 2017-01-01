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
import model.BO.LivroBO;
import model.Livro;
import model.Usuario;
/**
 *
 * @author Daniel
 */
@WebServlet(urlPatterns = {"/Livros",
    "/Livros/Marcar",
    "/Livros/Exibir"})
public class LivrosController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String userPath = request.getServletPath();
        if (userPath.contains("Exibir")) {
            try {
                int topico = Integer.parseInt(request.getParameter("livro"));
                request.setAttribute("livro", LivroBO.recuperar(topico));
                request.getRequestDispatcher("/WEB-INF/view/exibeLivro.jsp").forward(request, response);
            } catch (Exception ex) {
                request.setAttribute("erro", ex.getMessage());
                request.getRequestDispatcher("/WEB-INF/view/livros.jsp").forward(request, response);
            }
        } else if (userPath.contains("Marcar")) {
            try {
                int topico = Integer.parseInt(request.getParameter("livro"));
                Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
                LivroBO.marcar(usuario.getLogin(),topico);
                response.sendRedirect(request.getContextPath() + "/Livros");
            } catch (Exception ex) {
                request.setAttribute("erro", ex.getMessage());
                request.getRequestDispatcher("/WEB-INF/view/topicos.jsp").forward(request, response);
            }
        } else {
        try {
            
                request.setAttribute("livros", LivroBO.listar());
            } catch (Exception ex) {
                request.setAttribute("erro", ex.getMessage());
            }
        request.getRequestDispatcher("/WEB-INF/view/livros.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }
}
