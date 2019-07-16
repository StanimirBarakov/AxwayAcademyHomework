package com.keep.trying;

import java.nio.file.NoSuchFileException;

public interface FolderMonitorFinder {

    /**
     * Finds the absolute path of the file, previously named {@param oldFileName}.
     * The file, if exists, must be in {@param targetFolder}.
     *
     * @param oldFileName - the old file name.
     * @param targetFolder - the target directory in which the search must be performed.
     * @return the absolute path of the new file, or <code>null</code> in case this file has not been transferred by the
     * folder monitor.
     * @throws java.nio.file.NoSuchFileException in case the {@param targetFolder} does not exist.
     */
    String findFile(String oldFileName, String targetFolder) throws NoSuchFileException;
}
