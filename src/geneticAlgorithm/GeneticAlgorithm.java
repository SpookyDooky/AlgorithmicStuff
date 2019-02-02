package geneticAlgorithm;
import java.util.ArrayList;
import java.util.Arrays;

public class GeneticAlgorithm {

    private int populationSize;
    private int generations;
    private int tournamentSize;

    private int[][] distances;
    private int[] distanceStart;
    private int[] distanceEnd;

    public GeneticAlgorithm(int populationSize, int generations, int tournamentSize){
        this.populationSize = populationSize;
        this.generations = generations;
        this.tournamentSize = tournamentSize;
    }

    public int getPopulationSize(){
        return this.populationSize;
    }

    public int getGenerations(){
        return this.generations;
    }

    private int[] initializeSeq(int length){
        int[] result = new int[length];
        for(int x = 0; x < length;x++){
            result[x] = x;
        }
        return result;
    }

    private void shuffle(int[] toShuffle){
        int n = toShuffle.length;
        for(int x = 0;x<n;x++){
            int j = x + (int)(Math.random() * (n - x));
            int temp = toShuffle[x];
            toShuffle[x] = toShuffle[j];
            toShuffle[j] = temp;
        }
    }

    private int[][] initializePopulation(int[] beginSequence){
        int[][] population = new int[populationSize][beginSequence.length];
        for(int x = 0; x < populationSize;x++){
            int[] shuffleMe = beginSequence;
            shuffle(shuffleMe);
            population[x] = shuffleMe;
        }
        return population;
    }

    public int findOptimal(int[][] distances, int distancesToStart[], int distancesToEnd[]){
        this.distances = distances;
        this.distanceStart = distancesToStart;
        this.distanceEnd = distancesToEnd;
        int sequenceSize = distances.length;
        int[] beginSequence = initializeSeq(sequenceSize);
        int[][] population = initializePopulation(beginSequence);

        return evolutionDriver(population);
    }

    private int evolutionDriver(int[][] population){
        int[][] currentPopulation = population;
        for(int x = 0; x < generations;x++){
            currentPopulation = evolve(currentPopulation);
            System.out.println("GENERATION: " + x + " ,FITTEST: " + fitness(findFittest(currentPopulation)));
        }

        return fitness(findFittest(currentPopulation));
    }

    private int[][] evolve(int[][] population){
        int[][] newPopulation = new int[populationSize][population[0].length];
        newPopulation[0] = findFittest(population);

        for(int x = 1; x < populationSize;x++){
            int[] parent1 = hostTournament(population);
            int[] parent2 = hostTournament(population);

            int[] child = crossOverX(parent1,parent2);
            mutateX(child);
            newPopulation[x] = child;
        }
        return newPopulation;
    }

    private int[] crossOverX(int[] parent1, int[] parent2){
        int index1 = (int)(Math.random() * (parent1.length - 1));
        int index2 = (int)(Math.random() * (parent2.length - 1));
        int[] offSpring = new int[parent1.length];

        int crossStart = Math.min(index1,index2);
        int crossEnd = Math.max(index1,index2);

        Arrays.fill(offSpring,-1);
        int[] used = new int[crossEnd - crossStart];
        for(int x = crossStart; x < crossEnd;x++){
            offSpring[x] = parent1[x];
            used[x - crossStart] = parent1[x];
        }

        Arrays.sort(used);
        int index = 0;
        for(int x = 0; x < parent2.length;x++){
            int proposed = parent2[x];
            int searched = Arrays.binarySearch(used,proposed);
            if((index < crossStart || index >= crossEnd) && index < parent2.length){
                if(searched < 0){
                    offSpring[index] = proposed;
                    index++;
                }
            } else {
                index = crossEnd;
                if(searched < 0){
                    offSpring[index] = proposed;
                    index++;
                }
            }
        }
        return offSpring;
    }

    private void mutateX(int[] theSeq){
        int index_1 = (int)(Math.random() * (theSeq.length - 1));
        int index_2 = (int)(Math.random() * (theSeq.length - 1));

        if(index_1 != index_2){
            int temp = theSeq[index_1];
            theSeq[index_1] = theSeq[index_2];
            theSeq[index_2] = temp;
        } else {
            mutateX(theSeq);
        }
    }

    private int fitness(int[] seq){
        int fitness = distanceStart[seq[0]] + distanceEnd[seq[seq.length-1]];
        for(int x = 0; x < seq.length-1;x++){
            int start = seq[x];
            int end = seq[x+1];
            fitness += distances[start][end];
        }
        return fitness;
    }

    private int[] hostTournament(int[][] population){
        int[][] tournamentSelection = new int[tournamentSize][population[0].length];

        for(int x = 0; x < tournamentSize;x++){
            int randomIndex = (int)(Math.random() * (populationSize - 1));
            tournamentSelection[x] = population[randomIndex];
        }

        return findFittest(tournamentSelection);
    }

    private int[] findFittest(int[][] population){
        int fittest = Integer.MAX_VALUE;
        int[] fittestSeq = new int[1];
        for(int x = 0; x < population.length;x++){
            int[] current = population[x];
            int currentFitness = fitness(current);
            if(currentFitness < fittest){
                fittest = currentFitness;
                fittestSeq = current;
            }
        }
        return fittestSeq;
    }
}
