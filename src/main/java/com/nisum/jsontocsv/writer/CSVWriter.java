
package com.nisum.jsontocsv.writer;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;


public class CSVWriter {

    public static String getCSV(List<Map<String, String>> flatJson) {
        return getCSV(flatJson, ",");
    }

    public static String getCSV(List<Map<String, String>> flatJson, String separator) {
        Set<String> headers = collectHeaders(flatJson);
        String csvString = StringUtils.join(headers.toArray(), separator) + "\n";

        for (Map<String, String> map : flatJson) {
            csvString = csvString + getSeperatedColumns(headers, map, separator) + "\n";
        }

        return csvString;
    }

    public static void writeToFile(String csvString, String fileName) {
        try {
            FileUtils.write(new File(fileName), csvString);
        } catch (IOException e) {
            System.out.println("CSVWriter#writeToFile(csvString, fileName) IOException: "+e);
        }
    }


    private static String getSeperatedColumns(Set<String> headers, Map<String, String> map, String separator) {
        List<String> items = new ArrayList<String>();
        for (String header : headers) {
            String value = map.get(header) == null ? "" : map.get(header).replaceAll("[\\,\\;\\r\\n\\t\\s]+", " "); 
            items.add(value);
        }

        return StringUtils.join(items.toArray(), separator);
    }

    private static Set<String> collectHeaders(List<Map<String, String>> flatJson) {
        Set<String> headers = new LinkedHashSet<String>();

        for (Map<String, String> map : flatJson) {
            headers.addAll(map.keySet());
        }

        return headers;
    }

/*
    public static Set<String> collectOrderedHeaders(List<Map<String, String>> flatJson) {
        Set<String> headers = new TreeSet<String>();
        for (Map<String, String> map : flatJson) {
        	headers.addAll(map.keySet());
        }
        return headers;
    }  */
}