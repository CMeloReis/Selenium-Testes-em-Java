package br.com.alura.leilao.login;

import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LoginTestPageObject {
	
	private LoginPage paginaDeLogin;

	@BeforeEach
	public void beforeEach() {
		
		this.paginaDeLogin = new LoginPage();
		
	}
	
	@AfterEach
	public void afterEach() {
		
		this.paginaDeLogin.fechar();
		
	}
	
	@Test
	public void deveriaEfetuarLoginComDadosValidos() {
		
		paginaDeLogin.preencherFormularioDeLogin("fulano", "pass");
		paginaDeLogin.efetuarLogin();
		
		
		Assert.assertFalse(paginaDeLogin.isPaginaDeLogin());//VERIFICA SE NAO ESTA MAIS NA PAGINA DE LOGIN
		Assert.assertEquals("fulano", paginaDeLogin.getNomeUsuarioLogado());
		
	}
	
	@Test
	public void naoDeveriaEfetuarLoginComDadosInvalidos() {
		

		paginaDeLogin.preencherFormularioDeLogin("invalido", "123");
		paginaDeLogin.efetuarLogin();
		
		Assert.assertTrue(paginaDeLogin.isPaginaDeLoginComDadosInvalidos());
		Assert.assertTrue(paginaDeLogin.contemTexto("Usuário e senha inválidos."));
		Assert.assertNull(paginaDeLogin.getNomeUsuarioLogado());
		
	}
	
	@Test
	public void naoDeveriaAcessarPaginaRestritaSemEstarLogado () {
		
		paginaDeLogin.navegarParaPaginaDeLances();
		
		Assert.assertTrue(paginaDeLogin.isPaginaDeLogin());
		Assert.assertFalse(paginaDeLogin.contemTexto("Dados do Leilão"));
		
	}
	
}
