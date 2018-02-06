package lucas.webscraperproj.ws.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.poi.util.SystemOutLogger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import lucas.webscraperproj.domain.Anuncio;
import lucas.webscraperproj.domain.EntidadeDominio;
import lucas.webscraperproj.domain.Quarto;
import lucas.webscraperproj.util.StringToDouble;
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
		
		List<EntidadeDominio> anuncios = new ArrayList<EntidadeDominio>();
		List<String> links = buscarLinks(new ArrayList<WebElement>(), driver);
		
		for(int i = 0; i < 1 ; i++)
		{
			
			driver.navigate().to(links.get(i));
			Anuncio a = new Anuncio();
			a.setNome(TextElement.getText(driver, "//*[@class='hp__hotel-name']"));
			a.setEndereco(TextElement.getText(driver, "//*[@class='\nhp_address_subtitle\njs-hp_address_subtitle\njq_tooltip\n']"));
			
			List<WebElement> nome = driver.findElements(By.xpath("//*[@class='hprt-roomtype-icon-link']"));		
			List<WebElement> preco = driver.findElements(By.xpath("//*[@class='hprt-price-price ']"));
			WebElement nota = driver.findElement(By.xpath("(//*[@class='review-score-badge'])[1]"));
			
			
			List<WebElement> qtdePessoas = driver.findElements(By.xpath("(//*[@class='hprt-occupancy-"
					+ "occupancy-info jq_tooltip \n'])[1]"));
			

			if(nome.size() != 0 && preco.size() != 0)
			{
				if(qtdePessoas.size() == 0)
					qtdePessoas = driver.findElements(By.xpath("(//*[@class='hprt-occupancy-occupancy-info jq_tooltip\nbest_occ_fit\n'])[1]"));
				
				for(int j = 0; j < 1; j++)
				{
					System.out.println("Nome: " + nome.get(j).getText() + " Preço: " + 
				preco.get(j).getText() + " Nota: " + nota.getText() + qtdePessoas.get(j).getAttribute("data-title"));
					System.out.println("---------------------");
					a.getQuartos().add(encontrarQuarto(nome.get(j), preco.get(j), nota, qtdePessoas.get(j)));
				}
					
				
				anuncios.add(a);
			}
		}
		driver.close();
		return anuncios;
	}
	
	
	public List<String> buscarLinks(List<WebElement> e, WebDriver driver)
	{
		List<String> links = new ArrayList<String>();
		e = driver.findElements(By.xpath("//*[@class='hotel_name_link url']"));
		
		for(int i = 0; i < e.size(); i++)
			links.add(e.get(i).getAttribute("href"));
		
		return links;	
	}
	
	
	public Quarto encontrarQuarto(WebElement nome, WebElement preco, WebElement nota, WebElement qtdePessoas)
	{
		Quarto q = new Quarto();
		q.setNome(nome.getText());
		q.setPreco(StringToDouble.converter(preco.getText()));
		q.setNota(nota.getText());
		q.setQtdePessoas(qtdePessoas.getAttribute("data-title"));
		
		return q;		
	}
	


}
