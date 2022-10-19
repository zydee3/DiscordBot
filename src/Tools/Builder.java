package Tools;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

/**
 * @author vince zydea
 */
public class Builder {
    @NotNull
    @Contract(pure = true)
    public static String buildString(@NotNull String ... s){
        StringBuilder builder = new StringBuilder();
        for(String s1 : s){
            builder.append(s1);
        }
        return builder.toString();
    }

    public static MessageEmbed buildBasicEmebed(String name, String body){
        return buildBasicEmbed(new StringBuilder().append(DateTime.getTimeDiscriminator()).append(" ").append(name).append(", ").toString(), body);
    }

    public static MessageEmbed buildBasicEmbed(String title, String body){
        EmbedBuilder builder = new EmbedBuilder();
        builder.setColor(new Color(247, 255, 203));
        builder.addField(title, body, false);
        return builder.build();
    }
}
