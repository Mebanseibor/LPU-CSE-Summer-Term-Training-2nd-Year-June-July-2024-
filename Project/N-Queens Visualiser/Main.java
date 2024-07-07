import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


class Solution{
    public List<List<String>> solveNQueens(int n){
        List<List<String>> result = new ArrayList<>();
        return result;
    };
}


public class Main{
    public static void main(String[]args){
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter the number of queens: ");
        int n = scanner.nextInt();

        Solution s = new Solution();
        List<List<String>> nQueensSolution = s.solveNQueens(n);
    };
};