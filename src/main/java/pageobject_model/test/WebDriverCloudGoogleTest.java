package pageobject_model.test;

import org.testng.AssertJUnit;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageobject_model.page.CloudGoogleComputeEnginePage;
import pageobject_model.page.elements.RadioButtonElement;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.testng.AssertJUnit.assertTrue;
import static pageobject_model.page.CloudGoogleComputeEnginePage.DropDownName.*;

public class WebDriverCloudGoogleTest extends CommonConditions {
    private CloudGoogleComputeEnginePage computeEnginePage;

    @BeforeMethod(alwaysRun = true)
    public void browserSetup()  {
        computeEnginePage = new CloudGoogleComputeEnginePage(driver);
        computeEnginePage.openPage();
    }

    @Test(description = "Monthly rate for input parameters has correct value",
            dataProvider = "computeEngineTestValues",
            dataProviderClass = pageobject_model.service.TestDataProvider.class )
    public void calculateMonthlyRentForParameters(String numberOfInstances, String os, String machineType, String gpuModel, String gpuNumber) throws InterruptedException {
        computeEnginePage.getComputeEngineEntity().setNumberOfInstances(numberOfInstances);
        computeEnginePage.optionalPopUpClose();

        computeEnginePage.selectDropdownValue(OS, os);
        computeEnginePage.cookiesPopUpClose();

        computeEnginePage.getComputeEngineEntity().selectModelRadioButton();
        computeEnginePage.selectDropdownValue(MACHINE_TYPE, machineType);

        computeEnginePage.enableRadioButton(RadioButtonElement.RadioButtonName.ADD_GPU);
        computeEnginePage.selectDropdownValue(GPU_MODEL, gpuModel);
        computeEnginePage.getComputeEngineEntity().setGpuNumber(gpuNumber);

        computeEnginePage.getComputeEngineEntity().selectCommittedTerm1Year();

        Thread.sleep(5000);
        String calculatedMonthRent = computeEnginePage.getMonthlyRent();
        assertEquals("$3,384.50", calculatedMonthRent); //update dataProvider and add second line in csv
    }

    @Test(description = "Check Input Values Correctness",
            dataProvider = "computeEngineTestValues",
            dataProviderClass = pageobject_model.service.TestDataProvider.class )
    public void calculatorValuesCheck(String numberOfInstances, String os, String machineType, String gpuModel, String gpuNumber) {
        computeEnginePage.getComputeEngineEntity().setNumberOfInstances(numberOfInstances);
        computeEnginePage.optionalPopUpClose();

        computeEnginePage.selectDropdownValue(OS, os);
        computeEnginePage.cookiesPopUpClose();

        computeEnginePage.getComputeEngineEntity().selectModelRadioButton();
        computeEnginePage.selectDropdownValue(MACHINE_TYPE, machineType);

        computeEnginePage.enableRadioButton(RadioButtonElement.RadioButtonName.ADD_GPU);
        computeEnginePage.selectDropdownValue(GPU_MODEL, gpuModel);
        computeEnginePage.getComputeEngineEntity().setGpuNumber(gpuNumber);

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
