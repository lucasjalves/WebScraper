package lucas.webscraperproj.main;

import org.openqa.selenium.chrome.ChromeDriver;

import lucas.webscraperproj.util.Datas;
import lucas.webscraperproj.ws.impl.WebScraper;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) 
    {
    	
    	
      WebScraper w = new WebScraper();
      w.setDriver(new ChromeDriver());
        
      w.abrirNavegador("https://www.booking.com");
      w.filtrosPesquisa("São Paulo", Datas.dtAtual(), Datas.somarSemanas(1));
      w.test();

    	
    }
}
