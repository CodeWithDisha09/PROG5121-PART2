/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.taskmanage;
import java.util.Scanner;
import java.util.ArrayList;
/**
 *
 * @author RC_Student_lab
 */

public class TaskManage {
    static Scanner scanner = new Scanner(System.in);
    static ArrayList<Task> tasks = new ArrayList<>();
    static ArrayList<String> developers = new ArrayList<>();
    static ArrayList<String> taskNames = new ArrayList<>();
    static ArrayList<String> taskIDs = new ArrayList<>();
    static ArrayList<Integer> taskDurations = new ArrayList<>();
    static ArrayList<String> taskStatuses = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println("Welcome to EasyKanban");
        int option;
        do {
            System.out.println("1) Add tasks");
            System.out.println("2) Show report");
            System.out.println("3) Display tasks with status 'done'");
            System.out.println("4) Display developer and duration of the task with the longest duration");
            System.out.println("5) Search for a task by name and display task details");
            System.out.println("6) Search for tasks assigned to a developer and display details");
            System.out.println("7) Delete a task by name");
            System.out.println("8) Quit");
            System.out.print("Enter your option: ");
            option = scanner.nextInt();
            switch (option) {
                case 1:
                    addTask();
                    break;
                case 2:
                    displayReport();
                    break;
                case 3:
                    displayTasksWithStatus("Done");
                    break;
                case 4:
                    displayTaskWithLongestDuration();
                    break;
                case 5:
                    searchTaskByName();
                    break;
                case 6:
                    searchTasksByDeveloper();
                    break;
                case 7:
                    deleteTaskByName();
                    break;
                case 8:
                    break;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        } while (option != 8);
    }

    public static void addTask() {
        scanner.nextLine();
        System.out.print("Enter task name: ");
        String name = scanner.nextLine();
        taskNames.add(name);

        Task task = new Task();
        task.name = name;
        task.number = tasks.size() + 1;

        System.out.print("Enter task description: ");
        task.description = scanner.nextLine();
        if (task.description.length() > 50) {
            System.out.println("Please enter a task description of less than 50 characters");
            return;
        }

        System.out.print("Enter developer first name: ");
        task.developerFirstName = scanner.nextLine();
        System.out.print("Enter developer last name: ");
        task.developerLastName = scanner.nextLine();
        developers.add(task.developerFirstName + " " + task.developerLastName);

        System.out.print("Enter task duration in hours: ");
        task.duration = scanner.nextInt();
        taskDurations.add(task.duration);

        task.id = (task.name.substring(0, 2) + ":" + task.number + ":" + task.developerLastName.substring(task.developerLastName.length() - 3)).toUpperCase();
        taskIDs.add(task.id);

        System.out.println("Select task status:");
        System.out.println("1) To Do");
        System.out.println("2) Done");
        System.out.println("3) Doing");
        int statusOption = scanner.nextInt();
        switch (statusOption) {
            case 1:
                task.status = "To Do";
                break;
            case 2:
                task.status = "Done";
                break;
            case 3:
                task.status = "Doing";
                break;
            default:
                System.out.println("Invalid option. Try again.");
                return;
        }
        taskStatuses.add(task.status);

        tasks.add(task);
        System.out.println("Task successfully captured with ID: " + task.id);
    }

    public static void displayReport() {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println("Task Name: " + taskNames.get(i));
            System.out.println("Developer: " + developers.get(i));
            System.out.println("Task Duration: " + taskDurations.get(i));
            System.out.println("Task ID: " + taskIDs.get(i));
            System.out.println("Task Status: " + taskStatuses.get(i));
            System.out.println();
        }
    }

    public static void displayTasksWithStatus(String status) {
        for (int i = 0; i < tasks.size(); i++) {
            if (taskStatuses.get(i).equalsIgnoreCase(status)) {
                System.out.println("Task Name: " + taskNames.get(i));
                System.out.println("Developer: " + developers.get(i));
                System.out.println("Task Duration: " + taskDurations.get(i));
                System.out.println("Task ID: " + taskIDs.get(i));
                System.out.println("Task Status: " + taskStatuses.get(i));
                System.out.println();
            }
        }
    }

    public static void displayTaskWithLongestDuration() {
        int longestDurationIndex = 0;
        for (int i = 1; i < tasks.size(); i++) {
            if (taskDurations.get(i) > taskDurations.get(longestDurationIndex)) {
                longestDurationIndex = i;
            }
        }
        System.out.println("Task Name: " + taskNames.get(longestDurationIndex));
        System.out.println("Developer: " + developers.get(longestDurationIndex));
        System.out.println("Task Duration: " + taskDurations.get(longestDurationIndex));
        System.out.println("Task ID: " + taskIDs.get(longestDurationIndex));
        System.out.println("Task Status: " + taskStatuses.get(longestDurationIndex));
    }

    public static void searchTaskByName() {
        scanner.nextLine();
        System.out.print("Enter the task name to search: ");
        String searchName = scanner.nextLine();
        boolean found = false;
        for (int i = 0; i < tasks.size(); i++) {
            if (taskNames.get(i).equalsIgnoreCase(searchName)) {
                System.out.println("Task Name: " + taskNames.get(i));
                System.out.println("Developer: " + developers.get(i));
                System.out.println("Task Duration: " + taskDurations.get(i));
                System.out.println("Task ID: " + taskIDs.get(i));
                System.out.println("Task Status: " + taskStatuses.get(i));
                System.out.println();
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Task not found.");
        }
    }

    public static void searchTasksByDeveloper() {
        scanner.nextLine();
        System.out.print("Enter the developer name to search: ");
        String searchDeveloper = scanner.nextLine();
        boolean found = false;
        for (int i = 0; i < tasks.size(); i++) {
            if (developers.get(i).equalsIgnoreCase(searchDeveloper)) {
                System.out.println("Task Name: " + taskNames.get(i));
                System.out.println("Task Status: " + taskStatuses.get(i));
                System.out.println();
                found = true;
            }
        }
        if (!found) {
            System.out.println("No tasks assigned to the specified developer.");
        }
    }

    public static void deleteTaskByName() {
        scanner.nextLine();
        System.out.print("Enter the task name to delete: ");
        String deleteName = scanner.nextLine();
        boolean found = false;
        for (int i = 0; i < tasks.size(); i++) {
            if (taskNames.get(i).equalsIgnoreCase(deleteName)) {
                tasks.remove(i);
                taskNames.remove(i);
                developers.remove(i);
                taskDurations.remove(i);
                taskIDs.remove(i);
                taskStatuses.remove(i);
                System.out.println("Task successfully deleted.");
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Task not found.");
        }
    }

    static class Task {
        String name;
        String description;
        String developerFirstName;
        String developerLastName;
        int duration;
        String id;
        String status;
        int number;
    }
}
