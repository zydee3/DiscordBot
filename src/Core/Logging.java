package Core;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

/**
 * @author vince zydea
 */
public abstract class Logging {
    private final Logger logger;

    public Logging(){
        this.logger = LogManager.getLogger(getClass());
    }

    public final Logger getLogger(){
        return logger;
    }

    public void log(String message){
        logger.info(message);
    }

    public void alert(String message){
        logger.info(message);
        logger.error(message);
    }

    public void exceptionCaught(@NotNull Exception exception){
        logger.info("Exception caught: {}", exception.getMessage());
        logger.error("Exception caught: {}\n{}", exception.getMessage(), exception.getStackTrace());
    }

}
