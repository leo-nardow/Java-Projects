package sudoku;

import java.util.Scanner;

public class SudokuSolver {
	private static final int BOARD_SIZE = 9;

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int[][] board = { { 5, 3, 0, 0, 7, 0, 0, 0, 0 }, 
				{ 6, 0, 0, 1, 9, 5, 0, 0, 0 }, 
				{ 0, 9, 8, 0, 0, 0, 0, 6, 0 },
				{ 8, 0, 0, 0, 6, 0, 0, 0, 3 }, 
				{ 4, 0, 0, 8, 0, 3, 0, 0, 1 }, 
				{ 7, 0, 0, 0, 2, 0, 0, 0, 6 },
				{ 0, 6, 0, 0, 0, 0, 2, 8, 0 }, 
				{ 0, 0, 0, 4, 1, 9, 0, 0, 5 }, 
				{ 0, 0, 0, 0, 8, 0, 0, 7, 9 } };
		System.out.println("Before:");
		printSudoku(board);
		if (solveSudoku(board)) {
			System.out.println("After:");
			printSudoku(board);
			
		}else {
			System.out.println("Not Solvable");
		}
		solveSudoku(board);
		

		scanner.close();
	}

	private static void printSudoku(int[][] board) {
		for (int i = 0; i < BOARD_SIZE; i++) {
			if(i%3==0 && i!=0) {
				System.out.println("-------------------");
			}
			for (int j = 0; j < BOARD_SIZE; j++) {
				if(j%3==0 && j!=0) {
					System.out.print("|");
				}
				System.out.print(board[i][j]+ " ");
			}
			System.out.println("");

		}
		System.out.println();
	}

	private static boolean isInRow(int[][] board, int number, int row) {

		for (int i = 0; i < BOARD_SIZE; i++) {
			if (board[row][i] == number) {
				return true;
			}
		}

		return false;
	}

	private static boolean isInColumn(int[][] board, int number, int column) {
		for (int i = 0; i < BOARD_SIZE; i++) {
			if (board[i][column] == number) {
				return true;
			}
		}

		return false;
	}

	private static boolean isInGrid(int[][] board, int number, int row, int column) {
		int firstRow = row - row % 3;
		int firstColumn = column - column % 3;

		for (int i = firstRow; i < firstRow + 3; i++) {
			for (int j = firstColumn; j < firstColumn + 3; j++) {
				if (board[i][j] == number) {
					return true;
				}
			}
		}

		return false;
	}

	private static boolean isValid(int[][] board, int number, int row, int column) {
		return !isInRow(board, number, row) &&
			   !isInColumn(board, number, column) &&
			   !isInGrid(board, number, row, column);
	}

	private static boolean solveSudoku(int[][] board) {

		for (int row = 0; row < BOARD_SIZE; row++) {

			for (int column = 0; column < BOARD_SIZE; column++) {

				if (board[row][column] == 0) {
					for (int number = 1; number <= BOARD_SIZE; number++) {

						if (isValid(board, number, row, column)) {
							board[row][column] = number;

							if(solveSudoku(board)) {
								return true;
							}else {
								board[row][column] = 0;
							}
						}

					}
					return false;
				}

			}

		}
		return true;
	}

}
