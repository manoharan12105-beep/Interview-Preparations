import java.time.Duration;
import java.time.LocalDateTime;

class Billing {
    private Long billId;
    private double doctorFeePerHour;
    private double roomFeePerHour;
    private double totalBillAmount;

    public Billing(Long billId) {
        this.billId = billId;
    }

    public void billGenerator(Patient patient, Doctor doctor, Room room) {

        LocalDateTime admit = patient.getAdmitTime();
        LocalDateTime discharge = patient.getDischargeTime();

        long hours = Duration.between(admit, discharge).toHours();
        if (hours == 0) hours = 1;

        doctorFeePerHour = doctor.getDoctorFee(patient.getDoctorId());
        roomFeePerHour = room.getRoomFee(patient.getRoomId());

        totalBillAmount = hours * (doctorFeePerHour + roomFeePerHour);
    }

    public void viewBillDetails() {
        System.out.println("\n===== BILL =====");
        System.out.println("Bill ID: " + billId);
        System.out.println("Doctor Fee/hr: " + doctorFeePerHour);
        System.out.println("Room Fee/hr: " + roomFeePerHour);
        System.out.println("Total Amount: $" + totalBillAmount);
    }
}