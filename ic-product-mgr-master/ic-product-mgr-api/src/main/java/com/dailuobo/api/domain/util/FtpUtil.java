package com.dailuobo.api.domain.util;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class FtpUtil {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private String httpUrlPrefix;
    private String host;
    private int port;
    private String username;
    private String password;
    private String home;

    public void setHttpUrlPrefix(String httpUrlPrefix) {
        this.httpUrlPrefix = httpUrlPrefix;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setHome(String home) {
        this.home = home;
    }

    public String upload(int fileType, String fileName, InputStream inputStream) {
        FTPClient ftp = new FTPClient();
        try {
            int reply;
            ftp.connect(host, port);
            ftp.login(username, password);
            reply = ftp.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)) {
                ftp.disconnect();
                return null;
            }
            Calendar calendar = GregorianCalendar.getInstance();
            String year = String.valueOf(calendar.get(Calendar.YEAR));
            String month = String.valueOf(calendar.get(Calendar.MONTH) + 1);
            String day = String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));
            ftp.changeWorkingDirectory(home);
            ftp.makeDirectory(year);
            ftp.changeWorkingDirectory(year);
            ftp.makeDirectory(month);
            ftp.changeWorkingDirectory(month);
            ftp.makeDirectory(day);
            ftp.changeWorkingDirectory(day);
            ftp.setFileType(fileType);
            ftp.storeFile(fileName, inputStream);
            inputStream.close();
            ftp.logout();
            return httpUrlPrefix + "/" + year + "/" + month + "/" + day + "/" + fileName;
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        } finally {
            if (ftp.isConnected()) {
                try {
                    ftp.disconnect();
                } catch (IOException e) {
                    logger.error(e.getMessage(), e);
                }
            }
        }
        return null;
    }
}
