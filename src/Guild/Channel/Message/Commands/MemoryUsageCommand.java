package Guild.Channel.Message.Commands;

import Core.Enums.ErrorMessages;
import Guild.User.User;
import org.jetbrains.annotations.NotNull;

/**
 * @author vince zydea
 */
public class MemoryUsageCommand {
    @NotNull
    public static String handle(@NotNull User user){
        Runtime rt = Runtime.getRuntime();
        long total = rt.totalMemory();
        long free = rt.freeMemory();
        long used = total - free;
        return (((used) / (1024 * 1024)) + "mb (" + String.format("%.3f", (used*1.0)/total) + "%) of memory is currently being used.");

    }
}
