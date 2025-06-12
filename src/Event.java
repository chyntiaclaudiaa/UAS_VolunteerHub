public class Event {
    private String judul;
    private String lokasi;
    private String tanggal;
    private String deskripsi;
    private boolean openDonasi;
    private String metodePembayaran;

    public Event(String judul, String lokasi, String tanggal, String deskripsi, boolean openDonasi, String metodePembayaran) {
        this.judul = judul;
        this.lokasi = lokasi;
        this.tanggal = tanggal;
        this.deskripsi = deskripsi;
        this.openDonasi = openDonasi;
        this.metodePembayaran = metodePembayaran;
    }

    public String getJudul() {
        return judul;
    }

    public String getLokasi() {
        return lokasi;
    }

    public String getTanggal() {
        return tanggal;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public boolean isOpenDonasi() {
        return openDonasi;
    }

    public String getMetodePembayaran() {
        return metodePembayaran;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public void setOpenDonasi(boolean openDonasi) {
        this.openDonasi = openDonasi;
    }

    public void setMetodePembayaran(String metodePembayaran) {
        this.metodePembayaran = metodePembayaran;
    }

    public void tampilkanDetail() {
        System.out.println("----------------------------------------------------------------------------------------");
        System.out.printf("| %-20s : %-60s |\n", "Judul", judul);
        System.out.printf("| %-20s : %-60s |\n", "Lokasi", lokasi);
        System.out.printf("| %-20s : %-60s |\n", "Tanggal", tanggal);
        System.out.printf("| %-20s : %-60s |\n", "Deskripsi", deskripsi);
        if (openDonasi) {
            System.out.printf("| %-20s : %-60s |\n", "Donasi", "Ya");
            System.out.printf("| %-20s : %-60s |\n", "Metode Pembayaran", metodePembayaran);
        } else {
            System.out.printf("| %-20s : %-60s |\n", "Donasi", "Tidak");
        }
        System.out.println("----------------------------------------------------------------------------------------");
    }
}
