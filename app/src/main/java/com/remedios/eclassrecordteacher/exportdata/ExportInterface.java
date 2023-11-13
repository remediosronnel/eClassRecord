package com.remedios.eclassrecordteacher.exportdata;

import android.app.Activity;
import android.os.Environment;
import android.text.TextUtils;
import android.text.format.DateFormat;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;

public interface ExportInterface {


    default File generateXlsFile(Activity activity, String[] titles, String[] indexName,
                                 JsonArray jsonArray, HashMap<String, String> otherValueMap, String sheetName,
                                 String fileName, int otherRowItemCount) {

        try (Workbook wb = new HSSFWorkbook()) {
            Sheet sheet = wb.createSheet(sheetName);
            Cell cell;
            var rowIndex = 0;

            if (!otherValueMap.isEmpty() && !otherValueMap.keySet().isEmpty()){
                var keys = otherValueMap.keySet();
                var i = 0;
                var limit = 0;
                Row row = sheet.createRow(rowIndex);
                for (String one:keys){
                    if (otherValueMap.containsKey(one)){
                        if (limit == otherRowItemCount){
                            ++rowIndex;
                            row = sheet.createRow(rowIndex);
                            i=0;
                            limit = 0;
                        }else {
                            if (i!=0){
                                ++i;
                                cell = row.createCell(i);
                                cell.setCellValue("");
                            }
                        }
                        cell = row.createCell(i);
                        cell.setCellValue(one);
                        ++i;

                        cell = row.createCell(i);
                        cell.setCellValue(otherValueMap.get(one));
                        ++i;
                        ++limit;
                    }
                }
                ++rowIndex;
                sheet.createRow(rowIndex);
                ++rowIndex;

            }

            Row row = sheet.createRow(rowIndex);
            var a = 0;
            for(String title: titles){
                cell = row.createCell(a);
                cell.setCellValue(title);
                ++a;
            }

            for (int j=0; j<123; j++){
                sheet.setColumnWidth(j,(30*200));
            }

            for (int i=0; i < jsonArray.size(); i++){
                JsonObject jsonObject = jsonArray.get(i).getAsJsonObject();
                if (jsonObject!=null){
                    var b = 0;
                    Row row1 = sheet.createRow(i + rowIndex);
                    for (String index : indexName){
                        cell = row1.createCell(b);
                        try {
                            if (index!=null && !TextUtils.isEmpty(index)){
                                if ((jsonObject.has(index) && jsonObject.get(index).getAsString()!=null)
                                        && !TextUtils.isEmpty(jsonObject.get(index).getAsString())){
                                    cell.setCellValue(jsonObject.get(index).getAsString());
                                }else {
                                    cell.setCellValue(" - ");
                                }
                            }
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                        ++b;
                    }

                }
            }
            File file = null;
            try {
                file = getFile(activity, fileName + System.currentTimeMillis()+".xls");
            }catch (Exception e){
                e.printStackTrace();
            }

            FileOutputStream fileOutputStream = new FileOutputStream(file.getPath());
            wb.write(fileOutputStream);
            fileOutputStream.close();
            return file;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    default File getFile(Activity activity, String fileName) throws IOException {
        var now = new Date();
        DateFormat.format("yyyy-MM-dd_hh:mm:ss", now);
        var rootFolder = new File(String.valueOf(activity.getExternalFilesDir(Environment.DIRECTORY_DCIM)));
        final var filePath = new File(rootFolder, fileName.replaceAll("^a-zA-Z0-9._&", ""));

        if (!filePath.exists()){
            filePath.mkdir();
        }
        if (!filePath.exists()){
            filePath.createNewFile();
        }
        else {
            filePath.delete();
            filePath.createNewFile();
        }
        return filePath;
    }


}
