package sg.edu.nus.iss.phoenix.core.controller;

public class FCUtilities {
    
    public static final String FC_PREFIX="app";
	//Get userId from the servlet pathInfo    
    public static String stripPath(String pathInfo){
        int pos = pathInfo.indexOf("/");
        int len = pathInfo.length();
        String userId = pathInfo.substring(pos+1,len);
        System.out.println("Path: " + userId);
        return userId;
    }
    
    public static String createUrl(String controller, String action)
    {
        return null;
    }
}
