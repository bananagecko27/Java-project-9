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
import static org.hamcrest.core.IsSame.sameInstance;
import static org.hamcrest.junit.MatcherAssume.assumeThat;

import java.lang.reflect.Field;
import main.java.AbstractHashTable;
import main.java.DoubleHash;
import org.junit.Test;
import test.java.TUGrader.Deps;
import test.java.TUGrader.DisplayName;
import test.java.TUGrader.TestGroup;

public class DoubleHashTest {

  /** Returns the private field table of a {@code DoubleHash}. */
  private <K, V> Object[] getTable(DoubleHash<K, V> hashTable) {
    try {
      Field table = AbstractHashTable.class.getDeclaredField("table");
      table.setAccessible(true);
      return ((Object[]) table.get(hashTable));
    } catch (IllegalAccessException | NoSuchFieldException e) {
      throw new NoSuchFieldError(
          "expected that DoubleHash has field (variable) Object[] named table");
    }
  }

  @Test
  @TestGroup("default")
  @DisplayName("DoubleHash(int, double) should construct an empty table with the input capacity")
  @Deps("size()")
  public void testConstructorConstructsAnEmptyTable() {
    DoubleHash<String, String> table = new DoubleHash<>(11, 1.0);
    assertThat(this.getTable(table).length, is(equalTo(11)));
    assertThat(table.size(), is(equalTo(0)));
  }

  @Test
  @TestGroup("contains")
  @DisplayName("contains(K) should return true if key is in the table")
  @Deps("put(K, V)")
  public void testContainsReturnsTrueIfKeyIsInTable() {
    DoubleHash<String, String> table = new DoubleHash<>(11, 1.0);
    table.put("key", "value");
    assertThat(table.contains("key"), is(true));
  }

  @Test
  @TestGroup("contains")
  @DisplayName("contains(K) should return true if key is in the table even with collisions")
  @Deps("put(K, V)")
  public void testContainsReturnsTrueIfKeyIsInTableDespiteCollisions() {
    DoubleHash<Integer, String> table = new DoubleHash<>(11, 1.0);
    table.put(0, "v1");
    table.put(11, "v2");
    table.put(22, "v3");
    table.put(33, "v4");
    assertThat(table.contains(0), is(true));
    assertThat(table.contains(11), is(true));
    assertThat(table.contains(22), is(true));
    assertThat(table.contains(33), is(true));
  }

  @Test
  @TestGroup("contains")
  @DisplayName("contains(K) should return false if key is not in the table")
  @Deps("put(K, V)")
  public void testContainsReturnsFalseIfKeyIsNotInTable() {
    assertThat(new DoubleHash<String, String>(11, 1.0).contains("key"), is(false));
  }

  @Test
  @TestGroup("contains")
  @DisplayName("contains(K) should return false if key is not in the table even with collisions")
  @Deps("put(K, V)")
  public void testContainsReturnsFalseIfKeyIsNotInTableDespiteCollisions() {
    DoubleHash<Integer, String> table = new DoubleHash<>(11, 1.0);
    table.put(0, "v1");
    table.put(11, "v2");
    table.put(22, "v3");
    assumeThat(table.contains(0), is(true));
    assumeThat(table.contains(11), is(true));
    assumeThat(table.contains(22), is(true));
    assertThat(table.contains(33), is(false));
  }

  @Test
  @TestGroup("get")
  @DisplayName("get(K) should return the value of key that has been stored in the table")
  @Deps("put(K, V)")
  public void testGetReturnsValueInTable() {
    DoubleHash<String, String> table = new DoubleHash<>(11, 1.0);
    String value = "value";
    table.put("key", value);
    assertThat(table.get("key"), is(sameInstance(value)));
  }

