/**
 * Evaluate plays the game, creating the dictionary and identfiies who wins
 * @author kylechen
 *
 */
public class Evaluate {

	//Set instance variables
	private char gameBoard[][];
	private int size;
	private int tilesToWin;
	private int maxLevels;
	
	/**
	 * Constructor method creating the gameBoard
	 * @param size - size of gameboard
	 * @param tilesToWin - times needed in order to win
	 * @param maxLevels
	 */
	public Evaluate(int size, int tilesToWin, int maxLevels) {
		this.size = size;
		this.tilesToWin = tilesToWin;
		this.maxLevels = maxLevels;
		this.gameBoard = new char[size][size];
		
		//Initalize the game board with empty squares
		for (int rows = 0; rows < size; rows++) {
			for (int columns = 0; columns < size; columns++) {
				gameBoard[rows][columns] = 'e';
			}
		}
	}
	
	/**
	 * Method to create a dictionary of default size 7561
	 * @return - returns the dictionary that has been created
	 */
	public Dictionary createDictionary() {
		
		Dictionary dictionary = new Dictionary(7561);
		
		return dictionary;
		
	}
	
	/**
	 * Checks if there has been a repeated record with same key attribute, if there is then returns true
	 * Otherwise returns false
	 * @param dict - pass in the dictionary created
	 * @return - returns the record or null
	 */
	public Record repeatedState(Dictionary dict) {
		String characters = "";
		
		for (int row = 0; row < this.size; row++) {
			
			for (int column = 0; column < this.size; column++) {
				characters = characters + this.gameBoard[row][column];
			}
		}
		
		//Return the dictionary in this case
		if (dict.get(characters) != null) {
			return dict.get(characters);
		}
		else {
			return null;
		}
		
	}
	/**
	 * Inserts the gamestate of the board into a dictionary
	 * @param dict - dictionary creatred
	 * @param score - score of game
	 * @param level - levle of game
	 */
	public void insertState(Dictionary dict, int score, int level) {
		
		String characters = "";
		
		for (int row = 0; row < size; row++) {
			//Appends characters into a string
			for (int column = 0; column < size; column++) {
				characters = characters + this.gameBoard[row][column];
			}
		}
		//Adds to the dictionary
		Record record = new Record(characters, score, level);
		
		if (repeatedState(dict) == null) {
			dict.put(record);
		}
	}
	/*
	 * Adds characters into the game board
	 */
	public void storePlay (int row, int col, char symbol) {
		gameBoard[row][col] = symbol;
	}
	
	/**
	 * Checks if square is empty
	 * @param row
	 * @param col
	 * @return true or false 
	 */
	public boolean squareIsEmpty(int row, int col) {
		
		//Returns true if we see an empty square
		if (gameBoard[row][col] == 'e') {
				return true;
		}
		else {
			return false;
		}

	}
	
	/**
	 * Identifies if the tile belongs to a computer
	 * @param row
	 * @param col
	 * @return true or false
	 */
	public boolean tileOfComputer(int row, int col) {
		if (gameBoard[row][col] == 'c') {
			return true;
		}
		else {
			return false;
		}
	}
	/**
	 * Identifies if the tile belongs to a human
	 * @param row
	 * @param col
	 * @return true or false
	 */
	public boolean tileOfHuman(int row, int col) {
		if (gameBoard[row][col] == 'h') {
			return true;
		}
		else {
			return false;
		}
	}
	/**
	 * Identifies if there is a winning combination in the game board
	 * @param symbol
	 * @return
	 */
	public boolean wins (char symbol) {
	
		//Check for horizontal wins
		//Keep track of each similar symbol along the rows, reset if it is different, return if it is equal to the tiles needed to win
		int counter = 0;
		
		for (int rows = 0; rows < size; rows++) {
			
			counter = 0;
			
			for (int columns = 0; columns < size; columns++ ) {
				
				if (this.gameBoard[rows][columns] == symbol) {
					counter = counter +1;
				}
				else {
					counter = 0;
				}
				if (counter == this.tilesToWin) {
					return true;
				}
			}
			
		}
		//Check for vertical wins
		//Similar to horizontal wins, check for similar symbol along columns, reset counter if different, return true if someone reached the amount required to win
		counter = 0; 
		
		for (int columns = 0; columns < size; columns++) {
			
			counter = 0; 
			
			for (int rows = 0; rows < size; rows ++) {
				
				if (this.gameBoard[rows][columns] == symbol) {
					counter = counter + 1;
				}
				else {
					counter = 0;
				}
				if (counter == this.tilesToWin) {
					return true;
				}
			}
		}
			
			
		//Check for diagonal wins
		
		counter = 0;
		
		//Diagonals - checking for all possible diagonals within the grid
		
		//Left diagonals, checks for all diagonals going down and returns true if there is a line that matches up, otherwise breaks
		for (int x = 0; x < size; x++) {
			for (int y = 0; y < size; y++) {
				for (int j = 0; j < this.tilesToWin; j++) {
					
					if (x+j == size || y+j == size) {
						break;
					}
					if (this.gameBoard[x+j][y+j] != symbol) {
						break;
					}
					if (j == tilesToWin-1) {
						return true;
					}
						
				}
			}
		}
		//Right diagonals, similar to left diagonals - returns true as soon as there is a line that matches up, otherwise breaks
		for (int x = 0; x < size; x++) {
			for (int y = size-1; y >= 0; y--) {
				for (int j = 0; j < this.tilesToWin; j++) {
					
					if (x+j == size || y-j == -1) {
						break;
					}
					if (this.gameBoard[x+j][y-j] != symbol) {
						break;
					}
					if (j == tilesToWin-1) {
						return true;
					}
						
				}
			}
		}
		
		//Otherwise, no win and return false
		return false;

	}
	/**
	 * Checks if the game has ended in a draw
	 * @return
	 */
	public boolean isDraw() {
		for (int rows = 0; rows < size; rows++) {
			//If there is an empty square then return false
			for (int columns = 0; columns < size; columns++) {
				if (gameBoard[rows][columns] == 'e') {
					return false;
				}
			}
		}
		return true;
	}
	
	/**
	 * Evaluates the board and returns int based on who has won the game, if not won then returns 1 (undecided)
	 * @return
	 */
	public int evalBoard() {
		
		
		if (wins('c')) {
			return 3;
		}
		else if (wins('h')) {
			return 0;
		}
		else if (isDraw()) {
			return 2;
		}
		else {
		return 1;
		}
		
	}
	
}
