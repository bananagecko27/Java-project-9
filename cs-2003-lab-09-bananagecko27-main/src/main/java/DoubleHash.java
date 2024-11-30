package main.java;

/** An implementation of the HashTable ADT that uses double hashing. */
public final class DoubleHash<K, V> extends AbstractHashTable<K, V> {

  /**
   * Constructs an empty hash table.
   * @param capacity the initial capacity of the table
   */
  public DoubleHash(int capacity, double loadFactor) {
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

    /*int hash1 = hash(key);
    int hashIndex1 = hash1 % table.length;
    int hashIndex2 = doubleHash(key);
    hashIndex2 %= table.length;
    Entry<K,V> tep = getEntry(table, hashIndex1);

    int counter =0;
      while(tep != null && tep.key != null){
        /*if(counter>table.length){
          return null;
        }*/
        //Entry<K,V> tep = getEntry(table, hashIndex1);
       /*  if(tep.key.equals(key)){
          
          return tep.value;
        }
        //hashIndex++;
        hashIndex1 = hash1 + hashIndex2;
        hashIndex1 %= table.length;
        counter++;
        tep = getEntry(table, hashIndex1);
      }
      return null; */

      int hashIndex1 = hash(key);
      hashIndex1 %= table.length;
      int hashIndex2 = doubleHash(key);
      //hashIndex2 %= table.length;
      
      
        while(this.table[hashIndex1]!=null){
          /*if(counter>table.length){
            return null;
          }*/
          Entry<K,V> tep = getEntry(table, hashIndex1);
          if(tep.key == key){
            
            return tep.value;
          }
          //hashIndex++;
          hashIndex1 += hashIndex2;
          hashIndex1 %= table.length;
          
  
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
    /*if (size == table.length) {
 
      rehash();
  }*/
  
  int hash1 = hash(entry.key);
  int hashIndex1 = hash1 % table.length;
  int hashIndex2 = doubleHash(entry.key);
  //hashIndex2 %= table.length;

  Entry<K, V> h = getEntry(table, hashIndex1);
  Entry<K, V> temp = new Entry<K,V>(entry.key, entry.value);

  while (h != null && h.key != null && !h.key.equals(entry.key)) {
      hashIndex1 += hashIndex2;
      hashIndex1 %= table.length;
      h = getEntry(table, hashIndex1);
      count++;
  }
  if (h == null || h.key == null) {
    this.size++;
}
  this.table[hashIndex1] = temp;
  return count;

    //throw new UnsupportedOperationException("put(Entry) not yet implemented");
  }

  /**
   * Returns the second hash of the input key.
   * @param key the unique identifier to be hashed
   * @return the hashed value of the input key
   */
  @SuppressWarnings("unused")
  private int doubleHash(K key) {
    // TODO: implement doubleHash(K)
    int hashIndex2 = hash(key);
    int prime = Primes.findPrevPrime(table.length);
    hashIndex2 = prime - (hashIndex2 % prime);
    return hashIndex2 % table.length;
    //throw new UnsupportedOperationException("doubleHash(K) not yet implemented");
  }

  
      
}
    
