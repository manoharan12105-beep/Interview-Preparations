import java.util.*;

class Doctor {
    private List<List<String>> doctorList = new ArrayList<>();

    Doctor() {
        doctorList.add(Arrays.asList("1", "Alex", "fever", "10"));
        doctorList.add(Arrays.asList("2", "David", "fracture", "50"));
        doctorList.add(Arrays.asList("3", "Navatha", "ent", "35"));
        doctorList.add(Arrays.asList("4", "Keerthana", "organ related", "200"));
        doctorList.add(Arrays.asList("5", "John", "cancer", "350"));
    }

    public Long getDoctorId(String statement) {
        for (List<String> list : doctorList) {
            if (list.get(2).equalsIgnoreCase(statement)) {
                return Long.parseLong(list.get(0));
            }
        }
        return -1L;
    }

    public double getDoctorFee(Long doctorId) {
        for (List<String> list : doctorList) {
            if (Long.parseLong(list.get(0)) == doctorId) {
                return Double.parseDouble(list.get(3));
            }
        }
        return 0;
    }
}