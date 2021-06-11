package genericAlgorithm;


import java.io.*;
import java.util.Random;

public class Main {


    public static final int TARGET_IS_REACHED_FLAG = -1;

    private static final int TARGET_NOT_REACHED_FLAG = -2;

    public static final int POPULATION_COUNT = 5;

    public static final int GENES_COUNT = 5;

    public static final int GENE_MIN = -200;

    public static final int GENE_MAX = 200;

    public static final float P = 5.0F;

    public static final int MAX_ITERATIONS = 100000;

    private Chromosome population[] = new Chromosome[Main.POPULATION_COUNT];

    private int fillChromosomesWithFitnesses(int stage,int targetValue) {
        for (int i = 0; i < POPULATION_COUNT; ++i) {
            float currentFitness = population[i].calculateFitness(stage,targetValue);
            population[i].setFitness(currentFitness);
            if (currentFitness == TARGET_IS_REACHED_FLAG)
                return i;
        }

        return TARGET_NOT_REACHED_FLAG;
    }

    public static int function(int u, int y, int w, int x, int z) {
        return u * w * x * x * y + y + w * w * x * z + u * w * x * z + u * w * x * z ;
    }

    private float getAllFitnessesSum() {
        float allFitnessesSum = .0F;
        for (int i = 0; i < POPULATION_COUNT; ++i) {
            allFitnessesSum += population[i].getFitness();
        }
        return allFitnessesSum;
    }

    private void fillChromosomeWithLikelihoods() {
        float allFitnessesSum = getAllFitnessesSum();
        float last = .0F;
        int i;
        for (i = 0; i < POPULATION_COUNT; ++i) {
            float likelihood = last + (100 * population[i].getFitness() / allFitnessesSum);
            last = likelihood;
            population[i].setLikelihood(likelihood);
        }
    }

    private void printAllChromosomes() {
        for (int i = 0; i < POPULATION_COUNT; ++i) {
            log(population[i].toString());
        }
    }

    public static void log(String message) {
        System.out.println(message);
    }

    public static int getRandomInt(int min, int max) {
        Random randomGenerator;
        randomGenerator = new Random();
        return randomGenerator.nextInt(2 * max + 1) + min;
    }

    public static float getRandomFloat(float min, float max) {
        return (float) (Math.random() * max + min);
    }

    public static int getRandomGene() {
        return getRandomInt(GENE_MIN, GENE_MAX);
    }


    private void fillChromosomeWithRandomGenes(Chromosome chromosome) {
        for (int i = 0; i < GENES_COUNT; ++i) {
            chromosome.getGenes()[i] = getRandomGene();
        }
    }

    private void createInitialPopulation() {
        for (int i = 0; i < POPULATION_COUNT; ++i) {
            population[i] = new Chromosome();
            fillChromosomeWithRandomGenes(population[i]);
        }

    }

    private int[][] getPairsForCrossover() {
        int[][] pairs = new int[POPULATION_COUNT][2];
        for (int i = 0; i < POPULATION_COUNT; ++i) {
            float rand = getRandomFloat(0, 100);
            int firstChromosome = getChromosomeNumberForThisRand(rand);
            int secondChromosome;
            do {
                rand = getRandomFloat(0, 100);
                secondChromosome = getChromosomeNumberForThisRand(rand);

            } while (firstChromosome == secondChromosome);
            pairs[i][0] = firstChromosome;
            pairs[i][1] = secondChromosome;

        }
        return pairs;
    }


    private void analyzePairs(int[][] pairs) {
        int[] totals = new int[POPULATION_COUNT];
        for (int i = 0; i < POPULATION_COUNT; ++i) {
            totals[i] = 0;
        }
        for (int i = 0; i < POPULATION_COUNT; ++i) {
            for (int j = 0; j < 2; ++j) {
                totals[pairs[i][j]]++;
            }

        }
    }

    private int getChromosomeNumberForThisRand(float rand) {
        int i;
        for (i = 0; i < POPULATION_COUNT; ++i) {

            if (rand <= population[i].getLikelihood()) {
                return i;
            }
        }
        return i - 1;
    }

    private Chromosome[] performCrossoverAndMutationForThePopulationAndGetNextGeneration(int[][] pairs) {
        Chromosome nextGeneration[] = new Chromosome[Main.POPULATION_COUNT];
        for (int i = 0; i < POPULATION_COUNT; ++i) {
            Chromosome firstParent = population[pairs[i][0]];
            Chromosome secondParent = population[pairs[i][1]];
            Chromosome result = firstParent.singleCrossover(secondParent);
            nextGeneration[i] = result;
            nextGeneration[i] = nextGeneration[i].mutateWithGivenLikelihood();
        }
        return nextGeneration;
    }

    public Chromosome[] getPopulation() {
        return population;
    }

    public void setPopulation(Chromosome[] population) {
        this.population = population;
    }

    public static void main(String[] args) throws IOException {
        File file = new File("Result.txt");
        PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(file)));
        String[] startEquations  = new String[] {"u * w * x * x * y + y + w * w * x * z + u * w * x * z + u * w * x * z = 40"};

        int[] targetValue = new int[] {-40}; //11
        for(int i =0;i<startEquations.length;i++) {
            writer.write(startEquations[i] + "\n");
            Main finalEquation = new Main();
            finalEquation.createInitialPopulation();
            Chromosome chromosome = null;
            long iterationsNumber = 0;
            do {

                int fillingWithFitnessesResult = finalEquation.fillChromosomesWithFitnesses(i, targetValue[i]);

                if (fillingWithFitnessesResult != TARGET_NOT_REACHED_FLAG) {
                    chromosome = finalEquation.getPopulation()[fillingWithFitnessesResult];
                    break;
                }

                finalEquation.fillChromosomeWithLikelihoods();
                finalEquation.printAllChromosomes();
                int[][] pairs = finalEquation.getPairsForCrossover();
                finalEquation.analyzePairs(pairs);

                finalEquation.setPopulation(finalEquation.performCrossoverAndMutationForThePopulationAndGetNextGeneration(pairs));
            } while (iterationsNumber++ < MAX_ITERATIONS);

            if (chromosome != null) {
                writer.println("Number of iterations " + iterationsNumber);
                writer.println("Solution is found: " + chromosome);
            } else {
                writer.println("No solutions");
                System.out.println("no solution for this situation...");
            }
        }
        writer.close();
    }

}
