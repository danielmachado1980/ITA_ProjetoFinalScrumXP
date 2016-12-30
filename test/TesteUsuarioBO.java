
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import model.BO.UsuarioBO;

import org.dbunit.Assertion;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.util.fileloader.FlatXmlDataFileLoader;
import org.junit.Before;
import org.junit.Test;

import model.Usuario;

public class TesteUsuarioBO {

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
    public void ranking() throws Exception {
        List<Usuario> lstUsuarios = new ArrayList<>();
        lstUsuarios = UsuarioBO.ranking();
        assertEquals("lidia", lstUsuarios.get(0).getLogin());
    }

    @Test
    public void recuperar() throws Exception {
        Usuario usuario = new Usuario();
        usuario = UsuarioBO.recuperar("joao");
        assertEquals(20, usuario.getPontos());
        assertEquals("joao@mail.com", usuario.getEmail());
        assertEquals("jao", usuario.getSenha());
        assertEquals("joao", usuario.getLogin());
        assertEquals("JOAO BATISTA MACHADO", usuario.getNome());
    }

    @Test
    public void adicionaPontos() throws Exception {
        Usuario usuario = new Usuario();
        UsuarioBO.adicionarPontos("lidia", 10);
        usuario = UsuarioBO.recuperar("lidia");
        assertEquals(60, usuario.getPontos());
        UsuarioBO.adicionarPontos("joao", 5);
        usuario = UsuarioBO.recuperar("joao");
        assertEquals(25, usuario.getPontos());
    }

    @Test
    public void inserir() throws Exception {
        Usuario usuario = new Usuario();
        usuario.setEmail("duda@mail.com");
        usuario.setLogin("duda");
        usuario.setNome("MARIA EDUARDA GUERRA");
        usuario.setPontos(45);
        usuario.setSenha("duda123");
        UsuarioBO.inserir(usuario);

        usuario = new Usuario();
        usuario.setEmail("duda2@mail.com");
        usuario.setLogin("duda2");
        usuario.setNome("MARIA EDUARDA GUERRA2");
        usuario.setPontos(45);
        usuario.setSenha("duda1234");
        UsuarioBO.inserir(usuario);

        IDataSet currentDataset = jdt.getConnection().createDataSet();
        ITable currentTable = currentDataset.getTable("USUARIO");
        FlatXmlDataFileLoader loader = new FlatXmlDataFileLoader();
        IDataSet expectedDataset = loader.load("/verifica.xml");
        ITable expectedTable = expectedDataset.getTable("USUARIO");
        Assertion.assertEquals(expectedTable, currentTable);
    }
}
