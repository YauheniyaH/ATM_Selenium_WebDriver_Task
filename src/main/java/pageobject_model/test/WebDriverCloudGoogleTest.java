package pageobject_model.test;

import org.testng.AssertJUnit;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageobject_model.page.CloudGoogleComputeEnginePage;
import pageobject_model.page.DropDownObject;
import pageobject_model.service.TestDataReader;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.testng.AssertJUnit.assertTrue;
import static pageobject_model.service.TestDataReader.*;

public class WebDriverCloudGoogleTest extends CommonConditions {
    private CloudGoogleComputeEnginePage computeEnginePage;

    @BeforeMethod(alwaysRun = true)
    public void browserSetup()  {
        computeEnginePage = new CloudGoogleComputeEnginePage(driver);
        computeEnginePage.openPage();
    }

    @Test(description = "Monthly rate for input parameters has correct value")
    public void calculateMonthlyRentForParameters() throws InterruptedException {
        computeEnginePage.getComputeEngineEntity().setNumberOfInstances(TestDataReader.getTestData(TESTDATA_NUMBER_OF_INSTANCES));
        computeEnginePage.optionalPopUpClose();

        computeEnginePage.selectDropdownValue(DropDownObject.DropDownName.OS,TestDataReader.getTestData(TESTDATA_OS));
        computeEnginePage.cookiesPopUpClose();

        computeEnginePage.getComputeEngineEntity().selectModelRadioButton();
        computeEnginePage.selectDropdownValue(DropDownObject.DropDownName.MACHINE_TYPE,TestDataReader.getTestData(TESTDATA_MACHINE_TYPE));

        computeEnginePage.getComputeEngineEntity().switchAddGPURadioButton();
        computeEnginePage.selectDropdownValue(DropDownObject.DropDownName.GPU_MODEL,TestDataReader.getTestData(TESTDATA_GPU_MODEL));
        computeEnginePage.getComputeEngineEntity().setGpuNumber(TestDataReader.getTestData(TESTDATA_GPU_NUMBER));

        computeEnginePage.getComputeEngineEntity().selectCommittedTerm1Year();

        Thread.sleep(5000);
        String calculatedMonthRent = computeEnginePage.getMonthlyRent();
        assertEquals("$3,384.50", calculatedMonthRent);
    }

    @Test(description = "Check Input Values Correctness")
    public void calculatorValuesCheck() {
        computeEnginePage.getComputeEngineEntity().setNumberOfInstances(TestDataReader.getTestData(TESTDATA_NUMBER_OF_INSTANCES));
        computeEnginePage.optionalPopUpClose();

        computeEnginePage.selectDropdownValue(DropDownObject.DropDownName.OS,TestDataReader.getTestData(TESTDATA_OS));
        computeEnginePage.cookiesPopUpClose();

        computeEnginePage.getComputeEngineEntity().selectModelRadioButton();
        computeEnginePage.selectDropdownValue(DropDownObject.DropDownName.MACHINE_TYPE,TestDataReader.getTestData(TESTDATA_MACHINE_TYPE));

        computeEnginePage.getComputeEngineEntity().switchAddGPURadioButton();
        computeEnginePage.selectDropdownValue(DropDownObject.DropDownName.GPU_MODEL,TestDataReader.getTestData(TESTDATA_GPU_MODEL));
        computeEnginePage.getComputeEngineEntity().setGpuNumber(TestDataReader.getTestData(TESTDATA_GPU_NUMBER));

        computeEnginePage.getComputeEngineEntity().selectCommittedTerm1Year();

        assertTrue(computeEnginePage.checkControlValue(computeEnginePage.getComputeEngineEntity().getModelRadioButton(),"Regular"));
        assertTrue(computeEnginePage.checkControlValue(computeEnginePage.machineTypeSelected, "n1-standard-8"));
        assertTrue(computeEnginePage.checkControlValue(computeEnginePage.getComputeEngineEntity().getCommittedTerm1YearButton(),"1 year"));
    }

    @Test(description = "Click and hold Action test for Amount of memory scrollbar; check that set value by scrollbar equals value in text-box")
    public void numberOfCPUSliderCheck() throws IOException {
        computeEnginePage.moveNumberOfCPUScrollBar(50);
        computeEnginePage.highlightElement(driver, computeEnginePage.labelNumberOfGPUs);
        AssertJUnit.assertEquals("16 vCPUs", computeEnginePage.labelNumberOfGPUs.getText());
    }

    @Test(description = "Instance name input check using Actions")
    public void inputTextBoxCheck() {
        String inputValue = "test instance";
        computeEnginePage.inputInstanceName(inputValue);
        assertEquals(inputValue, computeEnginePage.getTexBoxInstanceName());
    }

    @Test(description = "Check that Estimated cost stay visible if scroll to the bottom of the page")
    public void labelEstimatedCostVisibilityCheck() {
        computeEnginePage.scrollToBottomOfPage();
        assertTrue(computeEnginePage.labelEstimatedCost.isDisplayed());
    }

}
