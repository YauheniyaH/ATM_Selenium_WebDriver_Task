package pageobject_model.service;

import pageobject_model.model.ComputeEngineEntity;

public class ComputeEngineEntityCreator {

    public static final String TESTDATA_NUMBER_OF_INSTANCES = "testdata.number.of.instances";
    public static final String TESTDATA_OS = "testdata.os";

    public static final String TESTDATA_MACHINE_TYPE = "testdata.machine.type";

    public static final String TESTDATA_GPU_MODEL = "testdata.gpu.model";

    public static final String TESTDATA_GPU_NUMBER = "testdata.gpu.number";

    public static ComputeEngineEntity withParametersFromProperty() {
        return new ComputeEngineEntity(TestDataReader.getTestData(TESTDATA_NUMBER_OF_INSTANCES),
                TestDataReader.getTestData(TESTDATA_OS),
                TestDataReader.getTestData(TESTDATA_MACHINE_TYPE),
                TestDataReader.getTestData(TESTDATA_GPU_MODEL),
                TestDataReader.getTestData(TESTDATA_GPU_NUMBER));
    }
}
