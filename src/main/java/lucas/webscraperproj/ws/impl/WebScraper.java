package lucas.webscraperproj.ws.impl;

import java.util.ArrayList;
import java.util.List;


import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


import lucas.webscraperproj.domain.Anuncio;
import lucas.webscraperproj.domain.EntidadeDominio;
import lucas.webscraperproj.domain.Quarto;
import lucas.webscraperproj.util.StringConverter;
import lucas.webscraperproj.util.TextElement;

public class WebScraper extends AbstractWebScraper{
	private WebDriver driver;
	
	public WebScraper()
	{
		setup();
	}
	
	public void setDriver(WebDriver driver)
	{
		this.driver = driver;
	}
	
	public void abrirNavegador(String site)
	{
		System.out.println("Abrindo link... " + site);
		driver.navigate().to(site);
	}
	
	public void filtrosPesquisa(String cidade, String dataCheckin, String dataCheckout) 
	{
		driver.manage().window().maximize();
		driver.findElement(By.id("ss")).sendKeys(cidade);
		driver.findElement(By.xpath("//*[@data-placeholder='Data de check-in']")).click();
		driver.findElement(By.xpath("//*[@data-id='"+dataCheckin+"']")).click(); 	
		driver.findElement(By.xpath("//*[@data-placeholder='Data de check-out']")).click(); 	
		driver.findElement(By.xpath("(//*[@data-id='"+dataCheckout+"'])[2]")).click();		
		driver.findElement(By.xpath("//*[text()='Pesquisar']")).click();
	}
	
	public List<EntidadeDominio> buscarAnuncios()
	{
		List<String> paginas = new ArrayList<String>();
		List<EntidadeDominio> anuncios = new ArrayList<EntidadeDominio>();
		boolean haPaginas = true;
		int nPagina = 1;
		int nAnuncios = 0;
		do
		{
			System.out.println("Página " + nPagina);
			if(nPagina == 1)
				paginas = buscarLinks(new ArrayList<WebElement>(), driver, "(//*[@class='results-paging']/a)[1]");
			else
				paginas = buscarLinks(new ArrayList<WebElement>(), driver, "(//*[@class='results-paging']/a)[2]");
			List<String> links = buscarLinks(new ArrayList<WebElement>(), driver, "//*[@class='hotel_name_link url']");
			
			for(int i = 0; i < links.size() ; i++)
			{
				driver.navigate().to(links.get(i));
				anuncios.add(lerInformacoes(driver));	
				nAnuncios ++;
				System.out.println(nAnuncios + " Adicionados");
			}
			if(paginas.size() == 0)
				haPaginas = false;
			else
				driver.navigate().to(paginas.get(0));
			nPagina++;
		}while(haPaginas);

		return anuncios;
	}
	
	
	public List<String> buscarLinks(List<WebElement> e, WebDriver driver, String xpath)
	{
		List<String> links = new ArrayList<String>();
		e = driver.findElements(By.xpath(xpath));
		
		for(int i = 0; i < e.size(); i++)
			links.add(e.get(i).getAttribute("href"));
		
		return links;	
	}
	
	
	public Quarto encontrarQuarto(WebElement nome, WebElement preco, 
			WebElement nota, WebElement qtdePessoas)
	{
		Quarto q = new Quarto();
		q.setNome(nome.getText());
		q.setPreco(StringConverter.precoConverter(preco.getText()));
		q.setNota(nota.getText());
		q.setQtdePessoas(qtdePessoas.getText().substring(qtdePessoas.getText().length() - 1));
		return q;		
	}
	
	
	public Anuncio lerInformacoes(WebDriver driver)
	{
		Anuncio a = new Anuncio();
		
		a.setNome(TextElement.getText(driver, "//*[@class='hp__hotel-name']"));
		a.setEndereco(TextElement.getText(driver, "//*[@class='\nhp_address_subtitle\njs-hp_address_subtitle\njq_tooltip\n']"));

		
		
		List<WebElement> nomeQuarto = driver.findElements(By.xpath("//*[@class='hprt-roomtype-icon-link']"));		
		List<WebElement> precoQuarto = driver.findElements(By.xpath("//*[@class='hprt-price-price ']"));
		WebElement nota = driver.findElement(By.xpath("(//*[@class='review-score-badge'])[1]"));
		List<WebElement> estrelasHotel = driver.findElements(By.xpath("(//*[@class='\nbk-icon-wrapper\nbk-icon-stars\nstar_track\n'])[1]"));
		List<WebElement> qtdePessoas = driver.findElements(By.xpath("(//*[@class='hprt-block']/span)[1]"));
		
		if(estrelasHotel.size() != 0)
			a.setEstrelas(StringConverter.estrelasConverter(estrelasHotel.get(0).getText()));
		
		if(nomeQuarto.size() != 0 && precoQuarto.size() != 0)
		{
			for(int j = 0; j < 1; j++)
				a.getQuartos().add(encontrarQuarto(nomeQuarto.get(j), precoQuarto.get(j),
						nota, qtdePessoas.get(j)));
			
		}
		else
		{
			Quarto q = new Quarto();
			q.setNome("QUARTO INDISPONÍVEL");
			a.getQuartos().add(q);
		}
		
		System.out.println(a.getQuartos().size());
		
		return a;
	}


}