package atf.tv3hub.pageImpl;


import java.io.IOException;
import java.util.Date;
import java.util.List;
import jxl.read.biff.BiffException;
import atf.tv3hub.common.Component;
import atf.tv3hub.common.EnvironmentConfig;
import atf.tv3hub.common.ExcelUtil;
import atf.tv3hub.common.PageObjects;
import atf.tv3hub.page.ActionableEvent;
import atf.tv3hub.page.LoginPage;

public class ActionableEventImpl extends PageObjects implements ActionableEvent {
	ExcelUtil read = EnvironmentConfig.testCaseXL;
	ExcelUtil object = EnvironmentConfig.objectRepositoryXL;
	ExcelUtil repository=EnvironmentConfig.Repository;
	public static String alarmId = new String();
	String url = "";
	Date date = null;
	List<String> paramNames = null;
	Component common=new Component();
	static LoginPage login = (LoginPage) atf.tv3hub.common.PageFactory.initElements(LoginPage.class);

	public void alarmCreationAPITest(int j) {
		Component.startHtmlUnitDriver();
		date = new Date();
		url=readFromTestData("URL", "DEVAPIURL");
		for (int i = j; i <= j; i++) {
			try {
				paramNames = repository.getDataByRowKeyFromExcel("Repository","Alarm");

				for (String paramName : paramNames) {
					String paramValue = read.getDataByRowAndColumnKey("Alarm", "ALARM"+ "_" + j + "", paramName);
					url = url + "&" + paramName + "=" + paramValue;
				}
				common.OpenRequestURL(url);
				common.getAEIdfromXML();
				common.sleep(3000);
				Component.stopDriver();
			}catch (BiffException e) {
				System.out.println("Biff Exception.PageImpl"+"=>>>"+e);
			} catch (IOException e) {
				System.out.println("IOException.PageImpl"+"=>>>"+e);
			}catch(Exception e){
				System.out.println("Alarm Creation Exception.PageImpl"+" "+e);
			}}
	}
	public void alarmQuickSearch(){
		common.sendKeysByXpath(object("Common","Type Ticket Id"),Component.alarmId);
		common.sleep(4000);
		common.clickByXpath(object("Common","Search Ticket"));
		common.waitForElementByXpath(object("Common","Edit Ticket"));
		common.getValueByXpath(object("Alarm","GetStatus"));
	}
	//Following Test cases from Alarm Console
	public void alarmSearchFromConsole(){
		common.clickByLink(object("Common","Click ServiceCenter"));
		common.waitForElementByXpath(object("Common","Type Ticket Id"));
		common.clickById(object("Common","Alarm Console"));
		common.waitForElementById(object("Alarm","ConsoleVisible"));
		common.clickByXpath(object("Common","Settings"));
		common.clickByLink(object("Common","Filter"));
		common.waitForElementByXpath(object("Common","SearchTicketId"));
		
		common.sendKeysByXpath(object("Common","SearchTicketId"),Component.alarmId);//Component.alarmId
		
		common.clickById(object("Common","ClickSearch"));
		common.waitForElementByXpath(object("Alarm","ClickCheckbox"));
	}
	public void acknowledgeAlarmFromConsole(){
		common.clickByXpath(object("Alarm","Acknowledge"));
		common.waitForElementByName(object("Incident","FirstEvent"));
		common.clickById(object("Common","Create Ticket"));
		common.waitForElementByXpath(object("Common","Edit Ticket"));
		common.toGetTicketId(object("Common","GetTitle"));
	}
	public void appendToIncidentFromConsole(){
		common.clickByXpath(object("Alarm","ClickCheckbox"));
		common.sendKeysById(object("Alarm","TypeIncidentId"),Component.alarmId);  
		common.sleep(3000);
		common.clickByXpath(object("Alarm","SelectIncidentId"));
		common.sleep(3000);
		System.out.println("=====================================Incident Selected=================================");
		common.clickByXpath(object("Alarm","Append"));
		common.sleep(3000);
		common.getValueByXpath(object("Alarm","GetMessage"));
	}
	public void createTaskFromConsole() {
		common.clickByXpath(object("Alarm","ClickCheckbox"));
		common.clickByXpath(object("Alarm","CreateTaskFromConsole"));
		common.waitForElementByName(object("Alarm","TTT ID"));
		common.clickById(object("Common","Create Ticket"));
		common.waitForElementByXpath(object("Common","Edit Ticket"));
		common.toGetTicketId(object("Common","GetTitle"));
	}
	public void clearAlarmFromConsole(){
		common.waitForElementByXpath(object("Alarm","ClearSymbol"));
		common.clickByXpath(object("Alarm","ClearSymbol"));
		common.sendKeysById(object("Alarm","Reason"),"Temporary Alarm");
		common.waitForElementByXpath(object("Alarm","ReasonSubmit"));
		common.clickByXpath(object("Alarm","ReasonSubmit"));
		common.sleep(8000);
		common.getValueByXpath(object("Alarm","GetClearStatus"));
	}
	//Following Test cases from Alarm Overview
	public void clearFromAlarmView(){
		common.clickByXpath(object("Alarm","ClearFromView"));
		common.waitForElementById(object("Alarm","ClearReasonFromView"));
		common.sendKeysById(object("Alarm","ClearReasonFromView"),"Temporary Alarm");
		common.clickByXpath(object("Alarm","ReasonSubmit"));
		common.sleep(5000);
		common.getValueByXpath(object("Alarm","GetMessage"));
	}
	public void createTaskFromAlarmView(){
		common.clickByXpath(object("Alarm","CreateTaskFromAlarm"));
		common.waitForElementByName(object("Alarm","TTT ID"));	
		common.clickById(object("Common","Create Ticket"));
		common.waitForElementByXpath(object("Common","Edit Ticket"));
		common.toGetTicketId(object("Common","GetTitle"));
	}
	public void createBugFromAlarmView(){
		common.clickByXpath(object("Alarm","CreateBUGFromAlarm"));
		common.waitForElementByName(object("Alarm","BugRelease"));
		common.selectValueByName(object("Bug","AreaPath"), read.getColumnDataWithKey("BUGTestData", "BUG_1", "AreaPath"));
		common.selectValueByName(object("Bug","Issue"), read.getColumnDataWithKey("BUGTestData", "BUG_1", "Issue"));
		common.selectValueByName(object("Bug","AssignedTo"), read.getColumnDataWithKey("BUGTestData", "BUG_1", "AssignedTo"));
		common.selectValueByName(object("Bug","Priority"), read.getColumnDataWithKey("BUGTestData", "BUG_1", "Priority"));
		common.selectValueByName(object("Bug","Severity"), read.getColumnDataWithKey("BUGTestData", "BUG_1", "Severity"));
		common.selectValueByName(object("Bug","FoundInEnvironment"), read.getColumnDataWithKey("BUGTestData", "BUG_1", "Environment"));
		common.sendKeysByName(object("Bug","StackRankValue"), read.getColumnDataWithKey("BUGTestData", "BUG_1", "StackRank"));
		common.sendKeysByName(object("Bug","OriginalEstimate"), read.getColumnDataWithKey("BUGTestData", "BUG_1", "OriginalEstimate"));
		common.sendKeysByName(object("Bug","CompletedWork"), read.getColumnDataWithKey("BUGTestData", "BUG_1", "CompletedWork"));
		common.sendKeysByName(object("Bug","RemainingWork"), read.getColumnDataWithKey("BUGTestData", "BUG_1", "RemainingWork"));
		common.selectValueByName(object("Bug","ReleaseNoteRequirement"), read.getColumnDataWithKey("BUGTestData", "BUG_1", "ReleaseNote")); 
		common.selectValueByName(object("Bug","ExposeToOperator"), read.getColumnDataWithKey("BUGTestData", "BUG_1", "Expose"));
		common.clickById(object("Common","Create Ticket"));
		common.waitForElementByXpath(object("Alarm","CreateIncidentFromAlarm"));
		common.toGetTicketId(object("Common","GetTitle"));
	}
	public void createIncidentFromAlarmView(){
		try {
			common.clickByXpath(object("Alarm","CreateIncidentFromAlarm"));
			common.waitForElementByName(object("Incident","FirstEvent"));
			common.clickById(object("Common","Create Ticket"));
			common.waitForElementByXpath(object("Common","Edit Ticket"));
			common.toGetTicketId(object("Common","GetTitle"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void appendFromAlarmView(){
		
		try {
			common.waitForElementByXpath(object("Alarm","Append"));
			common.clickByXpath(object("Alarm","Append"));
			common.waitForElementByXpath(object("Alarm","SelectIncidentTciket"));
			common.keyboardActionsid(object("Alarm", "SelectIncidentTciket"));
			common.clickByXpath(object("Alarm","Append"));
			common.sleep(2000);
			common.alertAccept();
			common.waitForElementByXpath(object("Common","Edit Ticket"));
			
			
	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	} 
}
