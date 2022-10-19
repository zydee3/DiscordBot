package Guild.User.Enums;

/**
 * @author vince zydea
 */
public enum Elements {
    ID(0),
    GUILD_ID(0),
    LEVEL(1),
    EXP(0),
    REWARDS(0),
    STATUSES(0);

    private int defaultValue;

    Elements(int defaultValue){
        this.defaultValue = defaultValue;
    }

    public int getDefaultValue(){
        return defaultValue;
    }
}
