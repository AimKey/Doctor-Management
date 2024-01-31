package Model;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class DoctorsData {
    private String fileName = "doctors.dat";
    private DoctorHash doctorsDat = new DoctorHash();

    public DoctorHash getDoctorsDat() {
        return doctorsDat;
    }

    public void setDoctorsDat(DoctorHash doctorsDat) {
        this.doctorsDat = doctorsDat;
    }

    public DoctorsData() {
        this.loadFile();
    }

    public DoctorsData(String fileName) {
        this.fileName = fileName;
        this.loadFile();
    }

    public void loadFile() {
        try {
            if (!new File(fileName).exists())
                throw new Exception("File not found!");
            Scanner sc = new Scanner(new FileReader(fileName));
            while (sc.hasNextLine()) {
                String[] inputs = sc.nextLine().split(",");
                doctorsDat.put(inputs[0],
                        new Doctor(inputs[0], inputs[1], inputs[2], Integer.parseInt(inputs[3])));
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public void doSave(DoctorHash doctorHash) throws IOException {
        System.out.println("Saving at " + fileName);
        File f = new File(fileName);
        if (!f.exists()) {
            f.createNewFile();
        }
        try (PrintWriter printWriter = new PrintWriter(new File(fileName))) {
            for (Doctor s : doctorHash.values()) {
                printWriter.printf("%s,%s,%s,%d\n", s.getCode(), s.getName(), s.getSpecialization(), s.getAvailability());
            }
        } catch (Exception e) {
            System.out.println("\u001B[31m" + e.getMessage() + "\u001B[0m");
        }
    }
}
