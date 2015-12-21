package atf.tv3hub.common;

import atf.tv3hub.run.RunConfig;

public class EnvironmentConfig {
	public static final ExcelUtil testCaseXL= new ExcelUtil(System.getProperty("user.dir")+"/TestProject/"+RunConfig.TEST_PROJECT+"/TestSuite/"+RunConfig.TEST_SUITE_NAME+".xls","read");
	public static final ExcelUtil objectRepositoryXL= new ExcelUtil(System.getProperty("user.dir")+"/TestProject/"+RunConfig.TEST_PROJECT+"/TestAsset/ObjectRepository.xls","read");
	public static final ExcelUtil Repository= new ExcelUtil(System.getProperty("user.dir")+"/TestProject/"+RunConfig.TEST_PROJECT+"/TestSuite/ObjectRepository.xls","read");
	public static final ExcelUtil testReportXL= new ExcelUtil(System.getProperty("user.dir")+"/TestProject/"+RunConfig.TEST_PROJECT+"/TestAsset/TestResultTemplate.xls",
													   System.getProperty("user.dir")+"/TestReport/"+RunConfig.TEST_PROJECT+"/TestResults/"+"TestResults.xls","copy");
	public static final String filepath=System.getProperty("user.dir")+"/TestReport/"+RunConfig.TEST_PROJECT+"/Screenshots/";
	public static final String chormeDriverWindows=System.getProperty("user.dir")+"/TestProject/"+RunConfig.TEST_PROJECT+"/TestAsset/"+"/Drivers/"+"/Windows/chromedriver.exe";
	public static final String chormeDriverLinux=System.getProperty("user.dir")+"/TestProject/"+RunConfig.TEST_PROJECT+"/TestAsset/"+"/Drivers/"+"/Linux/chromedriver";
	public static final String ieDriver=System.getProperty("user.dir")+"/TestProject/"+RunConfig.TEST_PROJECT+"/TestAsset/"+"/Drivers/IEDriverServer.exe";
}
