package com.akshayram.operatingsys;

import java.io.*;
import java.time.LocalDateTime;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class ProcessPoc {
  public static void main(String[] args) {
    System.out.println("***Testing the command run...***");
    //        anotherWay();
    //        actualMethod();
    //    randomDaysGenerator();
  }

  public static void oneWay() {
    try {
      String command = "ls";
      // Running the command
      Process process = Runtime.getRuntime().exec(command); //
      // Reading the output
      BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
      String line;
      while ((line = reader.readLine()) != null) {
        System.out.println(line);
      }
      // Waiting for the command to complete
      process.waitFor();
    } catch (IOException | InterruptedException e) {
      e.printStackTrace();
    }
  }

  public static void anotherWay() {
    try {
      // Command to run
      List<String> commands = new ArrayList<>();
      commands.add("/bin/sh"); // Use /bin/sh for Unix-based systems
      commands.add("-c");
      commands.add("ls"); // replace with your command

      // Creating the process
      ProcessBuilder processBuilder = new ProcessBuilder(commands);
      Process process = processBuilder.start();

      // Reading the output
      BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
      String line;
      while ((line = reader.readLine()) != null) {
        System.out.println(line);
      }

      // Waiting for the command to complete
      process.waitFor();
    } catch (IOException | InterruptedException e) {
      e.printStackTrace();
    }
  }

  public static String getCustomDate(int year, int month, int day) {
    double randomDouble = Math.random();
    double scaledDouble = randomDouble * 23; // 23 is exclusive upper bound
    int hour = (int) scaledDouble;

    randomDouble = Math.random();
    scaledDouble = randomDouble * 59;
    int minute = (int) scaledDouble;

    randomDouble = Math.random();
    scaledDouble = randomDouble * 59;
    int second = (int) scaledDouble;

    LocalDateTime desiredDateTime = LocalDateTime.of(year, month, day, hour, minute, second);
    DateTimeFormatter formatter =
        DateTimeFormatter
            .ISO_LOCAL_DATE_TIME; // DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    String customTimestamp = desiredDateTime.format(formatter);
    System.out.println(
        "getCustomDate(): Custom Timestamp for the desired date: " + customTimestamp);
    return customTimestamp;
  }

  public static List<Integer> randomDaysGenerator() {
    int totalDaysInMonth = 31;
    int daysToGenerate = (int) (totalDaysInMonth * 0.45);
    List<Integer> allDays = new ArrayList<>();
    for (int i = 1; i <= totalDaysInMonth; i++) {
      allDays.add(i);
    }
    Collections.shuffle(allDays);
    List<Integer> selectedDays = allDays.subList(0, daysToGenerate);
    Collections.sort(selectedDays);
    System.out.println("Randomly selected days representing 75% of the month: " + selectedDays);
    return selectedDays;
  }
}
