package classyCluster.models;

public class Pearson {
    public static double calculate(Article A, Article B) {

        // Calculate simple and squared sums
        double sumA = 0;
        double sumAsq = 0;
        for (Word word : A.words) {
            sumA += word.getCount();
            sumAsq += Math.pow(word.getCount(), 2);
        }

        double sumB = 0;
        double sumBsq = 0;
        for (Word word : B.words) {
            sumB += word.getCount();
            sumBsq += Math.pow(word.getCount(), 2);
        }

        // Calculate sum of products
        double pSum = 0;
        int n = Math.min(A.words.size(), B.words.size());
        for (int i = 0; i < n; i++) {
            pSum += A.words.get(i).getCount() * B.words.get(i).getCount();
        }

        // Calculate Pearson Score
        double num = pSum - (sumA * sumB / n);
        double den = Math.sqrt((sumAsq - Math.pow(sumA, 2) / n) * (sumBsq - Math.pow(sumB, 2) / n));

        if (den == 0) return 0;
        return 1.0 - num / den;
    }
}
