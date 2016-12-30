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
@WebServlet(urlPatterns = {"/Cadastrar"})
public class UsuarioController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/view/cadastroUsuario.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Usuario usuario = new Usuario();
        String nome = request.getParameter("nome");
        String senha = request.getParameter("senha");
        String login = request.getParameter("login");
        String email = request.getParameter("email");
        usuario.setEmail(email);
        usuario.setLogin(login);
        usuario.setNome(nome);
        usuario.setSenha(senha);
        try {
            UsuarioBO.inserir(usuario);
            response.sendRedirect(request.getContextPath() + "/");
        } catch (Exception ex) {
            request.setAttribute("erro", ex.getMessage());
            request.getRequestDispatcher("/WEB-INF/view/cadastroUsuario.jsp").forward(request, response);
        }
    }
}
