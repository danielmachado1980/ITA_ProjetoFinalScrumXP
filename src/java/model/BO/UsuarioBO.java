/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.BO;

import java.util.List;
import model.DAO.UsuarioDAOImpl;
import model.Usuario;

/**
 *
 * @author Daniel
 */
public class UsuarioBO {

    public static void inserir(Usuario usuario) throws Exception {
        UsuarioDAOImpl dao = new UsuarioDAOImpl();
        dao.inserir(usuario);
    }

    public static Usuario recuperar(String login) throws Exception {
        UsuarioDAOImpl dao = new UsuarioDAOImpl();
        return dao.recuperar(login);
    }

    public static void adicionarPontos(String login, int pontos) throws Exception {
        UsuarioDAOImpl dao = new UsuarioDAOImpl();
        dao.adicionarPontos(login, pontos);
    }

    public static List<Usuario> ranking() throws Exception {
        UsuarioDAOImpl dao = new UsuarioDAOImpl();
        return dao.ranking();
    }

    public static void deletar(String login) throws Exception {
       UsuarioDAOImpl dao = new UsuarioDAOImpl();
       dao.deletar(login);
    }

    public static void deletar() throws Exception {
        UsuarioDAOImpl dao = new UsuarioDAOImpl();
       dao.deletar();
    }
}
