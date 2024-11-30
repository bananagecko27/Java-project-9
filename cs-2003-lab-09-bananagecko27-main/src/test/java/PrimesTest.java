package test.java;

/**
 * DO NOT DISTRIBUTE.
 *
 * This code is intended to support the education of students associated with the Tandy School of
 * Computer Science at the University of Tulsa. It is not intended for distribution and should
 * remain within private repositories that belong to Tandy faculty, students, and/or alumni.
 */
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import java.util.Arrays;
import main.java.Primes;
import org.junit.Test;
import test.java.TUGrader.DisplayName;
import test.java.TUGrader.TestGroup;

public class PrimesTest {

  @Test
  @TestGroup("findNextPrime(int)")
  @DisplayName("findNextPrime(int) should a value larger than the bound")
  public void testFindNextPrimeReturnsValueLargerThanBound() {
    assertThat(Primes.findNextPrime(4), is(greaterThanOrEqualTo(4)));
    assertThat(Primes.findNextPrime(11), is(greaterThanOrEqualTo(11)));
  }

  @Test
  @TestGroup("findNextPrime(int)")
  @DisplayName("findNextPrime(int) should return the next prime in the sequence")
  public void testFindNextPrimeReturnsNextPrimeInSequence() {
    assertThat(Primes.findNextPrime(5), is(equalTo(11)));
    assertThat(Primes.findNextPrime(11), is(equalTo(17)));
    assertThat(Primes.findNextPrime(17), is(equalTo(23)));
    assertThat(Primes.findNextPrime(23), is(equalTo(29)));
  }

  @Test
  @TestGroup("findNextPrime(int)")
  @DisplayName("findNextPrime(int) should return only prime numbers")
  public void testFindNextPrimeReturnsOnlyPrimes() {
    assertThat(Primes.findNextPrime(29), is(equalTo(41)));
  }

  @Test
  @TestGroup("isPrime(int)")
  @DisplayName("isPrime(int) should return true if the input is prime")
  public void testIsPrimeReturnsTrueIfInputIsPrime() {
    assertThat(Primes.isPrime(2), is(true));
    assertThat(Primes.isPrime(3), is(true));
    assertThat(Primes.isPrime(5), is(true));
    assertThat(Primes.isPrime(7), is(true));
    assertThat(Primes.isPrime(11), is(true));
    assertThat(Primes.isPrime(13), is(true));
  }

  @Test
  @TestGroup("isPrime(int)")
  @DisplayName("isPrime(int) should return false if the input is not prime")
  public void testIsPrimeReturnsTrueIfInputIsNoPrime() {
    assertThat(Primes.isPrime(1), is(false));
    assertThat(Primes.isPrime(4), is(false));
    assertThat(Primes.isPrime(6), is(false));
    assertThat(Primes.isPrime(8), is(false));
    assertThat(Primes.isPrime(9), is(false));
    assertThat(Primes.isPrime(10), is(false));
  }

  @Test
  @TestGroup("primeSieve(int)")
  @DisplayName("primeSieve(int) should return a list of all primes less than or equal to the bound")
  public void testPrimeSieveReturnsAllPrimesWithinTheBound() {
    assertThat(Primes.primeSieve(23), is(Arrays.asList(2, 3, 5, 7, 11, 13, 17, 19, 23)));
  }
}
