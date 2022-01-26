package bot;

public interface BotUtils {
    // DATABASE CONNECTION
    String URL = "jdbc:postgresql://localhost:5432/Fine";
    String USER = "postgres";
    String PASSWORD = "smile181";

    // BOT
    String USERNAME = "@b2_smile_bot";
    String TOKEN = "5030642911:AAGbLvs899teKPu1u4ztf9GBPAUdHB1Rz4Y";

    // BOT COMMANDS
    String START = "/start";
    String CHOOSE_COMMAND = "Choose command";
    String WRONG_COMMAND = "Wrong command";
    String SEARCH_USER_BY_PASSPORT_NUMBER = "Search user by passport number";
    String ENTER_PASSPORT_NUMBER = "Enter passport number";
    String SEARCH_CAR_BY_NUMBER = "Search car by number";
    String ENTER_CAR_NUMBER = "Enter car number";
    String GET_FINES_BY_CAR_NUMBER = "Get fines by car number";
    String ENTER_CAR_NUMBER_TO_GET_FINES = "Enter car number to get fines";
    String GET_FINES_BY_MONTH_AND_CAR_NUMBER = "Get fines by month and car number";
    String ENTER_MONTH_AND_CAR_NUMBER_TO_GET_FINES = "Enter month and car number to get fines (\"[MONTH] [CAR NUMBER]\")";
    String GET_FINES_BY_USER_ID = "Get fines by user id";
    String ENTER_USER_ID_TO_GET_FINES = "Enter user id to get fines";
    String NOT_FOUND = "Not found";
    String BACK = "Back";
}
