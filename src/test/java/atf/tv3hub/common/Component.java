package atf.tv3hub.common;

import java.io.File;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import jxl.write.WriteException;
import org.codehaus.plexus.util.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.ResultSetMetaData;
import com.mysql.jdbc.Statement;

public class Component {
	static WebDriver driver = null;
	static WebElement element = null;
	public static String Time = new String();
	public static String TimeStamp = new String();
	public static String endTime = new String();
	public static String TicketId = new String();
	public static String UserPer = new String();
	public static String text = new String();
	public static String content = new String();
	public static String alarmId = new String();
	public static String osType = new String();
	static String os = System.getProperty("os.name").toLowerCase();
	Date date = null;
	static ExcelUtil write = EnvironmentConfig.testReportXL;

	public static  void startFirefoxDriver() {
		/*FirefoxProfile profile = new FirefoxProfile();
		driver = new FirefoxDriver(new FirefoxBinary(new File(EnvironmentConfig.firefoxDriver)), profile);*/
		driver = new FirefoxDriver();
	}

	public static void startHtmlUnitDriver() {
		driver = new HtmlUnitDriver();
		((HtmlUnitDriver) driver).setJavascriptEnabled(true);
	}

	public static  void startChromeDriverWindows() {
		System.setProperty("webdriver.chrome.driver",EnvironmentConfig.chormeDriverWindows);
		driver = new ChromeDriver();
	}
	public static  void startChromeDriverLinux() {
		System.setProperty("webdriver.chrome.driver",EnvironmentConfig.chormeDriverLinux);
		driver = new ChromeDriver();
	}

