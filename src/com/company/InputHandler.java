package com.company;

import java.util.Arrays;
import java.util.function.Predicate;
import java.util.function.Function;
import java.util.Scanner;
import java.util.Set;
import java.util.HashSet;

public class InputHandler<T> {
    private final Scanner scanner;
    private final Function<String, T> parser;
    private final String typeName;

    public InputHandler(Class<T> type) {
        this(type, new Scanner(System.in));
    }

    @SuppressWarnings("unchecked")
    public InputHandler(Class<T> type, Scanner scanner) {
        this.scanner = scanner;
        this.typeName = type.getSimpleName();

        // Define default parsers for common types
        if (type == Integer.class) {
            this.parser = str -> (T) Integer.valueOf(str);
        } else if (type == Double.class) {
            this.parser = str -> (T) Double.valueOf(str);
        } else if (type == Boolean.class) {
            this.parser = str -> (T) Boolean.valueOf(str);
        } else if (type == String.class) {
            this.parser = str -> (T) str;
        } else {
            throw new IllegalArgumentException("Unsupported type: " + type.getName());
        }
    }

    public InputHandler(Function<String, T> customParser, String typeName) {
        this(customParser, typeName, new Scanner(System.in));
    }

    public InputHandler(Function<String, T> customParser, String typeName, Scanner scanner) {
        this.scanner = scanner;
        this.parser = customParser;
        this.typeName = typeName;
    }

    public T getInput(String prompt, Predicate<T> validator, String validationMessage) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();

            try {
                T parsedInput = parser.apply(input);
                if (validator.test(parsedInput)) {
                    return parsedInput;
                } else {
                    System.out.println(validationMessage);
                }
            } catch (Exception e) {
                System.out.printf("Invalid input. Please enter a valid %s.%n", typeName);
            }
        }
    }

    @SuppressWarnings("unchecked")
    public T getInput(String prompt, T min, T max) {
        if (!(min instanceof Comparable && max instanceof Comparable)) {
            throw new IllegalArgumentException("Min and max must be Comparable");
        }
        if (min == null || max == null) {
            throw new IllegalArgumentException("Min and max cannot be null");
        }

        String validationMessage = String.format("Please enter a value between %s and %s", min, max);

        return getInput(
                prompt,
                value -> {
                    Comparable<T> minComp = (Comparable<T>) min;
                    Comparable<T> maxComp = (Comparable<T>) max;
                    return minComp.compareTo(value) <= 0 && maxComp.compareTo(value) >= 0;
                },
                validationMessage
        );
    }

    @SafeVarargs
    public final T getInput(String prompt, T... validOptions) {
        if (validOptions == null || validOptions.length == 0) {
            throw new IllegalArgumentException("At least one valid option must be provided");
        }
        
        Set<T> validSet = new HashSet<>(Arrays.asList(validOptions));
        String validationMessage = "Valid options are: " + Arrays.toString(validOptions);

        return getInput(
                prompt,
                validSet::contains,
                validationMessage
        );
    }

    public String getStringInputIgnoreCase(String prompt, String... validOptions) {
        if (validOptions == null || validOptions.length == 0) {
            throw new IllegalArgumentException("At least one valid option must be provided");
        }
        
        Set<String> validSet = new HashSet<>();
        for (String option : validOptions) {
            validSet.add(option.toLowerCase());
        }

        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim().toLowerCase();
            if (validSet.contains(input)) {
                for (String option : validOptions) {
                    if (option.toLowerCase().equals(input)) {
                        return option;
                    }
                }
                return input;
            }
            System.out.println("Valid options are: " + Arrays.toString(validOptions));
        }
    }

    public void close() {
        scanner.close();
    }
}
