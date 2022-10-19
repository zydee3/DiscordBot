package Guild.User;

import Core.Bot;
import Core.Constants.Constants;
import Guild.User.Enums.Elements;
import Guild.User.Enums.Status;
import Core.Logging;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.internal.utils.PermissionUtil;
import org.bson.Document;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author vince zydea
 */
public class User extends Logging {
    private long id, guildId;
    private int level, exp, rewards;

    List<Status> statuses = new ArrayList<>();

    public User(long id, long guildId, int level, int exp, int rewards, List<Status> statuses){
        try {
            this.id = id;
            this.guildId = guildId;
            this.level = level;
            this.exp = exp;
            this.rewards = rewards;

            if(statuses != null && !statuses.isEmpty()) {
                statuses.forEach(status -> {
                    addStatus(status);
                });
            }
        } catch (Exception exception){
            exception.printStackTrace();
        }
    }

    public void addStatus(Status status){
        if(!statuses.contains(status)){
            statuses.add(status);
        }
    }

    public boolean hasStatus(Status status){
        if(status != null) {
            return statuses.contains(status);
        }
        return false;
    }

    public long getId(){
        return id;
    }

    public int getLevel(){
        return level;
    }

    public int getExp(){
        return exp;
    }

    public int getRewards(){
        return rewards;
    }

    /**
     * Increases user experiences.
     * Increase value must be greater than zero, a user cannot lose experience.
     * @param i: Experience to gain.
     */
    public void gainExp(int i){
        if(i < 0){
            return;
        }

        int newExp = exp + i;
        int expToLevel = Math.min(2100000000, level * Constants.User.EXP_PER_LEVEL);

        while(newExp >= expToLevel){
            level++;
            newExp -= expToLevel;
        }

        exp = newExp;

    }

    /**
     * Increases or decreases user reward points.
     * Reward value will always be between 0 and Constants.User.MAX_REWARD_POINTS.
     * Reward value should be checked prior to calling this method to ensure the correct amount of reward points is subtracted.
     * @param i: Points to gain.
     */
    public void gainRewards(int i){
        rewards = Math.max(0, Math.min(Constants.User.MAX_REWARD_POINTS, rewards + i));
    }

    public boolean canTalk(){
        return hasStatus(Status.MUTED);
    }

    public Member getDiscordMember(){
        return Bot.getAPI().getGuildById(guildId).getMemberById(id);
    }

    public boolean isAdmin(){
        return PermissionUtil.checkPermission(getDiscordMember(), Permission.ADMINISTRATOR);
    }

    public boolean isModerator(){
        Member member = getDiscordMember();
        return (PermissionUtil.checkPermission(member, Permission.BAN_MEMBERS) || PermissionUtil.checkPermission(member, Permission.KICK_MEMBERS));
    }

    public boolean isDeveloper(){
        return Constants.Bot.DEVELOPERS.contains(id);
    }

    public Document getRecord() {
        statuses.add(Status.ADMIN);
        List<String> statusNames = statuses.stream().map(status -> status.name()).collect(Collectors.toList());
        Document record = new Document(Elements.ID.name(), id);
        record.append(Elements.LEVEL.name(), level)
                .append(Elements.GUILD_ID.name(), guildId)
                .append(Elements.EXP.name(), exp + 1)
                .append(Elements.REWARDS.name(), rewards)
                .append(Elements.STATUSES.name(), statusNames);
        return record;
    }

    @Override
    public String toString(){
        StringBuilder builder = new StringBuilder();
        builder.append("\nID: " ).append(id)
                .append("\nGuild ID: ").append(guildId)
                .append("\nLevel: ").append(level)
                .append("\nExp: ").append(exp)
                .append("\nRewards: ").append(rewards)
                .append("\nisAdmin: ").append(isAdmin())
                .append("\nisModerator: ").append(isModerator());

        for(int i = 0; i < statuses.size(); i++){
            builder.append("\nStatus ").append(i).append(":").append(statuses.get(i).name());
        }

        return builder.toString();
    }
}