  @Test
  @TestGroup("get")
  @DisplayName(
      "get(K) should return the value of key that has been stored in the table even with"
          + " collisions")
  @Deps("put(K, V)")
  public void testGetReturnsValueInTableDespiteCollisions() {
    DoubleHash<Integer, String> table = new DoubleHash<>(11, 1.0);
    String v1 = "v1";
    table.put(0, v1);
    String v2 = "v2";
    table.put(11, v2);
    String v3 = "v3";
    table.put(22, v3);
    String v4 = "v4";
    table.put(33, v4);
    assertThat(table.get(0), is(sameInstance(v1)));
    assertThat(table.get(11), is(sameInstance(v2)));
    assertThat(table.get(22), is(sameInstance(v3)));
    assertThat(table.get(33), is(sameInstance(v4)));
  }

  @Test
  @TestGroup("get")
  @DisplayName("get(K) should return null when the key has not been stored in the table")
  @Deps("put(K, V)")
  public void testGetReturnsNullIfKeyIsNotInTable() {
    assertThat(new DoubleHash<String, String>(11, 1.0).get("key"), is(nullValue()));
  }

  @Test
  @TestGroup("put")
  @DisplayName("put(K, V) should store the key-value entry in the table")
  @Deps("contains(K)")
  public void testPutStoresEntryIntoTable() {
    DoubleHash<String, String> table = new DoubleHash<>(11, 1.0);
    table.put("key", "value");
    assertThat(table.contains("key"), is(true));
  }

  @Test
  @TestGroup("put")
  @DisplayName(
      "put(K, V) should return the number of hashes to store the key-value entry in the table")
  public void testPutInsertsEntryIntoTableAndReturnsCollisions() {
    DoubleHash<Integer, String> table = new DoubleHash<>(11, 1.0); // prev prime is 5
    assertThat(table.put(3, "v1"), is(equalTo(1)));
    assertThat(table.put(3 + 11 * 1, "v2"), is(equalTo(2)));
    assertThat(table.put(3 + 11 * 6, "v3"), is(equalTo(3)));
    assertThat(table.put(3 + 11 * 11, "v4"), is(equalTo(4)));
  }

  @Test
  @TestGroup("put")
  @DisplayName("put(K, V) should store the key-value entry in the table using double hashing")
  public void testPutStoresEntryIntoTableUsingDoubleHashing() {
    DoubleHash<Integer, String> table = new DoubleHash<>(11, 1.0); // prev prime is 5
    assertThat(table.put(0, "v1"), is(equalTo(1))); // index 0
    assertThat(table.put(11, "v2"), is(equalTo(2))); // index 0, 4
    assertThat(table.put(4, "test-v2"), is(equalTo(2))); // index 4, 5
    assertThat(table.put(22, "v3"), is(equalTo(2))); // index 0, 3
    assertThat(table.put(3, "test-v3"), is(equalTo(3))); // index 3, 5, 7
    assertThat(table.put(33, "v4"), is(equalTo(2))); // index 0, 2
    assertThat(table.put(2, "test-v4"), is(equalTo(3))); // index 2, 5, 8
    assertThat(table.put(44, "v5"), is(equalTo(2))); // index 0, 1
    assertThat(table.put(1, "test-v5"), is(equalTo(3))); // index 1, 5, 9
  }

  @Test
  @TestGroup("put")
  @DisplayName(
      "put(K, V) should be able to wrap around to the beginning of the table when using double"
          + " hashing")
  public void testPutWrapsAroundTableWithDoubleHashing() {
    DoubleHash<Integer, String> table = new DoubleHash<>(11, 1.0); // prev prime is 5
    assumeThat(table.put(6, "v1"), is(equalTo(1)));
    assumeThat(table.put(10, "v2"), is(equalTo(1)));
    assumeThat(table.put(3, "v3"), is(equalTo(1)));
    assertThat(table.put(61, "v4"), is(equalTo(4))); // index 6, 10, 3, 7
    assertThat(table.put(7, "test-v4"), is(equalTo(3))); // index 7, 10, 2
  }

