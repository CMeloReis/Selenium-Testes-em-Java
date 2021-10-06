package br.com.alura.leilao;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import net.bytebuddy.asm.Advice.This;

public class PageObject {
		
	protected WebDriver browser;
		
	public PageObject(WebDriver browser) {//RECEBE O WEBDRIVE
			
		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");//SETAR VARIAVEL
			
		if (browser == null) {
			this.browser = new ChromeDriver();//ABRIR NOVA INSTANCIA DO BROWSER
				
		} else {
			this.browser = browser;//E ARMAZENA O WEBDRIVE AQUI
		}
		
		this.browser.manage().timeouts()
			.implicitlyWait(5, TimeUnit.SECONDS)//BUSCAR ELEMENTOS E NAO ENCONTRAR, ESPERA DE ATE 5 SEGUNDOS ANTES DE DA ERRO - 
			.pageLoadTimeout(10, TimeUnit.SECONDS);//PAGINA DEMORA PARA CARREGAR, ESPERA DE ATE 10 SEGUNDOS ANTES DE DA ERRO
			
	}
		
	public void fechar() {
		this.browser.quit();
			
	}

}
