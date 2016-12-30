/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.Topico;

/**
 *
 * @author daniel
 */
public class TopicoDAO extends BaseDAO {

    public void inserir(Topico topico) throws Exception {
        String sql = "INSERT INTO topico(titulo, conteudo, login) VALUES (?, ?, ?)";
        try {
            PreparedStatement stm = getConnection().prepareStatement(sql);
            stm.setString(1, topico.getTitulo());
            stm.setString(2, topico.getConteudo());
            stm.setString(3, topico.getLogin());
            stm.executeUpdate();
        } catch (Exception e) {
            throw new Exception("Erro ao tentar inserir tópico", e);
        }
    }

    public List<Topico> listar() throws Exception {
        String sql = "SELECT * FROM topico";
        List<Topico> lstTopicos = new ArrayList<>();
        try {
            PreparedStatement stm = getConnection().prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Topico topico = new Topico();
                UsuarioDAOImpl dao = new UsuarioDAOImpl();
                topico.setCodigo(rs.getInt("id_topico"));
                topico.setLogin(rs.getString("login"));
                topico.setTitulo(rs.getString("titulo"));
                topico.setConteudo(rs.getString("conteudo"));
                topico.setUsuario(dao.recuperar(topico.getLogin()));
                lstTopicos.add(topico);
            }
        } catch (Exception e) {
            throw new Exception("Erro ao tentar listar tópicos", e);
        }
        return lstTopicos;
    }

    public Topico recuperar(int codigo) throws Exception {
        Topico topico = new Topico();
        String sql = "SELECT * FROM topico WHERE id_topico = ?";
        try {
            PreparedStatement stm = getConnection().prepareStatement(sql);
            stm.setInt(1, codigo);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                ComentarioDAO comentarioDAO = new ComentarioDAO();
                UsuarioDAOImpl usuarioDAO = new UsuarioDAOImpl();
                topico.setCodigo(rs.getInt("id_topico"));
                topico.setLogin(rs.getString("login"));
                topico.setTitulo(rs.getString("titulo"));
                topico.setConteudo(rs.getString("conteudo"));
                topico.setUsuario(usuarioDAO.recuperar(topico.getLogin()));
                topico.setComentarios(comentarioDAO.listarPorTopico(codigo));
            }
        } catch (Exception e) {
            throw new Exception("Erro ao tentar exibir tópico", e);
        }
        return topico;
    }
    
    public void deletar(String login) throws Exception {
        String sql = "DELETE FROM topico WHERE login = ?";
        try {
            PreparedStatement stm = getConnection().prepareStatement(sql);
            stm.setString(1, login);
            stm.executeUpdate();
        } catch (Exception e) {
            throw new Exception("Erro ao tentar excluir o(s) tópico(s)", e);
        }
    }

    public void deletar() throws Exception {
        String sql = "DELETE FROM topico";
        try {
            PreparedStatement stm = getConnection().prepareStatement(sql);
            stm.executeUpdate();
        } catch (Exception e) {
            throw new Exception("Erro ao tentar excluir o(s) tópico(s)", e);
        }
    }
}
