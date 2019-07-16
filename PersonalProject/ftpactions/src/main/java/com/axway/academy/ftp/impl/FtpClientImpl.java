package com.axway.academy.ftp.impl;

import com.axway.academy.ftp.service.FtpClientService;
import com.axway.academy.ftp.base.FtpClient;
import com.axway.academy.utils.exceptions.NoSuchFileException;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

import java.io.*;

public class FtpClientImpl implements FtpClient {
    private final int port;
    private final String user;
    private final String pass;
    private FtpServerImpl server;
    private FTPClient client;

    public FtpClientImpl(int port, String user, String pass) {
        this.port = port;
        this.user = user;
        this.pass = pass;
        this.server = new FtpServerImpl();
        this.client = new FTPClient();
    }
    @Override
    public void upload(File file) throws NoSuchFileException {
        FtpClientService service = new FtpClientService(this.port, this.user, this.pass);
        boolean done = service.upload(file);
        if (!done) {
            throw new NoSuchFileException("No such file!");
        }
    }
    @Override
    public void download(String fileName, File target) throws NoSuchFileException {
        FtpClientService service = new FtpClientService(this.port, this.user, this.pass);
        boolean done = service.download(fileName, target);
        if (!done) {
            throw new NoSuchFileException("No such file!");
        }
    }
    @Override
    public void delete(String fileName) throws NoSuchFileException {
        FtpClientService service = new FtpClientService(this.port, this.user, this.pass);
        boolean done = service.delete(fileName);
        if (!done) {
            throw new NoSuchFileException("No such file!");
        }
    }
    public FTPFile[] getAllFiles() throws IOException {
        FtpClientService service = new FtpClientService(this.port, this.user, this.pass);
        return service.getAllFiles();
    }
}