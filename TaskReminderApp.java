import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.PriorityQueue;
import java.util.Scanner;

class Reminder implements Comparable<Reminder> {
    String task;
    Date deadline;

    public Reminder(String task, Date deadline) {
        this.task = task;
        this.deadline = deadline;
    }

    public int compareTo(Reminder other) {
        return this.deadline.compareTo(other.deadline);
    }

    public String toString() {
        SimpleDateFormat formatter = new SimpleDateFormat("EEE MMM yyyy || hh:mm a");
        return "[Deadline: " + formatter.format(deadline) + "] " + task;
    }
}

public class TaskReminderApp {
    public static void main(String[] args) {
        PriorityQueue<Reminder> reminders = new PriorityQueue<>();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nTask Reminder App");
            System.out.println("1. Add a reminder");
            System.out.println("2. View next reminder");
            System.out.println("3. Remove next reminder");
            System.out.println("4. View all reminders");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 1) {
                System.out.print("Enter task: ");
                String task = scanner.nextLine();
                System.out.print("Enter time: ");
                String deadlineInput = scanner.nextLine();
                Date deadline = parseDeadline(deadlineInput);

                if (deadline == null) {
                    System.out.println("Invalid deadline format. Please try again.");
                } else {
                    reminders.add(new Reminder(task, deadline));
                    System.out.println("Reminder added!");
                }
            } else if (choice == 2) {
                if (reminders.isEmpty()) {
                    System.out.println("No reminders available.");
                } else {
                    System.out.println("Next reminder: " + reminders.peek());
                }
            } else if (choice == 3) {
                if (reminders.isEmpty()) {
                    System.out.println("No reminders to remove.");
                } else {
                    System.out.println("Removed reminder: " + reminders.poll());
                }
            } else if (choice == 4) {
                if (reminders.isEmpty()) {
                    System.out.println("No reminders available.");
                } else {
                    System.out.println("All reminders:");
                    for (Reminder reminder : reminders) {
                        System.out.println(reminder);
                    }
                }
            } else if (choice == 5) {
                System.out.println("Exiting application. Goodbye!");
                break;
            } else {
                System.out.println("Invalid choice. Try again.");
            }
        }

        scanner.close();
    }

    private static Date parseDeadline(String input) {
        try {
            Calendar calendar = Calendar.getInstance();
            if (input.toLowerCase().endsWith("am") || input.toLowerCase().endsWith("pm")) {
                String[] parts = input.split("[: ]");
                int hour = Integer.parseInt(parts[0]);
                int minute = Integer.parseInt(parts[1]);
                boolean isPM = input.toLowerCase().contains("pm");

                if (isPM && hour != 12) {
                    hour += 12;
                } else if (!isPM && hour == 12) {
                    hour = 0;
                }

                calendar.set(Calendar.HOUR_OF_DAY, hour);
                calendar.set(Calendar.MINUTE, minute);
                calendar.set(Calendar.SECOND, 0);
                calendar.set(Calendar.MILLISECOND, 0);

                if (calendar.getTime().before(new Date())) {
                    calendar.add(Calendar.DAY_OF_MONTH, 1);
                }
                return calendar.getTime();
            } else if (input.toLowerCase().endsWith("minutes")) {
                int minutes = Integer.parseInt(input.split(" ")[0]);
                calendar.add(Calendar.MINUTE, minutes);
                return calendar.getTime();
            }
        } catch (Exception e) {
            return null;
        }
        return null;
    }
}
