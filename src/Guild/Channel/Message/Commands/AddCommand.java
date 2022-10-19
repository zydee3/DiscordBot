package Guild.Channel.Message.Commands;

import Core.Enums.ErrorMessages;
import Guild.Guild;
import Guild.User.User;

/**
 * @author vince zydea
 */
public class AddCommand {
    public static String handle(Guild guild, User user, String[] args) {
        if(args == null || args.length < 2){
            return "Invalid Command Usage: add <response> <parameters..>";
        }

        int numArgs = args.length;

        switch (args[1].toLowerCase()) {
            case "response":
            case "reply":
                if (numArgs < 6) {
                    return "Invalid Command Usage: add response <question> bksp <answer> bksp <regex pattern>";
                }

                int position = 0;
                String[] results = {"", "", ""};
                for (int i = 0; i < numArgs; i++) {
                    if (args[i].equalsIgnoreCase("bksp")) {
                        position++;
                        continue;
                    } else if (i < (numArgs - 1)) {
                        results[position] += args[i] + " ";
                    } else {
                        results[position] += args[i];
                    }
                }

                String question = results[0];
                String answer = results[1];
                String pattern = results[2];

                return "Added";
            default:
                return "Invalid Command Usage: add response <question> bksp <answer> bksp <regex pattern>";
        }
    }
}
