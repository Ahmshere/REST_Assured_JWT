/*
import com.googlecode.juniversalchardet.CharsetDetector;
import com.googlecode.juniversalchardet.ReaderFactory;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

public class EncodingChecker {
    public static void main(String[] args) {
        String word = "стоп";

        for (int i = 0; i < word.length(); i++) {
            char character = word.charAt(i);

            // Определение кодировки для каждого символа
            String encoding = getEncoding(character);

            if (encoding != null) {
                System.out.println("Символ '" + character + "' принадлежит кодировке " + encoding);
            } else {
                System.out.println("Символ '" + character + "' неизвестной кодировки");
            }
        }
    }

    public static String getEncoding(char character) {
        try {
            byte[] bytes = new String(new char[]{character}).getBytes("ISO-8859-1");
            CharsetDetector detector = new CharsetDetector();
            detector.setText(new InputStreamReader(new ByteArrayInputStream(bytes), Charset.forName("ISO-8859-1")));
            CharsetMatch match = detector.detect();

            if (match != null) {
                return match.getName();
            } else {
                return "Неизвестная кодировка";
            }
        } catch (IOException e) {
            return "Ошибка при определении кодировки";
        }
    }
}
*/