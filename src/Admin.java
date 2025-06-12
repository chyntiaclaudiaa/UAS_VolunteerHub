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
                        while (true) {
                            System.out.print("Metode pembayaran (Bank/E-Wallet): ");
                            String metodeInput = scanner.nextLine().toLowerCase();
                            if (metodeInput.equals("bank")) {
                                System.out.print("Nama bank (misal: BCA, Mandiri): ");
                                String namaBank = scanner.nextLine();
                                System.out.print("Nomor rekening: ");
                                String norek = scanner.nextLine();
                                System.out.print("Atas nama siapa: ");
                                String atasNama = scanner.nextLine();
                                metode = "Bank (" + namaBank + ") - " + norek + " a.n. " + atasNama;
                                break;
                            } else if (metodeInput.equals("e-wallet") || metodeInput.equals("ewallet")) {
                                System.out.print("Nomor HP untuk e-wallet: ");
                                String noHp = scanner.nextLine();
                                if (noHp.matches("\\d{11,12}")) {
                                    System.out.print("Atas nama siapa: ");
                                    String atasNama = scanner.nextLine();
                                    metode = "E-Wallet - " + noHp + " a.n. " + atasNama;
                                    break;
                                } else {
                                    System.out.println("❌ Nomor HP tidak valid. Harus 11–12 digit angka.");
                                }
                            } else {
                                System.out.println("❌ Metode tidak dikenali. Masukkan Bank atau E-Wallet.");
                            }
                        }
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

                    System.out.println("\n📋 Daftar Kegiatan:");
                    for (int i = 0; i < daftarKegiatan.size(); i++) {
                        System.out.printf("Kegiatan #%d:\n", i + 1);
                        daftarKegiatan.get(i).tampilkanDetail();
                    }

                    System.out.print("Masukkan nomor kegiatan yang ingin diedit: ");
                    int idxEdit = Integer.parseInt(scanner.nextLine()) - 1;

                    if (idxEdit >= 0 && idxEdit < daftarKegiatan.size()) {
                        Event e = daftarKegiatan.get(idxEdit);
                        System.out.println("Kamu bisa mengedit lebih dari satu bagian. Pilih 7 jika sudah selesai.");
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
                                            boolean menerimaDonasi = input.equals("y");

                                            // Simpan status donasi sebelumnya
                                            boolean sebelumnya = e.isOpenDonasi();
                                            e.setOpenDonasi(menerimaDonasi);

                                            // Jika sebelumnya tidak menerima, sekarang iya → minta isi metode
                                            if (!sebelumnya && menerimaDonasi) {
                                                while (true) {
                                                    System.out.print("Metode pembayaran (Bank/E-Wallet): ");
                                                    String metodeInput = scanner.nextLine().toLowerCase();
                                                    if (metodeInput.equals("bank")) {
                                                        System.out.print("Nama bank (misal: BCA, Mandiri): ");
                                                        String namaBank = scanner.nextLine();
                                                        System.out.print("Nomor rekening: ");
                                                        String norek = scanner.nextLine();
                                                        System.out.print("Atas nama siapa: ");
                                                        String atasNama = scanner.nextLine();
                                                        e.setMetodePembayaran("Bank (" + namaBank + ") - " + norek + " a.n. " + atasNama);
                                                        break;
                                                    } else if (metodeInput.equals("e-wallet") || metodeInput.equals("ewallet")) {
                                                        System.out.print("Nomor HP untuk e-wallet: ");
                                                        String noHp = scanner.nextLine();
                                                        if (noHp.matches("\\d{11,12}")) {
                                                            System.out.print("Atas nama siapa: ");
                                                            String atasNama = scanner.nextLine();
                                                            e.setMetodePembayaran("E-Wallet - " + noHp + " a.n. " + atasNama);
                                                            break;
                                                        } else {
                                                            System.out.println("❌ Nomor HP tidak valid. Harus 11–12 digit angka.");
                                                        }
                                                    } else {
                                                        System.out.println("❌ Metode tidak dikenali. Masukkan Bank atau E-Wallet.");
                                                    }
                                                }
                                            }

                                            // Jika diubah ke tidak menerima → reset metode
                                            if (!menerimaDonasi) {
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
                                        while (true) {
                                            System.out.print("Metode pembayaran baru (Bank/E-Wallet): ");
                                            String metodeInput = scanner.nextLine().toLowerCase();
                                            if (metodeInput.equals("bank")) {
                                                System.out.print("Nama bank (misal: BCA, Mandiri): ");
                                                String namaBank = scanner.nextLine();
                                                System.out.print("Nomor rekening: ");
                                                String norek = scanner.nextLine();
                                                System.out.print("Atas nama siapa: ");
                                                String atasNama = scanner.nextLine();
                                                e.setMetodePembayaran("Bank (" + namaBank + ") - " + norek + " a.n. " + atasNama);
                                                break;
                                            } else if (metodeInput.equals("e-wallet") || metodeInput.equals("ewallet")) {
                                                System.out.print("Nomor HP untuk e-wallet: ");
                                                String noHp = scanner.nextLine();
                                                if (noHp.matches("\\d{11,12}")) {
                                                    System.out.print("Atas nama siapa: ");
                                                    String atasNama = scanner.nextLine();
                                                    e.setMetodePembayaran("E-Wallet - " + noHp + " a.n. " + atasNama);
                                                    break;
                                                } else {
                                                    System.out.println("❌ Nomor HP tidak valid. Harus 11–12 digit angka.");
                                                }
                                            } else {
                                                System.out.println("❌ Metode tidak dikenali. Masukkan Bank atau E-Wallet.");
                                            }
                                        }
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

                    System.out.println("\n📋 Daftar Kegiatan:");
                    for (int i = 0; i < daftarKegiatan.size(); i++) {
                        System.out.printf("Kegiatan #%d:\n", i + 1);
                        daftarKegiatan.get(i).tampilkanDetail();
                    }

                    System.out.print("Masukkan nomor kegiatan yang ingin dihapus: ");
                    int idxDel = Integer.parseInt(scanner.nextLine()) - 1;

                    if (idxDel >= 0 && idxDel < daftarKegiatan.size()) {
                        System.out.printf("Yakin ingin menghapus kegiatan \"%s\"? (y/n): ", daftarKegiatan.get(idxDel).getJudul());
                        String konfirmasi = scanner.nextLine();
                        if (konfirmasi.equalsIgnoreCase("y")) {
                            daftarKegiatan.remove(idxDel);
                            System.out.println("✅ Kegiatan berhasil dihapus.");
                        } else {
                            System.out.println("❎ Penghapusan dibatalkan.");
                        }
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
