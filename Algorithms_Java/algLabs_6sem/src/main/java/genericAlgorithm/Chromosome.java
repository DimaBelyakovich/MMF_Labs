package genericAlgorithm;

import java.util.Random;

public class Chromosome {

    private int genes[] = new int[Main.GENES_COUNT];

    public Random rnd = new Random();

    private float fitness;

    private float likelihood;

    public float getFitness() {
        return fitness;
    }

    public void setFitness(float fitness) {
        this.fitness = fitness;
    }

    public int[] getGenes() {
        return genes;
    }

    public void setGenes(int[] genes) {
        this.genes = genes;
    }

    public float getLikelihood() {
        return likelihood;
    }

    public void setLikelihood(float likelihood) {
        this.likelihood = likelihood;
    }

    public float calculateFitness(int stage, int targetValue) {
        int u = genes[0];
        int y = genes[1];
        int w = genes[2];
        int x = genes[3];
        int z = genes[4];
        int closeness = 0;
        switch (stage) {
            case 0:
                closeness = Math.abs(targetValue - Main.function(u, y, w, x, z));
            default:
                break;
        }
        return 0 != closeness ? 1 / (float) closeness : Main.TARGET_IS_REACHED_FLAG;
    }


    public Chromosome mutateWithGivenLikelihood() {

        Chromosome result = (Chromosome) this.clone();

        for (int i = 0; i < Main.GENES_COUNT; ++i) {
            float randomPercent = Main.getRandomFloat(0, 100);
            if (randomPercent < Main.P) {
                int oldValue = result.getGenes()[i];
                int newValue = Main.getRandomGene();

                Main.log("Performing mutation for gene #" + i
                        + ". ( randomPercent ==" + randomPercent
                        + " ). Old value:" + oldValue + "; New value:" + newValue);
                result.getGenes()[i] = newValue;

            }
        }
        return result;
    }

    public Chromosome[] doubleCrossover(Chromosome chromosome) {

        int chance;
        Chromosome[] result = new Chromosome[2];
        result[0] = new Chromosome();
        result[1] = new Chromosome();

        for (int i = 0; i < Main.GENES_COUNT; ++i) {
            if(Math.random() < 0.5){
                chance = 0;
            }
            else{
                chance = 1;
            }
            if (chance == 1) {
                result[0].getGenes()[i] = this.getGenes()[i];
                result[1].getGenes()[i] = chromosome.getGenes()[i];

            } else {
                result[0].getGenes()[i] = chromosome.getGenes()[i];
                result[1].getGenes()[i] = this.getGenes()[i];
            }

        }
        return result;
    }


    public Chromosome singleCrossover(Chromosome chromosome) {
        Chromosome[] children = doubleCrossover(chromosome);
        int childNumber = rnd.nextInt(1);
        return children[childNumber];
    }


    public boolean equals(Chromosome chromosome) {
        for (int i = 0; i < Main.GENES_COUNT; ++i) {
            if (this.genes[i] != chromosome.genes[i]) {
                return false;
            }
        }
        return true;
    }


    public String toString() {
        StringBuffer result = new StringBuffer();
        String[] variables = new String[]{"u = ", "y = ", "w = ", "x = ", "z = "};
        result.append(": (");
        for (int i = 0; i < Main.GENES_COUNT; ++i) {
            result.append(variables[i] + genes[i]);
            result.append(i < Main.GENES_COUNT - 1 ? ", " : "");

        }
        result.append(")\n");
        return result.toString();
    }

    private static int getRandomCrossoverLine() {
        int line = Main.getRandomInt(0, Main.GENES_COUNT - 2);
        return line;
    }

    protected Object clone() {
        Chromosome resultChromosome = new Chromosome();
        resultChromosome.setFitness(this.getFitness());
        resultChromosome.setLikelihood(this.getLikelihood());

        int resultGenes[] = this.genes.clone();

        resultChromosome.setGenes(resultGenes);

        return resultChromosome;
    }
}
