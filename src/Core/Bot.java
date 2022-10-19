package Core;

import Core.Constants.Constants;
import Guild.Events.GuildMemberJoinEvent;
import Guild.Events.MessageReceivedEvent;
import net.dv8tion.jda.api.AccountType;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.Nullable;


/**
 * @author vince zydea
 */
public class Bot {
    private static final JDA api = getJDA();
    private static final Logger logger = LogManager.getLogger(Bot.class);

    public static void main(String[] args){
        logger.info("{} is now online.", api.getSelfUser().getName());
        api.getPresence().setActivity(Activity.of(Activity.ActivityType.WATCHING, "over " + api.getGuilds().size() + "  discords"));
    }

    @Nullable
    private static JDA getJDA(){
        try {
            JDABuilder builder = new JDABuilder(AccountType.BOT);
            return builder.setToken(Constants.Bot.TOKEN)
                    .addEventListeners(new EventListener())
                    .addEventListeners(new MessageReceivedEvent())
                    .addEventListeners(new GuildMemberJoinEvent())
                    .build()
                    .awaitReady();
        } catch (Exception exception){
            return null;
        }
    }

    public static JDA getAPI(){
        return api;
    }
}