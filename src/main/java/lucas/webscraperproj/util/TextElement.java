package lucas.webscraperproj.util;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class TextElement {
	public static String getText(WebDriver d, String xpath)
	{
		return d.findElement(By.xpath(xpath)).getText();
	}
}
