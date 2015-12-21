package atf.tv3hub.suite;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import atf.tv3hub.common.Component;
import atf.tv3hub.common.EnvironmentConfig;
import atf.tv3hub.common.ExcelUtil;
import atf.tv3hub.common.PageObjects;
import atf.tv3hub.page.ActionableEvent;
import atf.tv3hub.page.LoginPage;

public class TestActionableEventSuite extends PageObjects {

	ExcelUtil read = EnvironmentConfig.testCaseXL;
	ExcelUtil write = EnvironmentConfig.testReportXL;
	ExcelUtil object = EnvironmentConfig.objectRepositoryXL;
	Component common=new Component();
	ActionableEvent alarm =(ActionableEvent) atf.tv3hub.common.PageFactory.initElements(ActionableEvent.class);
	int columnIncrement = 1;
	public static String url = "";
	LoginPage login = (LoginPage) atf.tv3hub.common.PageFactory.initElements(LoginPage.class);

	@BeforeSuite
	public static void setUpBeforeClass() throws Exception {

	}

	//@Test(priority=1)
	public void searchAlarmFromConsole(){
		try{
			alarm.alarmCreationAPITest(1);
			write.putDataWithColumnName("Alarm","Ticket","ALARM1",Component.alarmId);
			if(common.OSDetector().equals("Linux")){
				Component.startChromeDriverLinux();
			}else{
				Component.startChromeDriverWindows();
			}
			url=readFromTestData("URL", "DEVURL");
			common.OpenRequestURL(url);
			common.windowMaximize();
			login.loginTv3Hub("AppUser");
			alarm.alarmSearchFromConsole();
			Thread.sleep(3000);
		}catch(Exception e){
			System.out.println("Search Alarm From Console" +" "+e.toString());
			write.putDataWithColumnName("Alarm", "Exception", "ALARM1", e.toString());
		}
	}

	//@Test(priority=2)
	public void toClearSearchedAlarmFromConsole(){
		alarm.clearAlarmFromConsole();
	}

	//@Test(priority=3)
	public void toSearchAlarmByQuickSearch(){
		alarm.alarmQuickSearch();
		login.logoutFromTv3Hub();
		Component.stopDriver();
	}
	//@Test(priority=4)
	public void validateAckfromConsole(){
		try{
			alarm.alarmCreationAPITest(2);
			write.putDataWithColumnName("Alarm","Ticket", "ALARM2", Component.alarmId);
			if(common.OSDetector().equals("Linux")){
				Component.startChromeDriverLinux();
			}else{
				Component.startChromeDriverWindows();
			}
			url=readFromTestData("URL", "DEVURL");
			common.OpenRequestURL(url);
			common.windowMaximize();
			login.loginTv3Hub("AppUser");
			alarm.alarmSearchFromConsole();
			alarm.acknowledgeAlarmFromConsole();
			write.putDataWithColumnName("Alarm","IncidentID" ,"ALARM2",Component.TicketId);
			login.logoutFromTv3Hub();
			Component.stopDriver();
		}catch(Exception e){
			System.out.println("Search Alarm From Console" +" "+e.toString());
			write.putDataWithColumnName("Alarm", "Exception", "ALARM2", e.toString());
		}
	}

	//@Test(priority=5)
	public void validateAppendfromConsole(){
		try{
			alarm.alarmCreationAPITest(3);
			write.putDataWithColumnName("Alarm","Ticket", "ALARM3", Component.alarmId);
			if(common.OSDetector().equals("Linux")){
				Component.startChromeDriverLinux();
			}else{
				Component.startChromeDriverWindows();
			}
			url=readFromTestData("URL", "DEVURL");
			common.OpenRequestURL(url);
			common.windowMaximize();
			login.loginTv3Hub("AppUser");
			alarm.alarmSearchFromConsole();
			alarm.appendToIncidentFromConsole();
			login.logoutFromTv3Hub();
			Component.stopDriver();
		}catch(Exception e){
			System.out.println("Search Alarm From Console" +" "+e.toString());
			write.putDataWithColumnName("Alarm", "Exception", "ALARM3", e.toString());
		}
	}
	//@Test(priority=6)
	public void createTaskfromConsole(){
		try{
			alarm.alarmCreationAPITest(4);
			write.putDataWithColumnName("Alarm","Ticket", "ALARM4", Component.alarmId);
			if(common.OSDetector().equals("Linux")){
				Component.startChromeDriverLinux();
			}else{
				Component.startChromeDriverWindows();
			}
			url=readFromTestData("URL", "DEVURL");
			common.OpenRequestURL(url);
			common.windowMaximize();
			login.loginTv3Hub("AppUser");
			alarm.alarmSearchFromConsole();
			alarm.createTaskFromConsole();
			write.putDataWithColumnName("Alarm", "TaskID", "ALARM4",Component.TicketId );
			login.logoutFromTv3Hub();
			Component.stopDriver();
		}catch(Exception e){
			System.out.println("Search Alarm From Console" +" "+e.toString());
			write.putDataWithColumnName("Alarm", "Exception", "ALARM4", e.toString());
		}
	}
	//	@Test(priority=7)
	public void verifyClearAlarmFromView(){
		try{
			alarm.alarmCreationAPITest(5);
			write.putDataWithColumnName("Alarm","Ticket", "ALARM5", Component.alarmId);
			Component.startChromeDriverWindows();
			url=readFromTestData("URL", "DEVURL");
			common.OpenRequestURL(url);
			common.windowMaximize();
			login.loginTv3Hub("AppUser");
			alarm.alarmSearchFromConsole();
			common.clickByLink(Component.alarmId);
			common.waitForElementByXpath(object("Common","Edit Ticket"));
			alarm.clearFromAlarmView();
			write.putDataWithColumnName("Alarm","Result", "ALARM5", Component.content);
			login.logoutFromTv3Hub();
			Component.stopDriver();
		}catch(Exception e){
			System.out.println("Search Alarm From Console" +" "+e.toString());
			write.putDataWithColumnName("Alarm", "Exception", "ALARM5", e.toString());
		}
	}

