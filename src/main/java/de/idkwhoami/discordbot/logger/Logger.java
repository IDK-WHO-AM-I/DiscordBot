package de.idkwhoami.discordbot.logger;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logger {

    private final String PREFIX;
    private final String SUCCESS = ANSI_BRIGHT_BG_BLACK + ANSI_BRIGHT_GREEN + "[SUCCESS]" + ANSI_RESET;
    private final String INFO = ANSI_BRIGHT_BG_BLACK + ANSI_BRIGHT_WHITE + "[INFO]" + ANSI_RESET;
    private final String ERROR = ANSI_BRIGHT_BG_BLACK + ANSI_BRIGHT_RED + "[ERROR]" + ANSI_RESET;
    private final String WARNING = ANSI_BRIGHT_BG_BLACK + ANSI_BRIGHT_YELLOW + "[WARNING]" + ANSI_RESET;

    public Logger(String prefix) {
        this.PREFIX = ANSI_BRIGHT_BG_BLACK + "[" + prefix + "]" + ANSI_RESET;

    }

    private String dateTime() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("E dd-MM-yyyy hh:mm:ss a"));
    }

    public void log(String... messages) {
        String message = String.join("\n", messages);
        System.out.println(ANSI_BRIGHT_BG_BLACK + "[" + dateTime() + "]" + ANSI_RESET + PREFIX + INFO + ANSI_BRIGHT_BG_BLACK + "   »  " + message);
    }

    public void success(String... messages) {
        String message = String.join("\n", messages);
        System.out.println(ANSI_BRIGHT_BG_BLACK + "[" + dateTime() + "]" + ANSI_RESET + PREFIX + SUCCESS + ANSI_BRIGHT_BG_BLACK + "   »  " + message);
    }

    public void error(String... messages) {
        String message = String.join("\n", messages);
        System.out.println(ANSI_BRIGHT_BG_BLACK + "[" + dateTime() + "]" + ANSI_RESET + PREFIX + ERROR + ANSI_BRIGHT_BG_BLACK + "  »  " + ANSI_BRIGHT_BG_BLACK + ANSI_RED + message);
    }

    public void warning(String... messages) {
        String message = String.join("\n", messages);
        System.out.println(ANSI_BRIGHT_BG_BLACK + "[" + dateTime() + "]" + ANSI_RESET + PREFIX + WARNING + ANSI_BRIGHT_BG_BLACK + "»  " + ANSI_BRIGHT_BG_BLACK + ANSI_BRIGHT_YELLOW + message);
    }

    public static void main(String[] args) {
        Logger logger = new Logger("TEST");
        logger.log("simple logged message");
        logger.error("an error as occoured!");
        logger.warning("this is a warning");
    }

    //region Colors
    public static final String ANSI_RESET = "\u001B[0m";

    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public static final String ANSI_BRIGHT_BLACK = "\u001B[90m";
    public static final String ANSI_BRIGHT_RED = "\u001B[91m";
    public static final String ANSI_BRIGHT_GREEN = "\u001B[92m";
    public static final String ANSI_BRIGHT_YELLOW = "\u001B[93m";
    public static final String ANSI_BRIGHT_BLUE = "\u001B[94m";
    public static final String ANSI_BRIGHT_PURPLE = "\u001B[95m";
    public static final String ANSI_BRIGHT_CYAN = "\u001B[96m";
    public static final String ANSI_BRIGHT_WHITE = "\u001B[97m";

    public static final String ANSI_BG_BLACK = "\u001B[40m";
    public static final String ANSI_BG_RED = "\u001B[41m";
    public static final String ANSI_BG_GREEN = "\u001B[42m";
    public static final String ANSI_BG_YELLOW = "\u001B[43m";
    public static final String ANSI_BG_BLUE = "\u001B[44m";
    public static final String ANSI_BG_PURPLE = "\u001B[45m";
    public static final String ANSI_BG_CYAN = "\u001B[46m";
    public static final String ANSI_BG_WHITE = "\u001B[47m";

    public static final String ANSI_BRIGHT_BG_BLACK = "\u001B[100m";
    public static final String ANSI_BRIGHT_BG_RED = "\u001B[101m";
    public static final String ANSI_BRIGHT_BG_GREEN = "\u001B[102m";
    public static final String ANSI_BRIGHT_BG_YELLOW = "\u001B[103m";
    public static final String ANSI_BRIGHT_BG_BLUE = "\u001B[104m";
    public static final String ANSI_BRIGHT_BG_PURPLE = "\u001B[105m";
    public static final String ANSI_BRIGHT_BG_CYAN = "\u001B[106m";
    public static final String ANSI_BRIGHT_BG_WHITE = "\u001B[107m";
    //endregion
}
