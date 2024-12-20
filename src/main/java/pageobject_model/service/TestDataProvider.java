package pageobject_model.service;

import org.testng.annotations.DataProvider;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TestDataProvider {
    private static final String DELIMITER = ";";

    @DataProvider(name = "computeEngineTestValues")
    public Iterator<Object[]> getTestData() {
       return readFromCSV();

    }

    public Iterator<Object []> readFromCSV() {
        String[] data;
        List<Object []> testCases = new ArrayList<>();
        BufferedReader br = null;
        String line ;

        try  (InputStream input = Files.newInputStream(Path.of("src/main/resources/test-data/testdata.csv"))){
            br = new BufferedReader(new FileReader("src/main/resources/test-data/testdata.csv"));
            while ((line = br.readLine()) != null) {
                data = line.split(DELIMITER);
                testCases.add(data);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return testCases.iterator();
    }
}
