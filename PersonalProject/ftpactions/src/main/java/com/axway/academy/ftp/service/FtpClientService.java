package com.axway.academy.ftp.service;

import com.axway.academy.ftp.impl.FtpServerImpl;
import com.axway.academy.model.entities.ActionRecord;
import com.axway.academy.utils.Operation;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

import java.io.*;

public class FtpClientService {

    private final String HOST = "localhost";
    private final int PORT;
    private final String USER;
    private final String PASS;
    private FtpServerImpl server;
    private FTPClient ftpClient;

    public FtpClientService(int PORT, String USER, String PASS) {
        this.PORT = PORT;
        this.USER = USER;
        this.PASS = PASS;
        this.ftpClient = new FTPClient();
        this.server = new FtpServerImpl();
        try {
            ftpClient.connect(HOST, PORT);
            ftpClient.login(USER, PASS);
            ftpClient.enterLocalPassiveMode();
        } catch (IOException e) {
            e.getMessage();
        }
    }

    public boolean upload(File file) {
        File firstLocalFile = new File(file.getAbsolutePath());
        String firstRemoteFile = file.getName();
        boolean done = false;
        try {
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
            InputStream inputStream = new FileInputStream(firstLocalFile);
            done = ftpClient.storeFile(firstRemoteFile, inputStream);
            inputStream.close();
            if (done) {
                ActionRecord record = new ActionRecord(file.getName(), Operation.UPLOAD, file.length());
                server.saveRecord(record);
                System.out.println("File " + file.getName() + " was uploaded successfully.");
            }
        } catch (IOException e) {
            e.getMessage();
        } finally {
            try {
                if (ftpClient.isConnected()) {
                    ftpClient.logout();
                    ftpClient.disconnect();
                }
            } catch (IOException ex) {
                ex.getMessage();
            }
        }
        return done;
    }

    public boolean download(String fileName, File target) {
        FileOutputStream fos = null;
        boolean done = false;
        try {
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
            fos = new FileOutputStream(target.getAbsolutePath() + File.separator + fileName);
            done = ftpClient.retrieveFile(fileName, fos);
            if (done) {
                ActionRecord record = new ActionRecord(fileName, Operation.DOWNLOAD, new File(target.getAbsolutePath() + File.separator + fileName).length());
                server.saveRecord(record);
                System.out.println("File " + fileName + " was downloaded successfully.");
            }
        } catch (IOException e) {
            e.getMessage();
        } finally {
            try {
                if (ftpClient.isConnected()) {
                    ftpClient.logout();
                    ftpClient.disconnect();
                }
                fos.close();
            } catch (IOException ex) {
                ex.getMessage();
            }
        }
        return done;
    }

    public boolean delete(String fileName) {
        boolean done = false;
        try {
            done = ftpClient.deleteFile(fileName);
            if (done) {
                ActionRecord record = new ActionRecord(fileName, Operation.DELETE, 0);
                server.saveRecord(record);
                System.out.println("File " + fileName + " was deleted successfully.");
            }
        } catch (IOException e) {
            e.getMessage();
        } finally {
            try {
                if (ftpClient.isConnected()) {
                    ftpClient.logout();
                    ftpClient.disconnect();
                }
            } catch (IOException ex) {
                ex.getMessage();
            }
        }
        return done;
    }

    public FTPFile[] getAllFiles() throws IOException {
        return ftpClient.listFiles();
    }
}