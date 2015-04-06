package com.onyxbeacon.couponbeacondemo7.logging;


import android.content.Context;
import android.os.Environment;

import com.onyxbeacon.model.ILogger;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * Created by Work 2 on 10/6/2014.
 */
public class MyLogger implements Serializable, ILogger {

    private transient Context mContext;
    private transient final Logger LOGGER = Logger.getLogger(MyLogger.class.getName());
    private transient FileHandler fileTxt;
    private transient SimpleFormatter formatterTxt;

    private transient static final String FILENAME = "logFile.log";
    private transient String dir;
    private transient static final int FILE_SIZE = 1024 * 1024; // 1MB

    public MyLogger(Context context) throws IOException {
        mContext = context;

        LOGGER.setLevel(Level.INFO);
        dir = Environment.getExternalStorageDirectory() +
                File.separator + mContext.getPackageName();
        File logDirectory = new File(dir);
        if (!logDirectory.exists()) {
            logDirectory.mkdir();
        }
        String filePath = dir + File.separator + FILENAME;

        fileTxt = new FileHandler(filePath, FILE_SIZE, 1, true);
        formatterTxt = new SimpleFormatter();
        fileTxt.setFormatter(formatterTxt);
        LOGGER.addHandler(fileTxt);
    }

    public void log(String logMessage) {
        LOGGER.info("[" + formatDate(new Date()) + "] " +
            logMessage + System.getProperty("line.separator"));
    }

    public String getFileName() {
        return FILENAME;
    }

    public String getFolderPath() {
        return dir;
    }

    private String formatDate(Date date) {
        SimpleDateFormat sdf =
                new SimpleDateFormat("MMM d, yyyy hh:mm:ss a");

        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
        return sdf.format(date);
    }
}
