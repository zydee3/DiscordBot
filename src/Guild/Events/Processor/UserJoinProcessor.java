package Guild.Events.Processor;


import Core.Storage.GuildStorage;
import Guild.Guild;
import Guild.User.User;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;

/**
 * @author vince zydea
 */
public class UserJoinProcessor {
    private net.dv8tion.jda.api.entities.Member _member;
    private net.dv8tion.jda.api.entities.User _user;
    private net.dv8tion.jda.api.entities.Guild _guild;

    private Guild guild;
    private User user;

    public UserJoinProcessor(GuildMemberJoinEvent event){
        _member = event.getMember();
        _user = event.getUser();
        _guild = event.getGuild();

        guild = GuildStorage.get(_guild.getIdLong());
        user = guild.getUserStorage().get(_user.getIdLong(), _guild.getIdLong());
    }

    public net.dv8tion.jda.api.entities.User get_user(){
        return _user;
    }

    public net.dv8tion.jda.api.entities.Member get_member(){
        return _member;
    }

    public net.dv8tion.jda.api.entities.Guild get_guild(){
        return _guild;
    }

    public User getUser(){
        return user;
    }

    public Guild getGuild(){
        return guild;
    }
}
