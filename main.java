package src.mydb;

import java.util.*;
import java.io.*;

class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("================================");
        System.out.println("       MY DATABASE v1.0");
        System.out.println("================================");

        while (true) {

            System.out.print("mydb> ");
            String command = sc.nextLine().trim();

            // =========================
            // EXIT
            // =========================

            if (command.equalsIgnoreCase("exit")) {

                System.out.println("Database closed.");
                break;
            }


            // =========================
            // CREATE TABLE
            // =========================

            if (command.toUpperCase()
                    .startsWith("CREATE TABLE OF NAME ")) {

                String prefix = "CREATE TABLE OF NAME ";

                String tableName =
                        command.substring(prefix.length()).trim();

                if (tableName.isEmpty()) {

                    System.out.println(
                            "Please enter table name."
                    );

                    continue;
                }

                File file = new File("database.db");

                try {

                    // Create database file if it doesn't exist
                    if (!file.exists()) {

                        file.createNewFile();
                    }

                    // Store table name
                    FileWriter writer =
                            new FileWriter(file, true);

                    writer.write(tableName);
                    writer.write("\n");

                    writer.close();

                    System.out.println(
                            "Table created: " + tableName
                    );

                } catch (IOException e) {

                    System.out.println(
                            "Error: " + e.getMessage()
                    );
                }

            }


            // =========================
            // RETRIEVE ALL DATABASE
            // =========================

            else if (command.equalsIgnoreCase(
                    "RETRIEVE ALL DATABASE")) {

                File file = new File("database.db");

                try {

                    // Create database file if it doesn't exist
                    if (!file.exists()) {

                        file.createNewFile();
                    }

                    // Open file for reading
                    BufferedReader reader =
                            new BufferedReader(
                                    new FileReader(file)
                            );

                    String line;

                    System.out.println();
                    System.out.println("All Tables:");
                    System.out.println("-----------");

                    boolean found = false;

                    while ((line = reader.readLine()) != null) {

                        if (!line.trim().isEmpty()) {

                            System.out.println(line);

                            found = true;
                        }
                    }

                    reader.close();

                    if (!found) {

                        System.out.println(
                                "No tables found."
                        );
                    }

                    System.out.println();

                } catch (IOException e) {

                    System.out.println(
                            "Error: " + e.getMessage()
                    );
                }

            }


            // =========================
            // UNKNOWN COMMAND
            // =========================

            else {

                System.out.println(
                        "Unknown command."
                );
            }
        }

        sc.close();
    }
}