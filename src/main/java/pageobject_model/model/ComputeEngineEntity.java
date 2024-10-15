package pageobject_model.model;

import java.util.Objects;

public class ComputeEngineEntity {

    private String numberOfInstances;

    private String os;

    private String gpuModel;

    private String machineType;

    private String gpuNumber;

    public ComputeEngineEntity(String numberOfInstances, String os, String machineType, String gpuModel, String gpuNumber) {
        this.numberOfInstances = numberOfInstances;
        this.os = os;
        this.machineType = machineType;
        this.gpuModel = gpuModel;
        this.gpuNumber=gpuNumber;
    }

    public String getGpuNumber() {
        return gpuNumber;
    }

    public void setGpuNumber(String gpuNumber) {
        this.gpuNumber = gpuNumber;
    }

    public String getNumberOfInstances() {
        return numberOfInstances;
    }

    public void setNumberOfInstances(String numberOfInstances) {
        this.numberOfInstances = numberOfInstances;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public String getMachineType() {
        return machineType;
    }

    public void setMachineType(String machineType) {
        this.machineType = machineType;
    }

    public String getGpuModel() {
        return gpuModel;
    }

    public void setGpuModel(String gpuModel) {
        this.gpuModel = gpuModel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ComputeEngineEntity that)) return false;
        return Objects.equals(numberOfInstances, that.numberOfInstances) && Objects.equals(os, that.os) && Objects.equals(gpuModel, that.gpuModel) && Objects.equals(machineType, that.machineType) && Objects.equals(gpuNumber, that.gpuNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numberOfInstances, os, gpuModel, machineType, gpuNumber);
    }

    @Override
    public String toString() {
        return "ComputeEngineEntity{" +
                "numberOfInstances='" + numberOfInstances + '\'' +
                ", os='" + os + '\'' +
                ", gpuModel='" + gpuModel + '\'' +
                ", machineType='" + machineType + '\'' +
                ", gpuNumber='" + gpuNumber + '\'' +
                '}';
    }
}
