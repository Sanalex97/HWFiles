import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Install {
    private final File logFile;
    private final StringBuilder stringBuilderLog = new StringBuilder();

    public Install(File logFile) {
        this.logFile = logFile;
    }

    public void createDir(String path) {
        File file = new File(path);

        stringBuilderLog.append(file.mkdir() ? "Каталог " + file.getName() + " создан" + "\n" : "Каталог " + file.getName() + " не создан" + "\n");
    }

    public void createFile(String path) {
        File file = new File(path);

        try {
            if (file.createNewFile()) {
                stringBuilderLog.append("Файл " + file.getName() + " создан" + "\n");
            }
        } catch (IOException e) {
            stringBuilderLog.append("Файл " + file.getName() + " не создан, " + "\n");
        }
    }

    public void addLogFile() {
        try (FileWriter fileWriter = new FileWriter(logFile, true)) {
            fileWriter.write(String.valueOf(stringBuilderLog));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
