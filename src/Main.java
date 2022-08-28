import java.io.*;

public class Main {

    public static void main(String[] args) {

        Install install = new Install(new File("D:\\WorkPlace\\Projects\\Games\\temp\\temp.txt"));

        install.createDir("D:\\WorkPlace\\Projects\\Games\\src");
        install.createDir("D:\\WorkPlace\\Projects\\Games\\res");
        install.createDir("D:\\WorkPlace\\Projects\\Games\\saveGames");
        install.createDir("D:\\WorkPlace\\Projects\\Games\\temp");

        install.createDir("D:\\WorkPlace\\Projects\\Games\\src\\main");
        install.createDir("D:\\WorkPlace\\Projects\\Games\\src\\test");

        install.createFile("D:\\WorkPlace\\Projects\\Games\\src\\main\\Main.java");
        install.createFile("D:\\WorkPlace\\Projects\\Games\\src\\main\\Utils.java");

        install.createDir("D:\\WorkPlace\\Projects\\Games\\res\\drawables");
        install.createDir("D:\\WorkPlace\\Projects\\Games\\res\\vectors");
        install.createDir("D:\\WorkPlace\\Projects\\Games\\res\\icons");

        install.addLogFile();


        GameProgress gameProgress1 = new GameProgress(100, 3, 2, 15);
        GameProgress gameProgress2 = new GameProgress(80, 5, 7, 23);
        GameProgress gameProgress3 = new GameProgress(60, 8, 9, 46);

        Save save = new Save("D:\\WorkPlace\\Projects\\Games\\saveGames");
        save.saveGames("save1.dat", gameProgress1);
        save.saveGames("save2.dat", gameProgress2);
        save.saveGames("save3.dat", gameProgress3);

        save.zipFiles("D:\\WorkPlace\\Projects\\Games\\saveGames\\saves.zip");

        for (String pathFile :
                save.getListSaveGames()) {
            if (new File(pathFile).delete()) {
                System.out.println(pathFile + " удален");
            }
        }

        Download download = new Download("D:\\WorkPlace\\Projects\\Games\\saveGames\\saves.zip");

        download.openZipSave();

        for (String pathSaveFile : save.getListSaveGames()) {
            System.out.println(download.openProgress(pathSaveFile));
        }
    }

}