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
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet(nomePlanilha);
		HSSFRow headRow = sheet.createRow(0);
		headRow.createCell(0).setCellValue("Nome");
		headRow.createCell(1).setCellValue("Endereco");
		headRow.createCell(2).setCellValue("Quarto");
		headRow.createCell(3).setCellValue("Valor");
		
		
        int i = 1;
        for(EntidadeDominio en: e)
        {
        	Anuncio a = (Anuncio) en;
        	
        	HSSFRow row = sheet.createRow(i++);
        	row.createCell(0).setCellValue(a.getNome());
        	row.createCell(1).setCellValue(a.getEndereco());
        	row.createCell(2).setCellValue(a.getQuartos().get(0).getNome());
        	row.createCell(3).setCellValue(a.getQuartos().get(0).getPreco());
        }
        /*
        for(int i = 0; i < e.size(); i++)
        {
        	Anuncio a = (Anuncio) e.get(i);
        	row = sheet.createRow(1 + i);

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
        */
        FileOutputStream fileOut = new FileOutputStream(nome);
        workbook.write(fileOut);
        workbook.close();
        fileOut.close();
	}
}
