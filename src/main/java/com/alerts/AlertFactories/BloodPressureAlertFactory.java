package com.alerts.AlertFactories;

import com.alerts.Alert;

public class BloodPressureAlertFactory extends AlertFactory{

    public Alert createAlert(String patientID, long timestamp){
        return createAlert(patientID, "Blood Pressure", timestamp);
    }
}
