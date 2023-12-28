package translate;

import com.cybozu.labs.langdetect.Detector;
import com.cybozu.labs.langdetect.DetectorFactory;
import com.cybozu.labs.langdetect.LangDetectException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class TranslateAPI {
	static String targetLanguage = "de"; // Na kakoi yazik perevodim
	public static void main(String[] args) {
		try {
			URL url = new URL("https://text-translator2.p.rapidapi.com/translate");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();

			conn.setRequestMethod("POST");
			conn.setRequestProperty("content-type", "application/x-www-form-urlencoded");
			conn.setRequestProperty("X-RapidAPI-Key", "ae85b32b53msh4322ee50b589bebp1d24aajsn1096587e4dbf");
			conn.setRequestProperty("X-RapidAPI-Host", "text-translator2.p.rapidapi.com");

			String postData = "source_language=en&target_language="+targetLanguage+"&text=What%20is%20your%20name%3F";
			conn.setDoOutput(true);
			conn.getOutputStream().write(postData.getBytes(StandardCharsets.UTF_8));

			if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
				BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
				StringBuilder response = new StringBuilder();
				String line;
				while ((line = reader.readLine()) != null) {
					response.append(line);
				}
				reader.close();

				// Обработка JSON-ответа
				String responseBody = response.toString();
				// Здесь можете использовать библиотеку org.json или любую другую для обработки JSON

				System.out.println("Ответ от сервера: " + responseBody);
				JSONObject jsonResponse = new JSONObject(responseBody);
				String translatedText = jsonResponse.getJSONObject("data").getString("translatedText");

				System.out.println("Переведенный текст: " + "\u001B[32m\""+ translatedText+"\u001B[37m"); // \u001B[32m
				System.out.println("DETECTED LANGUAGE : "+ detectLanguage(translatedText));
			} else {
				System.out.println("Ошибка при выполнении запроса. Код ответа: " + conn.getResponseCode());
			}

			conn.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static String detectLanguage(String text) {
		try {
			// Перед использованием детектора, загрузим профили языков
			DetectorFactory.loadProfile("profiles"); // Папка 'profiles' с профилями языков должна находиться в корневой директории вашего проекта

			Detector detector = DetectorFactory.create();
			detector.append(text);
			return detector.detect();
		} catch (LangDetectException e) {
			e.printStackTrace();
			return "Не удалось определить язык";
		}
	}
}
