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

                    String inputDonasi;
                    while (true) {
                        System.out.print("Apakah kegiatan ini menerima donasi? (y/n): ");
                        inputDonasi = scanner.nextLine().toLowerCase();
                        if (inputDonasi.equals("y") || inputDonasi.equals("n")) {
                            break;
                        } else {
                            System.out.println("❌ Input tidak valid. Masukkan 'y' atau 'n'.");
                        }
                    }

                    boolean openDonasi = inputDonasi.equals("y");
                    String metode = "";
                    if (openDonasi) {
                        System.out.print("Metode pembayaran donasi: ");
                        metode = scanner.nextLine();
                    }

                    daftarKegiatan.add(new Event(judul, lokasi, tanggal, deskripsi, openDonasi, metode));
                    System.out.println("✅ Kegiatan berhasil ditambahkan!");
                    break;

                case "2":
                    System.out.println("\n📋 Daftar Kegiatan:");
                    if (daftarKegiatan.isEmpty()) {
                        System.out.println("Belum ada kegiatan.");
                    } else {
                        for (int i = 0; i < daftarKegiatan.size(); i++) {
                            System.out.println((i + 1) + ".");
                            daftarKegiatan.get(i).tampilkanDetail();
                        }
                    }
                    System.out.print("Tekan ENTER untuk kembali...");
                    scanner.nextLine();
                    break;

                case "3":
                    if (daftarKegiatan.isEmpty()) {
                        System.out.println("Belum ada kegiatan untuk diedit.");
                        break;
                    }
                    System.out.print("Masukkan nomor kegiatan yang ingin diedit: ");
                    int idxEdit = Integer.parseInt(scanner.nextLine()) - 1;
                    if (idxEdit >= 0 && idxEdit < daftarKegiatan.size()) {
                        Event e = daftarKegiatan.get(idxEdit);
                        boolean editing = true;
                        while (editing) {
                            System.out.println("\n🔧 Edit Kegiatan: " + e.getJudul());
                            System.out.printf("1. %-25s : %s\n", "Judul", e.getJudul());
                            System.out.printf("2. %-25s : %s\n", "Lokasi", e.getLokasi());
                            System.out.printf("3. %-25s : %s\n", "Tanggal", e.getTanggal());
                            System.out.printf("4. %-25s : %s\n", "Deskripsi", e.getDeskripsi());
                            System.out.printf("5. %-25s : %s\n", "Status Donasi", e.isOpenDonasi() ? "Ya" : "Tidak");
                            System.out.printf("6. %-25s : %s\n", "Metode Pembayaran", e.isOpenDonasi() ? e.getMetodePembayaran() : "-");
                            System.out.println("7. Selesai");
                            System.out.print("Pilih bagian yang ingin diedit (1-7): ");
                            String editChoice = scanner.nextLine();

                            switch (editChoice) {
                                case "1":
                                    System.out.print("Judul baru: ");
                                    e.setJudul(scanner.nextLine());
                                    break;
                                case "2":
                                    System.out.print("Lokasi baru: ");
                                    e.setLokasi(scanner.nextLine());
                                    break;
                                case "3":
                                    System.out.print("Tanggal baru: ");
                                    e.setTanggal(scanner.nextLine());
                                    break;
                                case "4":
                                    System.out.print("Deskripsi baru: ");
                                    e.setDeskripsi(scanner.nextLine());
                                    break;
                                case "5":
                                    while (true) {
                                        System.out.print("Apakah menerima donasi? (y/n): ");
                                        String input = scanner.nextLine().toLowerCase();
                                        if (input.equals("y") || input.equals("n")) {
                                            e.setOpenDonasi(input.equals("y"));
                                            if (e.isOpenDonasi()) {
                                                System.out.print("Metode pembayaran: ");
                                                e.setMetodePembayaran(scanner.nextLine());
                                            } else {
                                                e.setMetodePembayaran("");
                                            }
                                            break;
                                        } else {
                                            System.out.println("❌ Input tidak valid. Masukkan 'y' atau 'n'.");
                                        }
                                    }
                                    break;
                                case "6":
                                    if (e.isOpenDonasi()) {
                                        System.out.print("Metode pembayaran baru: ");
                                        e.setMetodePembayaran(scanner.nextLine());
                                    } else {
                                        System.out.println("❌ Kegiatan ini tidak menerima donasi.");
                                    }
                                    break;
                                case "7":
                                    editing = false;
                                    System.out.println("✅ Selesai mengedit kegiatan.");
                                    break;
                                default:
                                    System.out.println("Pilihan tidak valid.");
                            }
                        }
                    } else {
                        System.out.println("❌ Nomor kegiatan tidak ditemukan.");
                    }
                    break;

                case "4":
                    if (daftarKegiatan.isEmpty()) {
                        System.out.println("Belum ada kegiatan untuk dihapus.");
                        break;
                    }
                    System.out.print("Masukkan nomor kegiatan yang ingin dihapus: ");
                    int idxDel = Integer.parseInt(scanner.nextLine()) - 1;
                    if (idxDel >= 0 && idxDel < daftarKegiatan.size()) {
                        daftarKegiatan.remove(idxDel);
                        System.out.println("✅ Kegiatan berhasil dihapus.");
                    } else {
                        System.out.println("❌ Nomor kegiatan tidak ditemukan.");
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
