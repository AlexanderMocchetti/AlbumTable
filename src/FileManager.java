import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

public class FileManager {
    private final File file;
    public FileManager(String filePath) {
        file = new File(filePath);
    }
    public void recordAlbum(Album album) {
        try {
            RandomAccessFile raf = new RandomAccessFile(file, "rw");
            raf.seek(raf.length());
            raf.writeChars(fillString(album.getTitle()));
            raf.writeChars(fillString(album.getArtist()));
            raf.writeInt(album.getYear());
            raf.writeInt(album.getNumberOfTracks());
            raf.writeFloat(album.getCost());
            raf.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private String fillString(String string) {
        if (string.length() == Album.MAX_STRING_FIELD_LENGTH)
            return string;
        StringBuilder stringBuilder = new StringBuilder(Album.MAX_STRING_FIELD_LENGTH);
        stringBuilder.append(string);
        for (int i = string.length(); i < Album.MAX_STRING_FIELD_LENGTH; i++) {
            stringBuilder.append('\0');
        }
        return stringBuilder.toString();
    }

    public List<Album> readAlbums() {
        try {
            RandomAccessFile raf = new RandomAccessFile(file, "r");
            int numberOfRecords = (int) (raf.length() / Album.MAX_RECORD_LENGTH);
            ArrayList<Album> albums = new ArrayList<>(numberOfRecords);
            for (int recordIndex = 0; recordIndex < numberOfRecords; recordIndex++) {
                char ch;
                StringBuilder string = new StringBuilder(Album.MAX_STRING_FIELD_LENGTH);
                Album album = new Album();
                for (int i = 0; i < Album.MAX_STRING_FIELD_LENGTH; i++) {
                    ch = raf.readChar();
                    if (ch != '\0')
                        string.append(ch);
                }
                album.setTitle(string.toString());
                string.setLength(0);
                for (int i = 0; i < Album.MAX_STRING_FIELD_LENGTH; i++) {
                    ch = raf.readChar();
                    if (ch != '\0')
                        string.append(ch);
                }
                album.setArtist(string.toString());
                album.setYear(raf.readInt());
                album.setNumberOfTracks(raf.readInt());
                album.setCost(raf.readFloat());
                albums.add(album);
            }
            raf.close();
            return albums;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    public Album getAlbumWithMostTracks() {
        List<Album> albums = readAlbums();
        if (albums == null)
            return null;
        Album albumWithMostTracks = albums.get(0);
        for (Album album : albums) {
            if (album.getNumberOfTracks() > albumWithMostTracks.getNumberOfTracks())
                albumWithMostTracks = album;
        }
        return albumWithMostTracks;
    }
    private void clearFile() {
        try {
            RandomAccessFile raf = new RandomAccessFile(file, "rw");
            raf.setLength(0);
            raf.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void reduceCostBy15Percent() {
        List<Album> albums = readAlbums();
        clearFile();
        for (Album album : albums) {
            album.setCost(album.getCost() * 0.85F);
            recordAlbum(album);
        }
    }
}
