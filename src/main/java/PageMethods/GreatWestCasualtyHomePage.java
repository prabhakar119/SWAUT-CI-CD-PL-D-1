package PageMethods;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Utilities.Common;

public class GreatWestCasualtyHomePage extends Page {

	@FindBy(xpath="//input[@name='userID']")
	private WebElement weUsername;
	
	@FindBy(xpath="//input[@name='password']")
	private WebElement wePassword;
	
	@FindBy(xpath="//input[@name='login']")
	private WebElement btnLogin;
	
	@FindBy(xpath="//a[contains(@href,'Register.php')]")
	private WebElement Registerlink;
	
	@FindBy(xpath="//a[contains(@href,'ForgotPassword.php')]")
	private WebElement weForgetpswdlink;
	
	@FindBy(xpath="//*[@id='emailadd_recovery']")
	private WebElement weEmailAddress;

	@FindBy(xpath="//*[@id='Submit']")
	private WebElement weSubmit;
	
	@FindBy(xpath="//a[contains(@href,'Register.php')]")
	private WebElement weNewRegister;
	
	
	@FindBy(xpath="//*[@id='username']")
	private WebElement weNewusername;
	@FindBy(xpath="//*[@id='password']")
	private WebElement weNewPassword;
	
	@FindBy(xpath="//*[@id='re_password']")
	private WebElement weRePassword;
	
	@FindBy(xpath="//*[@id='full_name']")
	private WebElement weFullName;
		
	@FindBy(xpath="//*[@id='email_add']")
	private WebElement weEmailAdd;
	
	@FindBy(xpath="//*[@id='tnc_box']")
	private WebElement chkAgree;
	
	@FindBy(xpath="//*[@id='Submit']")
	private WebElement btnRegister;
	
	protected static String HOME_PAGE_TITLE = "Great West Casualty Company - Truck Underwriting Application Quik-R8";

	public GreatWestCasualtyHomePage(WebDriver browser) {
		super(browser);		
	}

	@Override
	protected boolean isValidPage() {
		if (browser.getTitle().trim().contains(HOME_PAGE_TITLE)) {
			return true;
		}
		return false;
	}

	@Override
	protected void waitForPageLoad() {
		try{
			new WebDriverWait(browser,30).
			until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@name='login']")));				
		}catch(Exception e){
			System.out.println(e.getMessage());			
		}
	}
	
	
	//==========================================================
	
	public CustomerDetailsPage Login()
	{
		enterText(weUsername,Common.retrieve("UserName")); 
		Common.testStepPassed("Entered Username ->"+ Common.retrieve("UserName"));
		enterText(wePassword,Common.retrieve("Password"));
		Common.testStepPassed("Entered Password ->"+ Common.retrieve("UserName"));
		Common.takeScreenshot();
		weUsername.click();
		clickOn(btnLogin,"Login");
		return new CustomerDetailsPage(browser);
		//return new SearchHotel(browser);
	}
	
	
}
