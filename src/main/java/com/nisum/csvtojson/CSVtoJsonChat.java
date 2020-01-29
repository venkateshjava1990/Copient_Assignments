package com.nisum.csvtojson;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CSVtoJsonChat {
    static Pattern pattern= Pattern.compile(",");

    public static String replaceSym(String prop){
        return prop.replaceAll("~"," ");
    }

    public static Object  map(List<String> colls,String fileName){
        Object[] arr= colls.toArray();
        Object obj=null;
        if(fileName.equalsIgnoreCase("chat")){
            String timeStam="";
            String username="";
            String message="";
             String timeStampMessgae=((String)arr[1]).trim();
             if(timeStampMessgae.indexOf("]")>-1){
                 String[] arrMsg=timeStampMessgae.split("\\]");
                 if(arrMsg.length==1){
                     timeStam=null;
                     username=null;
                     message=timeStampMessgae;
                 }else {
                     timeStam = arrMsg[0].split("\\[")[1];
                     username = (arrMsg[1].indexOf(">") > -1 && arrMsg[1].indexOf("<") > -1) ? arrMsg[1].split(">")[0].split("\\<")[1] : null;
                     message = (username == null) ? arrMsg[1] : (arrMsg[1].split(">").length > 1) ? arrMsg[1].split(">")[1] : "";
                     if (arrMsg.length == 3) {
                         message = message + "]" + arrMsg[2];
                     }
                 }
             }else{
                 timeStam=null;
                 username=(timeStampMessgae.indexOf(">")>-1&&timeStampMessgae.indexOf("<")>-1)?timeStampMessgae.split(">")[0].split("\\<")[1]:null;
                 message=(username==null)?timeStampMessgae:timeStampMessgae.split(">")[1];
             }

             if(arr.length==3){
                 message=message+","+arr[2];
             }
             obj=new  Chat( ((String)arr[0]).trim(),timeStam,username,message);
        }else if(fileName.equalsIgnoreCase("contact-list")){
            obj=new  ContactList(((String)arr[0]).trim(),
                                 ((String)arr[1]).trim(),
                                 ((String)arr[2]).trim(),
                                 ((String)arr[3]).trim(),
                                 ((String)arr[4]).trim(),
                                 ((String)arr[5]).trim(),
                                 ((String)arr[6]).trim());

        }else if(fileName.equalsIgnoreCase("Sales")){
            obj=new Sales(((String)arr[0]).trim(),
                         ((String)arr[1]).trim(),
                         ((String)arr[2]).trim(),
                         ((String)arr[3]).trim(),
                         ((String)arr[4]).trim(),
                         ((String)arr[5]).trim(),
                         ((String)arr[6]).trim(),
                         ((String)arr[7]).trim(),
                         ((String)arr[8]).trim(),
                         ((String)arr[9]).trim(),
                         ((String)arr[10]).trim(),
                         ((String)arr[11]).trim(),
                         ((String)arr[12]).trim(),
                         ((String)arr[13]).trim());
         }
       return obj;
    }

    public static List<String> mapToObject(String line){
        return   Stream.of(pattern.split(line))
                .map(CSVtoJsonChat::replaceSym)
                .collect(Collectors.toList());
    }
    public static String mapToJson(Object obj){
        ObjectMapper mapper = new ObjectMapper();
        String json="";
        try {
            //mapper.writeValue(new File(jsonFile),obj);
            json= mapper.writeValueAsString(obj);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return json;
    }


    public static void main(String... vr){
       System.out.println("inside");
        Integer linesSkip=Integer.parseInt(vr[1]);
        File file=new File(vr[0]);
        String fileName=file.getName().split("\\.")[0];
      try(BufferedReader in=new BufferedReader(new FileReader(file))){
            writeTofile(in.lines()
                    .skip(linesSkip)
                    .filter(line->line!=null &&!line.equals(""))
                    .map(line->line.replaceAll("\"",""))
                    .map(line->line.replaceAll(",",",~"))
                    .map(CSVtoJsonChat::mapToObject)
                    .map( line->map(line,fileName))
                    .map(CSVtoJsonChat::mapToJson)
                    .peek(System.out::println)
                    .collect(Collectors.toList()),file.getAbsolutePath().split("\\.")[0]+".json");
        }  catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void writeTofile(List<String> jsondata,String destFile){
        String last=jsondata.get(jsondata.size()-1);
        jsondata.remove(jsondata.size()-1);
        try(BufferedWriter writer=new BufferedWriter(new FileWriter(new File(destFile)))){
            writer.write("[");
            jsondata.stream()
                    .forEach(str -> {
                        try {

                            writer.write(str+",");

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
            writer.write(last);
            writer.write("]");
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


