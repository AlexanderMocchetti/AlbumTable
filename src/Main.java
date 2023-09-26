public class Main {
    public static void main(String[] args) {
        Album album = new Album("aoinor", "lppoi", 2000, 0, 1.2f);
        FileManager fm = new FileManager("dati.dat");
        fm.recordAlbum(album);
        System.out.println(fm.readAlbums());
    }
}
