package bot;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Bot extends TelegramLongPollingBot implements BotUtils {

    String chatId;
    Map<String, String> state = new HashMap<>();

    @Override
    public String getBotUsername() {
        return USERNAME;
    }

    @Override
    public String getBotToken() {
        return TOKEN;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage()) {
            String text = update.getMessage().getText();
            this.chatId = update.getMessage().getChatId().toString();

            SendMessage sendMessage = new SendMessage();
            sendMessage.setChatId(this.chatId);

            switch (text) {
                case START, BACK -> {
                    state.put(this.chatId, START);
                    sendMessage.setText(CHOOSE_COMMAND);
                    sendMessage.setReplyMarkup(buttons());
                }
                case SEARCH_USER_BY_PASSPORT_NUMBER -> {
                    state.put(this.chatId, SEARCH_USER_BY_PASSPORT_NUMBER);
                    sendMessage.setText(ENTER_PASSPORT_NUMBER);
                    sendMessage.setReplyMarkup(buttons());
                }
                case SEARCH_CAR_BY_NUMBER -> {
                    state.put(this.chatId, SEARCH_CAR_BY_NUMBER);
                    sendMessage.setText(ENTER_CAR_NUMBER);
                    sendMessage.setReplyMarkup(buttons());
                }
                case GET_FINES_BY_CAR_NUMBER -> {
                    state.put(this.chatId, GET_FINES_BY_CAR_NUMBER);
                    sendMessage.setText(ENTER_CAR_NUMBER_TO_GET_FINES);
                    sendMessage.setReplyMarkup(buttons());
                }
                case GET_FINES_BY_MONTH_AND_CAR_NUMBER -> {
                    state.put(this.chatId, GET_FINES_BY_MONTH_AND_CAR_NUMBER);
                    sendMessage.setText(ENTER_MONTH_AND_CAR_NUMBER_TO_GET_FINES);
                    sendMessage.setReplyMarkup(buttons());
                }
                case GET_FINES_BY_USER_ID -> {
                    state.put(this.chatId, GET_FINES_BY_USER_ID);
                    sendMessage.setText(ENTER_USER_ID_TO_GET_FINES);
                    sendMessage.setReplyMarkup(buttons());
                }
                default -> {
                    switch (state.get(this.chatId)) {
                        case SEARCH_USER_BY_PASSPORT_NUMBER -> {
                            sendMessage.setText(BotService.findUserByPassportNumber(text));
                            sendMessage.setReplyMarkup(buttons());
                        }
                        case SEARCH_CAR_BY_NUMBER -> {
                            sendMessage.setText(BotService.findCarByNumber(text));
                            sendMessage.setReplyMarkup(buttons());
                        }
                        case GET_FINES_BY_CAR_NUMBER -> {
                            sendMessage.setText(BotService.sendFinesByCarNumber(text));
                            sendMessage.setReplyMarkup(buttons());
                        }
                        case GET_FINES_BY_MONTH_AND_CAR_NUMBER -> {
                            sendMessage.setText(BotService.sendFinesByMonthAndCarNumber(text));
                            sendMessage.setReplyMarkup(buttons());
                        }
                        case GET_FINES_BY_USER_ID -> {
                            sendMessage.setText(BotService.sendFinesByUserId(text));
                            sendMessage.setReplyMarkup(buttons());
                        }
                        default -> {
                            sendMessage.setText(WRONG_COMMAND);
                            sendMessage.setReplyMarkup(buttons());
                        }
                    }
                }
            }


            try {
                execute(sendMessage);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }

        }
    }

    private ReplyKeyboardMarkup buttons() {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        List<KeyboardRow> keyboardRowList = new ArrayList<>();
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setKeyboard(keyboardRowList);

        if(state.get(this.chatId).equals(START)) {
            KeyboardRow keyboardRow = new KeyboardRow();
            keyboardRow.add(SEARCH_USER_BY_PASSPORT_NUMBER);

            KeyboardRow keyboardRow1 = new KeyboardRow();
            keyboardRow1.add(SEARCH_CAR_BY_NUMBER);

            KeyboardRow keyboardRow2 = new KeyboardRow();
            keyboardRow2.add(GET_FINES_BY_CAR_NUMBER);

            KeyboardRow keyboardRow3 = new KeyboardRow();
            keyboardRow3.add(GET_FINES_BY_MONTH_AND_CAR_NUMBER);

            KeyboardRow keyboardRow4 = new KeyboardRow();
            keyboardRow4.add(GET_FINES_BY_USER_ID);

            keyboardRowList.add(keyboardRow);
            keyboardRowList.add(keyboardRow1);
            keyboardRowList.add(keyboardRow2);
            keyboardRowList.add(keyboardRow3);
            keyboardRowList.add(keyboardRow4);
        } else {

            KeyboardRow keyboardRow = new KeyboardRow();
            keyboardRow.add(BACK);

            keyboardRowList.add(keyboardRow);
        }

        return replyKeyboardMarkup;
    }

}
