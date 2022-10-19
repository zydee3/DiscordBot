package Guild.Channel.Message;

import Core.Enums.ErrorMessages;
import Guild.Enums.Settings;
import Guild.Channel.Message.Commands.*;
import Guild.Events.Processor.ChannelMessageProcessor;
import Tools.Builder;
import org.jetbrains.annotations.NotNull;

/**
 * @author vince zydea
 */
public class CommandMessageHandler {
    public static void handle(@NotNull ChannelMessageProcessor processor){
        if(processor == null || processor.get_channel() == null){
            return;
        } else if (processor.getCommandArguments().length == 0){
            processor.get_channel().sendMessage("Sorry, I cannot find that command. Ask me for a list of commands saying 'marshmallow commands'.").queue();
            return;
        }

        String response;

        switch(processor.getCommandArguments()[0]){
            case "status":
                response = ServerStatusCommand.handle();
                break;
            case "date":
            case "time":
                response = DateTimeCommand.handle();
                break;
            case "memory":
            case "memusage":
                response = (processor.getUser().isDeveloper() ? MemoryUsageCommand.handle(processor.getUser()) : ErrorMessages.NOT_ENOUGH_PRIVILEGES_TO_USE_COMMAND.getMessage());
                break;
            case "unban":
            case "ban":
                response = BanCommand.handle(processor.getGuild(), processor.getUser(), processor.getCommandArguments());
                break;
            case "add":
                response = (processor.getUser().isModerator() ? AddCommand.handle(processor.getGuild(), processor.getUser(), processor.getCommandArguments()) : ErrorMessages.NOT_ENOUGH_PRIVILEGES_TO_USE_COMMAND.getMessage());
                break;
            case "show":
                boolean can = (processor.getUser().isAdmin() || (processor.getUser().isModerator() && processor.getGuild().SettingsManager.get(Settings.ENABLE_MODERATORS_ARE_ADMINS) == 1));
                response = (can ? ShowCommand.handle(processor.get_channel(), processor.getGuild(), processor.getCommandArguments()) : ErrorMessages.NOT_ENOUGH_PRIVILEGES_TO_USE_COMMAND.getMessage());
                break;
            case "command":
            case "commands":
                StringBuilder builder = new StringBuilder();
                builder.append("Status - Shows the on/off status of the server currently")
                        .append("Time - Shows the current time of the server");
                response = builder.toString();
                break;
            default:
                response = "Sorry, I cannot find that command. Ask me for a list of commands saying 'marshmallow commands'.";
                break;
        }

        if(!response.isEmpty()){
            String name = processor.get_member().getNickname();
            if(name == null || name.isEmpty()){
                name = processor.get_user().getName();
            }
            processor.get_channel().sendMessage(Builder.buildBasicEmebed(name, response)).queue();
        }
    }
}
