package Guild.Channel.Message;

import Guild.Events.Processor.ChannelMessageProcessor;
import Tools.Builder;
import Tools.Random;
import org.jetbrains.annotations.NotNull;

/**
 * @author vince zydea
 */
public class ChannelMessageHandler {
    public static void handle(@NotNull ChannelMessageProcessor processor){
        processor.getUser().gainExp(1);

        int chance = 10; // 10%
        int max = 1000;
        if(Random.getInt(1, max) < (max/10)){
            int gain = Random.getInt(1, 3);
            processor.getUser().gainRewards(gain);
            processor.get_channel().sendMessage(Builder.buildString("Congrats <@", String.valueOf(processor.getUser().getId()), ">, you have gained ", String.valueOf(gain), " reward points!")).queue();
        }
    }
}
