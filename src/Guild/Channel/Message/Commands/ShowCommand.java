package Guild.Channel.Message.Commands;

import Guild.Guild;
import net.dv8tion.jda.api.entities.MessageChannel;
import org.jetbrains.annotations.NotNull;

/**
 * @author vince zydea
 */
public class ShowCommand {
    @NotNull
    public static String handle(MessageChannel channel, Guild guild, String[] args){
        if(args == null || args.length == 0){
            return "Invalid Command Usage: show <settings/>";
        }

        switch(args[1]) {
            case "setting":
            case "settings":
                channel.sendMessage(guild.SettingsManager.getEmbed()).queue();
                break;
            case "ban":
            case "banned":
                switch(args[1]){
                    case "player":
                    case "players":
                    case "user":
                    case "users":
                        break;
                    case "word":
                    case "words":

                }
            default:
                return "Invalid Command Usage: show <settings/>";
        }

        return "";
    }
}
