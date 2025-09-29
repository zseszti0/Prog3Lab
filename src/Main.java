import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    protected File wd = new File(System.getProperty("user.dir"));

    protected void pwd() throws IOException {
        System.out.println(wd.getCanonicalPath() + " " + wd.listFiles().length);
    }
    protected void ls(String arg){
        List<File> files = List.of(wd.listFiles());
        for(File file : files){
            if(file.isFile())
                System.out.println(file.getName() + " " + file.length() + " f");
            else if(file.isDirectory())
                System.out.println(file.getName() + " " + file.length() + " d");
        }
    }
    protected void ls(){
        List<File> files = List.of(wd.listFiles());
        for(File file : files){
            System.out.println(file.getName());
        }
    }
    protected void cd(String arg){
        if(arg.equals("..")){
            wd = wd.getParentFile();
            return;
        }
        wd = new File(wd,arg);
    }
    protected void rm(String arg){
        File toDel = new File(wd,arg);
        if(!toDel.delete()){
            throw new RuntimeException("Nem sikerült a törlés");
        }
    }
    protected void mkdir(String arg){
        File dir = new File(wd,arg);
        if(!dir.mkdir()){
            throw new RuntimeException("Nem sikerült a mappa létrehozása");
        }
    }
    protected void mv(String arg1,String arg2){
        File fileToRename = new File(wd,arg1);
        File fileName = new File(wd,arg2);
        if(!fileToRename.renameTo(fileName)){
            throw new RuntimeException("Nem sikerült a file átnevezése");
        }
    }
    protected void cp(String arg1,String arg2){
        File sourceFile = new File(wd,arg1);
        File targetFile = new File(wd,arg2);
        try (
                FileInputStream in = new FileInputStream(sourceFile.getPath());
                FileOutputStream out = new FileOutputStream(sourceFile.getPath());
        ){
            byte[] buffer = new byte[1024];
            int bytesRead;

            while ((bytesRead = in.read(buffer)) != -1) {
                out.write(buffer, 0, bytesRead);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    protected void cat(String arg){
        try (BufferedReader reader = new BufferedReader(new FileReader(arg))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    protected void length(String arg){
        File sourceFile = new File(wd,arg);
        System.out.println(sourceFile.length());
    }
    protected void head(String arg,int n){
        try (BufferedReader reader = new BufferedReader(new FileReader(arg))) {
            String line;
            for(int i = 0; i < n;i++){
                if((line = reader.readLine()) != null){
                    System.out.println(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    protected void tail(String arg,int n){
        try (BufferedReader reader = new BufferedReader(new FileReader(arg))) {
            String line;
            List<String> lines = new ArrayList<>();
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
            for(int i = lines.size()-n; i < lines.size(); i++){
                System.out.println(lines.get(i));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    protected void wc(String arg){
        try (BufferedReader reader = new BufferedReader(new FileReader(arg))) {
            String line;
            int linesC = 0;
            int wordsC = 0;
            int lettersC = 0;
            while ((line = reader.readLine()) != null) {
                linesC++;
                for(String split : line.split(" ")){
                    if(!split.isEmpty()){
                        wordsC++;
                    }
                }
                lettersC += line.length();
            }
            System.out.println(linesC + " " + wordsC + " " + lettersC);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    protected void grep(String arg1,String arg2){
        try (BufferedReader reader = new BufferedReader(new FileReader(arg2))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if(line.matches(arg1)){
                    System.out.println(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public  void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        String line;
        while (!(line = scanner.nextLine()).equals("exit")) {
            List<String> commands = Arrays.asList(line.split(" "));
            switch (commands.get(0)){
                case "pwd":
                    pwd();
                    break;
                case "ls":
                    if(commands.size() > 1)
                        ls(commands.get(1));
                    else
                        ls();
                    break;
                case "cd":
                    cd(commands.get(1));
                    break;
                case "rm":
                    rm(commands.get(1));
                    break;
                case "mv":
                    mv(commands.get(1),commands.get(2));
                    break;
                case "cp":
                    cp(commands.get(1),commands.get(2));
                    break;
                case "cat":
                    cat(commands.get(1));
                    break;
                case "length":
                    length(commands.get(1));
                    break;
                case "head":
                    if(commands.size() > 2)
                        head(commands.get(1),Integer.parseInt(commands.get(2)));
                    else
                        head(commands.get(1),10);
                    break;
                case "tail":
                    if(commands.size() > 2)
                        tail(commands.get(1),Integer.parseInt(commands.get(2)));
                    else
                        tail(commands.get(1),10);
                    break;
                case "wc":
                    wc(commands.get(1));
                    break;
                case "grep":
                    grep(commands.get(1),commands.get(2));
                    break;
            }

        }
    }
}
