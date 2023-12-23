package com.akshayram.operatingsys;

import java.io.*;

public class ProcessPoc {
  public static void main(String[] args) {
    try {
      // Command to run
      String command = "ls"; // replace with your command

      // Running the command
      Process process = Runtime.getRuntime().exec(command);

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
}
