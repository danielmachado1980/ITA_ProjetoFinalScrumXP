/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.BO;

import java.util.List;
import model.DAO.LivroDAO;
import model.DAO.UsuarioDAOImpl;
import model.Livro;

/**
 *
 * @author Daniel
 */
public class LivroBO {

//    public static void inserir(Livro livro) throws Exception {
//        TopicoDAO dao = new TopicoDAO();
//        try {
//            dao.getConnection().setAutoCommit(false);
//            dao.inserir(livro);
//            UsuarioDAOImpl usuDAO = new UsuarioDAOImpl();
//            usuDAO.setConnection(dao.getConnection());
//            //usuDAO.adicionarPontos(topico.getLogin(), 10);
//            dao.getConnection().commit();
//        } catch (Exception ex) {
//            dao.getConnection().rollback();
//            throw new Exception(ex.getMessage(), ex);
//        }
//    }

    public static List<Livro> listar() throws Exception {
        LivroDAO dao = new LivroDAO();
        return dao.listar();
    }

    public static Livro recuperar(int codigo) throws Exception {
        LivroDAO dao = new LivroDAO();
        return dao.recuperar(codigo);
    }

    public static void marcar(String login, int codigo) throws Exception {
        LivroDAO dao = new LivroDAO();
        Livro livro = new Livro();
        int pontoBonificacao = 0;
        try {
            dao.getConnection().setAutoCommit(false);
            livro = dao.recuperar(codigo);
            pontoBonificacao = livro.getPaginas()/100;
            dao.marcar(login, codigo);
            UsuarioDAOImpl usuDAO = new UsuarioDAOImpl();
            usuDAO.setConnection(dao.getConnection());
            usuDAO.adicionarPontos(login, 1 + pontoBonificacao);
            dao.getConnection().commit();
        } catch (Exception ex) {
            dao.getConnection().rollback();
            throw new Exception(ex.getMessage(), ex);
        }
    }
}
