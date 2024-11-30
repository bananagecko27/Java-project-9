package main.java;

/** An implementation of the HashTable ADT that uses linear probing. */
public final class LinearProbe<K, V> extends AbstractHashTable<K, V> {

  /**
   * Constructs an empty hash table.
   * @param capacity the initial capacity of the table
   */
  public LinearProbe(int capacity, double loadFactor) {
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
    int hashIndex = hash(key);
    hashIndex %= table.length;
    int counter =0;
      while(this.table[hashIndex]!=null){
        if(counter>table.length){
          return null;
        }
        Entry<K,V> tep = getEntry(table, hashIndex);
        if(tep.key == key){
          
          return tep.value;
        }
        hashIndex++;
        hashIndex%= table.length;
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
        
        int hashIndex = hash(entry.key);
        int num = hashIndex;
        num%= table.length;
        Entry<K, V> h = getEntry(table, num);
        
        while (h != null && h.key != entry.key && h.key != null) {
          count++; 
          hashIndex++;
          hashIndex %= table.length;
          h = getEntry(table, hashIndex);
        }
        
        if (h == null) {
            this.size++;
        }
        hashIndex %= table.length;
        this.table[hashIndex] = temp;
        return count;
       
    //throw new UnsupportedOperationException("put(Entry) not yet implemented");
  }
}
