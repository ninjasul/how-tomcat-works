package ex02.pyrmont;

import java.io.File;

public class Constants {
    public static final String WEB_ROOT =
            System.getProperty("user.dir") + File.separator + "webroot";

    public static final String SERVLET = "/servlet/";

    // shutdown command
    public static final String SHUTDOWN_COMMAND = "/SHUTDOWN";
}