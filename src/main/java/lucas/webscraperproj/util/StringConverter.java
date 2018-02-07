package lucas.webscraperproj.util;

public class StringConverter {
	public static double precoConverter(String preco)
	{
		return Double.parseDouble(preco.substring(3).replace(".", "").replace(",", "."));
	}
	
	public static String estrelasConverter(String estrelas)
	{
		return estrelas.replaceAll("[^\\d.]", "");
	}
}
