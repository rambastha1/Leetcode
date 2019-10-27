package main;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/* Your task is to design the basic function of Excel and implement the function of sum formula. Specifically, 
 * you need to implement the following functions:

Excel(int H, char W): This is the constructor. The inputs represents the height and width of the Excel form. 
H is a positive integer, range from 1 to 26. It represents the height. W is a character range from 'A' to 'Z'. 
It represents that the width is the number of characters from 'A' to W. The Excel form content is represented 
by a height * width 2D integer array C, it should be initialized to zero. You should assume that the first row of 
C starts from 1, and the first column of C starts from 'A'.


void Set(int row, char column, int val): Change the value at C(row, column) to be val.


int Get(int row, char column): Return the value at C(row, column).


int Sum(int row, char column, List of Strings : numbers): This function calculate and set the value at C(row, column), 
where the value should be the sum of cells represented by numbers. This function return the sum result at C(row, column). 
This sum formula should exist until this cell is overlapped by another value or another sum formula.

numbers is a list of strings that each string represent a cell or a range of cells. If the string represent a single cell, 
then it has the following format : ColRow. For example, "F7" represents the cell at (7, F).

If the string represent a range of cells, then it has the following format : ColRow1:ColRow2. 
The range will always be a rectangle, and ColRow1 represent the position of the top-left cell, and ColRow2 represents the 
position of the bottom-right cell.


Example 1:
Excel(3,"C"); 
// construct a 3*3 2D array with all zero.
//   A B C
// 1 0 0 0
// 2 0 0 0
// 3 0 0 0

Set(1, "A", 2);
// set C(1,"A") to be 2.
//   A B C
// 1 2 0 0
// 2 0 0 0
// 3 0 0 0

Sum(3, "C", ["A1", "A1:B2"]);
// set C(3,"C") to be the sum of value at C(1,"A") and the values sum of the rectangle range whose top-left cell 
 * is C(1,"A") and bottom-right cell is C(2,"B"). Return 4. 
//   A B C
// 1 2 0 0
// 2 0 0 0
// 3 0 0 4

Set(2, "B", 2);
// set C(2,"B") to be 2. Note C(3, "C") should also be changed.
//   A B C
// 1 2 0 0
// 2 0 2 0
// 3 0 0 6
Note:
You could assume that there won't be any circular sum reference. For example, A1 = sum(B1) and B1 = sum(A1).
The test cases are using double-quotes to represent a character.
Please remember to RESET your class variables declared in class Excel, as static/class variables are persisted 
across multiple test cases. Please see here for more details.
 * 
 */

