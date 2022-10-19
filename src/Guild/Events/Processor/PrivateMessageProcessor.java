package Guild.Events.Processor;

import Tools.StringModifier;
import net.dv8tion.jda.api.entities.MessageChannel;
import org.jetbrains.annotations.NotNull;

/**
 * @author vince zydea
 */
public class PrivateMessageProcessor {
    private MessageChannel _channel;
    private String message;
    private String[] arguments;

    public PrivateMessageProcessor(@NotNull net.dv8tion.jda.api.events.message.MessageReceivedEvent event){
        _channel = event.getChannel();

        message = StringModifier.removeNonAlphaNumeric(event.getMessage().getContentRaw());

        String[] contents = message.split(" ");
        arguments = StringModifier.sliceArray(1, contents.length, contents);
    }

    public MessageChannel get_channel(){
        return _channel;
    }

    public String getMessage(){
        return message;
    }

    public String[] getCommandArguments(){
        return arguments;
    }

    @Override
    public String toString(){
        StringBuilder builder = new StringBuilder();
        builder.append("\nChannel: ").append(_channel.getName())
                .append("\nMessage: ").append(message)
                .append("\nArguments: ");

        for(int i = 0; i < arguments.length; i++){
            builder.append(" ").append(i).append("=").append(arguments[i]);
        }

        return builder.toString();
    }
}
