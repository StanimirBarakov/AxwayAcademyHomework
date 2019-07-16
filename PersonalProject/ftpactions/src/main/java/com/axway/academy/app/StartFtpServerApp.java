package com.axway.academy.app;

import com.axway.academy.ftp.impl.FtpServerImpl;

import java.util.Scanner;

public class StartFtpServerApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the desired port: ");
        int port = Integer.parseInt(scanner.nextLine());
        FtpServerImpl impl = new FtpServerImpl();
        impl.startServer(port);
        System.out.println("The FTP server is started! Now you can" +
                " run the client application file: ClientApplication ");

    }
}
