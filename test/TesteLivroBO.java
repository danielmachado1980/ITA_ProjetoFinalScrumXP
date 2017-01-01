/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.ArrayList;
import java.util.List;
import junit.framework.TestCase;
import model.BO.LivroBO;
import model.BO.UsuarioBO;
import model.Livro;
import model.Usuario;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.util.fileloader.FlatXmlDataFileLoader;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Daniel
 */
public class TesteLivroBO {
    
    JdbcDatabaseTester jdt;

    @Before
    public void setUp() throws Exception {
        jdt = new JdbcDatabaseTester("org.postgresql.Driver", "jdbc:postgresql://127.0.0.1:5432/livraria", "postgres",
                "dan");
        FlatXmlDataFileLoader loader = new FlatXmlDataFileLoader();
        jdt.setDataSet(loader.load("/inicioLivro.xml"));
        jdt.onSetup();
    }

    @Test
    public void listar() throws Exception {
        List<Livro> lstLivros = new ArrayList<>();
        lstLivros = LivroBO.listar();
        assertEquals("TOPICO DO JOAO", lstLivros.get(0).getTitulo());
    }
    
}
