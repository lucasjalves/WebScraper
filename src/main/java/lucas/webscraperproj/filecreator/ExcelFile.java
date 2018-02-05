package lucas.webscraperproj.filecreator;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import lucas.webscraperproj.domain.Anuncio;
import lucas.webscraperproj.domain.EntidadeDominio;
import lucas.webscraperproj.domain.Quarto;

public class ExcelFile {
	
	public static void criarArquivo(List<EntidadeDominio> e, String nome, String nomePlanilha) throws IOException
	{
		int lastRow = 1;
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet(nomePlanilha);
		
		HSSFRow rowhead = sheet.createRow(0);
        rowhead.createCell(0).setCellValue("Nome");
        rowhead.createCell(1).setCellValue("Endereço");
        rowhead.createCell(2).setCellValue("Quartos");
        for(int i = 0; i < e.size(); i++)
        {
        	Anuncio a = (Anuncio) e.get(i);
            HSSFRow row = sheet.createRow(i + 1);
        
            row.createCell(0).setCellValue(a.getNome());
            row.createCell(1).setCellValue(a.getEndereco());     
            
            for(int j = 0; j < a.getQuartos().size(); j++)
            {
            	Quarto q = a.getQuartos().get(j);
            	HSSFRow rowQuartos = sheet.createRow(lastRow + 1);
            	rowQuartos.createCell(2).setCellValue(q.getNome());
            	lastRow = j;
            }
        }
        
        FileOutputStream fileOut = new FileOutputStream(nome);
        workbook.write(fileOut);
        fileOut.close();
	}
}
