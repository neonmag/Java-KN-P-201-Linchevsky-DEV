package step.learning;

import oop.OopDemo;
import step.learning.basics.BasicsDemo;
import step.learning.basics.FilesDemo;
public class App
{
    public static void main( String[] args )
    {
        //System.out.println( "Hello World!" );


        //int [][] nums = {
        //        {1,2,3},
        //        {4,5,6},
        //        {7,8,9},
        //};
//
        //for(int[] x : nums) {
        //    for(int y: x)
        //    {
        //        System.out.println(y);
        //    }
        //}
        //FilesDemo obj = new FilesDemo();
        //obj.run();
        //obj.run1();
        //obj.run2();
        //obj.HW();
        OopDemo objOOP = new OopDemo();
        objOOP.run();

    }
}
/*
Вступ.
Встановити:
1. JRE (Java Runtime Environment - аналог .NET - платформа запуску)
    https://www.oracle.com/java/technologies/downloads/
2. JDK (Java Developer Kit - набір розробника - компілятор + бібліотеки)
    за тим самим посиланням або через інструменти IDE
3. IDE (JetBrains Idea / NetBeans (Apache) / Eclipse / VS Code)

Новий проєкт.
1. Архетип (система збірки / управління пакетами ~ NuGet)
    Maven / Gradle / Ant /Idea
    Назва проєкту - довільна
    Архетип - quickstart
    ! розкрити додаткові налаштування, вписати групу "step.learning"
    Вибрати JDK, за відсутності - завантажити
2. Конфігурація запуску - після створення проєкту маємо лише розпакованний
    шаблон, потрібна конфігурація.
        меню Run - Edit Configuration - Add new - Application
        Вводимо назву конфігурації (довільна, App)
        Та вибираємо головний клас (з методом main)
3. Пробний запуск
*/
/*
Про Java
Парадигма - ООП
Покоління - 4
Запуск - трансльований, на базі платформи
Вихідний код - Юнікод, файли.java
Виконавчий код - проміжний, файли.class
Висока чутливість до організації:
 - назва файлу зберігається з назвою класу (реєстрочутливо) ->
    один файл - один клас (public)
 - назва пакету відповідає назві директорії
*/