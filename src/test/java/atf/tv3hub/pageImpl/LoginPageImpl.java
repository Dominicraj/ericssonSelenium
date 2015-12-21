package atf.tv3hub.pageImpl;

import java.util.List;
import atf.tv3hub.common.Component;
import atf.tv3hub.common.EnvironmentConfig;
import atf.tv3hub.common.ExcelUtil;
import atf.tv3hub.common.PageObjects;
import atf.tv3hub.page.LoginPage;

public class LoginPageImpl extends PageObjects implements LoginPage {
	ExcelUtil read = EnvironmentConfig.testCaseXL;
	ExcelUtil write = EnvironmentConfig.testReportXL;
	ExcelUtil object = EnvironmentConfig.objectRepositoryXL;
	ExcelUtil repository=EnvironmentConfig.Repository;
	List<String> paramNames = null;
	List<String> paramValues = null;
	public static String url="";
	public static String Preferance=new String();
	Component common=new Component();
	
	public void loginTv3Hub(String userType) {
		common.waitForElementByName(object("Common","Login"));
		try {
			paramNames = repository.getDataByRowKeyFromExcel("Repository","Tv3HUBLogin");
			paramValues = read.getDataByRowKeyFromExcel("UserCredentials",userType);
			for (int i=0;i<paramValues.size()-1;i++) {
				common.sendKeysById(paramNames.get(i),paramValues.get(i));
			}
			common.clickById(object("Common","Login"));
			Component.time();
		}catch(Exception e){
			System.out.println("Exception in Username and Password Enter into Tv3HUB"+"  "+e);
		}
		try{			
			if(userType.trim().equals("InvalidUser")){
				common.waitForElementById(object("Common","LoginError"));
	    		common.getValueById(object("Common","LoginError"));
			}
			else {
				common.waitForElementByLink(object("Incident","Click Incident"));
				common.getValueByXpath(object("Common","Click Username"));
	    		Component.time();
				}
		}catch(Exception e){
			System.out.println("Exception in Login into TV3Hub"+"==>"+" "+e.toString());
			write.putDatawithKey("LoginTest",3,"InValidLogin",e.toString());
			common.toTakeScreenShot();
		}
		common.toTakeScreenShot();
	}

	public void logoutFromTv3Hub() {
		common.clickByXpath(object("Common","Click Username"));
		common.clickByLink(object("Common","Logout"));
		common.waitForElementById(object("Common","Login"));
		System.out.println("Successfully Logout From Tv3HUB");
	}
}