  @Test
  @TestGroup("put")
  @DisplayName("put(K, V) should overwrite entries in the table with the same key")
  @Deps("get(K)")
  public void testPutOverwritesEntriesWithTheSameKey() {
    DoubleHash<Integer, String> table = new DoubleHash<>(11, 1.0);
    String v1 = "v1";
    table.put(0, v1);
    assumeThat(table.get(0), is(sameInstance(v1)));
    String v2 = "v2";
    table.put(11, v2);
    assumeThat(table.get(11), is(sameInstance(v2)));
    String v3 = "v3";
    table.put(22, v3);
    assumeThat(table.get(22), is(sameInstance(v3)));
    String v4 = "overwrite-v1";
    table.put(0, v4);
    assertThat(table.get(0), is(sameInstance(v4)));
    String v5 = "overwrite-v2";
    table.put(11, v5);
    assertThat(table.get(11), is(sameInstance(v5)));
    String v6 = "overwrite-v3";
    table.put(22, v6);
    assertThat(table.get(22), is(sameInstance(v6)));
  }

  @Test()
  @TestGroup("getters")
  @DisplayName("size() should return the current size of the table after storing new entires")
  @Deps("put(K, V)")
  public void testSizeReturnsSizeOfTableAfterStoringNewEtries() {
    DoubleHash<Integer, String> table = new DoubleHash<>(11, 1.0);
    table.put(0, "v1");
    assertThat(table.size(), is(equalTo(1)));
    table.put(1, "v2");
    assertThat(table.size(), is(equalTo(2)));
    table.put(2, "v3");
    assertThat(table.size(), is(equalTo(3)));
  }

  @Test()
  @TestGroup("getters")
  @DisplayName("size() should return the current size of the table after overwriting entries")
  @Deps("put(K, V)")
  public void testSizeReturnsSizeOfTableAfterOverwritingEtries() {
    DoubleHash<Integer, String> table = new DoubleHash<>(11, 1.0);
    table.put(0, "v1");
    assertThat(table.size(), is(equalTo(1)));
    table.put(1, "v2");
    assertThat(table.size(), is(equalTo(2)));
    table.put(2, "v3");
    assertThat(table.size(), is(equalTo(3)));
    table.put(0, "overwrite-v1");
    assertThat(table.size(), is(equalTo(3)));
    table.put(1, "overwrite-v2");
    assertThat(table.size(), is(equalTo(3)));
    table.put(2, "overwrite-v3");
    assertThat(table.size(), is(equalTo(3)));
  }

  @Test()
  @TestGroup("rehash")
  @DisplayName("put(K, V) should rehash the table if it exceeds its load factor")
  @Deps({"get(K)", "put(K, V)", "size()"})
  public void testPutRehashesTableUponExceedingLoadFactor() {
    DoubleHash<Integer, String> table = new DoubleHash<>(11, 0.33);
    assumeThat(this.getTable(table).length, is(equalTo(11)));
    String v1 = "v1";
    table.put(0, v1);
    assumeThat(table.get(0), is(sameInstance(v1)));
    String v2 = "v2";
    table.put(14, v2);
    assumeThat(table.get(14), is(sameInstance(v2)));
    String v3 = "v3";
    table.put(28, v3);
    assumeThat(table.get(28), is(sameInstance(v3)));
    assumeThat(table.size(), is(equalTo(3)));
    String v4 = "trigger-rehash";
    table.put(42, v4);
    assertThat(this.getTable(table).length, is(equalTo(23))); // prev prime is 17
    assertThat(table.size(), is(equalTo(4)));
    assertThat(table.get(0), is(sameInstance(v1))); // index 0
    assertThat(table.get(14), is(sameInstance(v2))); // index 14
    assertThat(table.get(28), is(sameInstance(v3))); // index 5
    assertThat(table.get(42), is(sameInstance(v4))); // index 19
    assertThat(table.put(0 + 23, "test-v1"), is(equalTo(2))); // index 0, 11
    assertThat(table.put(14 + 23, "test-v2"), is(equalTo(4))); // index 14, 5, 19, 10
    assertThat(table.put(5, "test-v3"), is(equalTo(2))); // index 5, 17
  }
}
