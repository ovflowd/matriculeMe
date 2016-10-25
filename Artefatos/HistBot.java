import org.openqa.selenium.*;
import io.selendroid.*;

public class HistBot {

	SelendroidCapabilities capa = new SelendroidCapabilities();

	WebDriver driver = new SelendroidDriver(capa);
	driver.get("https://wwwsec.serverweb.unb.br/graduacao/sec/login.aspx");
	WebDriverWait wait = new WebDriverWait(driver, TimeSpan.FromSeconds(30));
	wait.Until(driver => !driver.FindElement(By.Name("inputMatricula")));
	//Espera o campo de entrada de matricula sumir
	
	//abre historico
	driver.get("https://wwwsec.serverweb.unb.br/graduacao/sec/he.aspx");
	//html do Historico jogado na string
	String historicoHTML = driver.getPageSource();
	//fecha janela
	driver.quit;
	//profit
	
}
