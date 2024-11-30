package main.java;

public interface BasicHashTableInterface<K, V> {

  /**
   * Returns {@code true} if the input key is in the table.
   * @param key the unique identifier
   * @return {@code true} if the input key is in the table
   */
  public boolean contains(K key);

  /**
   * Returns the value in the table identified by the input key.
   * @param key the unique identifier
   * @return the value in the table identified by the input key or {@code null} if
   *     the key is not contained in the table
   */
  public V get(K key);

  /**
   * Puts the input value into the table identified by the input key.
   * @param key the unique identifier
   * @param value the value to be stored in the table
   * @return the number of collisions
   */
  public int put(K key, V value);

  /**
   * Returns the number of entries that have been stored in the hash table.
   * @return the number of entries in the hash table
   */
  public int size();
}
