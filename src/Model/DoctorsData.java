package Model;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
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
                String[] inputs = sc.nextLine().split("\\s+");
                doctorsDat.put(inputs[0] + " " + inputs[1],
                        new Doctor(inputs[0] + " " + inputs[1], inputs[2], inputs[3], Integer.parseInt(inputs[4])));
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
            // printWriter
            for (Doctor s : doctorHash.values()) {
                printWriter.println(s);
            }
        } catch (Exception e) {
            System.out.println("\u001B[31m" + e.getMessage() + "\u001B[0m");
        }
    }
}
