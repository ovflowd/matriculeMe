import org.openqa.selenium.*;
import io.selendroid.*;

public class HistBot {

	SelendroidCapabilities capa = new SelendroidCapabilities();
	capa.setEmulator(true);
	WebDriver driver = new SelendroidDriver(capa);
	capa.setEmulator(true);
	driver.get("https://wwwsec.serverweb.unb.br/graduacao/sec/login.aspx");
	WebDriverWait wait = new WebDriverWait(driver, TimeSpan.FromSeconds(30));
	//Espera ate 30 secs para elemento aparecer na tela
	wait.Until(ExpectedConditions.ElementExists(By.Name("inputMatricula")));
	//Espera o campo de entrada de matricula sumir
	wait.Until(ExpectedConditions.not(ExpectedConditions.presenceOfAllElementsLocatedBy(By.Name("inputMatricula")))).catch(Exception);
	
	
	//abre historico
	driver.get("https://wwwsec.serverweb.unb.br/graduacao/sec/he.aspx");
	//html do Historico jogado na string
	String historicoHTML = driver.getPageSource();
	//fecha janela
	driver.quit;
	//profit
	
}
