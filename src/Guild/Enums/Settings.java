package Guild.Enums;

/**
 * @author vince zydea
 */
public enum Settings {
    ENABLE_WORD_FILTER(1, "Enables the word message system that will delete any message that contains a blocked word. Add blocked words by using the add command."),
    ENABLE_MODERATORS_ARE_ADMINS(0, "By default, there are things only the discord admin can do. Enabling this removes the restriction and grants moderators all the privileges an admin has."),
    EXP_TO_LEVEL(20, "Exp needed to level increases linearly to a player's level. Simply put, respective exp needed to level is equal to this number multiplied by the player's level."),
    RANDOM_REWARD_CHANCE(10, "Players can gain reward points randomly while talking in discord. The number entered acts as a percentage, entering 10 would yield a 10% chance for players to gain rewards per message sent. Alternatively, entering 0 disables this system."),
    RANDOM_REWARD_AMOUNT(3, "When a player gains rewards, the amount they gain is a random amount between 1 and the value entered"),
    ENABLE_AUTO_PLAYER_PUNISHMENT(1, "Automatically mutes a player if they're being disruptive. If the player exhibits continuous disruptive behavior, they are permanently muted."),
    ENABLE_AUTO_BAN_IF_BANNED_INGAME(1, "When a player in-game is banned, if there is a discord associated to the account, the player's discord will also be banned from the discord."),
    ENABLE_SERVER_COMMUNICATION(1, "Allows the server to connect to the discord bot to send updates. Disabling this means the bot rejects all requests to connect from the server."),
    ENABLE_PENALTY_EVASION_AUTO_BAN(1, "Players often try to evade mutes or bans by re-joining discord or changing their IP respectively. If they're caught doing this, bot will either re-mute them or auto-kick them."),
    MINI_GAME_INTERVAL(30, "The bot can host a variety of mini-games automatically for players to participate. If a player is muted, they are unable to participate. Time entered is the time between events hosted from finish to start in minutes. Alternatively, entering 0 disables this system."),
    ENABLE_BOT_COMMUNICATION(1, "Allows the bot to connect to the maple server to send updates or commands to be handled. Disabling this means the bot will neglect any command to send any request to the server.");

    private int setting;
    private String description;

    Settings(int setting, String description){
        this.setting = setting;
        this.description = description;
    }

    public int getDefaultValue(){
        return setting;
    }

    public String getDescription(){
        return description;
    }
}
