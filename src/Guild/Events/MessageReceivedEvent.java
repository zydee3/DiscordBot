package Guild.Events;

import Guild.Channel.Message.ChannelMessageHandler;
import Guild.Channel.Message.CommandMessageHandler;
import Guild.Channel.Message.PrivateMessageHandler;
import Guild.Events.Processor.ChannelMessageProcessor;
import Guild.Events.Processor.PrivateMessageProcessor;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

/**
 * @author vince zydea
 */
public class MessageReceivedEvent extends ListenerAdapter {
    @Override
    public void onMessageReceived(net.dv8tion.jda.api.events.message.MessageReceivedEvent event){
        try {
            if (event.getAuthor().isBot()) {
                return;
            }

            switch (event.getChannelType()) {
                case PRIVATE: {
                    PrivateMessageProcessor processor = new PrivateMessageProcessor(event);
                    PrivateMessageHandler.handle(processor);
                    break;
                }

                case TEXT: {
                    ChannelMessageProcessor processor = new ChannelMessageProcessor(event);
                    if(processor.containsBannedWord() && !processor.isCommand() && processor.getUser().isModerator()){
                        processor.deleteMessage();
                    } else if (processor.isCommand()) {
                        CommandMessageHandler.handle(processor);
                    } else if (!processor.getResponse().isEmpty()) {
                        processor.get_channel().sendMessage(processor.getResponse()).queue();
                    } else {
                        ChannelMessageHandler.handle(processor);
                    }
                }
            }
        } catch (Exception exception){
            exception.printStackTrace();
        }
    }


}
