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
import model.Livro;

/**
 *
 * @author daniel
 */
public class LivroDAO extends BaseDAO {

    
    public List<Livro> listar() throws Exception {
        String sql = "SELECT * FROM livro";
        List<Livro> lstLivros = new ArrayList<>();
        try {
            PreparedStatement stm = getConnection().prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Livro livro = new Livro();
                
                livro.setCodigo(rs.getInt("id_livro"));
                livro.setTitulo(rs.getString("titulo"));
                livro.setAutor(rs.getString("autor"));
                livro.setPaginas(rs.getInt("paginas"));
                livro.setConteudo(rs.getString("conteudo"));
                
                lstLivros.add(livro);
            }
        } catch (Exception e) {
            throw new Exception("Erro ao tentar listar livros", e);
        }
        return lstLivros;
    }
    
    public Livro recuperar(int codigo) throws Exception {
        Livro livro = new Livro();
        String sql = "SELECT * FROM livro WHERE id_livro = ?";
        try {
            PreparedStatement stm = getConnection().prepareStatement(sql);
            stm.setInt(1, codigo);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                livro.setCodigo(rs.getInt("id_livro"));
                livro.setTitulo(rs.getString("titulo"));
                livro.setAutor(rs.getString("autor"));
                livro.setPaginas(rs.getInt("paginas"));
                livro.setConteudo(rs.getString("conteudo"));
            }
        } catch (Exception e) {
            throw new Exception("Erro ao tentar exibir livro", e);
        }
        return livro;
    }

    public void marcar(String login, int codigo) throws Exception {
        String sql = "INSERT INTO usuario_login(login, id_livro) VALUES (?, ?)";
        try {
            PreparedStatement stm = getConnection().prepareStatement(sql);
            stm.setString(1, login);
            stm.setInt(2, codigo);
            stm.executeUpdate();
        } catch (Exception e) {
            throw new Exception("Erro ao tentar marcar livro como lido", e);
        }
    }
    
}
