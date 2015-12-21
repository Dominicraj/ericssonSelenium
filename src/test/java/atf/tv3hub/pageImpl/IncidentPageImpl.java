package atf.tv3hub.pageImpl;

import atf.tv3hub.common.Component;
import atf.tv3hub.common.EnvironmentConfig;
import atf.tv3hub.common.ExcelUtil;
import atf.tv3hub.common.PageObjects;
import atf.tv3hub.page.IncidentPage;

public class IncidentPageImpl extends PageObjects implements IncidentPage {
	ExcelUtil read = EnvironmentConfig.testCaseXL;
	ExcelUtil write = EnvironmentConfig.testReportXL;
	ExcelUtil object = EnvironmentConfig.objectRepositoryXL;
	Component common=new Component();
	//public static String url = "";

	public void toCustomerIncident(){
      	/*common.clickById(object("Common","Click Service Directory"));
		common.waitForElementByLink(object("Incident","Click Incident"));*/
		common.clickByLink(object("Incident","Click Incident"));
		common.waitForElementByName(object("Incident","CustomerOne"));
		common.sendKeysByName(object("Incident","CustomerOne"),"att");   //"customer1", "att");
		common.sleep(1000);
		common.keyboardActions("customer1");
		common.sleep(1000);
		common.selectValueById(object("Incident", "ContactList"), "1122334455");
		common.sendKeysByName(object("Incident", "customerContactIncident"), "ana");
		common.sleep(1000);
		common.keyboardActions("customerContactIncident");
		common.sendKeysByName(object("Incident","customerContactsInfo"),"4562132132");
		common.selectValueById(object("Incident","MediaroomSeverity"), "MEDIUM");
		common.sleep(1000);
		common.sendKeysByName(object("Common", "Title"),read.getColumnDataWithKey("Incident", "CRE_TT_1", "title"));
		common.sendKeystoRTE(object("Common","Description IFrame"), object("Common","Description Body"), read.getColumnDataWithKey("Incident", "CRE_TT_1", "description"));
		common.defaultcontent();
		common.clickById(object("Common","Create Ticket"));
		common.waitForElementByXpath(object("Common","Edit Ticket"));
		common.toGetTicketId(object("Common","GetTitle"));
		Component.stopDriver();
	}
}
