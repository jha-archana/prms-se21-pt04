/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.utils;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jiqin
 */
public class URLUtils {
    public static String formURL(String urlStr, Map<String,String> params){
        try {
            URL url = new URL(urlStr);
            String queryStr = url.getQuery();
            Map<String, String> orig = new HashMap<>();
            if(queryStr != null){
                orig = getQueryMap(queryStr);
            }
            if(params != null){
                //update values for each params
                Set<String> keys = params.keySet();
                Iterator<String> it = keys.iterator();
                while(it.hasNext()){
                    String key = it.next();
                        orig.put(key, params.get(key));
                    
                }
            }
            //reform the url string
            StringBuilder sb = new StringBuilder(urlStr.subSequence(0, urlStr.indexOf("?")));
            
            Set<String> keys = orig.keySet();
            if(keys.size()>0) sb.append("?");
            Iterator<String> it = keys.iterator();
            while(it.hasNext()){
                String key = it.next();
                sb.append(key);
                sb.append("=");
                try{
                    sb.append(URLEncoder.encode(orig.get(key), "UTF-8"));
                }catch(Exception e){
                    e.printStackTrace();
                    sb.append("");
                }
                sb.append("&");
            }
            //remove last
            sb.deleteCharAt(sb.length()-1);
            return sb.toString();
        } catch (MalformedURLException ex) {
            Logger.getLogger(URLUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    
   public static Map<String, String> getQueryMap(String query)  
    {  
        String[] params = query.split("&");  
        Map<String, String> map = new HashMap<>();  
        for (String param : params)  
        {  
            String name = param.split("=")[0];  
            String value = param.split("=")[1];  
            map.put(name, value);  
        }  
        return map;  
    }  
}
