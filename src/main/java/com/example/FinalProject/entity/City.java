package com.example.FinalProject.entity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class City {
    public int[][] distances;
    public String[] createArrays(File file)
    {
        System.out.println("FILE==>"+file);
        StringBuilder sb = new StringBuilder();
        try {
            Scanner scanner = new Scanner(file, "UTF-8");
            while (scanner.hasNextLine()) {
                sb.append(scanner.nextLine()).append(System.lineSeparator());
            }
            scanner.close();
        } catch (
                IOException ex) {
            ex.printStackTrace();
        }
        String inputFile = sb.toString().trim();
        inputFile = inputFile.replace("\n", " ").replace("\r", " ");
        List<String> lines = new ArrayList<>();
        lines.add(reader("\\b[a-zA-Z\\p{IsCyrillic}]\\w+\\b", inputFile));
        String[] cities = lines.toArray(new String[]{});
        System.out.println(cities.toString());
        System.out.println(cities.length);
        return cities;
    }

    static String reader(String regExp, String inputFile) {
        StringBuilder str = new StringBuilder();
        Pattern p = Pattern.compile(regExp, 256);
        Matcher m = p.matcher(inputFile);
        while(m.find()) {
            str.append(m.group()).append(" ");
        }
        return str.toString();
    }
}
