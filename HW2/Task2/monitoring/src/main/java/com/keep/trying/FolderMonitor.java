package com.keep.trying;


import java.nio.file.NoSuchFileException;

public interface FolderMonitor {
    /**
     * Run the Folder monitor.
     *
     * @param monitoredFolder - the folder, that will be monitored
     * @param targetFolder - the folder, in which the renamed files will be stored
     * @throws java.nio.file.NoSuchFileException in case the {@param monitoredFolder} does not exist.
     */
    void runFolderMonitor(String monitoredFolder, String targetFolder) throws NoSuchFileException;

}
