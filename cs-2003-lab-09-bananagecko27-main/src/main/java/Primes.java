package main.java;

import java.util.ArrayList;
import java.util.List;

/** A library to generate primes. */
public class Primes {

  /**
   * Returns a prime number larger than x.
   * @param x the lower bound (exclusive)
   * @return a prime number larger than x
   */
  public static int findNextPrime(int x) {
    int prime = 5;
    while (prime <= x || !Primes.isPrime(prime)) {
      prime += 6;
    }
    return prime;
  }

  /**
   * Returns a prime number smaller than x.
   * @param x the upper bound (exclusive)
   * @return a prime number smaller than x
   */
  public static int findPrevPrime(int x) {
    if (x <= 5) {
      return 3;
    }
    if (x <= 3) {
      return 2;
    }
    if (x <= 2) {
      throw new RuntimeException("Cannot produce a prime smaller than 2");
    }
    int prime = Primes.findNextPrime(x);
    while (x <= prime || !Primes.isPrime(prime)) {
      prime -= 6;
    }
    return (prime <= 11) ? 5 : prime;
  }

  /**
   * Returns {@code true} if the input is prime
   * @param p a possible prime
   * @return {@code true} if p is prime
   */
  public static boolean isPrime(int p) {
    if (p <= 1) {
      return false;
    }
    for (int d = 2; 0 < d * d && d * d <= p; d++) {
      if (p % d == 0) {
        return false;
      }
    }
    return true;
  }

  /**
   * An implementation of the Sieve of Eratosthenes to generate all primes
   * less than or equal to the input x.
   * @param x the upper bound (inclusive)
   * @return a list of all primes less than or equal to the input x
   */
  public static List<Integer> primeSieve(int x) {
    if (x <= 1) {
      return new ArrayList<Integer>();
    }
    int[] nums = new int[x];
    for (int i = 0; i < nums.length; i++) {
      nums[i] = i + 1;
    }
    List<Integer> primes = new ArrayList<>();
    for (int i = 0; i < nums.length; i++) {
      if (nums[i] == 1) {
        continue;
      }
      primes.add(nums[i]);
      for (int j = i + nums[i]; 0 < j && j < nums.length; j += nums[i]) {
        nums[j] = 1;
      }
    }
    return primes;
  }
}
