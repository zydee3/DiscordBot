package Guild.Events;

import Guild.User.Enums.Status;
import Guild.Events.Processor.UserJoinProcessor;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

/**
 * @author vince zydea
 */
public class GuildMemberJoinEvent extends ListenerAdapter {
    @Override
    public void onGuildMemberJoin(net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent event) {
        UserJoinProcessor processor = new UserJoinProcessor(event);
        if(processor.getUser().hasStatus(Status.BANNED)){
            processor.get_guild().kick(processor.get_member());
        }
    }
}
