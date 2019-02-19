package PageMethods;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Utilities.Common;

public class CustomerDetailsPage extends Page{
	
	
	@FindBy(xpath="//span[text()='BEGIN NEW APPLICATION']")
	WebElement beginNewApplication;
	
	@FindBy(xpath="//span[@id='quote_details_form_header_hd-textEl'  and text()='Customer Details']")
	WebElement customerDetailsForm;
	
	@FindBy(xpath="//div[contains(text(),'The form has errors')]")
	WebElement errorMsg;
	
	@FindBy(xpath="//input[@name='agencyNumberInput']")
	WebElement txtAgency;
	
	@FindBy(xpath="//input[@name='producerNumberInput']")
	WebElement txtProducer;
	
	@FindBy(xpath="//input[@name='effectiveDateInput']")
	WebElement txtEffectiveDate;
	
	@FindBy(xpath="//input[@name='expirationDateInput']")
	WebElement txtExpirationDate;
	
	@FindBy(name="organizationTypeInput")
	WebElement txtOrganizationtType;
	
	@FindBy(name="firstNameInput")
	WebElement txtFirstName;
	
	@FindBy(name="lastNameInput")
	WebElement txtLastName;
	
	@FindBy(name="customerName2Input")
	WebElement txtDBAName;
	
	@FindBy(xpath="//a[@id='editPhysicalButton']")
	WebElement btnEditAddress;
	
	@FindBy(xpath="//div[@id='addressValidationWindow_header']")
	WebElement alertPhysicalAddress;
	
	@FindBy(name="addressLine1_addressValidationWindow")
	WebElement txtAddress1;
	
	@FindBy(name="zip_addressValidationWindow")
	WebElement txtZipCode;
	
	@FindBy(name="cityStateCounty_addressValidationWindow")
	WebElement txtCityStateCountry;
			
	@FindBy(name="customerNameInput")
	WebElement txtCustomerName;
	
	@FindBy(name="organizationnTypeList")
	WebElement lstOrganizationType;
	
	@FindBy(id="validateButton_addressValidationWindow")
	WebElement btnValidateEntry;
	
	@FindBy(id="acceptEnteredButton_addressValidationWindow")
	WebElement btnAcceptAsEntered;
	//td[@id='producerNumberInput-inputCell']/following-sibling::td/div
	@FindBy(xpath="//td[input[@id='producerNumberInput-inputEl']]/following-sibling::td/div[contains(@class,'x-form-trigger-click')]")
	WebElement ddProducer;
	
	@FindBy(xpath="//td[@id='agencyNumberInput-inputCell']/following-sibling::td/div")
	WebElement ddAgency;
	
	
	
	@FindBy(xpath="//span[text()='Save and Continue']")
	WebElement btnSaveAndContinue;
	
	protected static String HOME_PAGE_TITLE = "Great West Casualty Company - Truck Underwriting Application Quik-R8";

	public CustomerDetailsPage(WebDriver browser) 
	{
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
	protected void waitForPageLoad() 
	{
		try
		{
			new WebDriverWait(browser,5).
			until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[text()='Save and Continue']")));				
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());			
		}
	}

