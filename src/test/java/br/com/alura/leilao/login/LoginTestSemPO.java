package br.com.alura.leilao.login;

import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginTestSemPO {
	
	private static final String URL_LOGIN = "http://localhost:8080/login";
	private WebDriver browser;
	
	@BeforeAll
	public static void beforeAll() {
		
		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");//SETAR VARIAVEL
		
	}

	@BeforeEach
	public void beforeEach() {
		
		//USAR VARIOS PONTOS - TRANSFORMAR EM ATRIBUTO DA CLASSE
		this.browser = new ChromeDriver();//ABRIR NAVEGADOR
		this.browser.navigate().to(URL_LOGIN);
		
	}
	
	@AfterEach
	public void afterEach() {
		
		this.browser.quit();
		
	}
	
	@Test
	public void deveriaEfetuarLoginComDadosValidos() {
		
		browser.findElement(By.id("username")).sendKeys("fulano");
		browser.findElement(By.id("password")).sendKeys("pass");
		browser.findElement(By.id("login-form")).submit();//SUBMETER FORMULARIO/CAMPO
		
		Assert.assertFalse(browser.getCurrentUrl().equals(URL_LOGIN));//VERIFICA SE NAO ESTA MAIS NA PAGINA DE LOGIN
		Assert.assertEquals("fulano", browser.findElement(By.id("usuario-logado")).getText());
		
	}
	
	@Test
	public void naoDeveriaEfetuarLoginComDadosInvalidos() {
		
		browser.findElement(By.id("username")).sendKeys("invalido");
		browser.findElement(By.id("password")).sendKeys("123123");
		browser.findElement(By.id("login-form")).submit();//SUBMETER FORMULARIO/CAMPO
		
		Assert.assertTrue(browser.getCurrentUrl().equals("http://localhost:8080/login?error"));
		Assert.assertTrue(browser.getPageSource().contains("Usuário e senha inválidos."));
		Assert.assertThrows(NoSuchElementException.class, () -> browser.findElement(By.id("usuario-logado")));
		
	}
	
	@Test
	public void naoDeveriaAcessarPaginaRestritaSemEstarLogado () {
		
		this.browser.navigate().to("http://localhost:8080/leiloes/2");
		Assert.assertTrue(browser.getCurrentUrl().equals(URL_LOGIN));
		Assert.assertFalse(browser.getPageSource().contains("Dados do Leilão"));
		
	}
	
}
