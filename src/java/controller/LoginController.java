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
import model.BO.UsuarioBO;
import model.Usuario;

/**
 *
 * @author Daniel
 */
@WebServlet(urlPatterns = {""})
public class LoginController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Usuario usuario = new Usuario();
        String senha = request.getParameter("senha");
        String login = request.getParameter("login");
        try {
            usuario = UsuarioBO.recuperar(login);
        } catch (Exception ex) {
            request.setAttribute("erro", ex.getMessage());
        }
        if (usuario.getLogin() == null) {
            request.setAttribute("erro", "Usuário não encontrado.");
            request.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(request, response);
        } else if (!usuario.getSenha().equals(senha)) {
            request.setAttribute("erro", "Login/Senha incorretos. Tente novamente.");
            request.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(request, response);
        } else {
            request.getSession().setAttribute("usuario", usuario);
            response.sendRedirect(request.getContextPath() + "/Topicos");
        }
    }
}
