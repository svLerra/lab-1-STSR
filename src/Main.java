import java.io.*;
import java.util.*;

public class Main {

    public static Node createSortedList(ArrayList<Integer> numbers) {
        if (numbers.isEmpty()) return null;

        Collections.sort(numbers);

        Node head = new Node(numbers.get(0));
        Node current = head;
        for (int i = 1; i < numbers.size(); i++) {
            current.next = new Node(numbers.get(i));
            current = current.next;
        }
        return head;
    }

    public static void printList(Node head, String name) {
        System.out.print(name + ": ");
        Node current = head;
        while (current != null) {
            System.out.print(current.value + " ");
            current = current.next;
        }
        System.out.println();
    }

    public static Node mergeSortedLists(Node head1, Node head2) {
        if (head1 == null) return head2;
        if (head2 == null) return head1;

        Node mergedHead;
        Node current;

        if (head1.value <= head2.value) {
            mergedHead = head1;
            head1 = head1.next;
        } else {
            mergedHead = head2;
            head2 = head2.next;
        }

        current = mergedHead;

        while (head1 != null && head2 != null) {
            if (head1.value <= head2.value) {
                current.next = head1;
                head1 = head1.next;
            } else {
                current.next = head2;
                head2 = head2.next;
            }
            current = current.next;
        }

        if (head1 != null) {
            current.next = head1;
        } else {
            current.next = head2;
        }

        return mergedHead;
    }

    public static void main(String[] args) {
        try {
            File file = new File("input.txt");
            if (!file.exists()) {
                FileWriter createFile = new FileWriter("input.txt");
                createFile.write("5 81 14 3 9 -25 4 27 2 6 0");
                createFile.close();
                System.out.println("Файл input.txt создан с тестовыми данными\n");
            }

            BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
            ArrayList<Integer> firstSet = new ArrayList<>();
            ArrayList<Integer> secondSet = new ArrayList<>();

            boolean isFirstSet = true;
            String line;

            while ((line = reader.readLine()) != null) {
                String[] numbers = line.trim().split("\\s+");
                for (String numStr : numbers) {
                    if (numStr.isEmpty()) continue;
                    int num = Integer.parseInt(numStr);

                    if (num < 0) {
                        isFirstSet = false;
                    } else if (isFirstSet) {
                        firstSet.add(num);
                    } else {
                        secondSet.add(num);
                    }
                }
            }
            reader.close();

            Node C1 = createSortedList(firstSet);
            Node C2 = createSortedList(secondSet);


            printList(C1, "C1 (упорядоченный)");
            printList(C2, "C2 (упорядоченный)");
            System.out.println();

            Node merged = mergeSortedLists(C1, C2);
            printList(merged, "Объединенный упорядоченный список");

        } catch (FileNotFoundException e) {
            System.out.println("Файл input.txt не найден!");
        } catch (IOException e) {
            System.out.println("Ошибка при чтении файла: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Ошибка: в файле содержатся некорректные числа!");
        }
    }
}