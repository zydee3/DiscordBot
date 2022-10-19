package Guild.Events.Processor;

import Core.Constants.Constants;
import Core.Storage.GuildStorage;
import Guild.Guild;
import Guild.User.User;
import Tools.StringModifier;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import org.jetbrains.annotations.NotNull;

/**
 * @author vince zydea
 */
public class ChannelMessageProcessor {
    private User user;
    private Guild guild;

    private MessageChannel _channel;
    private net.dv8tion.jda.api.entities.Member _member;
    private net.dv8tion.jda.api.entities.User _user;
    private net.dv8tion.jda.api.entities.Guild _guild;

    private Message audit;
    private String message;
    private String response;
    private boolean isCommand;
    private String[] arguments;

    public ChannelMessageProcessor(@NotNull net.dv8tion.jda.api.events.message.MessageReceivedEvent event){
        _user = event.getAuthor();
        _member = event.getMember();
        _channel = event.getChannel();
        audit = event.getMessage();

        _guild = event.getGuild();
        guild = GuildStorage.get(_guild.getIdLong());
        user = guild.getUserStorage().get(_user.getIdLong(), _guild.getIdLong());

        message = StringModifier.removeNonAlphaNumeric(event.getMessage().getContentRaw());
        response = RegexResponse.getResponse(message, _user.getIdLong());

        String[] contents = message.split(" ");
        arguments = StringModifier.sliceArray(1, contents.length, contents);
        isCommand = (contents.length > 0 && Constants.Bot.COMMAND_NAMES.contains(contents[0].toLowerCase()));
    }

    public User getUser(){
        return user;
    }

    public net.dv8tion.jda.api.entities.User get_user(){
        return _user;
    }

    public Member get_member(){
        return _member;
    }

    public Guild getGuild(){
        return guild;
    }

    public net.dv8tion.jda.api.entities.Guild get_guild(){
        return _guild;
    }

    public MessageChannel get_channel(){
        return _channel;
    }

    public String getMessage(){
        return message;
    }

    public boolean isCommand(){
        return isCommand;
    }

    public String[] getCommandArguments(){
        return arguments;
    }

    public String getResponse(){
        return response;
    }

    public void deleteMessage(){
        audit.delete().queue();
    }

    /**
     * Check if a sentence contains a word banned in the specific guild.
     * @return True or false.
     */
    public boolean containsBannedWord(){
        return guild.BannedWordsManager.contains(message.split(" "));
    }

    @Override
    public String toString(){
        StringBuilder builder = new StringBuilder();
        builder.append("Author: ").append(_user.getName())
                .append("\nChannel: ").append(_channel.getName())
                .append("\nGuild: ").append(_guild.getName())
                .append("\nMessage: ").append(message)
                .append("\nisCommand: ").append(isCommand)
                .append("\nisAdmin: ").append(user.isAdmin())
                .append("\nisModerator: ").append(user.isModerator())
                .append("\nArguments: ");

        for(int i = 0; i < arguments.length; i++){
            builder.append(" ").append(i).append("=").append(arguments[i]);
        }

        return builder.toString();
    }
}