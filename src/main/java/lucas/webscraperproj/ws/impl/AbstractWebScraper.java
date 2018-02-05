package lucas.webscraperproj.ws.impl;

public abstract class AbstractWebScraper{
	protected void setup()
	{
		System.setProperty("webdriver.chrome.driver", 
				"C:\\selenium\\chromedriver.exe");
	}

}
