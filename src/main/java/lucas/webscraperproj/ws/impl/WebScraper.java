package lucas.webscraperproj.ws.impl;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import lucas.web.scraperproj.domain.Anuncio;

public class WebScraper extends AbstractWebScraper{
	private WebDriver driver;
	private List<Anuncio> anuncios;
	private List<WebElement> elements;
	
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
	
	public void buscarAnuncios()
	{
		elements = new ArrayList<WebElement>();

		elements = new ArrayList<WebElement>();
		elements = driver.findElements(By.xpath("//*[@class='sr-hotel__name\n']"));
		anuncios = new ArrayList<Anuncio>();
		
		for(int i = 0; i < elements.size(); i++)
		{
			int j = i + 1;
			String nota = driver.findElement(By.xpath("(//*[@class='review-score-badge'])["+ j +"]")).getText();
			Anuncio anuncio = new Anuncio();
			anuncio.setNota(nota);
			anuncio.setNome(elements.get(i).getText());
			System.out.println(anuncio.getNome() + " Nota: " + anuncio.getNota());
			anuncios.add(anuncio);		
		}
		
	}
	
	public void test()
	{
		elements = new ArrayList<WebElement>();

		elements = new ArrayList<WebElement>();
		elements = driver.findElements(By.xpath("//*[@class='hotel_name_link url']"));
		List<String> links = new ArrayList<String>();
		//List<Anuncio> anuncios = new ArrayList<Anuncio>();
		for(int i = 0; i < elements.size(); i++)
			links.add(elements.get(i).getAttribute("href"));
			
		for(int i =0; i <links.size(); i++)
		{
			driver.navigate().to(links.get(i));
			
			String nome = driver.findElement(By.xpath("//*[@class='hp__hotel-name']")).getText();
			String endereco = driver.findElement(By.xpath("//*[@class='\nhp_address_subtitle\njs-hp_address_subtitle\njq_tooltip\n']")).getText();
			
			//Anuncio a = new Anuncio();
			//a.setNome(nome);
			//a.setEndereco(endereco);
			System.out.println(nome);
			System.out.println(endereco);
		}
			
		
	}

}
