import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        ArrayList<Patient> patientList = new ArrayList<>();
        Doctor doctor = new Doctor();
        Room room = new Room();

        int patientId = 1;

        while (true) {
            System.out.println("\n==== MENU ====");
            System.out.println("1) Add Patient");
            System.out.println("2) View Patients");
            System.out.println("3) Discharge Patient");
            System.out.print("Enter choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
							case 1: {
									Patient patient = new Patient();
									if (patient.addPatient((long) patientId, doctor, room)) {
											patientList.add(patient);
											patientId++;
											System.out.println("Patient added successfully");
									} 

									else
										System.out.println("Failed to add patient");
									
									break;
							}

							case 2: {
									for (Patient p : patientList) {
											System.out.println(p);
									}
									break;
							}

							case 3: {
									System.out.print("Enter Patient ID: ");
									int id = scanner.nextInt();
									scanner.nextLine();

									if (id <= 0 || id > patientList.size()) {
											System.out.println("Invalid ID");
											break;
									}

									Patient patient = patientList.get(id - 1);

									// discharge
									patient.discharge();

									// billing
									Billing bill = new Billing((long) id);
									bill.billGenerator(patient, doctor, room);
									bill.viewBillDetails();

									// release room
									room.releaseRoom(patient.getRoomId());

									break;
							}

							default:
									System.out.println("Invalid choice");
            }
        }
    }
}