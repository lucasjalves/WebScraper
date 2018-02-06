package lucas.webscraperproj.filecreator;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import lucas.webscraperproj.domain.Anuncio;
import lucas.webscraperproj.domain.EntidadeDominio;

public class ExcelFile {
	
	public static void criarArquivo(List<EntidadeDominio> e, String nome, String nomePlanilha) throws IOException
	{
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet(nomePlanilha);
		HSSFRow headRow = sheet.createRow(0);
		headRow.createCell(0).setCellValue("Nome");
		headRow.createCell(1).setCellValue("Endereco");
		headRow.createCell(2).setCellValue("Quarto");
		headRow.createCell(3).setCellValue("Valor");
		headRow.createCell(4).setCellValue("Nota");
		headRow.createCell(5).setCellValue("Qtde Pessoas");
		
        int i = 1;
        for(EntidadeDominio en: e)
        {
        	Anuncio a = (Anuncio) en;
        	
        	HSSFRow row = sheet.createRow(i++);
        	row.createCell(0).setCellValue(a.getNome());
        	row.createCell(1).setCellValue(a.getEndereco());
        	row.createCell(2).setCellValue(a.getQuartos().get(0).getNome());
        	row.createCell(3).setCellValue(a.getQuartos().get(0).getPreco());
        	row.createCell(4).setCellValue(a.getQuartos().get(0).getNota());
        	row.createCell(5).setCellValue("sadn");
        	
        }
        FileOutputStream fileOut = new FileOutputStream(nome);
        workbook.write(fileOut);
        workbook.close();
        fileOut.close();
	}
}
