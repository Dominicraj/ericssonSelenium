package atf.tv3hub.common;

import atf.tv3hub.page.ActionableEvent;
import atf.tv3hub.page.IncidentPage;
import atf.tv3hub.page.LoginPage;
import atf.tv3hub.pageImpl.ActionableEventImpl;
import atf.tv3hub.pageImpl.IncidentPageImpl;
import atf.tv3hub.pageImpl.LoginPageImpl;

public class PageFactory {

public static Object initElements(Object page) {
		
	if (page.equals(LoginPage.class)){
		
		return new LoginPageImpl();
	}
	if (page.equals(IncidentPage.class)){
		
		return new IncidentPageImpl();
	}
	if (page.equals(ActionableEvent.class)){
		
		return new ActionableEventImpl();
	}
		return null;
	 }
}
