import java.util.*;
class FitnessTracker{
    private String userName;
    private int dailySteps;
    private double dailyCaloriesBurned;
    private int workoutMinutes;
    private int[] weeklySteps;
    private double goalSteps;
    private double goalCalories;
    private int totalSteps;

    public FitnessTracker(String userName, double goalCalories, double goalSteps) {
        this.userName = userName;
        this.goalCalories = goalCalories;
        this.goalSteps = goalSteps;
        weeklySteps = new  int[7];
    }
    void addSteps(int steps){
        if (steps>0){
            dailySteps += steps;
        }else System.out.println("Steps can't negative");

    }

    void recordWorkout(int minutes, String workoutType){
        if (minutes>0){
            workoutMinutes += minutes;
            dailyCaloriesBurned += calculateCaloriesBurned(minutes, workoutType);
        }else System.out.println("Minutes can't negative");

    }
    int getTotalSteps(){
        return totalSteps;
    }
    double calculateCaloriesBurned(int minutes, String workoutType) {
        workoutType = workoutType.toLowerCase();
        if (workoutType.equals("running")) {
            return 10 * minutes;
        } else if (workoutType.equals("walking")) {
            return 5 * minutes;
        } else if (workoutType.equals("cycling")) {
            return 8 * minutes;
        } else if (workoutType.equals("swimming")) {
            return 12 * minutes;
        } else {
            return 6 * minutes;
        }
    }

    double getDailyProgress(){
        return (dailyCaloriesBurned*100)/goalCalories;
    }
    void updateWeeklySteps(int dayIndex , int steps){
        if (dayIndex<0 || dayIndex>weeklySteps.length){
            System.out.println("Invalid day index. Must be between 0 and 6.");
            return;
        }
        if (steps>0){
            weeklySteps[dayIndex] = steps;
        }else System.out.println("Steps can't negative");
    }
    double getWeeklyAverage(){
        double sum=0.0;
        for (int steps :  weeklySteps){
            sum += steps;
        }
        totalSteps = (int) sum;
        return sum / 7.0;
    }
    int getBestDay(){
        int index = 0;
        for (int i = 1; i < weeklySteps.length; i++) {
            if (weeklySteps[i] > weeklySteps[index]) {
                index = i;
            }
        }
        return index;
    }
    boolean isGoalAchieved(){
        if (dailyCaloriesBurned>=goalCalories && dailySteps>=goalSteps){
            return true;
        }
        return false;
    }
    void resetDailyStats(){
        this.dailyCaloriesBurned= 0.0;
        this.dailySteps= 0;
        this.workoutMinutes=0;
    }
    void recordWorkout (int minutes){
        if (minutes>0){
            workoutMinutes += minutes;
        }else System.out.println("Minutes can't negative");
    }

    String getFitnessReport(){
        String report = "===== Fitness Report =====\n";
        report += "User: " + userName + "\n\n";
        report += "---- Daily Stats ----\n";
        report += "Steps: " + dailySteps + "\n";
        report += "Calories Burned: " + dailyCaloriesBurned + "\n";
        report += "Workout Minutes: " + workoutMinutes + "\n";
        report += "Daily Progress: " + getDailyProgress() + "%\n\n";
        report += "---- Weekly Stats ----\n";
        report += "Weekly Average Steps: " + getWeeklyAverage() + "\n";
        report += "Best Day Index: " + getBestDay() + "\n\n";
        report += "---- Goals ----\n";
        report += "Step Goal: " + goalSteps + "\n";
        report += "Calorie Goal: " + goalCalories + "\n";
        report += "Goal Achieved: ";
        if (isGoalAchieved()){
            report += "Yes";
        }else report += "No";
        return report;
    }
    static void compareProgress(FitnessTracker user1 , FitnessTracker user2){
        if (user1.getDailyProgress() > user2.getDailyProgress()){
            System.out.println("User 1 Win");
        }else System.out.println("User 2 Win");
    }

}
public class Task6 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        FitnessTracker user1 = new FitnessTracker("Asif", 10000, 500);
        FitnessTracker user2 = new FitnessTracker("Maria", 8000, 400);

        while (true) {
            System.out.println("\n===== Fitness Tracker Menu =====");
            System.out.println("1. Add Steps");
            System.out.println("2. Record Workout");
            System.out.println("3. Update Weekly Steps");
            System.out.println("4. View Daily Progress");
            System.out.println("5. Check Goal Achievement");
            System.out.println("6. View Fitness Report");
            System.out.println("7. Compare User Progress");
            System.out.println("8. Reset Daily Stats");
            System.out.println("9. Exit");
            System.out.print("Enter your choice: ");

            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    System.out.print("Enter steps to add: ");
                    int steps = sc.nextInt();
                    user1.addSteps(steps);
                    System.out.println("Steps added");
                    break;
                case 2:
                    System.out.print("Enter workout minutes: ");
                    int minutes = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter workout type (Running/Walking/Cycling/Swimming): ");
                    String type = sc.nextLine();
                    user1.recordWorkout(minutes, type);
                    System.out.println("Workout recorded");
                    break;
                case 3:
                    System.out.print("Enter day index (0-6): ");
                    int day = sc.nextInt();
                    System.out.print("Enter steps for that day: ");
                    int weeklySteps = sc.nextInt();
                    user1.updateWeeklySteps(day, weeklySteps);
                    System.out.println("Weekly steps updated");
                    break;
                case 4:
                    System.out.println("Daily Progress: " + user1.getDailyProgress() + "%");
                    break;
                case 5:
                    if (user1.isGoalAchieved())
                        System.out.println("Congratulations! Goals achieved!");
                    else
                        System.out.println("Goals not achieved yet");
                    break;
                case 6:
                    System.out.println(user1.getFitnessReport());
                    break;
                case 7:
                   FitnessTracker.compareProgress(user1, user2);
                    break;
                case 8:
                    user1.resetDailyStats();
                    System.out.println("Daily stats reset.");
                    break;
                case 9:
                    System.out.println("Exiting program...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}
