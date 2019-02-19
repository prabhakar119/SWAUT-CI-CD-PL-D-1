package PageMethods;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Utilities.Common;



public class Bookmaintain extends  Page {	
	
	private static final CharSequence HOME_PAGE_TITLE = "AdactIn.com - Select Hotel";

	public Bookmaintain(WebDriver browser) {
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
			until(ExpectedConditions.presenceOfElementLocated(By.xpath("//td[text()='Welcome to AdactIn Group of Hotels']")));				
		}catch(Exception e){
			System.out.println(e.getMessage());			
		}
		
	}

	@FindBy(xpath="//td[text()='Booked Itinerary']")
	private WebElement headerBookItinery;
	
	@FindBy(xpath="//input[@id='order_id_text']")
	private WebElement txtsearch;
	
	@FindBy(xpath="//input[@id='search_hotel_id']")
	private WebElement btnsearch;
	
	@FindBy(xpath="//input[starts-with(@name,'btn_id')]")
	private WebElement btncancel;
	
	
	public void BookedItinerary(){
		if(isElementPresent(headerBookItinery)){
			//txtsearch.sendKeys("B53W0V6H85");
			enterText(txtsearch, Common.retrieve("searchRec"));
			Common.testStepPassed("Entered searchRec ->"+ Common.retrieve("searchRec"));
			clickOn(btnsearch, "button search");
			waitForElementPresent(btncancel);
			clickOn(btncancel, "button cancel");
			switchToAlert("yes");
		}
			}

	}


