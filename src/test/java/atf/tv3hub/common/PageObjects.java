package atf.tv3hub.common;

import java.util.HashMap;

public class PageObjects {

	static ExcelUtil read = EnvironmentConfig.testCaseXL;
	static ExcelUtil object = EnvironmentConfig.objectRepositoryXL;
	static HashMap<String, String> pageObjects=new HashMap<String, String>();
	
	public static String object(String sheetName,String objectId){
  	  
        if (!pageObjects.containsKey(objectId)){
                try {
                      try {
                            pageObjects.put(objectId, object.getDatawithKey(sheetName,1,objectId));
                            } catch (NullPointerException e) {
                           throw new Exception("No such Object Named "+objectId+" in ObjectRepositary");
                        }
                } catch (Exception e) {
                		System.out.println("Exception From PageObjects"+"==>>"+" "+e);                
                  }        
        }
        
        return pageObjects.get(objectId);
    }
	
	public static String objectType(String sheetName,String objectId){
	  	  
        if (!pageObjects.containsKey(objectId)){
                try {
                      try {
                            pageObjects.put(objectId, object.getDatawithKey(sheetName,2,objectId));
                            } catch (NullPointerException e) {
                           throw new Exception("No such Object Named "+objectId+" in ObjectRepositary");
                        }
                } catch (Exception e) {
                		System.out.println("Exception From PageObjects"+"==>>"+" "+e);                
                  }        
        }
        
        return pageObjects.get(objectId);
    }
	public static String readFromTestData(String sheetName,String objectId){
	  	  
        if (!pageObjects.containsKey(objectId)){
                try {
                      try {
                            pageObjects.put(objectId, read.getDatawithKey(sheetName,1,objectId));
                            } catch (NullPointerException e) {
                           throw new Exception("No such Object Named "+objectId+" in ObjectRepositary");
                        }
                } catch (Exception e) {
                		System.out.println("Exception From PageObjects"+"==>>"+" "+e);                
                  }        
        }
        
        return pageObjects.get(objectId);
    }
    
    public static String getData(String sheetName, String key, String ColumnName) {
		
		if (!pageObjects.containsKey(key)) {
			try {
				try {
					pageObjects.put(key + ColumnName,read.getColumnDataWithKey(sheetName, key, ColumnName));
				} catch (NullPointerException e) {
					throw new Exception("No such Object Named " + key + " "	+ ColumnName + " in " + sheetName);
				}
			} catch (Exception e) {
				System.out.println("=================== Error in getDataFromExcelSheet ======================"+e);
			}
		}
		return pageObjects.get(key + ColumnName);
	}
    
    public static String getDatafromExcel(String sheetName,String key){
      	
   	   if (!pageObjects.containsKey(key)){
   		   try {
   			   try {
                   pageObjects.put(key,read.getDataByRowKey(sheetName, key));
   			   } catch (NullPointerException e) {
                       throw new Exception("No such Object Named "+ key +" in "+ sheetName);
               	}
   		   } catch (Exception e) {
   			   System.out.println("=================== Error in getDataFromExcelSheet ======================"+e);  		         }        
   	           }
   	        return pageObjects.get(key);        
          }	
   
}
