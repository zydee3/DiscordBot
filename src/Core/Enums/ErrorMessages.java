package Core.Enums;

/**
 * @author vince zydea
 */
public enum ErrorMessages {
    NOT_ENOUGH_PRIVILEGES_TO_USE_COMMAND("You do not have the privileges required to use this command.");

    private String error;

    ErrorMessages(String error){
        this.error = error;
    }

    public String getMessage(){
        return error;
    }
}
