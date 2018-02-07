package lucas.webscraperproj.main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.chrome.ChromeDriver;

import lucas.webscraperproj.domain.Anuncio;
import lucas.webscraperproj.domain.EntidadeDominio;
import lucas.webscraperproj.domain.Quarto;
import lucas.webscraperproj.filecreator.ExcelFile;
import lucas.webscraperproj.util.Datas;
import lucas.webscraperproj.ws.impl.WebScraper;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException 
    {
    	
    	WebScraper w = new WebScraper();
    	w.setDriver(new ChromeDriver()); 
    	w.abrirNavegador("https://www.booking.com");
    	w.filtrosPesquisa("São Paulo", Datas.dtAtual(), Datas.somarSemanas(2));
    	ExcelFile.criarArquivo(w.buscarAnuncios(), "C:\\Users\\Lucas\\Desktop\\teste.xls", "teste");

      
    }
}
