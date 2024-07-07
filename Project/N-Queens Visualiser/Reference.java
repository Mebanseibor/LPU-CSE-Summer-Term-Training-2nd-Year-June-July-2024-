import java.util.ArrayList;
import java.util.List;

class Solution {
    public void displayBoard(int[][] queensMatrix){
        int boardSize = queensMatrix.length;
        
        System.out.println("-----Board Start-----");
        for(int positionRank=0; positionRank<boardSize ; positionRank++){
            for(int positionFile=0 ; positionFile<boardSize ; positionFile++){
                if(queensMatrix[positionRank][positionFile] == 1){System.out.print("Q "); continue;};
                System.out.print("- ");
            };
            System.out.println();
        };
    }

    public boolean isValidSquare(int file, int rank, int[][] queensMatrix){
        int boardSize = queensMatrix.length;
        
        // How to replicate the following state:
        if(file>=boardSize || rank>=boardSize){return false;}

        // check in the current file
        for(int pointerRank = rank ; pointerRank>=0 ; pointerRank--){
            if(queensMatrix[pointerRank][file] == 1){return false;}
        }
        
        // check in the upper-left diagonal
        for(int pointerFile = file, pointerRank = rank ; pointerFile>=0 && pointerRank>=0 ; pointerFile--, pointerRank--){
            if(queensMatrix[pointerRank][pointerFile] == 1){return false;}
        }

        // check in the upper-right diagonal
        for(int pointerFile = file, pointerRank = rank ; pointerFile<boardSize && pointerRank>=0 ; pointerFile++, pointerRank--){
            if(queensMatrix[pointerRank][pointerFile] == 1){return false;}
        }
        
        return true;
    }

    public void display_queensPosition(List<String> queensPosition){
        for(int i=0 ; i<queensPosition.size() ; i++){
            System.out.println(queensPosition.get(i));
        }
    }

    public void placeQueen(int file, int rank, int[][] queensMatrix, List<List<String>> answer, int queensRemaining, List<String> queensPosition){
        int boardSize = queensMatrix.length;
        
        
        // if all queens are already placed
        if(queensRemaining == 0 && file == 0){
            displayBoard(queensMatrix);  // [#Debugging]
            List<String> newQueensPosition = new ArrayList<>();
            newQueensPosition= queensPosition;
            answer.add(newQueensPosition);
            System.out.println("FOUND!!!!!");    // [#Debugging]
            
            System.out.println("queensPosition:");  // [#Debugging]
            display_queensPosition(queensPosition); // [#Debugging]
            return;
        };

        if(file>=boardSize || rank>=boardSize){return;}
        
        System.out.println("Queens Remaining: " +queensRemaining);   // [#Debugging]
        if(!isValidSquare(file, rank, queensMatrix)){
            System.out.println("Not Possible for Rank: " +rank+ " and File: " +file);    // [#Debugging]
            return;
        }
        System.out.println("Possible at rank: " +rank+ " and file: " +file);  // [#Debugging]
        
        
        
        // add the string for the queen's position for the rank
        String queenPositionOnRank = "";
        for(int pointerFile=0 ; pointerFile<boardSize ; pointerFile++){
            if(pointerFile == file){queenPositionOnRank += "Q"; continue;}
            queenPositionOnRank += ".";
        }
        queensPosition.add(queenPositionOnRank);
        System.out.println("<<Position added>>, Size: " +queensPosition.size());   // [#Debugging]

        // change the queens Matrix
        queensMatrix[rank][file] = 1;
        System.out.println("Position after Adding Queen: ");    // [#Debugging]
        displayBoard(queensMatrix); // [#Debugging]
        System.out.println("--------------");   // [#Debugging]
        

        for(int pointerFile=0 ; pointerFile<boardSize ; pointerFile++){
            // try to place queen in the rank
            placeQueen(pointerFile, rank+1, queensMatrix, answer, queensRemaining-1, queensPosition);
        };
        
        System.out.println("\nNext File: " +(file+1)+ " for Rank: " +rank); // [#Debugging]
        
        // change the queens Matrix to remove previous states
        queensMatrix[rank][file] = 0;
        
        // update the queensPosition too to reflect the backtracking procedure
        if(queensPosition.size()>1){
            queensPosition.remove(queensPosition.size()-1);
            System.out.println("Last Added Queen position was Removed from Rank: " +rank+ " and File: " +file);   // [#Debugging]
        };
        
        System.out.println("Reference:");   // [#Debugging]
        displayBoard(queensMatrix); // [#Debugging]
        System.out.println(answer.get(0).size());
    };

    public List<List<String>> solveNQueens(int n) {
        List<List<String>> answer = new ArrayList<>();
        
        // create a matrix to keep track of all the squares that the queen has vision of
        int[][] queensMatrix = new int[n][n];
        
        // place the first queen on every square on the first rank
        for(int positionFile = 0; positionFile<n ; positionFile++){
            List<String> queensPosition = new ArrayList<>();
            placeQueen(positionFile, 0, queensMatrix, answer, n, queensPosition);
        };
        
        int size = answer.size();
        for(int i=0 ; i<size ; i++){
            System.out.println("Position " +(i+1));
            System.out.println("SIZE: " +answer.get(i).size());
        }

        return answer;
    }
}

public class Main{
    public static void main(String[] args){
        Solution solution = new Solution();
        List<List<String>> list = solution.solveNQueens(5);
        
        int size = list.size();
        for(int i=0 ; i<size ; i++){
            System.out.println("Position " +(i+1));
            List<String> queensPosition = list.get(i);
            for(int j=0 ; j<queensPosition.size() ; j++){
                System.out.println(queensPosition.get(j));
            };
            
            System.out.println("-----Position END-----\n");
        }
    };
}