	//	@Test(priority=8)
	public void verifyTaskCreationFromAlarmView(){
		try{
			alarm.alarmCreationAPITest(6);
			write.putDataWithColumnName("Alarm","Ticket", "ALARM6", Component.alarmId);
			Component.startChromeDriverWindows();
			url=readFromTestData("URL", "DEVURL");
			common.OpenRequestURL(url);
			common.windowMaximize();
			login.loginTv3Hub("AppUser");
			alarm.alarmSearchFromConsole();
			common.clickByLink(Component.alarmId);
			common.waitForElementByXpath(object("Common","Edit Ticket"));
			alarm.createTaskFromAlarmView();
			write.putDataWithColumnName("Alarm","TaskID" ,"ALARM6",Component.TicketId);
			login.logoutFromTv3Hub();
			Component.stopDriver();
		}catch(Exception e){
			System.out.println("Search Alarm From Console" +" "+e.toString());
			write.putDataWithColumnName("Alarm", "Exception", "ALARM6", e.toString());
			Component.stopDriver();
		}
	}
	//@Test(priority=9)
	public void createBugFromAlarmView(){
		try{
			alarm.alarmCreationAPITest(7);
			write.putDataWithColumnName("Alarm","Ticket", "ALARM7", Component.alarmId);
			Component.startChromeDriverWindows();
			url=readFromTestData("URL", "DEVURL");
			common.OpenRequestURL(url);
			common.windowMaximize();
			login.loginTv3Hub("AppUser");
			alarm.alarmSearchFromConsole();
			common.clickByLink(Component.alarmId);
			common.waitForElementByXpath(object("Common","Edit Ticket"));
			alarm.createBugFromAlarmView();
			write.putDataWithColumnName("Alarm","BUGID" ,"ALARM7",Component.TicketId);
			login.logoutFromTv3Hub();
			Component.stopDriver();
		}catch(Exception e){
			System.out.println("Search Alarm From Console" +" "+e.toString());
			write.putDataWithColumnName("Alarm", "Exception", "ALARM7", e.toString());
			Component.stopDriver();
		}
	}
	//@Test(priority=10)
	public void createIncidentFromAlarmView(){
		try{
			alarm.alarmCreationAPITest(8);
			write.putDataWithColumnName("Alarm","Ticket", "ALARM8", Component.alarmId);
			Component.startChromeDriverWindows();
			url=readFromTestData("URL", "DEVURL");
			common.OpenRequestURL(url);
			common.windowMaximize();
			login.loginTv3Hub("AppUser");
			alarm.alarmSearchFromConsole();
			common.clickByLink(Component.alarmId);
			common.waitForElementByXpath(object("Common","Edit Ticket"));
			alarm.createIncidentFromAlarmView();
			write.putDataWithColumnName("Alarm","IncidentID" ,"ALARM8",Component.TicketId);
			login.logoutFromTv3Hub();
			Component.stopDriver();
		}catch(Exception e){
			System.out.println("Search Alarm From Console" +" "+e.toString());
			write.putDataWithColumnName("Alarm", "Exception", "ALARM8", e.toString());
			Component.stopDriver();
		}
	}
	@Test(priority=11)
	public void appendFromAlarmView(){
		try{
			alarm.alarmCreationAPITest(9);
			write.putDataWithColumnName("Alarm","Ticket", "ALARM9", Component.alarmId);
			Component.startChromeDriverWindows();
			url=readFromTestData("URL", "DEVURL");
			common.OpenRequestURL(url);
			common.windowMaximize();
			login.loginTv3Hub("AppUser");
			alarm.alarmSearchFromConsole();
			common.clickByLink(Component.alarmId);
			common.waitForElementByXpath(object("Common","Edit Ticket"));
			alarm.appendFromAlarmView();
			login.logoutFromTv3Hub();
		}catch(Exception e){
			System.out.println("Search Alarm From Console" +" "+e.toString());
			write.putDataWithColumnName("Alarm", "Exception", "ALARM9", e.toString());

		}
	}
	//@Test(priority=1)
	public void checkDBConnection(){
		common.getDBConnection();
	}
	@AfterSuite
	public static void tearDownAfterClass() throws Exception {
		Component.toSavetheExcelFile();
		//Component.stopDriver();
		//Thread.sleep(8000);
		//Component.sendNotification();
	}
}
