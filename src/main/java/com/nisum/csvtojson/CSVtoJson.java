package com.nisum.csvtojson;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CSVtoJson {
   static Pattern pattern= Pattern.compile(",");
   public static String replaceSym(String prop){
     return prop.replaceAll("~"," ");
   }

   public static ContactList  map(List<String> colls){
       Object[] arr= colls.toArray();
       ContactList conList=new  ContactList();
       conList.setDepartment(((String)arr[0]).trim());
       conList.setFirstName(((String)arr[1]).trim());
       conList.setLastName(((String)arr[2]).trim());
       conList.setPrimaryContact(((String)arr[3]).trim());
       conList.setEmailId(((String)arr[4]).trim());
       conList.setPhno(((String)arr[5]).trim());
       conList.setRole(((String)arr[6]).trim());

       return conList;
   }

    public static List<String> mapToObject(String line){
           return   Stream.of(pattern.split(line))
                    .map(CSVtoJson::replaceSym)
                     .collect(Collectors.toList());
        }
    public static String mapToJson(ContactList obj){
        ObjectMapper mapper = new ObjectMapper();
        String json="";
        try {
            mapper.writeValue(new File("D:\\Practice\\Copient_Assignments\\contact-list.json"),obj);
            json= mapper.writeValueAsString(obj);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return json;
    }


    public static void main(String... vr){
         Integer linesSkip=1;

     try(BufferedReader in=new BufferedReader(new FileReader("D:\\Practice\\Copient_Assignments\\contact-list.csv"))){
       writeTofile(in.lines()
                 .skip(linesSkip)
                 .map(line->line.replaceAll(",",",~"))
                 .map(CSVtoJson::mapToObject)
                 .map(CSVtoJson::map)
                 .map(CSVtoJson::mapToJson)
                 .peek(System.out::println)
                 .collect(Collectors.toList()));
     }  catch (IOException e) {
         e.printStackTrace();
     }
    }
    public static void writeTofile(List<String> jsondata){
       String last=jsondata.get(jsondata.size()-1).substring(0,jsondata.get(jsondata.size()-1).length());
       System.out.println(last);
        jsondata.remove(jsondata.size()-1);
        try(BufferedWriter writer=new BufferedWriter(new FileWriter(new File("D:\\Practice\\Copient_Assignments\\contact-list.json")))){
            jsondata.stream()
                    .forEach(str -> {
                try {

                    writer.write(str+",");

                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            writer.write(last);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
