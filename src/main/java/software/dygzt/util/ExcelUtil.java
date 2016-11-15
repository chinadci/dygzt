package software.dygzt.util;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.hssf.record.formula.functions.T;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFComment;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.hssf.util.HSSFColor;

import software.dygzt.service.share.model.ExcelCell;

public class ExcelUtil{
	

	/**
	 * @param title
	 *            表格标题名
	 * @param dataset
	 *            需要显示的数据集合
	 * @param out
	 *            与输出设备关联的流对象，可以将EXCEL文档导出到本地文件或者网络中
	 */
    public static void exportExcel(String title, List<List<ExcelCell>> datas, OutputStream out) {
        //声明一个工作薄
        HSSFWorkbook workbook = new HSSFWorkbook();
        //生成一个表格
        HSSFSheet sheet = workbook.createSheet(title);
        
        //设置表格默认列宽度为15个字节
        sheet.setDefaultColumnWidth(15);
        
        //生成一个样式
        HSSFCellStyle style = workbook.createCellStyle();
        //设置这些样式
        style.setFillForegroundColor(HSSFColor.SKY_BLUE.index);
        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        //生成一个字体
        HSSFFont font = workbook.createFont();
        font.setColor(HSSFColor.VIOLET.index);
        font.setFontHeightInPoints((short) 12);
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        //把字体应用到当前的样式
        style.setFont(font);
        
        //生成并设置另一个样式
        HSSFCellStyle style2 = workbook.createCellStyle();
        style2.setFillForegroundColor(HSSFColor.WHITE.index);
        style2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        style2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style2.setBorderRight(HSSFCellStyle.BORDER_THIN);
        style2.setBorderTop(HSSFCellStyle.BORDER_THIN);
        style2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        style2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        // 生成另一个字体
        HSSFFont font2 = workbook.createFont();
        font2.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
        // 把字体应用到当前的样式
        style2.setFont(font2);

        for (List<ExcelCell> dataRow:datas) {
        	HSSFRow row;
        	if(dataRow!=null&&dataRow.size()>0){
        		//得到行
        		row = sheet.getRow(dataRow.get(0).getStart_x());
        		if(row==null){
        			row = sheet.createRow(dataRow.get(0).getStart_x());		
        		}
        		for(ExcelCell dataCell:dataRow){
        			//得到Cell
        			HSSFCell cell = row.createCell(dataCell.getStart_y());
        			if(dataCell.isHeader()){
        				cell.setCellStyle(style);
        			}else{
        				cell.setCellStyle(style2);
        			}
                    cell.setCellValue(dataCell.getValue());
        			if(dataCell.isMerged()){	//合并单元格，更改样式
        				for(int tmp_x=dataCell.getStart_x();tmp_x<=dataCell.getEnd_x();tmp_x++){
        					for(int tmp_y=dataCell.getStart_y();tmp_y<=dataCell.getEnd_y();tmp_y++){
        						HSSFRow tmpRow = sheet.getRow(tmp_x);
        						if(tmpRow==null){
        							tmpRow = sheet.createRow(tmp_x);
        		        		}
        						HSSFCell tmpCell = tmpRow.getCell(tmp_y);
        						if(tmpCell==null){
        							tmpCell = tmpRow.createCell(tmp_y);
        						}
        						if(dataCell.isHeader()){
        							tmpCell.setCellStyle(style);
        	        			}else{
        	        				tmpCell.setCellStyle(style2);
        	        			}
        					}
        				}
                    	sheet.addMergedRegion(new CellRangeAddress(dataCell.getStart_x(),
                    			dataCell.getEnd_x(),dataCell.getStart_y(),dataCell.getEnd_y()));
                    }
        		}
        	}
        }
        try {
            workbook.write(out);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