	public void verifyEntryCustomerDetails()
	{
		try
		{
			clickOn(beginNewApplication, "Click on Begin New Application Link");
			waitForElementPresent(customerDetailsForm);
			if(isElementPresent(customerDetailsForm))
			{
				Common.testStepPassed("Successfully displaying Customer Details");
			}
			else
			{
				Common.testStepFailed("Customer Details Form is not found");
			}
			
			
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		
	}
	
	public void verifyErrorMsgExists(String initial)
	{
		try
		{
			if(initial.equals("initial") && !(isElementPresent(errorMsg)))
			{
				Common.testStepPassed("No Validation message is found at inital stage");
			}
			else if(initial.equals("initial") && (isElementPresent(errorMsg)))
			{
				Common.testStepFailed("Validation message is found at inital stage");
			}
			else if(isElementPresent(errorMsg))
			{
				Common.testStepPassed("Validation message exists");
			}
			else
			{
				Common.testStepFailed("Validation message not found");
			}
		}
		catch(Exception e)
		{
			Common.testStepFailed(e.getMessage());
		}	
	}

	public void selectAgencyAndProducer()
	{
		String strAgency=Common.retrieve("Agency");
		String strProducer=Common.retrieve("Producer");
		WebElement lstProducer;
		WebElement lstAgency;
		try
		{			
			enterText(txtAgency,strAgency);
			clickOn(ddAgency, "Drop down-Agency");
			lstAgency=browser.findElement(By.xpath("//li[contains(text(),'2 - GREAT WEST CASUALTY COMPANY')]"));
			clickOn(lstAgency,"Agency");
		
			//enterText(txtProducer,strProducer);
			sleep(2000);
			clickOn(ddProducer, "Drop down-Producer");
			sleep(2000);
			lstProducer=browser.findElement(By.xpath("//li[contains(text(),'BENTZEL, STEVEN(99)')]"));
			sleep(2000);
			clickOn(lstProducer,"Producer");
			
			

		}
		catch(Exception e)
		{
			Common.testStepFailed("Failed to select Agency/Producer "+e.getMessage());
		}
	}
	
	

	public void verifyInitialDates()
	{
		  String strEffectiveDate=txtEffectiveDate.getAttribute("value");
		
		  boolean flag=true;
		
		  SimpleDateFormat currentDateFormat = new SimpleDateFormat("MM/dd/yyyy");//dd/MM/yyyy
		  Date now = new Date();
		  String currentDate = currentDateFormat.format(now);
		  
		  if(currentDate.equals(strEffectiveDate))
		  {
			  Common.testStepPassed("Effective date is current date as expected");
		  }
		  else
		  {
			  Common.testStepFailed("Effective date is not current date");
			  Common.takeScreenshot();
		  }				  
		  verifyExpirationDate(currentDate,"initial");
	
		
	}
	
	
	
	public void verifyExpirationDate(String currentDate,String initial)
	{
		  boolean flag=true;
		  String strExpirationDate=txtExpirationDate.getAttribute("value");
		
		  String[] splitDates=currentDate.split("/");
		  String[] splitExpirationDates=strExpirationDate.split("/");
		  int year;
		  for(int i=0;i<(splitDates.length-1);i++)
		  {
			  if(!(splitDates[i].equals(splitExpirationDates[i])))
				  flag=false;			  
		  }	  
		  if(flag)
		  {
			  int expectedYear=Integer.parseInt(splitDates[2])+1;
			  flag=(splitExpirationDates[2].equals(String.valueOf(expectedYear)))?true:false;
		  }
			
		  if(flag && initial.equals("initial"))
		  {
			  Common.testStepPassed("Initial Expiration Date is set to one year ahead of Effective Date as expected");
		  }
		  else
		  {
			  Common.testStepFailed("Initial Expiration Date is set to invalid date");
			  Common.takeScreenshot();
		  }
	}

	public void changeEffectiveDate() 
	{
		String effectiveDate=Common.retrieve("EffectiveDate");
		enterText(txtEffectiveDate, effectiveDate);
		clickOn(txtExpirationDate, "Expiration Date");
		verifyExpirationDate(effectiveDate,"");		
	}
	
	public void verifyOrganizationType()
	{
		String strOrganizationType=Common.retrieve("OrganizationType");
		String strFirstName="";
		String strLastName="";
		String strCustomerName="";
		enterText(txtOrganizationtType, strOrganizationType);
		lstOrganizationType=browser.findElement(By.xpath("//ul[@class='x-list-plain']/li[contains(text(),'"+strOrganizationType+"')]"));
		clickOn(lstOrganizationType,strOrganizationType);
		
		if(strOrganizationType.equalsIgnoreCase("INDIVIDUAL"))
		{
			if(isElementPresent(txtFirstName) && isElementPresent(txtLastName))
			{
				strFirstName=Common.retrieve("FirstName");
				strLastName=Common.retrieve("LastName");
				enterText(txtFirstName, strFirstName);
				enterText(txtLastName, strLastName);
				Common.testStepPassed("FirstName and LasttName entry fields are present as expected.");
			}
			else
			{
				Common.testStepFailed("FirstName and LasttName entry fields are not present.");
			}
		}
		else
		{
			strCustomerName=Common.retrieve("CustomerName");
			enterText(txtCustomerName, strCustomerName);
			Common.testStepFailed("FirstName and LastName entry fields are not present");
		}
		
	}
	
	public void enterDBAName()
	{
		String strDisplayName=Common.retrieve("DisplayName");
		try
		{
			enterText(txtDBAName, strDisplayName);
			Common.testStepPassed("Entry of Display name is available as expected");
		}
		catch(Exception e)
		{
			Common.testStepFailed("Entry of Display name is not available");
		}
	}
	
	public void enterPhysicalAddress()
	{
		try
		{
			String strAddress1="";
			String strZipCode="";
			String strCityStateCountry="";
			
			clickOn(btnEditAddress, "Edit Address under Physical Address Section");
			if(isElementPresent(alertPhysicalAddress))
			{
				Common.testStepPassed("Physical Address is visible");
				strAddress1=Common.retrieve("Address1");
				strZipCode=Common.retrieve("ZipCode");
				strCityStateCountry=Common.retrieve("CityStateCountry");
				enterText(txtAddress1, strAddress1);
				enterText(txtZipCode, strZipCode);
				enterText(txtCityStateCountry, strCityStateCountry);	
				//enterText(txtCityStateCountry, Keys.TAB);
				WebElement lstCountry=browser.findElement(By.xpath("//input[@name='cityStateCounty_addressValidationWindow']/parent::td/following-sibling::td/div"));
				clickOn(lstCountry, strCityStateCountry);
				
				lstCountry=browser.findElement(By.xpath("//div[@id='addressValidationWindow']/following-sibling::div[2]//li[contains(text(),'LAS VEGAS')]"));
				clickOn(lstCountry, strCityStateCountry);
				
				clickOn(btnValidateEntry, "Validate Entry");
				clickOn(btnAcceptAsEntered, "Accept as Entered");
				
			}
			else
			{
				Common.testStepFailed("Physical Address Alert box is not visible");
			}
			
		}
		catch(Exception e)
		{
			Common.testStepFailed("Failed to enter physical address: "+e.getMessage());
		}
	}
	

}





















