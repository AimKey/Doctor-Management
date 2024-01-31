package Model;

/**
 *
 * @author phamm
 */
public class Doctor implements Comparable<Doctor> {
    private String code, name, specialization;
    private int availability;

    public Doctor() {
    }

    public Doctor(String code, String name, String specialization, int availability) {
        this.code = code;
        this.name = name;
        this.specialization = specialization;
        this.availability = availability;
    }

    public Doctor(String name, String specialization, int availability) {
        this.name = name;
        this.specialization = specialization;
        this.availability = availability;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public int getAvailability() {
        return availability;
    }

    public void setAvailability(int availability) {
        this.availability = availability;
    }

    @Override
    public String toString() {
        String s = String.format("%-10s %-15s %-15s %-15d", code, name, specialization, availability);
        return s;
    }

    @Override
    public int compareTo(Doctor o) {
        return this.availability - o.availability;
    }

}
