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
import org.apache.log4j.Logger;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import org.telegram.telegrambots.exceptions.TelegramApiRequestException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
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

    static {
        weatherIcons.put("Clear", " Ясно ☀");
        weatherIcons.put("Rain", " Дождь ☔");
        weatherIcons.put("Snow", "Снег ❄");
        weatherIcons.put("Clouds", "Облачно ☁");
    }

    @Override
    public void onUpdateReceived(Update update) {
        //добавить try
        log.debug("Receive new Update. updateID: " + update.getUpdateId());
        Long chatId = update.getMessage().getChatId();
        String inputText = update.getMessage().getText();
        SendMessage message  = new SendMessage();
        Weather result;
        switch (inputText) {
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

        }
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

    private Weather getWeatherReport() throws IOException {
            //String weatherURL = "https://api.openweathermap.org/data/2.5/weather?q=Saratov&APPID=ed9089eae9a93ed613cd5a47f0f36cd9";
            String weatherURL = "https://api.openweathermap.org/data/2.5/weather?q=Saratov&units=metric&APPID=ed9089eae9a93ed613cd5a47f0f36cd9";
            URL urlObject = new URL(weatherURL);
            Weather result;

            HttpURLConnection connection = (HttpURLConnection) urlObject.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("User-Agent", USER_AGENT);

            int responseCode = connection.getResponseCode();
            if (responseCode == 404) {
                throw new IllegalArgumentException();
            }

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            System.out.println(response.toString());
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            //objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

            JsonNode mainNode = objectMapper.readTree(response.toString()).get("main");
            String temperature = mainNode.get("temp").toString();
            String weatherNode = objectMapper.readTree(response.toString()).get("weather").toString();
            System.out.println(weatherNode);
            List <WeatherJson> list = objectMapper.readValue(weatherNode, new TypeReference<List<WeatherJson>>(){});
            System.out.println(list.get(0).getId());
            System.out.println(list.get(0).getMain());
            System.out.println(list.get(0).getDescription());
            System.out.println(list.get(0).getIcon());
            String weatherDescription = weatherIcons.get(list.get(0).getMain());
            result = new Weather(Double.parseDouble(temperature), weatherDescription);

            return result;
        }





    @Override
    public String getBotUsername() {
        return userName;
    }

    @Override
    public String getBotToken() {
        return token;
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
}