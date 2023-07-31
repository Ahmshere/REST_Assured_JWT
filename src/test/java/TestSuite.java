


// Классы с тестами

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

import org.junit.runner.notification.Failure;

public class TestSuite {
    public static void main(String[] args) {
        // Запускаем тестовый класс с помощью JUnitCore.runClasses()
        Result result = JUnitCore.runClasses(TestApi2.class);

        // Обрабатываем результаты тестов
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }

        System.out.println("All tests passed: " + result.wasSuccessful());
    }
}
