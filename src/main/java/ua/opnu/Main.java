package ua.opnu;

import java.util.Arrays;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class Main {

    // --- ДОПОМІЖНІ МЕТОДИ ЛАБОРАТОРНОЇ ---

    // Метод фільтрації для студентів (Завдання 2)
    public static Student[] filterStudents(Student[] students, Predicate<Student> predicate) {
        Student[] result = new Student[students.length];
        int counter = 0;
        for (Student s : students) {
            if (predicate.test(s)) {
                result[counter] = s;
                counter++;
            }
        }
        return Arrays.copyOfRange(result, 0, counter);
    }

    // Метод фільтрації масиву цілих чисел за двома предикатами (Завдання 3)
    public static int[] filterTwoPredicates(int[] input, Predicate<Integer> p1, Predicate<Integer> p2) {
        int[] result = new int[input.length];
        int counter = 0;
        Predicate<Integer> combined = p1.and(p2); // Об'єднання умов
        for (int i : input) {
            if (combined.test(i)) {
                result[counter] = i;
                counter++;
            }
        }
        return Arrays.copyOfRange(result, 0, counter);
    }

    // Метод foreach для перебору студентів через Consumer (Завдання 4)
    public static void forEachStudent(Student[] students, Consumer<Student> action) {
        for (Student s : students) {
            action.accept(s);
        }
    }

    // Метод, який виконує Consumer, якщо виконується умова Predicate (Завдання 5)
    public static void processWithCondition(int[] input, Predicate<Integer> predicate, Consumer<Integer> action) {
        for (int i : input) {
            if (predicate.test(i)) {
                action.accept(i);
            }
        }
    }

    // Метод stringify для перетворення масиву через Function (Завдання 7)
    public static String[] stringify(int[] input, Function<Integer, String> func) {
        String[] result = new String[input.length];
        for (int i = 0; i < input.length; i++) {
            result[i] = func.apply(input[i]);
        }
        return result;
    }

    // --- ГОЛОВНИЙ МЕТОД ЗАПУСКУ ---
    public static void main(String[] args) {

        // ЗАВДАННЯ 1: Предикат для перевірки на просте число
        System.out.println("=== ЗАВДАННЯ 1 ===");
        Predicate<Integer> isPrime = n -> {
            if (n <= 1) return false;
            for (int i = 2; i <= Math.sqrt(n); i++) {
                if (n % i == 0) return false;
            }
            return true;
        };
        System.out.println("Чи 7 просте? " + isPrime.test(7));  // true
        System.out.println("Чи 4 просте? " + isPrime.test(4));  // false


        // ЗАВДАННЯ 2: Фільтрація масиву студентів (без боргів < 60)
        System.out.println("\n=== ЗАВДАННЯ 2 ===");
        Student[] students = {
                new Student("Андрій", "АІ-253", new int[]{85, 90, 78}),
                new Student("Іван", "АІ-253", new int[]{45, 70, 80}), // має борг
                new Student("Марія", "АІ-253", new int[]{95, 92, 100})
        };

        Predicate<Student> noDebts = student -> {
            for (int mark : student.getMarks()) {
                if (mark < 60) return false;
            }
            return true;
        };

        Student[] filteredStudents = filterStudents(students, noDebts);
        System.out.println("Студенти без боргів:");
        for (Student s : filteredStudents) {
            System.out.println(s.getName());
        }


        // ЗАВДАННЯ 3: Фільтрація за двома умовами
        System.out.println("\n=== ЗАВДАННЯ 3 ===");
        int[] numbers = {10, 15, 20, 30, 33, 40};
        Predicate<Integer> greaterThan15 = x -> x > 15;
        Predicate<Integer> divisibleBy5 = x -> x % 5 == 0;

        int[] res3 = filterTwoPredicates(numbers, greaterThan15, divisibleBy5);
        System.out.println("Числа > 15 і кратні 5: " + Arrays.toString(res3));


        // ЗАВДАННЯ 4: Інтерфейс Consumer для виведення студентів
        System.out.println("\n=== ЗАВДАННЯ 4 ===");
        Consumer<Student> printStudent = s -> System.out.println("Студент: " + s.getName());
        forEachStudent(students, printStudent);


        // ЗАВДАННЯ 5: Метод із Predicate та Consumer
        System.out.println("\n=== ЗАВДАННЯ 5 ===");
        int[] nums = {5, 12, 18, 25, 30};
        processWithCondition(nums,
                n -> n % 2 == 0,
                n -> System.out.println("Знайдено парне число: " + n)
        );


        // ЗАВДАННЯ 6: Function (піднесення до степеня 2^n)
        System.out.println("\n=== ЗАВДАННЯ 6 ===");
        Function<Integer, Integer> powerOfTwo = n -> (int) Math.pow(2, n);
        int[] tens = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        int[] res6 = new int[tens.length];
        for (int i = 0; i < tens.length; i++) {
            res6[i] = powerOfTwo.apply(tens[i]);
        }
        System.out.println("Степені двійки (2^n): " + Arrays.toString(res6));


        // ЗАВДАННЯ 7: Метод stringify() з Function
        System.out.println("\n=== ЗАВДАННЯ 7 ===");
        int[] digits = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        Function<Integer, String> numberToString = n -> {
            switch (n) {
                case 0: return "нуль";
                case 1: return "один";
                case 2: return "два";
                case 3: return "три";
                case 4: return "чотири";
                case 5: return "п'ять";
                case 6: return "шість";
                case 7: return "сім";
                case 8: return "вісім";
                case 9: return "дев'ять";
                default: return "невідомо";
            }
        };

        String[] res7 = stringify(digits, numberToString);
        System.out.println("Числа словами: " + Arrays.toString(res7));
    }
}