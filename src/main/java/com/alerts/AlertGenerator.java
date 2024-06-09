package com.alerts;

import java.util.List;

import com.DataEvaluaters.BloodOxygenSaturationevaluater;
import com.DataEvaluaters.BloodPressureEvaluater;
import com.data_management.DataStorage;
import com.data_management.Patient;
import com.data_management.PatientRecord;

/**
 * The {@code AlertGenerator} class is responsible for monitoring patient data
 * and generating alerts when certain predefined conditions are met. This class
 * relies on a {@link DataStorage} instance to access patient data and evaluate
 * it against specific health criteria.
 */
public class AlertGenerator {
    private DataStorage dataStorage;

    /**
     * Constructs an {@code AlertGenerator} with a specified {@code DataStorage}.
     * The {@code DataStorage} is used to retrieve patient data that this class
     * will monitor and evaluate.
     *
     * @param dataStorage the data storage system that provides access to patient
     *                    data
     */
    public AlertGenerator(DataStorage dataStorage) {
        this.dataStorage = dataStorage;
    }

    /**
     * Evaluates the specified patient's data to determine if any alert conditions
     * are met. If a condition is met, an alert is triggered via the
     * {@link #triggerAlert}
     * method. This method should define the specific conditions under which an
     * alert
     * will be triggered.
     *
     * @param patient the patient data to evaluate for alert conditions
     */
    public void evaluateData(Patient patient) {
        // Implementation goes here

        Long currTime = Long.parseLong("1717872982956"); // intialize the start time 
        //Long currTime = Long.parseLong(System.currentTimeMillis()+""); // get the current time from the system
        
        // get records from 10 minutes ago till current time
        List<PatientRecord> records = patient.getRecords(currTime - 10000, currTime);

        Alert Dbp = BloodPressureEvaluater.evaluateBP(records,"DiastollicPressure");
        boolean Dbptriggered = false;
        Alert Sbp = BloodPressureEvaluater.evaluateBP(records,"SystollicPressure");
        boolean Sbptriggered = false;

        Alert BOs = BloodOxygenSaturationevaluater.evaluateBO(records);
        boolean BOstriggered = false;
        

        if (Dbp!=null & !Dbptriggered){
            triggerAlert(BOs);
            Dbptriggered=true;
        }

        if (Sbp!=null & !Sbptriggered){
            if (BOs!=null & !BOstriggered){
                // trigger Hypotensive Hypoxemia alert
                triggerAlert(new Alert(patient.getPatientId()+"", "Hypotensive Hypoxemia Alert", currTime));
                BOstriggered=true;
            }
            triggerAlert(Sbp);
            Sbptriggered=true;
        }

        if (BOs!=null & !BOstriggered){
            triggerAlert(BOs);
            BOstriggered=true;
        }
    }

    /**
     * Triggers an alert for the monitoring system. This method can be extended to
     * notify medical staff, log the alert, or perform other actions. The method
     * currently assumes that the alert information is fully formed when passed as
     * an argument.
     *
     * @param alert the alert object containing details about the alert condition
     */
    private void triggerAlert(Alert alert) {
        // Implementation might involve logging the alert or notifying staff
        String alertString = "PatientID: "+alert.getPatientId()+" Timestamp: "+alert.getTimestamp()+" Label: "+alert.getCondition();

        System.out.println(alertString);

    }
}