	public static void startIEServer(){
		DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
		capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);	
		System.setProperty("webdriver.ie.driver",EnvironmentConfig.ieDriver);
		driver = new InternetExplorerDriver();
	}

	public static  File getFilepath(){
		File imagePath=new File(EnvironmentConfig.filepath+Component.time()+".png");
		return imagePath;
	}

	public static void stopDriver() {
		driver.close();
		driver.quit();
	}

	public void windowMaximize() {
		driver.manage().window().maximize();
		System.out.println("===== Running Command Window Maximize ======");
	}
	public String OSDetector(){
		if (os.contains("win")) {
			osType= "Windows";
		} else if (os.contains("nux") || os.contains("nix")) {
			osType= "Linux";
		}else if (os.contains("mac")) {
			osType= "Mac";
		}else {
			osType= "Other";
		}
		System.out.println("Machine OS:"+osType);
		return osType;
	}

	public void OpenRequestURL(String url) {
		driver.navigate().to(url);
		System.out.println("The Requested URL is "+" "+url);
	}
	public void defaultcontent() {
		try {
			driver.switchTo().defaultContent();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void sleep(long time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Click by Xpath, CSS, Link, Id, Name
	 * 
	 */
	public void clickByXpath(String object) {
		driver.findElement(By.xpath(object)).click();
		System.out.println("Running ClickByXpath" + " " + object);
	}

	public void clickById(String object) {
		driver.findElement(By.id(object)).click();
		System.out.println("Running ClickById" + " " + object);
	}

	public void clickByCSS(String object) {
		driver.findElement(By.cssSelector(object)).click();
		System.out.println("Running ClickByCSS" + " " + object);
	}

	public void clickByLink(String object) {
		driver.findElement(By.linkText(object)).click();
		System.out.println("Running ClickByLink" + " "+ object);
	}

	public void clickByName(String object) {
		driver.findElement(By.name(object)).click();
		System.out.println("Running ClickByName" + " " + "=>" + object);
	}
	/**
	 * 
	 * Send by Xpath, CSS, Id, Name
	 */
	public void sendKeysByXpath(String locator, String text) {
		driver.findElement(By.xpath(locator)).sendKeys(text);
		System.out.println("Running SendKeysByXpath" + " " + "Locator is"+" "+locator + " " + "And text"+"value is"	+ " " + text);
	}

	public void sendKeysById(String locator, String text) {
		driver.findElement(By.id(locator)).sendKeys(text);
		System.out.println("Running SendKeysById" + " " + "Locator is"+" "+locator + " " + "And text"+"value is"	+ " " + text);
	}

	public void sendKeysByNameElement(String locator, String text) {
		element.findElement(By.name(locator)).sendKeys(text);
		System.out.println("Running SendKeysById" + " " + "Locator is"+" "+locator + " " + "And text"+"value is"	+ " " + text);
	}
	public void sendKeystoRTE(String frame,String locator,String text) {
		System.out.println(frame);
		System.out.println(locator);
		System.out.println(text);
		WebElement ckEditor=driver.findElement(By.cssSelector(frame));
		driver.switchTo().frame(ckEditor);
		driver.findElement(By.cssSelector(locator)).sendKeys(text);
		System.out.println("Running SendKeysByCSS" + " " + "Locator is"+" "+locator + " " + "And text"+"value is"	+ " " + text);
	}

	public void keyboardTab(String locator) {

		driver.findElement(By.name(locator)).sendKeys(Keys.TAB);
	}
	public void sendKeysByName(String locator, String text) {
		driver.findElement(By.name(locator)).sendKeys(text);
		System.out.println("Running SendKeysByName" + " " + "Locator is"+" "+locator + " " + "And text"+"value is"	+ " " + text);
	}

	public void keyboardActions(String object){
		driver.findElement(By.name(object)).sendKeys(Keys.ARROW_DOWN);
		driver.findElement(By.name(object)).sendKeys(Keys.ENTER);
		System.out.println("keyboardActions" + " "+ object);

	}
	
	public void keyboardActionsid(String object){
		driver.findElement(By.id(object)).sendKeys(Keys.ARROW_DOWN);
		driver.findElement(By.id(object)).sendKeys(Keys.ENTER);
		System.out.println("keyboardActionsid" + " "+ object);

	}
	/**
	 * 
	 * To get Text from Object
	 */
	public String getText(String object) {
		System.out.println("obj " + object);
		Alert alert = driver.switchTo().alert();
		System.out.println("Running Get Text" + " " + object);
		return alert.getText();
	}
	
	public void alertAccept() {
		
		Alert alert = driver.switchTo().alert();
		alert.accept();
		
		
		
	}

	public String getValueByXpath(String object) {
		content = driver.findElement(By.xpath(object)).getText();
		System.out.println("Running Get Value From " + " "+object + "and the text is "+ " " + content);
		return content;
	}

	public String getValueById(String object) {
		text = driver.findElement(By.id(object)).getText();
		System.out.println("Running Get Value From " + " "+object + "and the text is "+ " " + text);
		return text;
	}
	public String getValueByAttribute(String object) {
		text = driver.findElement(By.name(object)).getAttribute("Value");
		System.out.println("Running Get Value From " + " "+object + "and the text is "+ " " + text);
		return text;
	}
	public String getValueByName(String object) {
		text = driver.findElement(By.name(object)).getText();
		System.out.println("Running Get Value From " + " "+object + "and the text is "+ " " + text);
		return text;
	}

	public void getAEIdfromXML() {
		String getTicketIdWithTitle = driver.getPageSource();
		int startIndex = getTicketIdWithTitle.indexOf("AE-");
		int endIndex = getTicketIdWithTitle.indexOf("</");
		alarmId = getTicketIdWithTitle.substring(startIndex, endIndex).trim();
		System.out.println("Actionable Event id is"+" "+alarmId);
	}
	/*---------------------------------------------------------------------------------------------------------------------------------------*/

	public void clearElementByXpath(String object) {
		driver.findElement(By.xpath(object)).clear();
		System.out.println("Running Clear Element By Xpath" + "  " + object);
	}

	public void clearElementById(String object) {
		driver.findElement(By.id(object)).clear();
		System.out.println("Running Clear Element By Id" + "  " + object);
	}

	public void clearElementByName(String object) {
		driver.findElement(By.name(object)).clear();
		System.out.println("Running Clear Element By Name" + "  " + object);
	}

	public void clearElementByCSS(String object) {
		driver.findElement(By.cssSelector(object)).clear();
		System.out.println("Running Clear Element By CSS Selector" + "  " + object);
	}

	public boolean isVisible(String object) {
		return driver.findElement(By.xpath(object)) != null;
	}

	public void isElementVisible(String object) {

		driver.findElement(By.xpath(object)).isEnabled();
	}

	public void isElementDisplayed(String object) {
		System.out.println(object);
		driver.findElement(By.xpath(object)).isDisplayed();
		System.out.println(object+ "Displayed Successfully");
	}

	public void toTakeScreenShot() {
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(scrFile,Component.getFilepath()); 
		} catch (IOException e) {
			System.out.println("Exception in taking Screenshot" + "==>" + " "+ e);
		}
	}
	/*---------------------------------------------------------------------------------------------------------------------------------------*/
	/**
	 * 
	 * Wait For Element by Id, Xpath, CSS, Name, Link
	 */
	public void waitForElementByXpath(String object) {
		WebDriverWait wait = new WebDriverWait(driver, 40);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(object)));
		System.out.println("Wait For Element By Xpath" + " " + object);
	}

	public void waitForElementByCSS(String object) {
		WebDriverWait wait = new WebDriverWait(driver, 40);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(object)));
		System.out.println("Wait For Element By CSS" + " " + object);
	}

	public void waitForElementByLink(String object) {
		WebDriverWait wait = new WebDriverWait(driver, 40);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(object)));
		System.out.println("Wait For Element By Link" + " " + object);
	}

	public void waitForElementByName(String object) {
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(object)));
		System.out.println("Wait For Element By Name" + " " + object);
	}

	public void waitForElementById(String object) {
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(object)));
		System.out.println("Wait For Element By Id" + " " + object);
	}

	public void waitForElementClickable(String object) {
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(object)));
		System.out.println("Able to click the "+" "+object);
	}

	public void waitForPageload() {
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		System.out.println("Loading Page Please Wait");
	}

	public void waitForText(String text) {
		driver.getPageSource().contains(text);
	}

	public void waitForData(String data) {
		driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//option[@value='"+data+"']"));
		System.out.println("Waiting for Data"+ " "+data);
	}

	/*public void toTakeTicketId() {

		String getTicketIdWithTitle = driver.getTitle();
		int startIndex = getTicketIdWithTitle.indexOf("[");
		int endIndex = getTicketIdWithTitle.indexOf("]");
		TicketId = getTicketIdWithTitle.substring(startIndex+1, endIndex);
		System.out.println(TicketId);
	}*/

	public void getDBConnection() {
		String url1 ="jdbc:mysql://tv3hubdbdev.cloudapp.net:3306/RDPV_ERICSSON";
		Connection conn=null;
		String APPticketId=null;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			conn = (Connection) DriverManager.getConnection(url1, "hubdev", "$e^hU6");
			System.out.println("DB Connected");
			Statement stmt = (Statement) conn.createStatement();
			//   String queryString="SELECT * FROM RT_DATA_DEMO_KIT";

			String queryString=" SELECT * FROM RT_DATA_ONCALL_CALENDARS WHERE OCCSTATUS='Active' ";

			ResultSet result = stmt.executeQuery(queryString);
			java.sql.ResultSetMetaData meta=result.getMetaData();
			int columnsNumber= meta.getColumnCount();
			for( int i=1;i<=columnsNumber;i++){
				System.out.println(meta.getColumnName(i));
			}

			while(result.next()){
				for(int i=1;i<=columnsNumber;i++){
					System.out.println(result.getString(i)+" ");
				}
			}
		}catch(Exception e){
			System.out.println("Expection"+ " "+ e);
		}finally{
			try {
				conn.close();
				System.out.println("Connection Closed");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void toGetTicketId(String title) {

		String getTicketIdWithTitle = driver.findElement(By.id(title)).getText();
		int startIndex = getTicketIdWithTitle.indexOf("[");
		int endIndex = getTicketIdWithTitle.indexOf("]");
		TicketId = getTicketIdWithTitle.substring(startIndex+1, endIndex);
		System.out.println(TicketId);
	}

	public void selectCheckbox(String object) {
		if (!driver.findElement(By.id(object)).isSelected()) {
			driver.findElement(By.id(object)).click();
		}
	}

	public void multiSelect(String object, String name) {
		driver.findElement(By.id(object)).sendKeys(name);
		driver.findElement(By.xpath("//input[@id='" + object + "' and @name='add']")).click();
		System.out.println("MultiselectValue" + " " + object + " " + "value is"	+ " " + name);
	}

	public void multiSelectAll(String object, String name) {
		driver.findElement(By.id(object)).sendKeys(name);
		driver.findElement(By.xpath("//input[@id='" + object + "' and @name='addAll']")).click();
		System.out.println("MultiselectValue" + " " + object + " " + "value is"	+ " " + name);
	}

	public static  String time() {
		DateFormat dateFormat = null;
		dateFormat = new SimpleDateFormat("MM_dd_yy_HH_mm_s");
		dateFormat.setTimeZone(TimeZone.getTimeZone("IST"));
		TimeStamp = dateFormat.format(new Date());
		System.out.println(TimeStamp);
		return TimeStamp;
	}
	public void selectValueById(String object,String text)
	{
		Select select = new Select(driver.findElement(By.id(object)));
		select.selectByValue(text)	;
	}
	public void selectValueByName(String object,String text)
	{
		Select select = new Select(driver.findElement(By.name(object)));
		select.selectByValue(text)	;
		System.out.println("Select"+object+" "+"and value as"+text);
	}
	public String toGetTimeAndDate(String value) {
		UserPer = driver.findElement(By.xpath("//span[@id='timezone_span']")).getText();
		DateFormat dateFormat = null;
		System.out.println("Date Format.CommonUtil" + "=>>>" + value);
		System.out.println("Logged in User Preference.CommonUtil" + "=>>>"+ UserPer);
		if (value.trim().equals("HourFormat")) {
			dateFormat = new SimpleDateFormat("MM/dd/yy HH:mm");
		}
		if (value.trim().equals("YearFormat")) {
			dateFormat = new SimpleDateFormat("MM/dd/yy");
		}
		if (UserPer.equals("GMT")) {
			dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
		}
		if (UserPer.equals("PST")) {
			dateFormat.setTimeZone(TimeZone.getTimeZone("PST"));
		}
		if (UserPer.equals("IST")) {
			dateFormat.setTimeZone(TimeZone.getTimeZone("IST"));
		}
		Time = dateFormat.format(Calendar.getInstance().getTime());
		System.out.println("Time is.CommonUtil" + "=>>>" + Time);
		return Time;
	}

	public String toGetEndTimeAndDate(String value) {

		UserPer = driver.findElement(By.xpath("//span[@id='timezone_span']")).getText();
		DateFormat dateFormat = null;

		if (value.trim().equals("HourFormat")) {
			dateFormat = new SimpleDateFormat("MM/dd/yy HH:mm");
		}
		if (value.trim().equals("YearFormat")) {
			dateFormat = new SimpleDateFormat("MM/dd/yy");
		}
		if (UserPer.equals("GMT")) {
			dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
		}
		if (UserPer.equals("PST")) {
			dateFormat.setTimeZone(TimeZone.getTimeZone("PST"));
		}
		if (UserPer.equals("IST")) {
			dateFormat.setTimeZone(TimeZone.getTimeZone("IST"));
		}
		Calendar cal = Calendar.getInstance();
		if (value.trim().equals("YearFormat")) {
			cal.add(Calendar.DATE, 20);
		}
		if (value.trim().equals("HourFormat")) {
			cal.add(Calendar.HOUR, 2);
		}
		endTime = dateFormat.format(cal.getTime());
		return endTime;
	}

	public void toSelectWindow() {

		driver.manage().window().getPosition();
	}

	public void toGetOverallTime() {
		long MS = (new Date().getTime() - date.getTime());
		long k = MS / 60000;
		long l = MS / 1000;
		System.out.println("The Total Time Taken to Complete Task" + k + "Minutes" + "or" + l + "Second(s)");
	}
	public static void toSavetheExcelFile(){
		try {
			write.saveExcel();
		} catch (WriteException e) {
			System.out.println("Exception in Save the Excel File"+"==="+" "+e);
		}
	}
	public static void sendNotification(){

		final String username = "support@radaptive.com";
		final String password = "R1d1ptive";
		String subject = "Ericsson-TestNG report", toAddress = "denny.ideas2it@gmail.com", ccAddress = "narayanan.ideas2it@gmail.com", dateString = null;
		//	boolean attachment = true;

		Properties props = new Properties();
		props.put("mail.smtp.host", "box516.bluehost.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");



		Session session = Session.getInstance(props,
				new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try {
			System.out.println("TestNG Data jobs is Processing.................");

			MimeMessage message = new MimeMessage(session);
			message.setSubject(subject);
			message.setText(subject);
			message.setFrom(new InternetAddress("support@radaptive.com"));
			message.setRecipients(javax.mail.Message.RecipientType.TO, InternetAddress.parse(toAddress));
			message.setRecipients(javax.mail.Message.RecipientType.CC, InternetAddress.parse(ccAddress));



			MimeBodyPart messageBodyPart = new MimeBodyPart();

			Multipart multipart = new MimeMultipart();

			messageBodyPart = new MimeBodyPart();
			String file = "G:/SeleniumDatas/ericssonSelenium/target/surefire-reports/testng-results.xml";//G:/Selenium_Datas/ericsson-selenium/target/surefire-reports/emailable-report.html
			String fileName = "Ericsson-Emailreport";
			DataSource source = new FileDataSource(file);
			messageBodyPart.setDataHandler(new DataHandler(source));
			messageBodyPart.setFileName(fileName);
			multipart.addBodyPart(messageBodyPart);

			message.setContent(multipart);

			System.out.println("Sending.................");

			Transport.send(message);

			System.out.println("Done...............");

		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}



	/*outputFile = new File("./TestNGResult.csv");
				dateString = "TestNGResult.csv";

				message.setSubject(subject);
				message.setText(subject);
				MimeBodyPart messageBodyPart = new MimeBodyPart();
				Multipart multipart = new MimeMultipart();

				if(attachment){
					messageBodyPart = new MimeBodyPart();
					DataSource source = new FileDataSource(outputFile.toString());
					messageBodyPart.setDataHandler(new DataHandler(source));
					messageBodyPart.setFileName(dateString);
					multipart.addBodyPart(messageBodyPart);
					message.setContent(multipart);
				}

				System.out.println("Processing........");
				Transport.send(message);
				System.out.println("Done");

			} catch (MessagingException e) {
				System.err.println(e);
			}catch (Exception e) {
				e.printStackTrace();
			}

		}

		public void csvGenerator(String testCase, String result){

			try{
				Date date = new Date();
				String dateArr[] = date.toString().split(" ");
				String dateString = dateArr[2]+" "+dateArr[1]+" "+dateArr[5];
				String outputFile = null;
				outputFile = "./TestNGResult.csv";

				boolean alreadyExists = new File(outputFile).exists();       
				try{           
					CsvWriter csvOutput = new CsvWriter(new FileWriter(outputFile, true), ',');       

					if(!alreadyExists){
						csvOutput.write("Test Cases");
						csvOutput.write("Result");
						csvOutput.endRecord();
					}


					csvOutput.write(testCase);
					csvOutput.write(result);
					csvOutput.endRecord();
					csvOutput.close();           
				}catch(Exception e){
					System.out.println(e);
				}
			}catch(Exception e){
				System.err.println("Exception while generating csv "+e);
			}

		}

		private Date getDateOneDayLater() {
			// TODO Auto-generated method stub
			return null;
		}
	}
	 */

}



