
package com.nisum.jsontocsv.parser;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.io.FileUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JSONFlattener {

    private static final Class<?> JSON_OBJECT = JSONObject.class;
    private static final Class<?> JSON_ARRAY = JSONArray.class;

    public static List<Map<String, String>> parseJson(File file) {
        List<Map<String, String>> flatJson = null;
        String json = "";
         String encoding="UTF-8";
        try {

            json = FileUtils.readFileToString(file, encoding);
            List<String> headers=getHeaders(json);
            flatJson = parseJson(json,headers);

        } catch (IOException e) {
            System.out.println("JsonFlattener#ParseJson(file, encoding) IOException: "+ e);
        } catch (Exception ex) {
            System.out.println("JsonFlattener#ParseJson(file, encoding) Exception: "+ ex);
        }

        return flatJson;
    }

    public static List<Map<String, String>> parseJson(String json,List<String> headers) {
        List<Map<String, String>> flatJson = null;

        try {
            JSONObject jsonObject = new JSONObject(json);
            flatJson = new ArrayList<Map<String, String>>();
            flatJson.add(parse(jsonObject,headers));
        } catch (JSONException je) {
            System.out.println("Handle the JSON String as JSON Array");
            flatJson = handleAsArray(json,headers);
        }

        return flatJson;
    }

    private static List<String> getHeaders(String json) {
        String[] jsonArr=json.split("\\},")[0].split("\\{")[1].split(",");
        List<String> headerString= Stream.of(jsonArr)
                 .map(line->line.replaceAll("\"","")
                                .replaceAll("\\n","")
                                .replaceAll("\\r","")
                                .replaceAll("\\t",""))
                 .map(line->line.split("\\:")[0])
                 .collect(Collectors.toList()) ;
        return headerString;
    }

    public static Map<String, String> parse(JSONObject jsonObject,List<String> headers) {
        Map<String, String> flatJson = new LinkedHashMap<String, String>();
        flatten(jsonObject, flatJson, "",headers);

        return flatJson;
    }

    public static List<Map<String, String>> parse(JSONArray jsonArray,List<String> headers) {
        JSONObject jsonObject = null;
        List<Map<String, String>> flatJson = new ArrayList<Map<String, String>>();
        int length = jsonArray.length();
        Stream.of(jsonArray).map(obj->parse(obj,headers));
                            //.collect(Collectors.toMap());
        for (int i = 0; i < length; i++) {
            try {
                jsonObject = jsonArray.getJSONObject(i);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            Map<String, String> stringMap = parse(jsonObject,headers);
            flatJson.add(stringMap);
        }

        return flatJson;
    }

    private static List<Map<String, String>> handleAsArray(String json,List<String> headers) {
        List<Map<String, String>> flatJson = null;

        try {
            JSONArray jsonArray = new JSONArray(json);
            flatJson = parse(jsonArray,headers);
        } catch (Exception e) {
            System.out.println("JSON might be malformed, Please verify that your JSON is valid");
        }

        return flatJson;
    }

    private static void flattenByHeaders(String key,JSONObject obj, Map<String, String> flatJson, String prefix,List<String> headers){
        try {
            String _prefix = prefix != "" ? prefix + "." : "";
            if (obj.get(key).getClass() == JSON_OBJECT) {
                JSONObject jsonObject = (JSONObject) obj.get(key);
                flatten(jsonObject, flatJson, _prefix + key,headers);
            } else if (obj.get(key).getClass() == JSON_ARRAY) {
                JSONArray jsonArray = (JSONArray) obj.get(key);

                if (jsonArray.length() >0) {
                    flatten(jsonArray, flatJson, _prefix + key,headers);
                }


            } else {
                String value = obj.get(key).toString();

                if (value != null && !value.equals("null")) {
                    flatJson.put(initCaptal(_prefix + key), value);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    private static void flatten(JSONObject obj, Map<String, String> flatJson, String prefix,List<String> headers) {
        headers.stream().forEach(key->flattenByHeaders(key,obj,flatJson,prefix,headers));
    }
    public static String initCaptal(String fieldName){
        return fieldName.substring(0,1).toUpperCase() + fieldName.substring(1).toLowerCase();
    }
    private static void flatten(JSONArray obj, Map<String, String> flatJson, String prefix,List<String> headers) {
        int length = obj.length();
        for (int i = 0; i < length; i++) {
            try {
                if (obj.get(i).getClass() == JSON_ARRAY) {
                    JSONArray jsonArray = (JSONArray) obj.get(i);
                    if (jsonArray.length() < 1) {
                        continue;
                    }
                    flatten(jsonArray, flatJson, prefix + "[" + i + "]",headers);
                } else if (obj.get(i).getClass() == JSON_OBJECT) {
                    JSONObject jsonObject = (JSONObject) obj.get(i);
                    flatten(jsonObject, flatJson, prefix + "[" + (i + 1) + "]",headers);
                } else {
                    String value = obj.get(i).toString();
                    if (value != null) {
                        flatJson.put(initCaptal(prefix + "[" + (i + 1)) + "]", value);
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

}