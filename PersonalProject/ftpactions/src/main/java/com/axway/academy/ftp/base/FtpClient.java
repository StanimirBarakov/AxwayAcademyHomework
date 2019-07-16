package com.axway.academy.ftp.base;

import com.axway.academy.utils.exceptions.NoSuchFileException;

import java.io.File;

public interface FtpClient {
    void upload(File file)throws NoSuchFileException;

    void download(String fileName, File target)throws NoSuchFileException;

    void delete(String filename) throws NoSuchFileException;
}
