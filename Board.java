import java.util.HashMap;

public class Board {

  private static char matrix[][] = new char[8][10];
  private static HashMap<Integer, Integer> row = new HashMap<Integer, Integer>();       // used to map the index of 'surrounding' array to the row and column coordinates
  private static HashMap<Integer, Integer> col = new HashMap<Integer, Integer>();


  public static void main(String[] args) {
    setHMap();
    // matrix[2][1] = 'x';
    enterCoin(1, 'x');

    enterCoin(0, 'o');
    enterCoin(0, 'o');
    enterCoin(0, 'o');

    boolean in_a_row = check4(enterCoin(0, 'o'), 0);
    System.out.println(in_a_row);

    printMatrix();
  }

  public static void printMatrix() {
    for (int i = 0; i < 10; i++)
      System.out.print(i + " ");
    System.out.println();

    for (int i = 0; i < 8; i++) {
      for (int j = 0; j < 10; j++)
        System.out.print(matrix[i][j] + " ");
      System.out.println();
    }
  }

  public static boolean check4(int r, int c) {
    char surroundings[] = checkSurroundings(r, c);

    for (int direction = 0; direction < surroundings.length; direction++) {
      try {
        if (surroundings[direction] == matrix[r][c]) {
          r += row.get(direction);
          c += col.get(direction);
        }

        surroundings = checkSurroundings(r, c);
        if (surroundings[direction] == matrix[r][c]) {
          r += row.get(direction);
          c += col.get(direction);
        }

        surroundings = checkSurroundings(r, c);
        if (surroundings[direction] == matrix[r][c]) {
          return true;
        }
      } catch (ArrayIndexOutOfBoundsException e) {
        continue;
      }
    }

    return false;
  }

  public static int enterCoin (int c, char coin) {
    int r = 0;
    while (r < 8 && matrix[r][c] == '\0')       //default value in a character array is \0
      r++;
    matrix[r -1][c] = coin;
    return r -1;          // returns the row index so that check4() can be called only on the spot where the change has taken place
  }

  private static char[] checkSurroundings(int r, int c) {         // returns an array that contains the values of the surrounding 8 spots
    char surroundings[] = new char[8];
    if (r*c > 0) {
      surroundings[0] = matrix[r-1][c-1];
      surroundings[1] = matrix[r-1][c];
      surroundings[3] = matrix[r][c-1];
    }

    if (r < 7 && c < 7 && r*c >= 0) {
      surroundings[4] = matrix[r][c+1];
      surroundings[6] = matrix[r+1][c];
      surroundings[7] = matrix[r+1][c+1];
    }

    if (r < 7 & c > 0 && r >= 0) {
      surroundings[3] = matrix[r][c-1];
      surroundings[5] = matrix[r+1][c-1];
      surroundings[6] = matrix[r+1][c];
    }

    if (r > 0 && c < 7 && c >= 0) {
      surroundings[1] = matrix[r-1][c];
      surroundings[2] = matrix[r-1][c+1];
      surroundings[4] = matrix[r-1][c+1];
    }

    return surroundings;
  }

  private static void setHMap() {
    row.put(0, -1);
    row.put(1, -1);
    row.put(2, -1);
    row.put(3, 0);
    row.put(4, 0);
    row.put(5, 1);
    row.put(6, 1);
    row.put(7, 1);

    col.put(0, -1);
    col.put(1, 0);
    col.put(2, 1);
    col.put(3, -1);
    col.put(4, 1);
    col.put(5, -1);
    col.put(6, 0);
    col.put(7, 1);
  }

}
