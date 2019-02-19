package PageMethods;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Utilities.Common;

public class Register extends Page{

	private static final CharSequence HOME_PAGE_TITLE = "Register";

	protected Register(WebDriver browser) {
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
			until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[text()='Sign Up']")));				
		}catch(Exception e){
			System.out.println(e.getMessage());			
		}
		
	}
	@FindBy(xpath="//input[@name='firstname']")
	private WebElement txtfirstname;
	
	@FindBy(xpath="//input[@name='lastname']")
	private WebElement txtlastname;
	
	@FindBy(xpath="//input[@name='phone']")
	private WebElement txtphone;
	
	
	@FindBy(xpath="//input[@name='email']")
	private WebElement txtemail;
	
	
	@FindBy(xpath="//input[@name='password']")
	private WebElement textpassword;
	
	
	@FindBy(xpath="//input[@name='confirmpassword']")
	private WebElement txtconfirmpassword;
	
	@FindBy(xpath="//button[@class='signupbtn btn_full btn btn-action btn-block btn-lg']")
	private WebElement btnsignupbtn;
	
	
	
	public void enterSignUpDetails(){
		
		enterText(txtfirstname, Common.retrieve("username"));
		enterText(txtlastname, Common.retrieve("lastname"));
		enterText(txtphone, Common.retrieve("phone"));
		enterText(txtemail, Common.retrieve("email"));
		enterText(textpassword, Common.retrieve("password"));
		enterText(txtconfirmpassword, Common.retrieve("conpassword"));
		clickOn(btnsignupbtn, "signup");
	
	}

}