/* Approach 1: Topological Sort
Before discussing the required design, we'll discuss some prerequisites to help ease the understanding of the solution.

Firstly, we can note that once a formula is applied to any cell in excel, let's say C1 = C2 + C3C1=C2+C3, 
if any change is made to C2C2 or C3C3, the result to be put into C1C1 needs to be evaluated again based on the new values 
of C2C2 and C3C3. Further, suppose some other cell, say D2D2 is also dependent on C1C1 due to some prior formula applied to D2D2. 
Then, when any change is made to, say, C2C2, we re-evaluate C1C1's value. Furhter, since D2D2 is dependent on C1C1, 
we need to re-evaluate D2D2's value as well.

Thus, whenever, we make any change to any cell, xx, we need to determine the cells which are dependent on xx, and update 
these cells, and further determine the cells which are dependent on the changed cells and so on. We can assume that no 
cycles are present in the formulas, i.e. Any cell's value won't directly or indirectly be dependent on its own value.

But, while doing these set of evaluations of the cells to determine their updated values, we need to update the cells in 
such an order that the cell on which some other cell is dependent is always evaluated prior to the cell which is dependent 
on the former cell.

In order to do so, we can view the dependence between the cells in the form of a dependency graph, which can be a Directed Graph. 
Since, no cycles are allowed between the formulas, the graph reduces to a Directed Acyclic Graph. 
Now, to solve the problem of evaluating the cells in the required order, we can make use of a very well known method 
specifically used for such problems in Directed Acyclic Graphs, known as the Topological Sorting.

Now, let's discuss how we implement the various required functions. We make use of a simple structure(Class), 
FormulaFormula, which contains two elements. First, the value of the cell which it represents, valval, and a HashMap, cellscells. 
It is a list of cells on which the current cell's value is dependent. This cellscells hashmap stores the data in the 
form (cellName, count)(cellName,count). cellNamecellName has the format ColRowColRow. countcount refers to the number of 
times the current cell directly or indirectly comes in the current cell's formulas. e.g. C1 = C2 + C3 + C2C1=C2+C3+C2. 
In this case, the frequency of C3C3 is 1 and that of C2C2 is 2.

set(int row, char column, int val) : For setting the value of the cell corresponding to the given rowrow and columncolumn, 
we can simply change the value , valval, in the FormulasFormulas array at the indices corresponding to the current cell. 
Further, if any new formula is applied to a particular cell, we need to remove the previously applied formulas on the same cell. 
This is because two formulas can't be used to determine the value of a cell simultaneously. 
Now, setting a cell to a particular value can also be seen as a formula e.g. C1 = 2C1=2. 
Thus, we remove all the cellscells in the FormulasFormulas for the current cell. Further, when the current cell's value is changed, 
all the other cells which are dependent on it also need to be evaluated in the correct order. 
Thus, we make use of Topological Sorting starting with the current cell. We make use of a function topologicalSort(r, c) 
for this purpose.
 * 
 *  https://leetcode.com/problems/design-excel-sum-formula/discuss/104857/Java-implement-the-logic-in-Cell-class-easy-to-understand
 */

class Excel {
     Cell[][] cells;

    public Excel(int H, char W) {
        cells = new Cell[H + 1][W - 'A' + 1];
        for (int i = 0; i <= H; i++)
            for (int j = 0; j <= W-'A'; j++)
                cells[i][j] = new Cell(0);
    }

    public void set(int r, char c, int v) { 
    	cells[r][c - 'A'].set(v);
    }

    public int get(int r, char c) { 
    	// return sum of all dependencies as well
    	return cells[r][c - 'A'].val();
    }

    public int sum(int r, char c, String[] strs) {
        Cell cell = cells[r][c - 'A'];
        cell.set(0);
        
        for (String s : strs) {
            int k = s.indexOf(':');
            if (k > 0) {
                int[] start = position(s.substring(0, k)), end = position(s.substring(k + 1));
                
                for (int i = start[0]; i <= end[0]; i++) {
                    for (int j = start[1]; j <= end[1]; j++) {
                        cell.add(cells[i][j]);
                    }
                }
            } else {
                int[] p = position(s);
                cell.add(cells[p[0]][p[1]]);
            }
        }
        // returns sum of depencies as well
        return cell.val();
    }

    int[] position(String s) {
        return new int[]{Integer.parseInt(s.substring(1)), s.charAt(0) - 'A'};
    }

    class Cell {
        int val;
        // dependency map cells -> count
        // if there is change in keyset this cell needs to be changed
        Map<Cell, Integer> map = new HashMap<>();

        Cell(int v) {
        	val = v;
        }

        void add(Cell cell) { 
        	map.put(cell, map.getOrDefault(cell, 0) + 1);
        }

        int val() {
            if (map.isEmpty())
                return val;
            else {
            	// have dependency
                int sum = 0;
                // add dependency value with their count
                for (Cell cell : map.keySet())
                    sum += map.get(cell) * cell.val();
                return sum;
            }
        }

        void set(int v) {
            val = v;
            // two rules cannot be applied together read above
            map.clear();
        }
    }
}

/**
 * Your Excel object will be instantiated and called as such:
 * Excel obj = new Excel(H, W);
 * obj.set(r,c,v);
 * int param_2 = obj.get(r,c);
 * int param_3 = obj.sum(r,c,strs);
 */

public class Main {
	public static void main(String[] args) {

	}
}