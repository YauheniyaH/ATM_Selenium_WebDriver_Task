package pageobject_model.service;

import java.util.ResourceBundle;

public class TestDataReader {
    public static final String TESTDATA_NUMBER_OF_INSTANCES = "testdata.number.of.instances";
    public static final String TESTDATA_OS = "testdata.os";

    public static final String TESTDATA_MACHINE_TYPE = "testdata.machine.type";

    public static final String TESTDATA_GPU_MODEL = "testdata.gpu.model";

    public static final String TESTDATA_GPU_NUMBER = "testdata.gpu.number";
    public static final String COMPUTE_ENGINE_ULR = "testdata.compute.engine.page.url";

    public static final String HOMEPAGE_URL = "testdata.home.page";
    private static ResourceBundle resourceBundle =ResourceBundle.getBundle(System.getProperty("environment"));

    public static String getTestData (String key){
        return resourceBundle.getString(key);
    }
}
