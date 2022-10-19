package Core;

import Guild.User.Enums.Status;
import Guild.Events.Processor.UserJoinProcessor;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.events.guild.member.GuildMemberLeaveEvent;
import net.dv8tion.jda.api.events.guild.member.GuildMemberRoleAddEvent;
import net.dv8tion.jda.api.events.guild.member.GuildMemberRoleRemoveEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Nonnull;

/**
 * @author vince zydea
 */
public class EventListener extends ListenerAdapter {
    private final Logger logger = LogManager.getLogger(EventListener.class.getName());

    @Override
    public void onGuildMemberJoin(GuildMemberJoinEvent event) {
        UserJoinProcessor processor = new UserJoinProcessor(event);
        if(processor.getUser().hasStatus(Status.BANNED)){
            processor.get_guild().kick(processor.get_member());
        }
    }

    @Override
    public void onGuildMemberLeave(GuildMemberLeaveEvent event) {

    }

    @Override
    public void onGuildMemberRoleAdd(@Nonnull GuildMemberRoleAddEvent event) {

    }

    @Override
    public void onGuildMemberRoleRemove(@Nonnull GuildMemberRoleRemoveEvent event) {
    }
}
