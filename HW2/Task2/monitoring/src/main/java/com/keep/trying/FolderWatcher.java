package com.keep.trying;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FolderWatcher implements FolderMonitor, FolderMonitorFinder {

    @Override
    public void runFolderMonitor(String monitoredFolder, String targetFolder) throws NoSuchFileException {
        String rename = "new_";

        HashMap<String, String> fileNames = new HashMap<>();
        Path path = Paths.get(monitoredFolder);

        System.out.println("Monitoring directory for changes...");
        while (true) {
            try {

                WatchService watcher = path.getFileSystem().newWatchService();

                path.register(watcher, StandardWatchEventKinds.ENTRY_CREATE);

                WatchKey watchKey = watcher.take();

                List<WatchEvent<?>> events = watchKey.pollEvents();
                File f = null;

                for (WatchEvent event : events) {
                    String fileRename = rename + event.context().toString();

                    if (event.kind() == StandardWatchEventKinds.ENTRY_CREATE) {

                        f = new File(monitoredFolder + File.separator + event.context().toString());

                        f.createNewFile();

                        f.renameTo(new File(targetFolder + File.separator + fileRename));

                        System.out.println("Created: " + event.context().toString());

                        fileNames.put(event.context().toString(), fileRename);

                    }
                }
                File file = new File(System.getProperty("user.home") +
                        File.separator + "folder_monitor" + File.separator + "folder_monitor.txt");
                File logFile = new File(System.getProperty("user.home") +
                        File.separator + "folder_monitor" + File.separator + "logs" + File.separator + "folder_monitor.log");

                file.getParentFile().mkdir();
                logFile.getParentFile().mkdir();

                try (FileWriter writer = new FileWriter(file); FileWriter logger = new FileWriter(logFile)) {

                    writer.append(printFileNames(fileNames));
                    logger.append(logFileNames(fileNames));

                } catch (IOException e) {
                    e.printStackTrace();
                }

            } catch (IOException e) {
                e.getMessage();
            } catch (InterruptedException e) {
                e.getMessage();
            }
        }

    }

    @Override
    public String findFile(String oldFileName, String targetFolder) throws NoSuchFileException {
        String absoluteFilePathName = " ";
        File[] files = new File(targetFolder).listFiles();

        for (File file : files) {
            if (file.getName().contains(oldFileName)) {
                absoluteFilePathName = file.getAbsolutePath();
            } else {
                throw new NoSuchFileException("No such file!");
            }
        }
        return absoluteFilePathName;

    }

    public static String printFileNames(Map<String, String> map) {
        String output = "";
        String oldName = "Old Name: ";
        String newName = " New Name: ";
        for (String letter : map.keySet()) {
            String key = letter.toString();
            String value = map.get(letter).toString();
            output += oldName + key + newName + value + "\n";
        }
        return output;
    }

    public static String logFileNames(Map<String, String> map) {
        String output = "";
        String oldName = "Got file: ";
        String newName = " The file will be renamed to: ";
        for (String letter : map.keySet()) {
            String key = letter.toString();
            String value = map.get(letter).toString();
            output += oldName + key + newName + value + "\n";
        }
        return output;
    }


}
