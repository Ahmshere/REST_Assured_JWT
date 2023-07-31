package html;

public class HelperWithMain {
    public static void main(String[]args){
        System.out.println("Черный: \u001B[30m" + "Этот текст черным цветом" + "\u001B[0m");
        System.out.println("Красный: \u001B[31m" + "Этот текст красным цветом" + "\u001B[0m");
        System.out.println("Зеленый: \u001B[32m" + "Этот текст зеленым цветом" + "\u001B[0m");
        System.out.println("Желтый: \u001B[33m" + "Этот текст желтым цветом" + "\u001B[0m");
        System.out.println("Синий: \u001B[34m" + "Этот текст синим цветом" + "\u001B[0m");
        System.out.println("Пурпурный: \u001B[35m" + "Этот текст пурпурным цветом" + "\u001B[0m");
        System.out.println("Голубой: \u001B[36m" + "Этот текст голубым цветом" + "\u001B[0m");
        System.out.println("Белый: \u001B[37m" + "Этот текст белым цветом" + "\u001B[0m");
        System.out.println("Жирный текст: \u001B[1m" + "Этот текст жирным" + "\u001B[0m");
        System.out.println("Подчеркнутый текст: \u001B[4m" + "Этот текст подчеркнутый" + "\u001B[0m");
        System.out.println("Наклонный текст: \u001B[3m" + "Этот текст наклонный" + "\u001B[0m");
        System.out.println("Комбинация стилей: \u001B[1m\u001B[4m" + "Жирный и подчеркнутый текст" + "\u001B[0m");


    }
}
