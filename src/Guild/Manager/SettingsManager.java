package Guild.Manager;

import Guild.Enums.Settings;
import Core.Logging;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * @author vince zydea
 */
public class SettingsManager extends Logging {
    private Map<Settings, Integer> settings = new HashMap<>();

    public void add(Map<String, Integer> records){
        if(records == null || records.isEmpty()){
            return;
        }
        records.forEach((k,v) -> {
           add(Settings.valueOf(k), v);
        });
    }

    public void add(@NotNull Settings setting, int value){
        if(setting != null){
            getLogger().info("Adding Setting: {} -> {}", setting.name(), value);
            settings.put(setting, value);
        }
    }

    public int get(Settings setting){
        return settings.containsKey(setting) ? settings.get(setting) : setting.getDefaultValue();
    }

    public List<Integer> getRecord(){
        List<Integer> values = new ArrayList<>();
        for(Settings setting : Settings.values()){
            values.add(get(setting));
        }
        return values;
    }

    public MessageEmbed getEmbed(){
        EmbedBuilder builder = new EmbedBuilder();
        builder.setColor(new Color(247, 255, 203));
        for(Settings setting : Settings.values()){
            int value = get(setting);
            String currentValue = "";
            if(setting.name().split("_")[0].equalsIgnoreCase("enable")){
                if(value == 1){
                    currentValue = "Enabled";
                } else {
                    currentValue = "Disabled";
                }
            }
            builder.addField(new StringBuilder().append(setting.name()).append(" - Currently ").append(currentValue.isEmpty() ? value : currentValue).toString(), setting.getDescription(), false);
        }
        return builder.build();
    }

    public Map<String, Integer> getSettings(){
        Map<String, Integer> record = new HashMap<>();
        settings.forEach((k, v) -> {
           record.put(k.name(), v);
        });
        return record;
    }
}
