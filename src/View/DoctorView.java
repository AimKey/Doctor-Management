package View;

import java.util.HashMap;

import Model.Doctor;

public class DoctorView {

    public void display(HashMap<String, Doctor> doctorHashMap) {
        if (doctorHashMap.isEmpty())
            System.out.println("Empty");
        else {
            System.out.printf("%-10s %-15s %-15s %-15s\n", "Code", "Name", "Specialization", "Availability");
            doctorHashMap.forEach((key, value) -> {
                System.out.println(value);
            });
        }
    }
}
