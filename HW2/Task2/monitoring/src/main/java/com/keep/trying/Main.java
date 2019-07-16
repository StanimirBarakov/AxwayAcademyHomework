package com.keep.trying;

import java.nio.file.NoSuchFileException;
import java.util.Scanner;

public class Main  {
    public static void main(String[] args) {
         Scanner scanner = new Scanner(System.in);
        String monitoredFolder = scanner.nextLine();
           String targetFolder = scanner.nextLine();
           String oldFileName = scanner.nextLine();
           String targetFolderFind = scanner.nextLine();

        FolderWatcher watcher = new FolderWatcher();
        try {
            watcher.runFolderMonitor(monitoredFolder,targetFolder);
            System.out.println(watcher.findFile(oldFileName,targetFolderFind));
        } catch (NoSuchFileException e) {
            e.printStackTrace();
        }
    }
}
