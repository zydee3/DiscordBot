package Core.Constants;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author vince zydea
 */
public class Constants {
    public static class Bot {
        public static final String NAME = "marshmallow";
        public static List<String> COMMAND_NAMES = Arrays.asList(new String[]{"m", "marsh", NAME});
        public static final String TOKEN = "NjYwNjc3NTE4NTQ5ODQzOTY4.XggZFw.nMWRD3bCPM9PMqmpTY_SGiO4ZB4";
        public static List<Long> DEVELOPERS = Arrays.asList(new Long[]{139914511023996928L});
    }

    public static class Mongo {
        public static final String SCHEMA = "discord";
        public static final String COLLECTION_GUILD = "guilds";
        public static final String COLLECTION_USER = "users";
    }

    public static class User {
        public static final int EXP_PER_LEVEL = 20;
        public static final int MAX_REWARD_POINTS = 1000000;
    }
}
