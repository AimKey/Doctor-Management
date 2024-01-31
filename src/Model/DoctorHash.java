package Model;

import java.util.HashMap;
import java.util.function.Predicate;

/**
 *
 * @author phamm
 */
public class DoctorHash extends HashMap<String, Doctor> {

    public DoctorHash() {
    }

    @Override
    public Doctor put(String key, Doctor value) {
        return super.put(key, value);
    }

    public void add(Doctor n) throws Exception {
        if (this.containsKey(n.getCode()))
            throw new Exception("Doctor code " + n.getCode() + " is duplicated!");
        if ("".equals(n.getCode()) || "".equals(n.getName()) || "".equals(n.getSpecialization()) || n.getAvailability() == -1)
            throw new Exception("Data must not empty");
        put(n.getCode(), n);
    }

    public void update(String code, String newName, String newSpec, int newAvail) throws Exception {
        Doctor temp = this.get(code);
        if (temp == null)
            throw new Exception("Code not exist");
        if ("".equals(newName))
            newName = temp.getName();
        if ("".equals(newSpec))
            newSpec = temp.getSpecialization();
        if (newAvail == -1)
            newAvail = temp.getAvailability();
        this.replace(code, new Doctor(code, newName, newSpec, newAvail));
    }

    public void delete(String code) throws Exception {
        Doctor test = this.remove(code);
        if (test == null)
            throw new Exception("Code not exist");
        else {
            System.out.println("Delete completed!");
        }
    }

    public HashMap<String, Doctor> search(Predicate<Doctor> p) {
        HashMap<String, Doctor> resultHashMap = new HashMap<>();
        this.forEach((key, value) -> {
            if (p.test(value))
                resultHashMap.put(key, value);
        });
        return resultHashMap;
    }

}
