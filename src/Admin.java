import java.util.ArrayList;
import java.util.Scanner;

public class Admin extends User {
    private ArrayList<Event> daftarKegiatan;

    public Admin(String username, String password, ArrayList<Event> daftarKegiatan) {
        super(username, password);
        this.daftarKegiatan = daftarKegiatan;
    }

    @Override
    public void dashboard() {
        Scanner scanner = new Scanner(System.in);
        boolean stay = true;

        while (stay) {
            System.out.println("\n[Dashboard Admin]");
            System.out.println("1. Tambah Kegiatan");
            System.out.println("2. Lihat Daftar Kegiatan");
            System.out.println("3. Edit Kegiatan");
            System.out.println("4. Hapus Kegiatan");
            System.out.println("5. Logout");
            System.out.print("Pilih opsi: ");
            String pilihan = scanner.nextLine();

            switch (pilihan) {
                case "1":
                    System.out.print("Judul kegiatan: ");
                    String judul = scanner.nextLine();
                    System.out.print("Lokasi kegiatan: ");
                    String lokasi = scanner.nextLine();
                    System.out.print("Tanggal kegiatan: ");
                    String tanggal = scanner.nextLine();
                    System.out.print("Deskripsi kegiatan: ");
                    String deskripsi = scanner.nextLine();
                    System.out.print("Apakah kegiatan ini menerima donasi? (ya/tidak): ");
                    boolean openDonasi = scanner.nextLine().equalsIgnoreCase("ya");
                    String metode = "";
                    if (openDonasi) {
                        System.out.print("Metode pembayaran donasi: ");
                        metode = scanner.nextLine();
                    }
                    daftarKegiatan.add(new Event(judul, lokasi, tanggal, deskripsi, openDonasi, metode));
                    System.out.println("✅ Kegiatan berhasil ditambahkan!");
                    break;
                case "2":
                    System.out.println("📋 Daftar Kegiatan:");
                    if (daftarKegiatan.isEmpty()) {
                        System.out.println("Belum ada kegiatan.");
                    } else {
                        for (int i = 0; i < daftarKegiatan.size(); i++) {
                            System.out.println((i + 1) + ".");
                            daftarKegiatan.get(i).tampilkanDetail();
                        }
                    }
                    break;
                case "3":
                    System.out.print("Masukkan nomor kegiatan yang ingin diedit: ");
                    int idxEdit = Integer.parseInt(scanner.nextLine()) - 1;
                    if (idxEdit >= 0 && idxEdit < daftarKegiatan.size()) {
                        Event e = daftarKegiatan.get(idxEdit);
                        System.out.print("Judul baru: ");
                        e.setJudul(scanner.nextLine());
                        System.out.print("Lokasi baru: ");
                        e.setLokasi(scanner.nextLine());
                        System.out.print("Tanggal baru: ");
                        e.setTanggal(scanner.nextLine());
                        System.out.print("Deskripsi baru: ");
                        e.setDeskripsi(scanner.nextLine());
                        System.out.print("Apakah menerima donasi? (ya/tidak): ");
                        e.setOpenDonasi(scanner.nextLine().equalsIgnoreCase("ya"));
                        if (e.isOpenDonasi()) {
                            System.out.print("Metode pembayaran: ");
                            e.setMetodePembayaran(scanner.nextLine());
                        }
                        System.out.println("✅ Kegiatan berhasil diperbarui.");
                    }
                    break;
                case "4":
                    System.out.print("Masukkan nomor kegiatan yang ingin dihapus: ");
                    int idxDel = Integer.parseInt(scanner.nextLine()) - 1;
                    if (idxDel >= 0 && idxDel < daftarKegiatan.size()) {
                        daftarKegiatan.remove(idxDel);
                        System.out.println("✅ Kegiatan berhasil dihapus.");
                    }
                    break;
                case "5":
                    System.out.println("Logout dari Admin...");
                    stay = false;
                    break;
                default:
                    System.out.println("Pilihan tidak valid.");
            }
        }
    }
}
