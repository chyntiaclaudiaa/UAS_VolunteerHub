import java.util.ArrayList;
import java.util.Scanner;

public class Volunteer extends User {
    private ArrayList<Event> daftarKegiatan;
    private ArrayList<Event> riwayatPartisipasi = new ArrayList<>();

    public Volunteer(String username, String password, ArrayList<Event> daftarKegiatan) {
        super(username, password);
        this.daftarKegiatan = daftarKegiatan;
    }

    @Override
    public void dashboard() {
        Scanner scanner = new Scanner(System.in);
        boolean stay = true;

        while (stay) {
            System.out.println("\n[Dashboard Relawan]");
            System.out.println("1. Lihat Daftar Kegiatan");
            System.out.println("2. Lihat Riwayat Partisipasi");
            System.out.println("3. Logout");
            System.out.print("Pilih opsi: ");
            String pilihan = scanner.nextLine();

            switch (pilihan) {
                case "1":
                    if (daftarKegiatan.isEmpty()) {
                        System.out.println("Belum ada kegiatan yang tersedia.");
                    } else {
                        for (int i = 0; i < daftarKegiatan.size(); i++) {
                            System.out.println((i + 1) + ".");
                            daftarKegiatan.get(i).tampilkanDetail();
                        }
                        System.out.print("Pilih nomor kegiatan untuk mendaftar atau ketik 0 untuk batal: ");
                        int pilihanEvent = Integer.parseInt(scanner.nextLine());
                        if (pilihanEvent > 0 && pilihanEvent <= daftarKegiatan.size()) {
                            Event selectedEvent = daftarKegiatan.get(pilihanEvent - 1);
                            System.out.print("Nama lengkap: ");
                            String nama = scanner.nextLine();
                            System.out.print("Umur: ");
                            String umur = scanner.nextLine();
                            System.out.print("Alamat: ");
                            String alamat = scanner.nextLine();
                            System.out.print("Pekerjaan: ");
                            String pekerjaan = scanner.nextLine();
                            System.out.print("Kenapa ingin menjadi relawan di kegiatan ini? ");
                            String alasan = scanner.nextLine();
                            riwayatPartisipasi.add(selectedEvent);
                            System.out.println("✅ Berhasil mendaftar ke kegiatan: " + selectedEvent.getJudul());
                            if (selectedEvent.isOpenDonasi()) {
                                System.out.println("📢 Kegiatan ini menerima donasi. Silakan donasi melalui: " + selectedEvent.getMetodePembayaran());
                            }
                        } else if (pilihanEvent != 0) {
                            System.out.println("Pilihan tidak valid.");
                        }
                    }
                    break;
                case "2":
                    System.out.println("\n📘 Riwayat Partisipasi Anda:");
                    if (riwayatPartisipasi.isEmpty()) {
                        System.out.println("Belum ada kegiatan yang diikuti.");
                    } else {
                        for (Event e : riwayatPartisipasi) {
                            e.tampilkanDetail();
                        }
                    }
                    break;
                case "3":
                    System.out.println("Logout dari Relawan...");
                    stay = false;
                    break;
                default:
                    System.out.println("Pilihan tidak valid.");
            }
        }
    }
}