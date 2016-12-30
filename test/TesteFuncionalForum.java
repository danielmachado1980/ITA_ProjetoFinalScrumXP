
import java.util.concurrent.TimeUnit;
import model.BO.ComentarioBO;
import model.BO.TopicoBO;
import model.BO.UsuarioBO;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.testng.annotations.*;
import static org.testng.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TesteFuncionalForum {

    private WebDriver driver;
    private String baseUrl;
    private boolean acceptNextAlert = true;
    private StringBuffer verificationErrors = new StringBuffer();

    @BeforeClass(alwaysRun = true)
    public void setUp() throws Exception {
        driver = new FirefoxDriver();
        baseUrl = "http://localhost/";
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        ComentarioBO.deletar("teste");
        TopicoBO.deletar("teste");
        UsuarioBO.deletar("teste");
    }

    @Test
    public void firstTestCadastroUsuario() throws Exception {
        driver.get(baseUrl + "/ITA_TrabalhoFinalMVC/");
        driver.findElement(By.linkText("Cadastrar Usuário")).click();
        driver.findElement(By.name("nome")).clear();
        driver.findElement(By.name("nome")).sendKeys("joao da silva");
        driver.findElement(By.name("email")).clear();
        driver.findElement(By.name("email")).sendKeys("joao@mail.com");
        driver.findElement(By.name("login")).clear();
        driver.findElement(By.name("login")).sendKeys("test");
        driver.findElement(By.name("login")).clear();
        driver.findElement(By.name("login")).sendKeys("teste");
        driver.findElement(By.name("senha")).clear();
        driver.findElement(By.name("senha")).sendKeys("123");
        driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
        driver.findElement(By.id("inputLogin")).clear();
        driver.findElement(By.id("inputLogin")).sendKeys("teste");
        driver.findElement(By.id("inputPassword")).clear();
        driver.findElement(By.id("inputPassword")).sendKeys("123");
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        assertEquals(driver.findElement(By.id("forms")).getText(), "Tópicos");
    }

    @Test
    public void secondTestCadastroTopico() throws Exception {
        driver.get(baseUrl + "/ITA_TrabalhoFinalMVC/");
        driver.findElement(By.id("inputLogin")).clear();
        driver.findElement(By.id("inputLogin")).sendKeys("teste");
        driver.findElement(By.id("inputPassword")).clear();
        driver.findElement(By.id("inputPassword")).sendKeys("123");
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        driver.findElement(By.linkText("Inserir Tópico")).click();
        driver.findElement(By.name("titulo")).clear();
        driver.findElement(By.name("titulo")).sendKeys("Topico teste");
        driver.findElement(By.id("textArea")).clear();
        driver.findElement(By.id("textArea")).sendKeys("Conteudo teste");
        driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
        assertEquals(driver.findElement(By.id("forms")).getText(), "Tópicos");
    }

    @Test
    public void thirdTestSomandoPontos() throws Exception {
        driver.get(baseUrl + "/ITA_TrabalhoFinalMVC/");
        driver.findElement(By.id("inputLogin")).clear();
        driver.findElement(By.id("inputLogin")).sendKeys("teste");
        driver.findElement(By.id("inputPassword")).clear();
        driver.findElement(By.id("inputPassword")).sendKeys("123");
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        driver.findElement(By.linkText("Inserir Tópico")).click();
        driver.findElement(By.name("titulo")).clear();
        driver.findElement(By.name("titulo")).sendKeys("Topico teste");
        driver.findElement(By.id("textArea")).clear();
        driver.findElement(By.id("textArea")).sendKeys("Conteudo teste");
        driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
        assertEquals(driver.findElement(By.id("forms")).getText(), "Tópicos");
        driver.findElement(By.linkText("Ranking")).click();
        assertEquals(driver.findElement(By.id("forms")).getText(), "Ranking");
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() throws Exception {
        driver.quit();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }
    }

    private boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    private boolean isAlertPresent() {
        try {
            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    private String closeAlertAndGetItsText() {
        try {
            Alert alert = driver.switchTo().alert();
            String alertText = alert.getText();
            if (acceptNextAlert) {
                alert.accept();
            } else {
                alert.dismiss();
            }
            return alertText;
        } finally {
            acceptNextAlert = true;
        }
    }
}
