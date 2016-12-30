/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.BO;

import java.util.List;
import model.DAO.TopicoDAO;
import model.DAO.UsuarioDAOImpl;
import model.Topico;

/**
 *
 * @author Daniel
 */
public class TopicoBO {

    public static void inserir(Topico topico) throws Exception {
        TopicoDAO dao = new TopicoDAO();
        try {
            dao.getConnection().setAutoCommit(false);
            dao.inserir(topico);
            UsuarioDAOImpl usuDAO = new UsuarioDAOImpl();
            usuDAO.setConnection(dao.getConnection());
            usuDAO.adicionarPontos(topico.getLogin(), 10);
            dao.getConnection().commit();
        } catch (Exception ex) {
            dao.getConnection().rollback();
            throw new Exception(ex.getMessage(), ex);
        }
    }

    public static List<Topico> listar() throws Exception {
        TopicoDAO dao = new TopicoDAO();
        return dao.listar();
    }

    public static Topico recuperar(int codigo) throws Exception {
        TopicoDAO dao = new TopicoDAO();
        return dao.recuperar(codigo);
    }
    
    public static void deletar(String login) throws Exception {
       TopicoDAO dao = new TopicoDAO();
       dao.deletar(login);
    }

    public static void deletar() throws Exception {
        TopicoDAO dao = new TopicoDAO();
        dao.deletar();
    }
}
