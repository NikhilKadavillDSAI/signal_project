package com.alerts.AlertFactories;

import com.alerts.Alert;

public class BloodOxygenAlertFactory extends AlertFactory {

    public Alert createAlert(String patientID, long timestamp){
        return createAlert(patientID, "Blood Oxygen Saturation", timestamp);
    }

    
    
}
