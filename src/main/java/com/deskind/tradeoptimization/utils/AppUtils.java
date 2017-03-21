
package com.deskind.tradeoptimization.utils;

import com.deskind.tradeoptimization.entities.Bar;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class AppUtils {
    public static BufferedReader getReader(String path){
        BufferedReader br = null;
        try {
            File f = new File(path);
            br = new BufferedReader(new FileReader(f));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(AppUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
        return br;
    }
    
    public static List<Bar> getBars(BufferedReader br){
        /**Process lines which format is 
        2010.06.23,09:25,1.22935,1.22935,1.22797,1.22827,257*/
        String line = null;
        List<Bar> list = new ArrayList();
        try {
            while((line=br.readLine())!=null){
                String[] arr = line.split(",");
                
                /**The length of line must be 7 if there is no signal*/
                if(arr.length==7){
                    /**Process first element in array which is date in format
                     of 2010.06.23*/
                    String[] dateElemenets = arr[0].split("\\.");
                    //end
                    
                    /**Process second element in array which is time in format
                     of 09:25*/
                    String[] timeElements = arr[1].split(":");
                    //end
                    
                    /**Create Bar element*/
                    LocalDateTime ldt = LocalDateTime.of(Integer.parseInt(dateElemenets[0]),
                                                        Integer.parseInt(dateElemenets[1]),
                                                        Integer.parseInt(dateElemenets[2]),
                                                        Integer.parseInt(timeElements[0]),
                                                        Integer.parseInt(timeElements[1]));
                    Bar b = new Bar(ldt,
                                Float.parseFloat(arr[2]),
                                Float.parseFloat(arr[3]),
                                Float.parseFloat(arr[4]),
                                Float.parseFloat(arr[5]),
                                Integer.parseInt(arr[6]));
                    //end
                    
                    /**Add the bar object to an ArrayList*/
                    list.add(b);
                    //end
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(AppUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return list;
    }
}
