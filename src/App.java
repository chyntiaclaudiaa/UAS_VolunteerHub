import java.util.ArrayList;
import java.util.Scanner;

public class App {
    private Scanner scanner = new Scanner(System.in);
    private ArrayList<Event> daftarKegiatan = new ArrayList<>();
    private ArrayList<Volunteer> daftarRelawan = new ArrayList<>();

    public static void main(String[] args) {
        new App().start();
    }

    public void start() {
        seedSampleEvents();
        while (true) {
            System.out.println("\n=== Welcome to VolunteerHub ===");
            System.out.println("1. Login sebagai Admin");
            System.out.println("2. Login sebagai Relawan");
            System.out.println("3. Daftar sebagai Relawan");
            System.out.println("4. Keluar");
            System.out.print("Pilih opsi: ");
            String pilihan = scanner.nextLine();

            switch (pilihan) {
                case "1":
                    loginAdmin();
                    break;
                case "2":
                    loginVolunteer();
                    break;
                case "3":
                    registerVolunteer();
                    break;
                case "4":
                    System.out.println("Terima kasih telah menggunakan VolunteerHub!");
                    return;
                default:
                    System.out.println("Pilihan tidak valid.");
            }
        }
    }

    private void seedSampleEvents() {
        daftarKegiatan.add(new Event("Bakti Sosial Panti Asuhan", "Jakarta", "2025-07-01", "Mengunjungi dan membantu anak-anak di panti asuhan.", true, "Transfer Bank"));
        daftarKegiatan.add(new Event("Pembersihan Pantai", "Bali", "2025-07-10", "Membersihkan sampah di pantai dan kampanye lingkungan.", false, ""));
    }

    private void loginAdmin() {
        System.out.print("Username: ");
        String username = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();

        if (username.equals("admin") && password.equals("admin123")) {
            Admin admin = new Admin(username, password, daftarKegiatan);
            admin.dashboard();
        } else {
            System.out.println("Login admin gagal. Coba lagi.");
        }
    }

    private void loginVolunteer() {
        System.out.print("Username: ");
        String username = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();

        for (Volunteer v : daftarRelawan) {
            if (v.getUsername().equals(username) && v.getPassword().equals(password)) {
                v.dashboard();
                return;
            }
        }

        System.out.println("Login gagal. Username atau password salah.");
    }

    private void registerVolunteer() {
        System.out.print("Buat username: ");
        String username = scanner.nextLine();

        for (Volunteer v : daftarRelawan) {
            if (v.getUsername().equals(username)) {
                System.out.println("❌ Username sudah digunakan. Coba yang lain.");
                return;
            }
        }

        System.out.print("Buat password: ");
        String password = scanner.nextLine();
        Volunteer newVolunteer = new Volunteer(username, password, daftarKegiatan);
        daftarRelawan.add(newVolunteer);
        System.out.println("✅ Pendaftaran berhasil! Silakan login.");
        loginVolunteer();
    }
}