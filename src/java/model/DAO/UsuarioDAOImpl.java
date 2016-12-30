/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.DAO;

import interfaces.UsuarioDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.Usuario;

/**
 *
 * @author daniel
 */
public class UsuarioDAOImpl extends BaseDAO implements UsuarioDAO {
    
    @Override
    public void inserir(Usuario u) throws Exception {
        String sql = "INSERT INTO usuario(login, email, nome, senha, pontos) VALUES (?, ?, ?, ?, ?)";
        try {
            PreparedStatement stm = getConnection().prepareStatement(sql);
            stm.setString(1, u.getLogin());
            stm.setString(2, u.getEmail());
            stm.setString(3, u.getNome());
            stm.setString(4, u.getSenha());
            stm.setInt(5, u.getPontos());
            stm.executeUpdate();
        } catch (Exception e) {
            throw new Exception("Erro ao tentar inserir usuário", e);
        }
    }

    @Override
    public Usuario recuperar(String login) throws Exception {
        Usuario usuario = new Usuario();
        String sql = "SELECT * FROM usuario WHERE login = ?";
        try {
            PreparedStatement stm = getConnection().prepareStatement(sql);
            stm.setString(1, login);
            ResultSet rs = stm.executeQuery();
            rs.next();
            usuario.setLogin(rs.getString("login"));
            usuario.setNome(rs.getString("nome"));
            usuario.setEmail(rs.getString("email"));
            usuario.setSenha(rs.getString("senha"));
            usuario.setPontos(rs.getInt("pontos"));
        } catch (Exception e) {
            throw new Exception("Erro ao tentar recuperar usuário", e);
        }
        return usuario;
    }

    @Override
    public void adicionarPontos(String login, int pontos) throws Exception {
        String sql = "UPDATE usuario SET pontos = pontos + ? WHERE login = ?";
        try {
            PreparedStatement stm = getConnection().prepareStatement(sql);
            stm.setInt(1, pontos);
            stm.setString(2, login);
            stm.executeUpdate();
        } catch (Exception e) {
            throw new Exception("Erro ao tentar adicionar pontos para o usuário", e);
        }
    }

    @Override
    public List<Usuario> ranking() throws Exception {
        List<Usuario> lstUsuarios = new ArrayList<>();
        String sql = "SELECT rank() over (order by pontos desc) as colocacao, u.* FROM usuario u ORDER BY pontos DESC";
        try {
            PreparedStatement stm = getConnection().prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setLogin(rs.getString("login"));
                usuario.setNome(rs.getString("nome"));
                usuario.setEmail(rs.getString("email"));
                usuario.setSenha(rs.getString("senha"));
                usuario.setPontos(rs.getInt("pontos"));
                usuario.setColocacao(rs.getInt("colocacao"));
                lstUsuarios.add(usuario);
            }
        } catch (Exception e) {
            throw new Exception("Erro ao tentar listar ranking", e);
        }
        return lstUsuarios;
    }

    public void deletar(String login) throws Exception {
        String sql = "DELETE FROM usuario WHERE login = ?";
        try {
            PreparedStatement stm = getConnection().prepareStatement(sql);
            stm.setString(1, login);
            stm.executeUpdate();
        } catch (Exception e) {
            throw new Exception("Erro ao tentar excluir o usuário", e);
        }
    }
    
    public void deletar() throws Exception {
        String sql = "DELETE FROM usuario";
        try {
            PreparedStatement stm = getConnection().prepareStatement(sql);
            stm.executeUpdate();
        } catch (Exception e) {
            throw new Exception("Erro ao tentar excluir o(s) usuário(s)", e);
        }
    }
}
