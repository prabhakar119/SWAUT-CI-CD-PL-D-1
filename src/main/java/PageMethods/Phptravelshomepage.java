package PageMethods;

import org.openqa.selenium.By;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Utilities.Common;

public class Phptravelshomepage extends Page {

	private static final CharSequence HOME_PAGE_TITLE = "PHPTRAVELS | Travel Technology Partner";

	protected Phptravelshomepage(WebDriver browser) {
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
			until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(text(),'Hotels')]")));				
		}catch(Exception e){
			System.out.println(e.getMessage());			
		}
	}
	
	
	@FindBy(xpath="(//li[@id='li_myaccount']//b/preceding-sibling::*/following::*[1])[2]")
	private WebElement btnmyaccoumt;
	@FindBy(xpath="//li[@class='open']//following::a[contains(text(),'Sign Up') and not(contains(@class,'warning'))]")
	private WebElement btnsignup;

	public Register clickOnSignup() {
		sleep(2000);
		Common.takeScreenshot();
		waitForElementPresent(btnmyaccoumt);
		clickOn(btnmyaccoumt, "myaccount");
		clickOn(btnsignup, "sign up");
		
		return new Register(browser);
	}

}
