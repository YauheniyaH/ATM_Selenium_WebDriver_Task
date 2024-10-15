package pageobject_model.test;

import org.testng.AssertJUnit;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageobject_model.model.ComputeEngineEntity;
import pageobject_model.page.CloudGoogleComputeEnginePage;
import pageobject_model.page.DropDownObject;
import pageobject_model.service.ComputeEngineEntityCreator;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

public class WebDriverCloudGoogleTest extends CommonConditions {
    private CloudGoogleComputeEnginePage computeEnginePage;

    @BeforeMethod(alwaysRun = true)
    public void browserSetup()  {
        computeEnginePage = new CloudGoogleComputeEnginePage(driver);
        computeEnginePage.openPage();
    }

    @Test(description = "Monthly rate for input parameters has correct value")
    public void calculateMonthlyRentForParameters() throws InterruptedException {
        ComputeEngineEntity testEntity = ComputeEngineEntityCreator.withParametersFromProperty();
        computeEnginePage.inputNumberOfInstances(testEntity.getNumberOfInstances());
        computeEnginePage.optionalPopUpClose();

        computeEnginePage.selectDropdownValue(DropDownObject.DropDownName.OS,testEntity.getOs());
        computeEnginePage.cookiesPopUpClose();

        computeEnginePage.modelRadioButton.click();
        computeEnginePage.selectDropdownValue(DropDownObject.DropDownName.MACHINE_TYPE,testEntity.getMachineType());

        computeEnginePage.addGPURadioButton.click();
        computeEnginePage.selectDropdownValue(DropDownObject.DropDownName.GPU_MODEL,testEntity.getGpuModel());
        computeEnginePage.inputGPUNumber(testEntity.getGpuNumber());

        computeEnginePage.committedTerm1YearButton.click();

        Thread.sleep(5000);
        String calculatedMonthRent = computeEnginePage.getMonthlyRent();
        assertEquals("$3,384.50", calculatedMonthRent);
    }

    @Test(description = "Check Input Values Correctness")
    public void calculatorValuesCheck() {
        ComputeEngineEntity testEntity = ComputeEngineEntityCreator.withParametersFromProperty();
        computeEnginePage.inputNumberOfInstances(testEntity.getNumberOfInstances());
        computeEnginePage.optionalPopUpClose();

        computeEnginePage.selectDropdownValue(DropDownObject.DropDownName.OS,testEntity.getOs());
        computeEnginePage.cookiesPopUpClose();

        computeEnginePage.modelRadioButton.click();
        computeEnginePage.selectDropdownValue(DropDownObject.DropDownName.MACHINE_TYPE,testEntity.getMachineType());

        computeEnginePage.addGPURadioButton.click();
        computeEnginePage.selectDropdownValue(DropDownObject.DropDownName.GPU_MODEL,testEntity.getGpuModel());
        computeEnginePage.inputGPUNumber(testEntity.getGpuNumber());

        computeEnginePage.committedTerm1YearButton.click();

        assertTrue(computeEnginePage.checkControlValue(computeEnginePage.modelRadioButton,"Regular"));
        assertTrue(computeEnginePage.checkControlValue(computeEnginePage.instanceType, "n1-standard-8"));
        assertTrue(computeEnginePage.checkControlValue(computeEnginePage.committedTerm1YearButton,"1 year"));
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
