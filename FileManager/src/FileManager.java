import java.io.*;
import java.util.Scanner;
import java.util.zip.*;
import java.nio.file.*;

public class FileManager {

    //архивирование файла
    public static void zipFile(String path) throws IOException{
        File fileToZip = new File(path);
        String nameFile = fileToZip.getName();
        int pos = nameFile.lastIndexOf(".");
        String nameWithoutExtension = pos > 0 ? nameFile.substring(0, pos) : nameFile;
        FileOutputStream fileOutput = new FileOutputStream(nameWithoutExtension + ".zip");
        ZipOutputStream zipOutput = new ZipOutputStream(fileOutput);
        FileInputStream fileInput = new FileInputStream(fileToZip);
        ZipEntry zipEntry = new ZipEntry(nameFile);
        zipOutput.putNextEntry(zipEntry);
        byte[] bytes = new byte[1024];
        int length;
        while((length = fileInput.read(bytes)) >= 0) {
            zipOutput.write(bytes, 0, length);
        }
        zipOutput.close();
        fileInput.close();
        fileOutput.close();
        System.out.println("Создан архив " + nameWithoutExtension + ".zip");
    }
    //копия файла в той же директории
    public static void copyFile(String path){
        Path source = Paths.get(path);
        int pos = path.lastIndexOf(".");
        String nameAndPath = pos > 0 ? path.substring(0, pos) : path;
        String extension = pos > 0 ? path.substring(pos) : path;
        Path target = Paths.get(nameAndPath + "_copy" + extension);
        try {
            Files.copy(source, target,
                    StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Была создана копия файла, расположенного по адресу " + path);

        } catch (IOException ex) {
            System.err.format("I/O Error when copying file");
        }
    }
//удаление файла
    public static void deleteFile(String path){
        Path source = Paths.get(path);
        try {
            Files.delete(source);
            System.out.println("Был удален файл, расположенный по адресу " + path);
        } catch(IOException ioException) {
            ioException.printStackTrace();
        }
    }
//перемещение файла
    public static void moveFile(String sourceStr, String targetStr){
        Path source = Paths.get(sourceStr);
        Path target = Paths.get(targetStr);
        try {
            Files.move(source, target,
                    StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Был перемещен файл, расположенный по адресу " + sourceStr +
                    ", в директорию: " + targetStr);
        } catch (IOException ex) {
            System.err.format("I/O Error when moving file");
        }
    }

    public static void main(String[] args) throws IOException {
        while (true) {
            System.out.println("Добро пожаловать в менеджер файлов!");
            System.out.println("Список команд:\nzip - для архивации файла\n" +
                    "copy - для копирования файла\ndelete - для удаления файла" +
                    "\nmove - для перемещения файла");
            Scanner scanner = new Scanner(System.in);
            String command = scanner.nextLine().toLowerCase();
            switch (command) {
                case "zip":
                    System.out.println("Введите путь к файлу, который хотите заархивировать");
                    zipFile(scanner.nextLine());
                    break;
                case "copy":
                    System.out.println("Введите путь к файлу, который хотите скопировать");
                    copyFile(scanner.nextLine());
                    break;
                case "delete":
                    System.out.println("Введите путь к файлу, который хотите удалить");
                    deleteFile(scanner.nextLine());
                    break;
                case "move":
                    System.out.println("Введите путь к файлу, который хотите переместить");
                    String source = scanner.nextLine();
                    System.out.println("Введите путь, в который хотите переместить");
                    String target = scanner.nextLine();
                    moveFile(source, target);
                    break;
            }
        }
    }
}

