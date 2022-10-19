package Tools;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

/**
 * @author vince zydea
 */
public class StringModifier {
    @NotNull
    @Contract(pure = true)
    public static String removeNonAlphaNumeric(@NotNull String s){
        return s.replaceAll("[^a-zA-Z0-9 ]", "");
    }

    @NotNull
    @Contract(value = "_, _, null -> new", pure = true)
    public static String[] sliceArray(int begin, int end, String[] a){
        if(a != null && a.length > 0){
            return Arrays.copyOfRange(a, begin, end);
        } else {
            return new String[]{};
        }
    }

    @NotNull
    public static String listItems(@NotNull String[] items){
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < items.length; i++) {
            builder.append(items[i]);
            if (i < (items.length - 2)) {
                builder.append(", ");
            } else if (i < items.length - 1) {
                builder.append(" and ");
            }
        }

        return builder.toString();
    }
}
