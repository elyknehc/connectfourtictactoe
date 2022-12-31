/**
 * Kyle Chen CS 2210
 * This class represents a dictionary. Stores records in a hash table, includes methods of get, put, remove, numRecords
 * @author kylechen
 *
 */
public class Dictionary {
	
	//Set instance variables
	private Node[] hashTable;
	private int numberOfRecords;

	
	/**
	 * Constructor for dictionary
	 * @param dictionarySize
	 */
	public Dictionary(int dictionarySize) {
		this.hashTable = new Node[dictionarySize];
		this.numberOfRecords = 0;
		
	}
	
	//Hash the record to a location on the hashmap, polynomial hash function
	/**
	 * Hashes the dictionary using a polynomial hash function, modding it at the end
	 * @param key 
	 * @return the index of the hash table
	 */
	public int hash(String key) {
		int position = 0;
		int multiplier = 37; //Apparently 37 works well in practice
		int tableSize = this.hashTable.length;
		
 		//Convert string key into an integer
		char[] charArray = new char[key.length()];
		
		for (int i = 0; i < key.length(); i++) {
			charArray[i] = key.charAt(i);
		}
		
		//Polynomial hash here
		for (int i = 0; i < charArray.length; i++) {
			position = position + (multiplier * (int) Math.pow(charArray[i], i))% tableSize;
		}
		
		//Mod again
		position = position % tableSize;

		return position;
	}
	
	
	/**
	 * Puts a record in the hash table
	 * @param rec - record to be put in the hash table
	 * @return
	 * @throws DuplicatedKeyException - if there is another key that is the same (can't have to keys that are the same)
	 */
	

	public int put(Record rec) throws DuplicatedKeyException {
		String key = rec.getKey();
		int position = hash(key);
		boolean check = true;
		Node<Record> record = new Node<Record>(rec);
	
		//if position is empty then just put the record in the index of the hashtable, return 0
		if (this.hashTable[position] == null) {
			this.hashTable[position] = record;
			this.numberOfRecords++;
			return 0;
		}
		
		@SuppressWarnings("unchecked")
		Node<Record> current = this.hashTable[position];
		//If the key already exists, throw exception
		while (check) {
			if ((current.getElement().getKey().equals(key))) {
				throw new DuplicatedKeyException("This key already exists!");
			}
			//Traverse to the end of the linked list
			if (current.getNext() != null) {
				current = current.getNext();
			}
			else {
				break;
			}
		}
		//Set the next record to the end of the collision
		current.setNext(record);
		
		return 1;
		
	}
	
	/**
	 * Removes the key from the hashtable
	 * @param key - key to be removed
	 * @throws InexistentKeyException - no key exists
	 */
	
	public void remove(String key) throws InexistentKeyException {
		
		//If the table at that index is null then it should throw an exception at that position
		int position = hash(key);
		if (this.hashTable[position] == null) {
			throw new InexistentKeyException("This key does not exist here!");
		}
		
		Node currentKeyNode = this.hashTable[position];
		Record curRec = (Record) this.hashTable[position].getElement();
		Node previousNode = null;
		
		//If its in the starting position, then set it to the next node or make it null
		if (curRec.getKey().equals(key)) {
			hashTable[position] = hashTable[position].getNext();
			this.numberOfRecords = this.numberOfRecords - 1;
		}
		
		else {
			//Remove node from collision
			
			while (currentKeyNode != null) {
				
				//Removing middle node, keeping track with a previous node to update pointers
				if (curRec.getKey().equals(key)) {
					previousNode.setNext(currentKeyNode.getNext());
					currentKeyNode.setNext(null);
					this.numberOfRecords = this.numberOfRecords - 1;
					return;
				}
				
				previousNode = currentKeyNode;
				currentKeyNode = currentKeyNode.getNext();
				//Gets the node if its not null
				if (currentKeyNode != null) {
				curRec = (Record) currentKeyNode.getElement();
				}
				
			}
		}
	}
	/**
	 * Uses the hash function to return the key
	 * @param key
	 * @return Returns the record if it exists
	 */
	public Record get(String key) {
		
		int position = hash(key);
		Node currentKeyNode = this.hashTable[position];

		//If it is empty then return null
		if (currentKeyNode == null) {
			return null;
		}
		
		Record curRec = (Record) this.hashTable[position].getElement();
		
		//If it is at the first point of the index return it, otherwise search through the collision
		//Traverse through the linked list to search
		if (curRec.getKey().equals(key)) {
			return curRec;
		}
		else {
			while (currentKeyNode != null) {
				
				if ( curRec.getKey().equals(key)) {
					return curRec;
				}
				else {
					currentKeyNode = currentKeyNode.getNext();
					if (currentKeyNode != null) {
					curRec = (Record) currentKeyNode.getElement();
					}
				}
			}
			
		}
		//default return case
		return null; 
		
	}
	
	//Returns number of records
	/**
	 * 
	 * @return - returns number of keys in the hash table
	 */
	public int numRecords() {	
		return numberOfRecords;
	}
	

}
