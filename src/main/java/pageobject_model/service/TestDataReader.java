package pageobject_model.service;

import java.util.ResourceBundle;

public class TestDataReader {
    public static final String COMPUTE_ENGINE_ULR = "compute.engine.page.url";

    public static final String HOMEPAGE_URL = "home.page";

    public static final String BROWSER = "browser";

    private static final ResourceBundle resourceBundle =ResourceBundle.getBundle(System.getProperty("environment"));

    public static String getTestData (String key){
        return resourceBundle.getString(key);
    }
}
