package atf.tv3hub.page;

public interface ActionableEvent {
	
	public void alarmCreationAPITest(int j);
	public void alarmQuickSearch();

	//From Alarm Console
	public void clearAlarmFromConsole();
	public void alarmSearchFromConsole();
	public void acknowledgeAlarmFromConsole();
	public void appendToIncidentFromConsole();
	public void createTaskFromConsole();
	
	//From Alarm View
	public void clearFromAlarmView();
	public void createTaskFromAlarmView();
	public void appendFromAlarmView();
	public void createBugFromAlarmView();
	public void createIncidentFromAlarmView();
	//public void sendNotification();
}
