import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.http.ConnectionClosedException;
import org.apache.log4j.Logger;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import org.telegram.telegrambots.exceptions.TelegramApiRequestException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
public class Bot extends TelegramLongPollingBot {
    private static final Logger log = Logger.getLogger(Bot.class);
    private String weatherApiKey = "ed9089eae9a93ed613cd5a47f0f36cd9";
    private final static String USER_AGENT = "Mozilla/5.0";
    private String city = "Саратов";
    final int RECONNECT_PAUSE = 10000;
    String userName = "simpl3_2020193_bot";
    String token = "1432180729:AAHzlrW3IbL2Mls8B_-HTvClIxYxpHwgW1A";
    public final static Map<String, String> weatherIcons = new HashMap<>();
    WebServices webServices = new WebServices();
    // Клавиатура
    ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
    public WeatherJson weatherJson;
    Utils utils = new Utils();

    static {
        weatherIcons.put("Clear", "Ясно ☀");
        weatherIcons.put("Rain", "Дождь ☔");
        weatherIcons.put("Snow", "Снег ❄");
        weatherIcons.put("Clouds", "Облачно ☁");
    }

    public void botConnect() {
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        try {
            telegramBotsApi.registerBot(this);
            log.info("TelegramAPI started. Look for messages");
        } catch (TelegramApiRequestException e) {
            log.error("Cant Connect. Pause " + RECONNECT_PAUSE / 1000 + "sec and try again. Error: " + e.getMessage());
            try {
                Thread.sleep(RECONNECT_PAUSE);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
                return;
            }
            botConnect();
        }
    }

    @Override
    public void onUpdateReceived(Update update) {
        log.debug("Receive new Update. updateID: " + update.getUpdateId());
        Long chatId = update.getMessage().getChatId();
        String inputText = update.getMessage().getText();
        SendMessage sendMessage  = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        Weather result;
        try {
            sendMessage.setText(getMessage(inputText, sendMessage));
            execute(sendMessage);
        } catch (TelegramApiException e){
            e.printStackTrace();
        }
        /*switch (inputText) {
            case "/weather":
                //message  = new SendMessage();
                message.setChatId(chatId);
                try {
                    result = getWeatherReport();
                    message.setText(result.getDescription() + "    " + result.getTemperature() + " \t°");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    execute(message);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
                break;
            default:
                message  = new SendMessage();
                message.setChatId(chatId);
                message.setText("Ошибка, пожалуйста введите команду из вышеперечисленных");
                break;
        }*/
        /*if (inputText.startsWith("/start")) {
            SendMessage message  = new SendMessage();
            message.setChatId(chatId);
            message.setText("Hey! This bot is working!");
            try {
                execute(message);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
        } */
    }

    private String getCovidStats(){

        //проверить на наличие каждого из файлов
        //скачать недостающее
        //распарсить каждый из файлов? но если уже скачан и уже использовали,
        //можно ли сохранять в переменную, чтобы не парсить?

        return utils.processCSVFile();
    }




    // TODO УБРАТЬ system.out.println!!! убрать webServices.downloadSCVFile();
    private WeatherJson getWeatherReport() throws IOException {
        String weatherURL = "https://api.openweathermap.org/data/2.5/weather?q=Saratov&units=metric&APPID=" + weatherApiKey;
        HttpURLConnection weatherConnection = webServices.getURLConnection(weatherURL);
        String weatherLine = webServices.getFileContent(weatherConnection);

       /* BufferedReader in = new BufferedReader(new InputStreamReader(weatherConnection.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        */
        return weatherJson.parseWeatherJson(weatherLine);
        // TODO Вынести в отдельный метод по обработке json запроса из класса WeatherJson.java
        /*ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        JsonNode mainNode = objectMapper.readTree(weatherLine).get("main");
        String temperature = mainNode.get("temp").toString();
        String weatherNode = objectMapper.readTree(weatherLine).get("weather").toString();
        System.out.println(weatherNode);
        List <WeatherJson> weatherJsonList = objectMapper.readValue(weatherNode, new TypeReference<List<WeatherJson>>(){});
        String weatherDescription = weatherIcons.get(weatherJsonList.get(0).getMain());

        Weather result = new Weather(Double.parseDouble(temperature), weatherDescription);
        WeatherJson weather = new WeatherJson(mainNode.toString(), Double.parseDouble(temperature), weatherJsonList.get(0).getMain(), weatherDescription);
        return weather; */
        }

    private String getMessage(String msg, SendMessage sendMessage){
        ArrayList <KeyboardRow> buttons = new ArrayList<>();
        KeyboardRow keyBoardFirstRow = new KeyboardRow();
        KeyboardRow keyBoardSecondRow = new KeyboardRow();
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);
        msg = msg.toLowerCase();
        //message.setReplyMarkup(replyKeyboardMarkup);
        //TODO нужно ли возвращать выбрать постоянно?
        if (msg.equals("меню")){
            buttons.clear();
            keyBoardFirstRow.clear();
            keyBoardFirstRow.add("Погода");
            keyBoardFirstRow.add("Пробки");
            keyBoardSecondRow.add("Статистика по COVID-19");
            buttons.add(keyBoardFirstRow);
            buttons.add(keyBoardSecondRow);
            replyKeyboardMarkup.setKeyboard(buttons);
            return "Выбрать";
        }
        if (msg.equals("погода")){
            /*
            buttons.clear();
            keyBoardFirstRow.clear();
            keyBoardFirstRow.add("Меню");
            keyBoardFirstRow.add("Covid-19");
            keyBoardSecondRow.add("Меню");
            buttons.add(keyBoardFirstRow);
            buttons.add(keyBoardSecondRow);
            replyKeyboardMarkup.setKeyboard(buttons);
            */
            try {
                WeatherJson weatherReport = getWeatherReport();
                sendMessage.setText(weatherReport.getIcon() + "    " + weatherReport.getTemperature() + "\t°С");
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                execute(sendMessage);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }


            return "Выбрать";
        }
        if (msg.equals("пробки")){
            buttons.clear();
            keyBoardFirstRow.clear();
            keyBoardFirstRow.add("Погода");
            keyBoardFirstRow.add("Пробки");
            keyBoardSecondRow.add("Статистика по COVID-19");
            buttons.add(keyBoardFirstRow);
            buttons.add(keyBoardSecondRow);
            replyKeyboardMarkup.setKeyboard(buttons);
            return "Выбрать...";
        }
        if (msg.equals("статистика по covid-19")){
            sendMessage.setText("Число заболевших в Саратовской области  " + getCovidStats());
            try {
                execute(sendMessage);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
            return "Выбрать";
        }
        return "";
    }




    @Override
    public String getBotUsername() {
        return userName;
    }

    @Override
    public String getBotToken() {
        return token;
    }
}