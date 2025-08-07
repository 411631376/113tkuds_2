public class GradeStatisticsSystem {
    public static void main(String[] args) {
        int[] scores = {85, 92, 78, 96, 87, 73, 89, 94, 82, 90};
        int sum = 0, max = scores[0], min = scores[0];
        for (int score : scores) {
            sum += score;
            if (score > max) max = score;
            if (score < min) min = score;
        }
        double avg = sum * 1.0 / scores.length;

        int[] grades = new int[5]; // A B C D F
        int aboveAvg = 0;
        for (int score : scores) {
            if (score >= 90) grades[0]++;
            else if (score >= 80) grades[1]++;
            else if (score >= 70) grades[2]++;
            else if (score >= 60) grades[3]++;
            else grades[4]++;
            if (score > avg) aboveAvg++;
        }

        System.out.println("平均: " + avg);
        System.out.println("最高: " + max);
        System.out.println("最低: " + min);
        System.out.println("A: " + grades[0] + ", B: " + grades[1] + ", C: " + grades[2] + ", D: " + grades[3] + ", F: " + grades[4]);
        System.out.println("高於平均人數: " + aboveAvg);
        System.out.println("--- 成績報表 ---");
        for (int score : scores) System.out.print(score + " ");
    }
}