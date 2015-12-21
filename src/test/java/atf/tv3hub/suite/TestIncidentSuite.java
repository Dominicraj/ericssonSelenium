package atf.tv3hub.suite;

import org.testng.annotations.Test;
//import org.junit.Test;
import atf.tv3hub.common.Component;
import atf.tv3hub.common.EnvironmentConfig;
import atf.tv3hub.common.ExcelUtil;
import atf.tv3hub.common.PageObjects;
import atf.tv3hub.page.IncidentPage;
import atf.tv3hub.page.LoginPage;

public class TestIncidentSuite extends PageObjects {
	ExcelUtil read = EnvironmentConfig.testCaseXL;
	ExcelUtil write = EnvironmentConfig.testReportXL;
	ExcelUtil object = EnvironmentConfig.objectRepositoryXL;
	Component common=new Component();
	public static String url = "";
	LoginPage login = (LoginPage) atf.tv3hub.common.PageFactory.initElements(LoginPage.class);
	IncidentPage incident = (IncidentPage) atf.tv3hub.common.PageFactory.initElements(IncidentPage.class);

	//@Test(priority=1)
	public void toCustomerIncident()
	{		
		url=readFromTestData("URL", "DEVURL");
		Component.startChromeDriverWindows();
		common.OpenRequestURL(url);
		common.windowMaximize();
		login.loginTv3Hub("AppAdmin");
		incident.toCustomerIncident(); 
	}

	/*@Test
	 
	public void toCreateBugIncident()
	{

	}*/
	/*@Test
	public void toValidateStickyNoteField(){

	}
	@Test
	public void toValidateStatusBacklog(){

	}
	@Test
	public void toValidateStatusEscalated(){

	}
	@Test
	public void toValidateStatusClosed(){

	}
	@Test
	public void toValidateAddMessage(){

	}
	@Test
	public void toValidateAddAttachments(){

	}
	@Test
	public void toValidateAllbuttons(){

	}
	@Test
	public void toValidateAddRelatedTickets(){

	}
	@Test
	public void toValidateUpdateTFSId(){

	}
	@Test
	public void toValidateOperatorVisibleMessages(){

	}
	@Test
	public void toValidateRefresh(){

	}
	@Test
	public void toUpdateIncident(){

	}
	@Test
	public void toValidateNewTagUpdate(){

	}
	@Test
	public void toValidateOldTagUpdate(){

	}
	@Test
	public void toValidateSharedTagUpdate(){

	}*/
}
