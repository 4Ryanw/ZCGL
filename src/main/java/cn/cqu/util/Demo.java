package cn.cqu.util;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Demo {
    public static void main(String[] args) throws IOException {
        write();
    }

    /**
     * 读取
     * @throws IOException
     */
    public static void read() throws IOException {
        //获取工作簿
        XSSFWorkbook workbook =  new XSSFWorkbook("E:\\aaa.xlsx");
        //获取工作表
        XSSFSheet workbookSheet =workbook.getSheetAt(0);
        //获取行
        for (Row row:workbookSheet
        ) {
            //获取单元格
            for (Cell cell:row
            ) {
                //获取内容
                String  a = cell.getStringCellValue();
                System.out.println(a);
            }
        }
        //释放资源
        workbook.close();
    }


    /**
     * 写
     */
    public static void write() throws IOException {
       XSSFWorkbook workbook = new XSSFWorkbook();
       //创建工作表
       XSSFSheet worksheet = workbook.createSheet("工作表一");
       //创建工作行
       XSSFRow row =  worksheet.createRow(0);
       //创建单元格
        row.createCell(0).setCellValue("wwww");
        row.createCell(1).setCellValue("gag");
        row.createCell(2).setCellValue("的撒大");
        XSSFRow row2 =  worksheet.createRow(1);
        row2.createCell(0).setCellValue("顶顶顶");
        //输出流
        FileOutputStream out = new FileOutputStream("E:\\aaa.xlsx");
        workbook.write(out);
        out.flush();
        out.close();
        workbook.close();
        System.out.println("成功");


    }

}
