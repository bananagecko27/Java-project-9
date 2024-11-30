package main.java;

/** An implementation of the HashTable ADT that uses quadratic probing. */
public final class QuadraticProbe<K, V> extends AbstractHashTable<K, V> {

  /**
   * Constructs an empty hash table.
   * @param capacity the initial capacity of the table
   */
  public QuadraticProbe(int capacity, double loadFactor) {
    super(capacity, loadFactor);
  }

  /**
   * Returns the value in the table identified by the input key.
   * @param key the unique identifier
   * @return the value in the table identified by the input key or {@code null} if
   *     the key is not contained in the table
   */
  public V get(K key) {
    // TODO: implement get(K)
    int hash = hash(key);
    int hashIndex = hash % table.length;
    Entry<K,V> tep = getEntry(table, hashIndex);
    
    int counter =1;
      while(tep != null && tep.key != null){
        /*if(counter>table.length){
          return null;
        }*/
        if(tep.key.equals(key)){
          
          return tep.value;
        }
        //hashIndex++;
        hashIndex = hash + (counter*counter);
        hashIndex%= table.length;
        tep = getEntry(table, hashIndex);
        counter++;

      }
      return null; 

    //throw new UnsupportedOperationException("get(K) not yet implemented");
  }

  /**
   * Puts the input entry into the table using a probing algorithm.
   * @param entry the entry to put into the table
   * @return the number of hashes generated to store the input entry
   */
  protected int put(Entry<K, V> entry) {
    // TODO: implement put(Entry)

    int count =1;
       Entry<K, V> temp = new Entry<K, V>(entry.key, entry.value);
        
        int hash = hash(entry.key);
        int hashIndex = hash % table.length;
        Entry<K, V> h = getEntry(table, hashIndex);
        
        while (h != null && h.key != null && !h.key.equals(entry.key)) {
          
          hashIndex = hash + (count * count);
          hashIndex %= table.length;
          h = getEntry(table, hashIndex);
          count++; 
        }
        
        if (h == null || h.key == null) {
            this.size++;
        }
        //hashIndex %= table.length;
        this.table[hashIndex] = temp;
        return count;
          

    //throw new UnsupportedOperationException("put(Entry) not yet implemented");
  }
}
