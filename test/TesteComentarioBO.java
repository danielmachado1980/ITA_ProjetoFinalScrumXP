/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;
import java.util.List;
import model.BO.ComentarioBO;
import model.Comentario;
import org.dbunit.Assertion;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.util.fileloader.FlatXmlDataFileLoader;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author Daniel
 */
public class TesteComentarioBO {

    JdbcDatabaseTester jdt;

    @Before
    public void setUp() throws Exception {
        jdt = new JdbcDatabaseTester("org.postgresql.Driver", "jdbc:postgresql://127.0.0.1:5432/coursera", "postgres",
                "dan");
        FlatXmlDataFileLoader loader = new FlatXmlDataFileLoader();
        jdt.setDataSet(loader.load("/inicio.xml"));
        jdt.onSetup();
    }

    @Test
    public void inserir() throws Exception {
        Comentario comentario = new Comentario();
        comentario.setComentario("COMENTARIO NO TOPICO DO JOAO 2");
        comentario.setLogin("lidia");
        comentario.setIdTopico(3);

        ComentarioBO.inserir(comentario);

        IDataSet currentDataset = jdt.getConnection().createDataSet();
        ITable currentTable = currentDataset.getTable("COMENTARIO");
        FlatXmlDataFileLoader loader = new FlatXmlDataFileLoader();
        IDataSet expectedDataset = loader.load("/verifica.xml");
        ITable expectedTable = expectedDataset.getTable("COMENTARIO");
        Assertion.assertEqualsIgnoreCols(expectedTable, currentTable, new String[]{"id_comentario"});
    }

    @Test
    public void listarPorTopico() throws Exception {
        Comentario comentario = new Comentario();
        comentario.setComentario("COMENTARIO NO TOPICO DO JOAO 2");
        comentario.setLogin("lidia");
        comentario.setIdTopico(3);

        ComentarioBO.inserir(comentario);

        List<Comentario> lstComentarios = new ArrayList<>();
        lstComentarios = ComentarioBO.listarPorTopico(3);
        assertEquals("lidia", lstComentarios.get(0).getLogin());
    }
}
