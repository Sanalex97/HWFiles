import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class Download {
    private final String pathZipSave;

    public Download(String pathZipSave) {
        this.pathZipSave = pathZipSave;
    }

    public void openZipSave() {
        try (ZipInputStream zis = new ZipInputStream(new FileInputStream(pathZipSave))) {

            ZipEntry zipEntry;
            String name;

            while ((zipEntry = zis.getNextEntry()) != null) {

                name = zipEntry.getName();

                FileOutputStream fot = new FileOutputStream(name);

                for (int c = zis.read(); c != -1; c = zis.read()) {
                    fot.write(c);
                }

                fot.flush();
                zis.closeEntry();
                fot.close();
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public GameProgress openProgress(String nameFileSave) {
        GameProgress gameProgress = null;
        try (FileInputStream fis = new FileInputStream(nameFileSave);
             ObjectInputStream ois = new ObjectInputStream(fis)) {

            gameProgress = (GameProgress) ois.readObject();

        } catch (IOException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
        return gameProgress;
    }
}
