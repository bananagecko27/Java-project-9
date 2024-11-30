package main.java;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * Driver class to test the three implementations of the HashTable ADT, which are
 * {@code LinearProbe}, {@code QuadraticProbe}, and {@code DoubleHash}.
 */
public class Main {

  public static void main(String args[]) {
    List<String[]> pairs;
    try {
      pairs =
          Files.readAllLines(Path.of("country-capitals.csv")).stream()
              .map(line -> line.trim().split(","))
              .collect(Collectors.toList());
    } catch (IOException e) {
      throw new RuntimeException(e.getMessage());
    }
    // TODO: modify load factors here if you want additional load factors
    double[] loadFactors = new double[] {0.25, 0.50, 0.65};
    for (double loadFactor : loadFactors) {
      int cap = Primes.findNextPrime(pairs.size() * (int) (1.0 / loadFactor));
      System.out.println(cap);
      // Create instances of the 3 hash classes
      BasicHashTableInterface<String, String> linProbe = new LinearProbe<>(cap, loadFactor);
      BasicHashTableInterface<String, String> quadProbe = new QuadraticProbe<>(cap, loadFactor);
      BasicHashTableInterface<String, String> dblHash = new DoubleHash<>(cap, loadFactor);
      // Create placeholders for storing number of hashes and runtimes
      int numHashesLin = 0, numHashesQuad = 0, numHashesDouble = 0;
      long runTimeLin = 0, runTimeQuad = 0, runTimeDouble = 0;
      // TEST for addition of elements
      // Iterate over list and add elements to respective hash objects
      for (String[] pair : pairs) {
        // linear probing
        long startTime = System.nanoTime();
        numHashesLin += linProbe.put(pair[0], pair[1]);
        runTimeLin += System.nanoTime() - startTime;
        // quadratic probing
        startTime = System.nanoTime();
        numHashesQuad += quadProbe.put(pair[0], pair[1]);
        runTimeQuad += System.nanoTime() - startTime;
        // double hashing
        startTime = System.nanoTime();
        numHashesDouble += dblHash.put(pair[0], pair[1]);
        runTimeDouble += System.nanoTime() - startTime;
      }
      System.out.println("Adding time and hash analysis:");
      System.out.printf(
          "Linear probing had %d hashes with runtime %,dns\n", numHashesLin, runTimeLin);
      System.out.printf(
          "Quadratic probing had %d hashes with runtime %,dns\n", numHashesQuad, runTimeQuad);
      System.out.printf(
          "Double hashing had %d hashes with runtime %,dns\n", numHashesDouble, runTimeDouble);
      // Create arraylist and store random elements to be retrieved
      ArrayList<String[]> randomList = new ArrayList<>();
      Random randomNumGenerator = new Random();
      for (int i = 0; i < 200; i++) {
        randomList.add(pairs.get(randomNumGenerator.nextInt(pairs.size())));
      }
      // TEST for retrieval of elements
      runTimeLin = 0;
      runTimeQuad = 0;
      runTimeDouble = 0;
      for (String[] pair : randomList) {
        // Linear probing
        long startTime = System.nanoTime();
        String item = linProbe.get(pair[0]);
        runTimeLin += System.nanoTime() - startTime;
        if (!item.equals(pair[1])) {
          throw new RuntimeException(
              String.format("Could not retrieve %s using linear probing", item));
        }
        // Quadratic probing
        startTime = System.nanoTime();
        item = quadProbe.get(pair[0]);
        runTimeQuad += System.nanoTime() - startTime;
        if (!item.equals(pair[1])) {
          throw new RuntimeException(
              String.format("Could not retrieve %s using quadratic probing", item));
        }
        // Double hashing
        startTime = System.nanoTime();
        item = dblHash.get(pair[0]);
        runTimeDouble += System.nanoTime() - startTime;
        if (!item.equals(pair[1])) {
          throw new RuntimeException(
              String.format("Could not retrieve %s using double hashing", item));
        }
      }
      System.out.println("\nRetrieval time analysis:");
      System.out.printf("Linear probing retrieval time: %,dns\n", runTimeLin);
      System.out.printf("Quadratic probing retrieval time: %,dns\n", runTimeQuad);
      System.out.printf("Double probing retrieval time: %,dns\n", runTimeDouble);
    }
  }
}
