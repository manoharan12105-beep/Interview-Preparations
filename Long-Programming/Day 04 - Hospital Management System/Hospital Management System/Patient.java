import java.time.LocalDateTime;
import java.util.Scanner;

class Patient {
    private Long id;
    private String name;
    private String problemStatement;
    private Long doctorId;
    private Long roomId;
    private LocalDateTime admitTime;
    private LocalDateTime dischargeTime;
    private static Scanner scanner = new Scanner(System.in);

    public boolean addPatient(Long id, Doctor doctor, Room room) {
        this.id = id;

        System.out.print("Enter Name: ");
        this.name = scanner.nextLine();

        System.out.print("Enter Problem (fever / fracture / ent / cancer / organ related): ");
        this.problemStatement = scanner.nextLine().toLowerCase();

        this.doctorId = doctor.getDoctorId(problemStatement);
        this.roomId = room.assignRoom(problemStatement);

        if (doctorId == -1 || roomId == -1) {
            System.out.println("Doctor or Room not available");
            return false;
        }

        this.admitTime = LocalDateTime.now();
        return true;
    }

    public void discharge() {
        this.dischargeTime = LocalDateTime.now();
    }

    public Long getDoctorId() {
        return doctorId;
    }

    public Long getRoomId() {
        return roomId;
    }

    public LocalDateTime getAdmitTime() {
        return admitTime;
    }

    public LocalDateTime getDischargeTime() {
        return dischargeTime;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Problem: " + problemStatement + ", DoctorID: " + doctorId + ", RoomID: " + roomId + ", AdmitTime: " + admitTime;
    }
}