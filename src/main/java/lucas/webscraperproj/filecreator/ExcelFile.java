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
import lucas.webscraperproj.util.Datas;

public class ExcelFile {
	
	public static void criarArquivo(List<EntidadeDominio> e, String caminho, String nomePlanilha) throws IOException
	{
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet(nomePlanilha);
		HSSFRow headRow = sheet.createRow(0);
		headRow.createCell(0).setCellValue("Nome");
		headRow.createCell(1).setCellValue("Endereco");
		headRow.createCell(2).setCellValue("Quarto");
		headRow.createCell(3).setCellValue("Valor por " + Datas.QTDE_DIAS + " dias");
		headRow.createCell(4).setCellValue("Diária");
		headRow.createCell(5).setCellValue("Nota");
		headRow.createCell(6).setCellValue("Qtde Pessoas");
		headRow.createCell(7).setCellValue("Estrelas");
		
        int i = 1;
        for(EntidadeDominio en: e)
        {
        	
        	Anuncio a = (Anuncio) en;
        	Quarto q = a.getQuartos().get(0);
        	HSSFRow row = sheet.createRow(i++);
        	row.createCell(0).setCellValue(a.getNome());
        	row.createCell(1).setCellValue(a.getEndereco());
        	row.createCell(2).setCellValue(q.getNome());
        	row.createCell(3).setCellValue(q.getPreco());
        	row.createCell(4).setCellValue(q.getPreco() / Datas.QTDE_DIAS);
        	row.createCell(5).setCellValue(q.getNota());
        	row.createCell(6).setCellValue(q.getQtdePessoas());
        	row.createCell(7).setCellValue(a.getEstrelas());
        	
     
        	
        }
        FileOutputStream fileOut = new FileOutputStream(caminho);
        workbook.write(fileOut);
        workbook.close();
        fileOut.close();
	}
}
