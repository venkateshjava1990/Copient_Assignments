package com.nisum.jsontocsv;
import com.nisum.jsontocsv.parser.JSONFlattener;
import com.nisum.jsontocsv.writer.CSVWriter;
import java.io.File;
import java.util.List;
import java.util.Map;

public class JsonToCsv {
    public static void main(String...vr){
        String fileName=vr[0];
        File file=new File(fileName);
        String destFile=file.getAbsolutePath().split("\\.")[0]+"1.csv";
        List<Map<String, String>> flatJson   = JSONFlattener.parseJson(file);
        CSVWriter.writeToFile(CSVWriter.getCSV(flatJson, ","), destFile);

    }

}
