package Controller;

import java.io.IOException;

import Common.Library;
import Model.Doctor;
import Model.DoctorHash;
import Model.DoctorsData;
import View.DoctorView;
import View.Menu;

public class Controller extends Menu<String> {

    Library lib;
    DoctorHash doctors;
    DoctorView doctorView;
    DoctorsData getDoctors;

    public Controller() {
        super(new String[] { "Add doc", "Update doc", "Delete doc", "Search doc", "Exit" },
                "Doctor management");
        lib = new Library();
        doctorView = new DoctorView();
        try {
            getDoctors = new DoctorsData();
            doctors = (DoctorHash) getDoctors.getDoctorsDat();
            if (doctors.isEmpty())
                throw new Exception("Database is empty");
        } catch (Exception e) {
            lib.printErr(e.getMessage());
            System.exit(1);
        }
    }

    @Override
    public void execute(int n) {
        switch (n) {
            case 1:
                doAdd();
                break;
            case 2:
                doUpdate();
                break;
            case 3:
                doDelete();
                break;
            case 4:
                doSearch();
                break;
            case 5:
                try {
                    getDoctors.doSave(doctors);
                } catch (IOException e) {
                    lib.printErr(e.getMessage());
                }
                System.out.println("See you next time");
                System.exit(0);
                break;
        }
    }

    public void doAdd() {
        String code = lib.getString("Enter doctor code");
        String name = lib.getString("Enter name", "Wrong name format");
        String spec = lib.getString("Enter specialization", "Wrong specialization format");
        int avail = lib.getInt("Enter availability", "Number only!");
        try {
            doctors.add(new Doctor(code, name, spec, avail));
        } catch (Exception e) {
            lib.printErr(e.getMessage());
        }
        doctorView.display(doctors);
    }

    public void doUpdate() {
        try {
            String dCode = lib.getString("Enter code to update").toUpperCase();
            String newName = lib.getString("Enter new name", "Name only");
            String spec = lib.getString("Enter new specialization");
            int avail = lib.getInt("Enter new availability", "Number only");
            doctors.update(dCode, newName, spec, avail);
            System.out.println("Update completed!");
            doctorView.display(doctors);
        } catch (Exception e) {
            lib.printErr(e.getMessage());
        }

    }

    public void doDelete() {
        try {
            String code = lib.getString("Enter code");
            doctors.delete(code);
            System.out.println("Delete completed!");
            doctorView.display(doctors);
        } catch (Exception e) {
            lib.printErr(e.getMessage());
        }
    }

    public void doSearch() {
        Menu<String> searchMenu = new Menu<>(new String[] { "By code", "By name" }, "Search menu") {
            @Override
            public void execute(int n) {
                switch (n) {
                    case 1:
                        String code = lib.getString("Enter code to search").toUpperCase();
                        doctorView.display(doctors.search(p -> p.getCode().contains(code)));
                        break;
                    case 2:
                        String name = lib.getString("Enter name");
                        doctorView.display(doctors.search(p -> p.getName().contains(name)));
                        break;
                }
            }

        };
        searchMenu.run();
    }
}
