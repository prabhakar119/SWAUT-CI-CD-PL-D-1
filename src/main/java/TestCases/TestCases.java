package TestCases;

import java.io.File;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import PageMethods.AdactinApplication;
import PageMethods.AdactinHomePage;
import PageMethods.Bookmaintain;
import PageMethods.ConfirmationPage;
import PageMethods.ForgetPassword;
import PageMethods.NewUserRegistration;
import PageMethods.Phptravelapplication;
import PageMethods.Phptravelshomepage;
import PageMethods.Register;
import PageMethods.SearchHotel;
import PageMethods.SelectHotel;
import Utilities.Common;
import Utilities.GenericKeywords;
import Utilities.TestLinkConnection;
import Utilities.XmlValidation;

@Listeners({ Utilities.TestListener.class })
public class TestCases extends Common {

	public static int count = 1;
	
	private AdactinHomePage adactinHomePage;
	private SearchHotel searchHotel;
	private SelectHotel selectHotel;

	private Bookmaintain bookMaintain;
	private Phptravelapplication phptravelapplication;
	private Phptravelshomepage phptravelshomepage;
	private Register register;


	@BeforeClass
	public void start(){	
		GenericKeywords.extent.loadConfig(new File("./config/extent-config.xml"));

	}


	public void testStart(String testCaseDescription) {
		GenericKeywords.testFailure = false;
		GenericKeywords.currentStep = 0;
		reportStart(GenericKeywords.testCaseName,testCaseDescription);
		phptravelapplication=new Phptravelapplication();
		phptravelshomepage=phptravelapplication.openphptravelsApplication();

	}

	public void testEnd() {
		try {
			phptravelapplication.close();			
		} catch (Exception e) {
			System.out.println("Expception : " + e.getMessage());
		}finally{
			GenericKeywords.extent.endTest(GenericKeywords.parent);
			GenericKeywords.extent.flush();	
			if(getConfigProperty("UpdateTestLinkStatus").toString().trim().toUpperCase().equals("YES")){
				if(GenericKeywords.testFailure){
					TestLinkConnection.updateExecutionStatus(GenericKeywords.testCaseName, "", "FAIL");					
				}else{
					TestLinkConnection.updateExecutionStatus(GenericKeywords.testCaseName, "passed", "PASS");
				}
			}
		}
	}
	
	
	@Test(alwaysRun = true)
	public void TC_008() {
		GenericKeywords.testCaseName = new Exception().getStackTrace()[0].getMethodName();
		testStart("sign in to phptravel app");


		for (String testDataSet : GenericKeywords.testCaseDataSets) {
			GenericKeywords.testCaseDataRow = returnRowNumber(testDataSet);
			testStepInfoStart(testDataSet);

			register=phptravelshomepage.clickOnSignup();

			testStepInfoEnd(testDataSet);
		}
		testEnd();

	}
	@Test(alwaysRun = true)
	public void TC_009() {
		GenericKeywords.testCaseName = new Exception().getStackTrace()[0].getMethodName();
		testStart("sign in successfully");


		for (String testDataSet : GenericKeywords.testCaseDataSets) {
			GenericKeywords.testCaseDataRow = returnRowNumber(testDataSet);
			testStepInfoStart(testDataSet);
			register=phptravelshomepage.clickOnSignup();
			register.enterSignUpDetails();
			

			testStepInfoEnd(testDataSet);
		}
		testEnd();

	}
	
	
	
	
	
	
	
	


	@Test(alwaysRun = true)
	public void TC_001() {
		GenericKeywords.testCaseName = new Exception().getStackTrace()[0].getMethodName();
		testStart("Login to Adactin Application");


		for (String testDataSet : GenericKeywords.testCaseDataSets) {
			GenericKeywords.testCaseDataRow = returnRowNumber(testDataSet);
			testStepInfoStart(testDataSet);

			searchHotel = adactinHomePage.Login();	
			if(searchHotel.verifyLoginpage())
				searchHotel.logoutFromApp();

			testStepInfoEnd(testDataSet);
		}
		testEnd();

	}


	@Test(alwaysRun = true)
	public void TC_002() {
		GenericKeywords.testCaseName = new Exception().getStackTrace()[0].getMethodName();
		testStart("Search a hotel");


		for (String testDataSet : GenericKeywords.testCaseDataSets) {
			GenericKeywords.testCaseDataRow = returnRowNumber(testDataSet);
			testStepInfoStart(testDataSet);

			searchHotel = adactinHomePage.Login();			
			selectHotel=searchHotel.BookHotel();

			testStepInfoEnd(testDataSet);
		}
		testEnd();

	}


	@Test(alwaysRun = true)
	public void TC_003() {
		GenericKeywords.testCaseName = new Exception().getStackTrace()[0].getMethodName();
		testStart("Book a hotel");


		for (String testDataSet : GenericKeywords.testCaseDataSets) {
			GenericKeywords.testCaseDataRow = returnRowNumber(testDataSet);
			testStepInfoStart(testDataSet );

			searchHotel = adactinHomePage.Login();			
			selectHotel=searchHotel.BookHotel();
			selectHotel.Selecthotel();

			testStepInfoEnd(testDataSet);
		}
		testEnd();

	}


	@Test(alwaysRun = true)
	public void TC_004() {
		GenericKeywords.testCaseName = new Exception().getStackTrace()[0].getMethodName();
		testStart("Forget Password functionality");


		for (String testDataSet : GenericKeywords.testCaseDataSets) {
			GenericKeywords.testCaseDataRow = returnRowNumber(testDataSet);
			testStepInfoStart(testDataSet);

		//	ForgetPswd=adactinHomePage.forgetpassword();

			testStepInfoEnd(testDataSet );
		}
		testEnd();

	}


	@Test(alwaysRun = true)
	public void TC_005() {
		GenericKeywords.testCaseName = new Exception().getStackTrace()[0].getMethodName();
		testStart("New User Register functionality");


		for (String testDataSet : GenericKeywords.testCaseDataSets) {
			GenericKeywords.testCaseDataRow = returnRowNumber(testDataSet);
			testStepInfoStart(testDataSet);

			//Registrationpage=adactinHomePage.UserRegistration();

			testStepInfoEnd(testDataSet );
		}
		testEnd();

	}


	@Test(alwaysRun = true)
	public void TC_006() {
		GenericKeywords.testCaseName = new Exception().getStackTrace()[0].getMethodName();
		testStart("XML Validation");


		for (String testDataSet : GenericKeywords.testCaseDataSets) {
			GenericKeywords.testCaseDataRow = returnRowNumber(testDataSet);
			testStepInfoStart(testDataSet);

			XmlValidation.SoapCollateralPositionData();	

			//String text;
			try {
				XmlValidation.SoapCollateralPositionData();	
				XmlValidation.validateXml();			
			} catch (Exception e) {

			} 

			testStepInfoEnd(testDataSet);
		}
		testEnd();

	}

	@Test(alwaysRun=true)
	public void TC_007(){
		GenericKeywords.testCaseName = new Exception().getStackTrace()[0].getMethodName();
		testStart("Search and Cancel Booked Hotel");


		for (String testDataSet : GenericKeywords.testCaseDataSets) {
			GenericKeywords.testCaseDataRow = returnRowNumber(testDataSet);
			testStepInfoStart(testDataSet);

			searchHotel = adactinHomePage.Login();	
			bookMaintain=searchHotel.clickOnBookedItinery();
			bookMaintain.BookedItinerary();
			testStepInfoEnd(testDataSet);
		}
		testEnd();
	}

	


}
