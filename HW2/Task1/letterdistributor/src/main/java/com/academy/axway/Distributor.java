package com.academy.axway;

import java.io.*;
import java.nio.file.NoSuchFileException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class Distributor implements LettersDistribution {
    @Override
    public void doLettersDistribution(String directoryToSearch) throws NoSuchFileException {

        File file = new File(System.getProperty("user.home") + File.separator + "distribution" + File.separator + "letters_distribution.txt");
        file.getParentFile().mkdir();
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.getMessage();
        }

        try (FileWriter writer = new FileWriter(file)) {
            writer.append(findLetterOccurrences(directoryToSearch) + findLetterDistribution(directoryToSearch));


        } catch (IOException e) {
            e.printStackTrace();
        }



    }

    public static String findLetterOccurrences(String pathToSearch) {
        HashMap<String, Integer> alphabet = new HashMap<String, Integer>();
        for (char ch = 'a'; ch <= 'z'; ch++) {
            alphabet.put(String.valueOf(ch), 0);
        }

        File[] files = new File(pathToSearch).listFiles();

        for (File file : files) {

            if (file.getName().endsWith(".txt")) {

                try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                    String currentLine = reader.readLine();

                    while (currentLine != null) {

                        String[] letters = currentLine.split("");

                        for (int i = 0; i < letters.length; i++) {
                            if (alphabet.containsKey(letters[i])) {
                                alphabet.put(letters[i], alphabet.get(letters[i]).intValue() + 1);
                            }
                        }

                        currentLine = reader.readLine();
                    }


                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                continue;
            }

        }
        return printLetterOccurrences(alphabet);
    }

    public static String printLetterOccurrences(Map<String, Integer> map) {
        String output = "";
        for (String letter : map.keySet()) {
            String key = letter.toString();
            String value = map.get(letter).toString();
            output += key + " -> " + value + "\n";
        }
        return output;
    }

    public static String findLetterDistribution(String pathToSearch) {
        HashSet<String> fileNames = new HashSet<>();
        HashMap<String, HashSet<String>> alphabet = new HashMap<String, HashSet<String>>();

        for (char ch = 'a'; ch <= 'z'; ch++) {
            alphabet.put(String.valueOf(ch), new HashSet<String>());
        }


        File[] files = new File(pathToSearch).listFiles();


        for (File file : files) {

            if (file.getName().endsWith(".txt")) {

                try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                    String currentLine = reader.readLine();

                    while (currentLine != null) {

                        String[] letters = currentLine.split("");

                        for (int i = 0; i < letters.length; i++) {

                            if (alphabet.containsKey(letters[i])) {

                                alphabet.get(letters[i]).add(file.getName());


                            }

                        }

                        currentLine = reader.readLine();

                    }


                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                //System.out.println(file.getName() + " IS NOT A TXT FILE");
                continue;
            }

        }
        return printLetterDistribution(alphabet);
    }

    public static String printLetterDistribution(Map<String, HashSet<String>> map) {
        String output = "";
        for (String letter : map.keySet()) {
            String key = letter.toString();
            String value = map.get(letter).toString();
            if (map.get(letter).isEmpty()) {
                output += key + " -> NO_FILES_CONTAINING_LETTER_" + key + "\n";
            } else {
                output += key + " -> " + value + "\n";
            }
        }
        return output;
    }


}

