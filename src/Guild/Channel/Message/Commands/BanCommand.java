package Guild.Channel.Message.Commands;

import Core.Enums.ErrorMessages;
import Guild.Guild;
import Guild.User.User;
import Tools.Builder;
import Tools.StringModifier;

import java.util.Arrays;

/**
 * @author vince zydea
 */
public class BanCommand {
    public static String handle(Guild guild, User user, String[] args){
        int numArgs = args.length;
        boolean unban = args[0].equalsIgnoreCase("unban");

        if(numArgs < 3){
            return "Invalid Command Usage: " + (unban ? "unban" : "ban") + " <player/word> <player name(s)/word(s).";
        }

        switch(args[1].toLowerCase()){
            case "player":
                //processor.get_guild().getMemberById(processor.get_user().getIdLong()).ban(Integer.MAX_VALUE, )
                return "Something went wrong! Error has been logged.";
            case "word":
            case "words":
                String[] targets = StringModifier.sliceArray(2, numArgs, args);
                if (unban) {
                    guild.BannedWordsManager.remove(Arrays.asList(targets));
                } else {
                    for(String word : targets){
                        guild.BannedWordsManager.add(word);
                    }
                }
                return (Builder.buildString("The word(s) ", StringModifier.listItems(targets), " have been ", (unban ? "unban" : "ban"), " from being used in chats."));
            default:
                return "Invalid Command Usage: " + (unban ? "unban" : "ban") + " <player/word> <player name(s)/word(s).";
        }
    }
}
