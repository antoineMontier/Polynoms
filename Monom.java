public class Monom {
    
    /**
     *coefficient in front of the x value : coefficient*x^{pow}
     */
    protected double coefficient;

    /**
     *power of x : coefficient*x^{pow}
     */
    protected int pow;



   
    /**
     * create a 0*x^1
     */
    public Monom(){
        this.coefficient = 0;
        this.pow = 1;
    }

    /**
     * @param coefficient is the coefficient in front of x : coefficient*x^1
     */
    public Monom(double coefficient) {
        this.coefficient = coefficient;
        this.pow = 1;
    }

    /**
     * @param coefficient   is the value that multiplies x
     * @param pow   is the power of x
     */
    public Monom(double coefficient, int pow) {
        if(pow < 0){
            throw new IllegalArgumentException("the new power value for the monom you want to build. must be positive, you entered " + pow);
        }
        this.coefficient = coefficient;
        this.pow = pow;
    }

    /**
     * @param m is the monom we want to copy
     */
    public Monom(Monom m){
        this.coefficient = m.getCoefficient();
        this.pow = m.getPow();
    }

    /**
     * @return the power of the current monom
     */
    public int getPow(){
        return pow;
    }


    /**
     * @return the coefficient of the acutal monom
     */
    public double getCoefficient(){
        return coefficient;
    }

    /**
     * @param p is the new power of the acutal monom
     */
    public void setPow(int p){
        if(p < 0){
            throw new IllegalArgumentException("the new power value for the monom " + this + " must be positive, you entered " + p);
        }
        pow = p;
    }

    /**
     * @param c is the new coefficient of the acutal monom
     */
    public void setCoefficient(double c){
        coefficient = c;
    }

    /**
     * @param c is the new coefficient of the acutal monom
     * @param p is the new power of the acutal monom
     */
    public void setMonom(double c, int p){
        if(p < 0){
            throw new IllegalArgumentException("the new power value for the monom " + this + " must be positive, you entered " + p);
        }
        pow = p;
        coefficient = c;
    }


    /**
     * @param m is the monom we want to compare
     * @return true if both the coefficients are 0 or if the coefficients and pow are equals ; else false
     */
    public boolean equals(Monom m) {
       if(m.getCoefficient() == 0 && coefficient == 0){
        return true;
       }
       return m.getPow() == pow && m.getCoefficient() == coefficient;
    }

    public Monom times(Monom m){
        return new Monom(this.coefficient*m.getCoefficient() , this.pow+m.getPow());
    }

    public String toString(){
        if(coefficient == 0){
            return "";
        }
        if(pow > 1){
            if(coefficient < 0){
                return coefficient + "x^" + pow;
            }else{
                return "+" + coefficient + "x^" + pow;
            }
        }
        if(pow == 1){
            if(coefficient < 0){
                return coefficient + "x";
            }else{
                return "+" + coefficient + "x";
            }
        }
        if(coefficient < 0){//case pow == 0
            return coefficient + "";
        }else{
            return "+" + coefficient;
        }
    }


}
