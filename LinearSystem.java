import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LinearSystem{
    //parameters :
    private double c[][]; // array of double to keep the coefficient 
    private List<String> inconnues = new ArrayList<>(Arrays.asList("a", "b", "c", "d", "e", "f", "g","h","i","j","k","l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"));//inconnues pour le toString()
    private int nbL, nbC; //number of lines & columns -1 each times


    //build :
   
    public LinearSystem(double[][] k){
        if(k[0].length-1 > k.length){         //if there are more coefficients than lines, the solutions will be infinity... 
            System.out.print("System linked");
            c = new double[0][0];
            return;
        }
        c = new double[k.length][k[0].length];
        for(int i = 0 ; i < k.length ; i++){
            for(int j = 0 ; j < k[i].length ; j++){
                c[i][j] = k[i][j];
            }
        }
        nbC = k[0].length - 1;
        nbL = k.length - 2;
    }
    
    //access :
    
    public String toString(){
        String res = "";
        for(int i = 0 ; i <= nbL ; i++){
            res += "{";
            for(int j = 0 ; j < nbC -1; j++){
                res += c[i][j] + "*" + inconnues.get(j) + " + ";
            }
            res += c[i][nbC -1] + "*" + inconnues.get(nbC-1) + " + " + (c[i][nbC])  + " = 0\n";
        }
        return res;
    }
    
    //méthodes :
    public void deleteCol(int n){
      if(n < 0 || n > nbC){
        System.out.println("impossible to delete the column " + n);
        return;
      }
      for(int i = 0 ; i <= nbL ; i++){//selection of all the lines
        for(int j = n+1 ; j <= nbC ; j++){
          c[i][j - 1] = c[i][j];
        }
      }
      inconnues.remove(n);
      nbC--; //the column number decrease by 1
    }
    
    public void deleteLine(int n){
      if(n < 0 || n > nbL){
        System.out.println("impossible to delete the line " + n);
        return;
      }
      for(int j = 0 ; j <= nbC ; j++){//column selection
        for(int i = n+1 ; i <= nbL ; i++){ //line selection
          c[i-1][j] = c[i][j];
        }
      }
      nbL--;
    }
    
    public void multLine(int n, double x){
      if(n < 0 || n > nbL){
        System.out.println("impossible to multiply the line " + n + " by " + x);
        return;
      }
      for(int j = 0 ; j <= nbC ; j++){
        c[n][j] *= x;
      }
    }
    
    public void swapLine(int dest, int src){
      if(dest < 0 || dest > nbL){
        System.out.println("impossible to swap line " + dest +" and " + src);
        return;
      }
      if(src < 0 || src > nbL){
        System.out.println("impossible to swap line " + dest +" and " + src);
        return;
      }
      double tmp;
      for(int j = 0 ; j <=nbC ; j++){
        tmp = c[dest][j];
        c[dest][j] = c[src][j];
        c[src][j] = tmp;
      }
    }
    
    public void addLine(int dest, int src, double coef){ //<=>  L(dest) <- L(dest) + coef * L(source)
      if(coef == 0){
        return;                     //no modifications if we multiply the line by 0
      }
      if(dest < 0 || dest > nbL){
        System.out.println("impossible to add line " + dest + " " + coef +" time by " + src);
        return;
      }
      if(src < 0 || src > nbL){
        System.out.println("impossible to add line " + dest + " " + coef +" time by " + src);
        return;
      }   
      for(int j = 0 ; j <=nbC ; j++){
        c[dest][j] += coef*c[src][j];
      }
    }
    
    public void deleteEmptyLine(){
      List<Integer> l = new ArrayList<Integer>();   //keep the index of empty lines
      boolean empty;
      for(int i = 0 ; i <= nbL ; i++){
        empty = true;                                //empty set to true before the analysis of every line
        for(int j = 0 ; (j <= nbC) && empty ; j++){
            empty = empty && (c[i][j] == 0);
        }
        if(empty){
          l.add(i);                                 //add i if line n°i is empty
        }
      }
      for(int i = l.size() -1 ; i >= 0 ; i--){
        deleteLine(l.get(i));
      }
    }
    
    public void deleteEmptyCol(){
      List<Integer> l = new ArrayList<Integer>(); //keep the index of empty columns
      boolean empty;
      for(int j = 0 ; j <= nbC ; j++){
        empty = true;//empty set to true before the analysis of every column
        for(int i = 0 ; (i <= nbL) && empty ; i++){
            empty = empty && (c[i][j] == 0);
        }
        if(empty){
          l.add(j);//add j if line n°j is empty
        }
      }
      for(int i = l.size() -1 ; i >= 0 ; i--){
        deleteCol(l.get(i));
      }
    }
    
    
    
    //solution :
    
    public void scale(){                
         //reduce system's size if lines or columns are empty
      deleteEmptyCol();
      deleteEmptyLine();
      int pivot;
      
      for(int k = 0  ; k < nbL ; k++){//we need to repeat the principal loop n-2 times (that's nbL -1)
      
        
        
        pivot = k;
        
        while(c[pivot][k] == 0){    //we seach line by line a good pivot (with a coefficient != 0)
            pivot++;                //increase pivot value to access the next line
          }
          
        if(pivot != k){
            swapLine(k, pivot);    //put pivot's line on the top of the system (by swapping)
            pivot = k;
          }
        
        multLine(pivot, 1/c[pivot][k]);         //transform pivot's line to make a 1 appear as the pivot's coefficient
        
        for(int l = k+1 ; l <= nbL ; l++){
          addLine(l, pivot, -c[l][k]);          //scale
          }
        deleteEmptyCol();
        deleteEmptyLine();                    
        }
    }
    
    
    public LinkedList solutions(){
      LinkedList sol = new LinkedList();
      sol.addTail(-c[nbL][nbC]/c[nbL][nbC - 1]);
      double r = 0;
      for(int l = nbL-1 ; l >= 0 ; l--){ 
        r = -c[l][nbC];
        for(int b = nbL ; b > l ; b--){
          r -= ((double)sol.get(nbL - b))*c[l][b]; 
        }
        r/=c[l][l];
        sol.addTail(r);
      }
      if(sol.size() == nbC){
       sol.reverse();
      }else{
        System.out.println("no solutions");
      }
      return sol;
    }
}
