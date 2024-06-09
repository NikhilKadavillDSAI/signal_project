package data_management;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import com.DataEvaluaters.BloodOxygenSaturationevaluater;
import com.DataEvaluaters.BloodPressureEvaluater;
import com.alerts.Alert;
import com.alerts.AlertGenerator;
import com.data_management.DataReader;
import com.data_management.DataReader1;
import com.data_management.DataStorage;
import com.data_management.Patient;
import com.data_management.PatientRecord;

import java.io.IOException;
import java.util.List;

class DataStorageTest {

    @Test
    void DataReader1GetRecords() {
        // TODO Perhaps you can implement a mock data reader to mock the test data?
        DataReader reader = new DataReader1("src\\main\\java\\com\\data_management\\GenData\\DiastolicPressure.txt");
        DataStorage storage = new DataStorage();
        try {
            reader.startreadData(storage);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        List<PatientRecord> records = storage.getRecords(41, 1717940439582L, 1717940499403L);
        assertEquals(2, records.size()); // Check if two records are retrieved
        assertEquals(70.0, records.get(0).getMeasurementValue()); // Validate first record
    }
    @Test
    void testAddAndGetRecords() {
        // TODO Perhaps you can implement a mock data reader to mock the test data?
        // DataReader reader
        DataStorage storage = new DataStorage();
        storage.addPatientData(1, 100.0, "WhiteBloodCells", 1714376789050L);
        storage.addPatientData(1, 200.0, "WhiteBloodCells", 1714376789051L);

        List<PatientRecord> records = storage.getRecords(1, 1714376789050L, 1714376789051L);
        assertEquals(2, records.size()); // Check if two records are retrieved
        assertEquals(100.0, records.get(0).getMeasurementValue()); // Validate first record
    }


    @Test
    void testSystollicPressurealert(){
        DataStorage storage = new DataStorage();
        storage.addPatientData(1, 120,"SystollicPressure", 1714376789050L);
        storage.addPatientData(1, 150,"SystollicPressure", 1714376789060L);
        storage.addPatientData(1, 110,"SystollicPressure", 1714376789080L);

        List<PatientRecord> records = storage.getRecords(1, 1714376789050L, 1714376789100L);
        assertEquals(records.size(), 3);

        Alert aAlert = BloodPressureEvaluater.evaluateBP(records, "SystollicPressure");
    }

    @Test
    void testDiastollicPressurealert(){
        DataStorage storage = new DataStorage();
        storage.addPatientData(1, 120,"DiastolicPressure", 1714376789050L);
        storage.addPatientData(1, 170,"DiastolicPressure", 1714376789060L);
        storage.addPatientData(1, 70,"DiastolicPressure", 1714376789080L);

        List<PatientRecord> records = storage.getRecords(1, 1714376789050L, 1714376789100L);
        assertEquals(records.size(), 3);

        Alert aAlert = BloodPressureEvaluater.evaluateBP(records, "DiastolicPressure");
    }

    @Test
    void testSystollicPressurealert2(){
        DataStorage storage = new DataStorage();
        storage.addPatientData(1, 110,"SystollicPressure", 1714376789050L);
        storage.addPatientData(1, 100,"SystollicPressure", 1714376789060L);
        storage.addPatientData(1, 80,"SystollicPressure", 1714376789080L);

        List<PatientRecord> records = storage.getRecords(1, 1714376789050L, 1714376789100L);
        assertEquals(records.size(), 3);

        Alert aAlert = BloodPressureEvaluater.evaluateBP(records, "SystollicPressure");

    }

    @Test
    void testDiastollicPressurealert2(){
        DataStorage storage = new DataStorage();
        storage.addPatientData(1, 70,"DiastolicPressure", 1714376789050L);
        storage.addPatientData(1, 90,"DiastolicPressure", 1714376789060L);
        storage.addPatientData(1, 110,"DiastolicPressure", 1714376789080L);

        List<PatientRecord> records = storage.getRecords(1, 1714376789050L, 1714376789100L);
        assertEquals(records.size(), 3);

        Alert aAlert = BloodPressureEvaluater.evaluateBP(records, "DiastolicPressure");

    }

    @Test
    void testBloodOxygenSaturation(){
        DataStorage storage = new DataStorage();
        storage.addPatientData(1, 90,"Saturation", 1714376789050L);
        
        List<PatientRecord> records = storage.getRecords(1,1714376789050L, 1714376789100L);

        Alert aAlert = BloodOxygenSaturationevaluater.evaluateBO(records);

    }

    @Test 
    void testBloodOxygenSaturation2(){
        DataStorage storage = new DataStorage();
        storage.addPatientData(1, 99,"Saturation", 1714376789050L);
        storage.addPatientData(1, 98,"Saturation", 1714376789055L);
        storage.addPatientData(1, 97,"Saturation", 1714376789060L);
        storage.addPatientData(1, 95,"Saturation", 1714376789065L);
        storage.addPatientData(1, 93,"Saturation", 1714376789070L);
        
        List<PatientRecord> records = storage.getRecords(1,1714376789050L, 1714376789100L);

        Alert aAlert = BloodOxygenSaturationevaluater.evaluateBO(records);

    }

    @Test
    void testAlertGeneratorandHypotensiveHypoxemia(){
        DataStorage storage = new DataStorage();
        storage.addPatientData(1, 120,"SystollicPressure", 1714376789050L);
        storage.addPatientData(1, 150,"SystollicPressure", 1714376789060L);
        storage.addPatientData(1, 110,"SystollicPressure", 1714376789080L);
        storage.addPatientData(1, 99,"Saturation", 1714376789050L);
        storage.addPatientData(1, 98,"Saturation", 1714376789055L);
        storage.addPatientData(1, 97,"Saturation", 1714376789060L);
        storage.addPatientData(1, 95,"Saturation", 1714376789065L);
        storage.addPatientData(1, 93,"Saturation", 1714376789070L);

        List<PatientRecord> records = storage.getRecords(1, 1714376789050L, 1714376789100L);
        AlertGenerator generator = new AlertGenerator(storage);

        generator.evaluateData(storage.getAllPatients().get(0));

    }
}
