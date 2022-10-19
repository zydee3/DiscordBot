package Guild.Channel.Message;

import Guild.Events.Processor.PrivateMessageProcessor;

/**
 * @author vince zydea
 */
public class PrivateMessageHandler {
    public static void handle(PrivateMessageProcessor processor){
        processor.get_channel().sendMessage("Hello").queue();
    }
}
