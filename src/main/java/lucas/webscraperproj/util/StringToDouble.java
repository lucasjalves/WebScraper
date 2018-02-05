package lucas.webscraperproj.util;

public class StringToDouble {
	public static double converter(String preco)
	{
		return Double.parseDouble(preco.substring(3).replace(".", "").replace(",", "."));
	}
}
