package com.axway.academy.app;

import com.axway.academy.ftp.impl.FtpClientImpl;
import com.axway.academy.ftp.impl.FtpServerImpl;
import com.axway.academy.utils.exceptions.NoSuchFileException;
import com.axway.academy.utils.exceptions.UserInputException;
import org.apache.commons.net.ftp.FTPFile;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class ClientApplication {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the port you chosed!");
        int port = Integer.parseInt(scanner.nextLine());
        System.out.println("USERNAME: ");
        String username = scanner.nextLine();
        System.out.println("PASSWORD: ");
        String password = scanner.nextLine();
        if ((!(username.equalsIgnoreCase("admin")))&&(!(username.equalsIgnoreCase("anonymous")))) {
            System.out.println("Invalid Login! You can log with an existing user or anonymous login!");

        } else {

            FtpClientImpl ftpClient = new FtpClientImpl(port, username, password);
            System.out.println("CHOOSE BETWEEN THESE OPTIONS: DOWNLOAD,UPLOAD,DELETE and type it!");
            String option = scanner.nextLine();

            while (!option.equalsIgnoreCase("End")) {
                if (option.equalsIgnoreCase("UPLOAD")) {
                    System.out.println("Type the full path of the file you want to upload!");
                    String filename = scanner.nextLine();
                    try {
                        ftpClient.upload(new File(filename));
                    } catch (NoSuchFileException e) {
                        System.out.println(e.getMessage());
                    }
                    System.out.println("Choose the next option or type (end) to stop the program!");
                } else if (option.equalsIgnoreCase("DOWNLOAD")) {
                    try {
                        FTPFile[] files = ftpClient.getAllFiles();
                        for (int i = 0; i < files.length; i++) {
                            System.out.println(files[i].getName());
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Enter the file name that you want to download!");
                    String filename = scanner.nextLine();
                    System.out.println("Enter the download target!");
                    String target = scanner.nextLine();
                    try {
                        ftpClient.download(filename, new File(target));
                    } catch (NoSuchFileException e) {
                        System.out.println(e.getMessage());
                    }
                    System.out.println("Choose the next option or type (end) to stop the program!");
                } else {
                    if (option.equalsIgnoreCase("DELETE")) {
                        System.out.println("Enter the file name that you want to delete!");
                        String deleteFileName = scanner.nextLine();
                        try {
                            ftpClient.delete(deleteFileName);
                        } catch (NoSuchFileException e) {
                            System.out.println(e.getMessage());
                        }
                        System.out.println("Choose the next option or type (end) to stop the program!");
                    }
                }
                option = scanner.nextLine();
            }
        }
    }
}
