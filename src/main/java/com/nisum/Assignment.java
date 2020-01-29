package com.nisum;

import java.util.stream.Stream;

public class Assignment {
    public static void main(String... vr){
      int[] arr={1,2,5,3,6,8,7,9,4};
      sort(arr);
      int mid=((arr.length)/2);
      int right=arr.length-1;
       while(mid>=0){
          if(arr[mid]<arr[right] && mid!=0){
             System.out.print(arr[mid]+"<"+arr[right]+">");
          }
          if(mid==0){
              System.out.print(arr[mid]);
          }
          mid--;
          right--;
       }

    }
    public static  int[] sort(int[] arr){
        for(int i=0;i<arr.length-1;i++){
            for(int j=0;j<arr.length-i-1;j++){
               if(j+1<=arr.length-1){
                    if(arr[j+1]<arr[j]){
                       int temp=arr[j+1];
                        arr[j+1]=arr[j];
                        arr[j]=temp;
                    }
               }
            }
        }
      return arr;
    }
}
