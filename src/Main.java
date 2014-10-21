/*
 * 2 days ago I have encountered strange behavior in one of the exercises:
 * /interfaces/exercise 13
 * from Eckel book
 * https://github.com/AndrewBaliushin/Learning-Java/blob/master/src/FactoryMethodExercise.java
 *
 * Outcome of unlikely results is very high (on my opinion).
 *
 * So, I decided to check how random is random.
 *
 * Created by Andrew Baiushin on 21.10.14.
 */

import java.util.*;
import java.text.*;

interface Randomizer {
    boolean getRandom();
}

class MathRandom implements Randomizer {
    @Override
    public boolean getRandom() {
        return Math.random() >= 0.5d;
    }
}

class RandomRandom implements Randomizer {
    @Override
    public boolean getRandom() {
        Random rd = new Random();
        return rd.nextBoolean();
    }
}

class Lab {

    private int[] counter;
    private int iterations;

    /**
     * Get dispersion of random booleans.
     *
     * Track and display how many times streaks (rows of consecutive booleans with same value) occurred.
     *
     * @param r -- Object, that generates booleans.
     * @param iterations -- number of generation cycles
     * @param streakLength -- Limit for streak length.
     */
    public void getDispersion(Randomizer r, int iterations, int streakLength) {
        this.iterations = iterations;
        counter = new int[streakLength];

        for (int i = 0; i < iterations; i++) {

            int innerCounter = 0;
            boolean lastState = true; //init required by If

            for (int j = 0; j < streakLength; j++) {
                if ( j == 0) {
                    lastState = r.getRandom();
                    innerCounter++;
                } else {
                    if (lastState == r.getRandom()) {
                        innerCounter++;
                        if ( j == ( streakLength - 1 )) {
                            rememberStreakLength(innerCounter);
                            break;
                        }
                    } else {
                        rememberStreakLength(innerCounter);
                        break;
                    }
                }
            }
        }

        display(r);

    }

    private void rememberStreakLength(int length) {
       counter[length - 1]++;
    }

    private void display(Randomizer r) {
        Formatter f = new Formatter(System.out);
        NumberFormat nf = new DecimalFormat(".########E00");
        String formatTemplate = "%-15s %25s %25s\n";
        String formatTemplateResults = "row of %-7s  %25s %25s\n";
        String randomizerClassName = r.getClass().getSimpleName();

        System.out.print("Generating random booleans with ");
        System.out.println(randomizerClassName);
        f.format(formatTemplate, "Streak", "Chance (Experiment)", "Chance (Theory)");
        f.format(formatTemplate, "------", "-------------------", "---------------");

        for (int i = 0; i < counter.length; i++) {
            //count probability
            double probTheory = Math.pow(0.5, (i+1));
            double probExperiment = ((double)counter[i]/(double)iterations);

            //convert to sci notation
            f.format(formatTemplateResults, i+1, nf.format(probExperiment), nf.format(probTheory));
        }

        System.out.println("Raw data" + Arrays.toString(counter));
        System.out.println( );

    }
}

public class Main {

    static int iterations = 10000;
    static int streakSize = 8;


    public static void main(String[] args) {

        List<Randomizer> randomizers = new ArrayList<Randomizer>();

        randomizers.add(new MathRandom());
        randomizers.add(new RandomRandom());

        Lab experimentTool = new Lab();

        for (Randomizer r : randomizers) {
            experimentTool.getDispersion(r, iterations, streakSize);
        }
    }
}
/*
Out
Generating random booleans with MathRandom
Streak                Chance (Experiment)           Chance (Theory)
------                -------------------           ---------------
row of 1                       .499743E00                     .5E00
row of 2                       .249677E00                    .25E00
row of 3                       .125148E00                   .125E00
row of 4                       .63166E-01                  .625E-01
row of 5                       .31281E-01                 .3125E-01
row of 6                       .15478E-01                .15625E-01
row of 7                         .782E-02                .78125E-02
row of 8                        .7687E-02               .390625E-02
Raw data[499743, 249677, 125148, 63166, 31281, 15478, 7820, 7687]

Generating random booleans with RandomRandom
Streak                Chance (Experiment)           Chance (Theory)
------                -------------------           ---------------
row of 1                       .499836E00                     .5E00
row of 2                       .250029E00                    .25E00
row of 3                       .125158E00                   .125E00
row of 4                       .62402E-01                  .625E-01
row of 5                       .31251E-01                 .3125E-01
row of 6                       .15639E-01                .15625E-01
row of 7                         .786E-02                .78125E-02
row of 8                        .7825E-02               .390625E-02
Raw data[499836, 250029, 125158, 62402, 31251, 15639, 7860, 7825]
 */

