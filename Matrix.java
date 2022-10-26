public class Matrix{

    private double coefficients[][];

    public Matrix(int dimension){
        coefficients = new double[dimension][dimension];
    }

    /**
     * @param m is the matrix we want to copy
     */
    public Matrix(Matrix m){
        coefficients = new double[m.size()][m.size()];
        for(int l = 0 ; l < m.size() ; l++){
            for(int c = 0 ; c < m.size() ; c++){
                coefficients[l][c] = m.get(l, c);
            } 
        }
    }

    /**
     * @return the number of lines (which is equal to the number of lines)
     */
    public int size(){
        return coefficients.length;
    }

    /**
     * @param l is the line number you want to get
     * @param c is the column number you want to get
     * @return double coefficient located at (l, c)
     */
    public double get(int l, int c){
        if(l < 0 || l > size()){
            throw new IllegalArgumentException("l must be greater or equal to 0 and lower than " + size() + " you entered " + l);
        }
        if(c < 0 || c > size()){
            throw new IllegalArgumentException("c must be greater or equal to 0 and lower than " + size() + " you entered " + c);
        }
        return coefficients[l][c];
    }
}
