package cn.cqu.util;

import org.apache.poi.xssf.usermodel.*;
import org.springframework.core.io.ClassPathResource;

import javax.servlet.http.HttpServletResponse;
import javax.xml.crypto.Data;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * 功能说明：文件工具类
 * @author xuwen
 * @date 2019/04/22
 */
public class ExcelExportUtil {
    /**
     * 导出文件
     * @param response   请求响应
     * @param sheetName  Sheet名称
     * @param headers    表头
     * @param stringList 表数据
     */
    public static void downloadExcelFile(HttpServletResponse response, String sheetName, String[] headers,
                                         List<String[]> stringList,String fileName) {
        ExcelExportUtil.exportExcelFile(response, ExcelExportUtil.createExcelFile(sheetName, headers, stringList),fileName);
    }

    /**
     * 创建excel文件
     * @param sheetName  Sheet名称
     * @param headers    表头
     * @param stringList 表数据
     * @author xuwen
     */
    private static XSSFWorkbook createExcelFile(String sheetName, String[] headers, List<String[]> stringList) {
        // 创建excel文件
        XSSFWorkbook workbook = new XSSFWorkbook();
        // 设置excel文件格式
        XSSFCellStyle cellStyle = workbook.createCellStyle();
        // 设置sheet名称
        XSSFSheet sheet = workbook.createSheet(sheetName);
        // 初始化行号
        int rowNum = 0;
        // 创建表头行
        XSSFRow row = sheet.createRow(rowNum);
        // 填充表头
        for (int m = 0; m < headers.length; m++) {
            XSSFCell cell = row.createCell(m);
            cell.setCellValue(headers[m]);
        }
        // 填充表格数据
        for (int n = 0; n < stringList.size(); n++) {
            XSSFRow dataRow = sheet.createRow(++rowNum);
            String[] data = stringList.get(n);
            for (int j = 0; j < data.length; j++) {
                dataRow.createCell(j).setCellValue(data[j]);
            }
        }
        return workbook;
    }

    /**
     * 下载模板
     * @param response 请求响应
     * @param file     文件
     * @author xuwen
     */
    public static void downloadTemplate(HttpServletResponse response, String file) {
        try {
            ClassPathResource classPathResource = new ClassPathResource(file);
            InputStream inputStream = classPathResource.getInputStream();
            XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
            ExcelExportUtil.exportExcelFile(response, workbook,file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 在响应中返回文件流
     * @param response 请求响应
     * @param workbook excel文件
     * @author xuwen
     */
    public static void exportExcelFile(HttpServletResponse response, XSSFWorkbook workbook,String fileName) {
        response.setContentType("application/octet-stream;charset=UTF-8");
        fileName+= System.currentTimeMillis();
        fileName+=".xlsx";
        try {
            response.addHeader("Content-Disposition", "attachment;filename="
                    + new String(fileName.getBytes("GB2312"), "iso8859-1"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        try {
            OutputStream outputStream = response.getOutputStream();
            workbook.write(outputStream);
            outputStream.flush();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
