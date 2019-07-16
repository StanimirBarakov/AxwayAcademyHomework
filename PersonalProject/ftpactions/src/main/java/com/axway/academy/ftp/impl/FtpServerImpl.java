package com.axway.academy.ftp.impl;

import com.axway.academy.model.daos.ActionRecordDao;
import com.axway.academy.model.entities.ActionRecord;
import com.axway.academy.ftp.base.FTPServer;
import org.apache.ftpserver.ConnectionConfigFactory;
import org.apache.ftpserver.FtpServer;
import org.apache.ftpserver.FtpServerFactory;
import org.apache.ftpserver.ftplet.Authority;
import org.apache.ftpserver.ftplet.FtpException;
import org.apache.ftpserver.ftplet.UserManager;
import org.apache.ftpserver.listener.ListenerFactory;
import org.apache.ftpserver.usermanager.PropertiesUserManagerFactory;
import org.apache.ftpserver.usermanager.impl.BaseUser;
import org.apache.ftpserver.usermanager.impl.WritePermission;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FtpServerImpl implements FTPServer {
    private static final String FTP_HOME_DIRECTORY =
            System.getProperty("user.dir") + File.separator + "FTP_HOME_DIRECTORY";
    private static final String USERS_FILE = "users.txt";
    private static final String DEFAULT_LISTENER = "default";
    String username;
    String password;

    public FtpServerImpl() {
    }

    @Override
    public void startServer(int port) {
        FtpServerFactory serverFactory = new FtpServerFactory();
        ListenerFactory factory = new ListenerFactory();
        factory.setPort(port);
        serverFactory.addListener(DEFAULT_LISTENER, factory.createListener());
        PropertiesUserManagerFactory userManagerFactory = new PropertiesUserManagerFactory();
        userManagerFactory.setFile(new File(USERS_FILE));
        BaseUser user = new BaseUser();
        //user.setName("admin");
        //user.setPassword("admin");
        user.setHomeDirectory(FTP_HOME_DIRECTORY);
        List<Authority> authorities = new ArrayList<>();
        authorities.add(new WritePermission());
        user.setAuthorities(authorities);
        UserManager um = userManagerFactory.createUserManager();
        serverFactory.setUserManager(um);
        ConnectionConfigFactory connectionConfigFactory = new ConnectionConfigFactory();
        connectionConfigFactory.setAnonymousLoginEnabled(true);
        try {
            //um.save(user);
            serverFactory.setUserManager(um);
            FtpServer server = serverFactory.createServer();
            server.start();
        } catch (FtpException e) {
            e.getMessage();
        }
    }

    public void saveRecord(ActionRecord record) {
        ActionRecordDao recordDao = new ActionRecordDao();
        recordDao.saveRecord(record);
    }
}

