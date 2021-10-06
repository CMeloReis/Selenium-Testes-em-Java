package br.com.alura.leilao.leiloes;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import br.com.alura.leilao.PageObject;

public class LeiloesPage extends PageObject {
	
	private static final String URL_CADASTRO_LEILAO = "http://localhost:8080/leiloes/new";
	private static final String URL_LEILOES = "http://localhost:8080/leiloes";

	
	public LeiloesPage(WebDriver browser) {
		
		super(browser);
		this.browser = browser;
		
	}

	public CadastroLeilaoPage carregarFormulario() {
		this.browser.navigate().to(URL_CADASTRO_LEILAO);
		return new CadastroLeilaoPage(browser);
		
	}

	public boolean isLeilaoCadastrado(String nome, String valor, String data) {
		WebElement linhaDaTabela = this.browser.findElement(By.cssSelector("#tabela-leiloes tbody tr:last-child"));//PEGA A ULTIMA LINHA DA TABELA (CSS)
		WebElement colunaNome = linhaDaTabela.findElement(By.cssSelector("td:nth-child(1)"));//RECUPERAR ELEMENTO ATRAVES DE OUTRO ELEMENTO
		WebElement colunaDataAbertura = linhaDaTabela.findElement(By.cssSelector("td:nth-child(2)"));//NAO PRECISA SER PELO BROWSER
		WebElement colunaValorInicial = linhaDaTabela.findElement(By.cssSelector("td:nth-child(3)"));//NESSE CASO, PELA linhaDaTabela
		
		
		return colunaNome.getText().equals(nome) 
				&& colunaDataAbertura.getText().equals(data) 
				&& colunaValorInicial.getText().equals(valor);
		
	}

	public boolean isPaginaAtual() {
		
		return browser.getCurrentUrl().contentEquals(URL_LEILOES);
	}
	
}
