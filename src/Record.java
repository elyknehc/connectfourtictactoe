/**
 * Kyle Chen
 * Represents a record class
 * Getters  for the record class using instance variables
 * @author kylechen
 *
 */
public class Record {

	//Declare instance variables
	private String key;
	private int score;
	private int level;
	
	//Constructor
	/**
	 * Create a record object with 3 paramters
	 * @param key
	 * @param score
	 * @param level
	 */
	public Record(String key, int score, int level) {
		this.key = key;
		this.score = score;
		this.level = level;
	}
	
	//Getters for instance variables
	
	/**
	 * Returns string "key"
	 * @return
	 */
	public String getKey() {
		return key;
	}
	
	/**
	 * Returns score of record
	 * @return
	 */
	public int getScore() {
		return score;
	}
	
	/**
	 * Returns level of record
	 * @return
	 */
	public int getLevel() {
		return level;
	}
}
