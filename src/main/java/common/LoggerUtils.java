package common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggerUtils {
    private Logger logger = LoggerFactory.getLogger(getClass());

    private static LoggerUtils utils = new LoggerUtils();

    public static Logger getLogger(){
        return utils.logger;
    }
}