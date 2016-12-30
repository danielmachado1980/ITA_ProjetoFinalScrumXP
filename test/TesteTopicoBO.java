/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;
import java.util.List;
import model.BO.TopicoBO;
import model.Topico;
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
public class TesteTopicoBO {

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
        Topico topico = new Topico();
        topico.setTitulo("TOPICO DO JOAO4");
        topico.setConteudo("CONTEUDO DO TOPICO DO JOAO4");
        topico.setLogin("joao");

        TopicoBO.inserir(topico);

        IDataSet currentDataset = jdt.getConnection().createDataSet();
        ITable currentTable = currentDataset.getTable("TOPICO");
        FlatXmlDataFileLoader loader = new FlatXmlDataFileLoader();
        IDataSet expectedDataset = loader.load("/verifica.xml");
        ITable expectedTable = expectedDataset.getTable("TOPICO");
        Assertion.assertEqualsIgnoreCols(expectedTable, currentTable, new String[]{"id_topico"});
    }

    @Test
    public void listar() throws Exception {
        List<Topico> lstTopicos = new ArrayList<>();
        lstTopicos = TopicoBO.listar();
        assertEquals("joao", lstTopicos.get(0).getLogin());
    }

    @Test
    public void recuperar() throws Exception {
        Topico topico = new Topico();
        topico = TopicoBO.recuperar(4);
        assertEquals("lidia", topico.getLogin());
    }
}
