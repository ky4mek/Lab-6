package com.company;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) { // Главный метод
        Scanner sc = new Scanner(System.in, StandardCharsets.UTF_8); // Создание экземляра класса Scanner для считывания и обработки вводимых данных

        try {
            // Создается файл
            File f1 = new File("first.txt");
            f1.createNewFile();    // файл создан
            System.out.println("Полный путь файла:	" + f1.getAbsolutePath());

            // Создается поток для записи с учетом типа данных – DataOutputStream,
            // и ему в качестве параметра передается поток FileOutputStream
            DataOutputStream dOut = new DataOutputStream(new FileOutputStream(f1));

            System.out.print("Введите строку для записи в файл => ");
            String s = sc.nextLine();
            dOut.writeUTF(s); // запись строки в формате UTF
            dOut.flush();    // дописываем несохраненные данные на диск
            dOut.close(); // закрываем поток

            // Создание файла result.txt и переписывание в него чисел из first.txt
            File f2 = new File("result.txt");
            f2.createNewFile();

            // поток для чтения из исходного файла first.txt
            DataInputStream rd = new DataInputStream(new FileInputStream(f1.getAbsolutePath()));
            // поток для записи в результирующий файл result.txt
            dOut = new DataOutputStream(new FileOutputStream(f2.getAbsolutePath()));
            try {
                while (true) {
                    // чтение - запись из одного файла в другой
                    String str = rd.readUTF();
                    int count = 0;
                    for (int i = 0; i < str.length(); i++) {
                        count++;
                    }
                    System.out.println("Количество символов в строке: " + count);
                    dOut.write(count);
                    System.out.println("Строка: " + str);
                    dOut.writeUTF(str);
                }
            } catch (EOFException e) {
            }
            dOut.flush();
            dOut.close();
            rd.close();
        } catch (IOException e) {
            System.out.println("Ошибка! " + e);
        }
    }
}
