import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Save {
    private final List<String> listSaveGames = new ArrayList<>();
    private final String pathSave;

    public Save(String pathSave) {
        this.pathSave = pathSave;
    }

    public List<String> getListSaveGames() {
        return listSaveGames;
    }

    public void saveGames(String nameFileSave, GameProgress gameProgress) {
        try (FileOutputStream fos = new FileOutputStream(pathSave + "\\" + nameFileSave);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {

            oos.writeObject(gameProgress);

            listSaveGames.add(pathSave + "\\" + nameFileSave);

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void zipFiles(String path) {
        try (ZipOutputStream zot = new ZipOutputStream(new FileOutputStream(path))) {
            for (String fileSaveGames : listSaveGames) {
                try (FileInputStream fis = new FileInputStream(fileSaveGames)) {
                    ZipEntry entry = new ZipEntry(fileSaveGames);
                    zot.putNextEntry(entry);
                    byte[] buffer = new byte[fis.available()];
                    fis.read(buffer);

                    zot.write(buffer);

                    zot.closeEntry();


                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }

            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
