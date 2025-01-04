package com.app.controller.Admin;

import com.app.model.Person;
import java.util.*;
import java.util.stream.Collectors;

public class StreamApi {

    public static void main(String[] args) {
        // Original list of numbers
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        List<Integer> evenNumbers = numbers.stream()
                .filter(n -> n % 2 == 0)
                .collect(Collectors.toList());
        System.out.println("Even numbers: " + evenNumbers);

        List<String> names = Arrays.asList("John", "Jane", "Alice", "Bob");

        List<Integer> nameLengths = names.stream()
                .map(String::length)
                .collect(Collectors.toList());
        System.out.println("Lengths of names: " + nameLengths);

        List<Integer> numbersWithDuplicates = Arrays.asList(1, 2, 3, 3, 4, 4, 5, 5);
        List<Integer> distinctNumbers = numbersWithDuplicates.stream()
                .distinct()
                .collect(Collectors.toList());
        System.out.println("Distinct numbers: " + distinctNumbers);

        List<Integer> numbersToSort = Arrays.asList(5, 2, 9, 1, 7, 3);
        List<Integer> sortedNumbers = numbersToSort.stream()
                .sorted()
                .toList();
        System.out.println("Sorted numbers: " + sortedNumbers);

        List<Person> persons = Arrays.asList(
                new Person("John", 30),
                new Person("Alice", 25),
                new Person("Bob", 35),
                new Person("Charlie", 20)
        );

        List<Person> sortedPersons = persons.stream()
                .sorted(Comparator.comparing(Person::getAge))
                .toList();
        System.out.println("Persons sorted by age: " + sortedPersons);

        System.out.println("Peeking at even numbers:");
        List<Integer> peekedEvenNumbers = numbers.stream()
                .filter(n -> n % 2 == 0)
                .peek(n -> System.out.println("Processing number: " + n))
                .toList();
        System.out.println("Peeked even numbers: " + peekedEvenNumbers);

        Integer[] numberArray = numbers.toArray(Integer[]::new);
        System.out.println("Numbers as array: " + Arrays.toString(numberArray));

        // Calculate the sum of numbers
        Integer sum = numbers.stream()
                .reduce(0, Integer::sum);
        System.out.println("Sum of numbers: " + sum);

        // Check if there is any even number
        boolean hasEvenNumber = numbers.stream()
                .anyMatch(n -> n % 2 == 0);
        System.out.println("Contains even number: " + hasEvenNumber);

        // Check if all numbers are even
        boolean allEvenNumbers = numbers.stream()
                .allMatch(n -> n % 2 == 0);
        System.out.println("All numbers are even: " + allEvenNumbers);

        Optional<Integer> firstNumber = numbers.stream()
                .findFirst();
        System.out.println("First number in the list: " + firstNumber.orElse(null));

        Optional<Integer> anyNumber = numbers.stream()
                .filter(n -> n > 5)
                .findAny();
        System.out.println("Any number greater than 5: " + anyNumber.orElse(null));

        Optional<Integer> maxNumber = numbers.stream().max(Integer::compareTo);
        System.out.println("Maximum number: " + maxNumber.orElse(null));
    }
}
