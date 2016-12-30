/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.BO;

import java.util.List;
import model.Comentario;
import model.DAO.BaseDAO;
import model.DAO.ComentarioDAO;
import model.DAO.UsuarioDAOImpl;

/**
 *
 * @author Daniel
 */
public class ComentarioBO {
    
    public static void inserir(Comentario comentario) throws Exception{
        ComentarioDAO dao = new ComentarioDAO();
        try {
            dao.getConnection().setAutoCommit(false);
            dao.inserir(comentario);
            UsuarioDAOImpl usuDAO = new UsuarioDAOImpl();
            usuDAO.setConnection(dao.getConnection());
            usuDAO.adicionarPontos(comentario.getLogin(), 3);
            dao.getConnection().commit();
        } catch (Exception ex) {
            dao.getConnection().rollback();
            throw new Exception(ex.getMessage(),ex);
        }
    }
    
    public static List<Comentario> listarPorTopico(int codigo) throws Exception {
       ComentarioDAO dao = new ComentarioDAO();
       return dao.listarPorTopico(codigo);
    }
    
    public static void deletar(String login) throws Exception {
       ComentarioDAO dao = new ComentarioDAO();
       dao.deletar(login);
    }

    public static void deletar() throws Exception {
        ComentarioDAO dao = new ComentarioDAO();
        dao.deletar();
    }
}
