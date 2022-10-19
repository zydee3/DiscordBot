package Guild.Manager;


import Core.Logging;

/**
 * @author vince zydea
 */
public class ChannelsManager extends Logging {
    /*
    private Map<TargetChannels, Integer> channels = new HashMap<>();

    public void add(Map<String, Integer> records){
        if(records == null || records.isEmpty()){
            return;
        }

        records.forEach((k,v) -> {
            add(TargetChannels.valueOf(k), v);
        });

    }

    public void add(@NotNull TargetChannels target, int value){
        if(target == null){
            return;
        }

        channels.put(target, value);
        getLogger().info("Adding Setting: {} -> {}", target.name(), value);
    }

    public int get(TargetChannels channel){
        return settings.containsKey(channel) ? settings.get(setting) : setting.getDefaultValue();
    }

    public List<Integer> getRecord(){
        List<Integer> values = new ArrayList<>();
        for(GuildSettings setting : GuildSettings.values()){
            values.add(get(setting));
        }
        return values;
    }

    public MessageEmbed getEmbed(){
        EmbedBuilder builder = new EmbedBuilder();
        builder.setColor(new Color(247, 255, 203));
        for(GuildSettings setting : GuildSettings.values()){
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

     */
}
