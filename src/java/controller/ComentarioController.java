package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.BO.ComentarioBO;
import model.Comentario;
import model.Usuario;

/**
 *
 * @author Daniel
 */
@WebServlet(urlPatterns = {"/Topicos/Comentario"})
public class ComentarioController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int topico = Integer.parseInt(request.getParameter("topico"));
        String conteudo = request.getParameter("conteudo");
        Comentario comentario = new Comentario();
        comentario.setComentario(conteudo);
        comentario.setIdTopico(topico);
        comentario.setLogin(((Usuario) request.getSession().getAttribute("usuario")).getLogin());
        try {
            ComentarioBO.inserir(comentario);
            response.sendRedirect(request.getContextPath() + "/Topicos/Exibir?topico=" + topico);
        } catch (Exception ex) {
            request.setAttribute("erro", ex.getMessage());
            request.getRequestDispatcher("/WEB-INF/view/topicos.jsp").forward(request, response);
        }
    }
}
