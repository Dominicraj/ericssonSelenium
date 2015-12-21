package atf.tv3hub.suite;

import org.testng.annotations.Test;
//import org.junit.Test;
import atf.tv3hub.common.Component;
import atf.tv3hub.common.EnvironmentConfig;
import atf.tv3hub.common.ExcelUtil;
import atf.tv3hub.common.PageObjects;
import atf.tv3hub.page.LoginPage;

public class LoginSuite extends PageObjects {
	ExcelUtil read = EnvironmentConfig.testCaseXL;
	ExcelUtil write = EnvironmentConfig.testReportXL;
	ExcelUtil object = EnvironmentConfig.objectRepositoryXL;
	Component common=new Component();
	public static String url = "";
	static LoginPage login = (LoginPage) atf.tv3hub.common.PageFactory.initElements(LoginPage.class);
	int columnIncrement = 1;

	@Test
	public void toLoginAppwithInValidAccount(){
		url = readFromTestData("URL","DEVURL");
		common.windowMaximize();
		common.OpenRequestURL(url);
		login.loginTv3Hub("InvalidUser");
		write.putDatawithKey("LoginTest",2,"InValidLogin",Component.text);
	}
	@Test
	public void toLoginAppwithValidAccount(){
		url = readFromTestData("URL", "DEVURL");
		common.windowMaximize();
		common.OpenRequestURL(url);
		login.loginTv3Hub("AppUser");	
		write.putDatawithKey("LoginTest",2,"ValidLogin",Component.content+" "+"Logged in SuccessFully");
	}
	@Test
	public void toLogoutFromApp(){		
		login.logoutFromTv3Hub();
	}
}
