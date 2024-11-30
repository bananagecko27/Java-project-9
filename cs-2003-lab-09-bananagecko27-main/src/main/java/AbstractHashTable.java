package main.java;

/** A partial implementation of the HashTable ADT. */
public abstract class AbstractHashTable<K, V> implements BasicHashTableInterface<K, V> {

  protected double loadFactor; // the number of elements that can be stored before rehashing
  protected int size; // the number of entries that have been stored in the table
  protected Object[] table; // stores elements of type Entry<K, V>

  // placeholder is used to delete entries by overwriting them
  private Entry<K, V> placeholder = new Entry<K, V>(null, null);

  /**
   * Constructs an empty hash table.
   * @param capacity the initial capacity of the table
   */
  public AbstractHashTable(int capacity, double loadFactor) {
    this.table = new Object[capacity];
    this.size = 0;
    this.loadFactor = loadFactor;
  }

  /**
   * Returns {@code true} if the input key is in the table.
   * @param key the unique identifier
   * @return {@code true} if the input key is in the table
   */
  public boolean contains(K key) {
    return this.get(key) != null;
  }

  /**
   * Returns the value in the table identified by the input key.
   * @param key the unique identifier
   * @return the value in the table identified by the input key or {@code null} if
   *     the key is not contained in the table
   */
  public abstract V get(K key);

  /**
   * Puts the input value into the table identified by the input key.
   * @param key the unique identifier
   * @param value the value to be stored in the table
   * @return the number of hashes
   */
  public int put(K key, V value) {
    int hashes = 0;
    if (((double) this.size + 1) / this.table.length > this.loadFactor) {
      hashes += this.rehash();
    }
    return hashes + this.put(new Entry<K, V>(key, value));
  }

  /**
   * Returns the number of entries that have been stored in the hash table.
   * @return the number of entries in the hash table
   */
  public int size() {
    return this.size;
  }

  /**
   * Returns an entry in the table.
   * @param index the index of the entry
   * @return the entry in the table at the input index or {@code null} if there
   *     is no entry at the input index
   */
  protected Entry<K, V> getEntry(int index) {
    return this.getEntry(this.table, index);
  }

  /**
   * Returns an entry from the input table.
   * @param table the table of entries
   * @param index the index of the entry
   * @return the entry in the input table at the input index or {@code null} if there
   *     is no entry at the input index
   */
  protected Entry<K, V> getEntry(Object[] table, int index) {
    @SuppressWarnings("unchecked")
    Entry<K, V> entry = (Entry<K, V>) table[index];
    return entry;
  }

  /**
   * Returns the hash of the input key.
   * @param key the unique identifier to be hashed
   * @return the hashed value of the input key
   */
  protected int hash(K key) {
    return Math.abs(key.hashCode());
  }

  /**
   * Returns {@code true} if the input index is a null entry.
   * @param index the index to check
   * @return {@code true} if the input index is a null entry
   */
  protected boolean isNull(int index) {
    return this.isNull(this.getEntry(index));
  }

  /**
   * Returns {@code true} if the input entry is a null entry.
   * @param entry the index to check
   * @return {@code true} if the input entry is a null entry
   */
  protected boolean isNull(Entry<K, V> entry) {
    return entry == null || entry == this.placeholder;
  }

  /**
   * Puts the input entry into the table using a probing algorithm.
   * @param entry the entry to put into the table
   * @return the number of hashes generated to store the input entry
   */
  protected abstract int put(Entry<K, V> entry);

  /**
   * Increases the capacity of the hash table and rehashes its elements.
   * @return the number of hashes generated to rehash the table
   */
  protected int rehash() {
    // TODO: implement rehash()
    int newCapacity = Primes.findNextPrime(table.length*2);
    int count =0;

    Object[] newTable = new Object[newCapacity];
    Object[] tableCopy = new Object[table.length];

    for(int i=0; i<table.length; i++){
      tableCopy[i] = table[i];
    }
    
    table = newTable;
    size = 0;

    for(int i=0; i<tableCopy.length; i++){
      if(getEntry(tableCopy, i)!= null){
        K k = getEntry(tableCopy, i).key;
        V v = getEntry(tableCopy, i).value;
        Entry<K,V> p = new Entry<K, V>(k, v);
        put(p);
        count++;
      }
    }
    
    return count;
    //throw new UnsupportedOperationException("rehash() not yet implemented");
  }

  /**
   * Stores the input entry in the table at the input index and updates the size if necessary.
   * @param index the index of the entry
   * @param entry the entry to store in the table
   */
  protected void storeEntry(int index, Entry<K, V> entry) {
    if (!(this.isNull(index) || this.isNull(entry))) {
      this.table[index] = entry;
      return;
    }
    if (this.isNull(index) && !this.isNull(entry)) {
      this.table[index] = entry;
      this.size++;
      return;
    }
    if (!this.isNull(index) && this.isNull(entry)) {
      this.table[index] = this.placeholder;
      this.size--;
      return;
    }
  }

  /**
   * An Entry for the implementation of {@code AbstractHashTable}. An Entry contains a
   * key of type K and a value of type V.
   */
  protected static class Entry<K, V> {

    K key;
    V value;

    public Entry(K key, V value) {
      this.key = key;
      this.value = value;
    }

    public boolean equals(Entry<K, V> entry) {
      return this.key.equals(entry.key) && this.value.equals(entry.value);
    }

    public boolean matches(Entry<K, V> entry) {
      return this.matches(entry.key);
    }

    public boolean matches(K key) {
      return this.key.equals(key);
    }
  }
}
