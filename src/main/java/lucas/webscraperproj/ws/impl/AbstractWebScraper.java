package lucas.webscraperproj.ws.impl;

public abstract class AbstractWebScraper{
	protected void setup()
	{
		System.setProperty("webdriver.chrome.driver", 
				"C:\\Users\\Lucas\\Downloads\\chromedriver_win32\\chromedriver.exe");
	}

}
