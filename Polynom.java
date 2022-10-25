public class Polynom {


    private LinkedList c;//coefficient tab

    public Polynom(){
        c = new LinkedList();
    }

    public Polynom(Monom m){
        c = new LinkedList(m);
    }

    public Polynom(double x){
        Monom m = new Monom(x, 0);
        c = new LinkedList(m);
    }

    public Polynom(double x, int n){
        Monom m = new Monom(x, n);
        c = new LinkedList(m);
    }

    public String toString(){
        String res = "";
        for(int i = 0 ; i < c.size() ; i++){
            res += c.get(i) + " ";
        }
        return res;
    }

    /**
     * @return the monom number
     */
    public int size(){
        int r = 0;
        for(int i = 0 ; i < c.size() ;i++){
            if(((Monom)c.get(i)).getCoefficient() != 0){//assert the monom value isn't zero
                r++;
            }
        }
        return r;
    }

    public void add(Monom m){
        if(m.getCoefficient() == 0){
            return;
        }
        int k = m.getPow();

        for(int i = 0 ; i < size() ; i++){
            if(k == ((Monom)c.get(i)).getPow()){
                c.set(i, new Monom(((Monom)c.get(i)).getCoefficient() + m.getCoefficient(), k));//if there's already a monom with this power, just add the two coefficients
                return;
            }
        }
        c.addHead(m);
    }

    public void add(double x){
        if(x == 0 ){
            return;
        }
        for(int i = 0 ; i < size() ; i++){
            if(0 == ((Monom)c.get(i)).getPow()){
                c.set(i, new Monom(((Monom)c.get(i)).getCoefficient() + x, 0));//if there's already a monom with this power, just add the two coefficients
                return;
            }
        }
        Monom m = new Monom(x, 0);
        System.out.print("adding " + m);
        c.addTail(m);
    }

    public void add(double x, int n){
        if(n < 0){
            throw new IllegalArgumentException("the power of a monom must be positif but you entered " + n + " as power");
        }
        if(x == 0){
            return;
        }
        for(int i = 0 ; i < size() ; i++){
            if(n == ((Monom)c.get(i)).getPow()){
                c.set(i, new Monom(((Monom)c.get(i)).getCoefficient() + x, n));//if there's already a monom with this power, just add the two coefficients
                return;
            }
        }
        Monom m = new Monom(x, n);
        c.addTail(m);
    }




    /**
     * sort the polynom by the power desc : x^n + x^n-1 + ... + x^2 + x + a
     */
    public void sort(){//to test with a 0-monom to assert that the function that kills useless monoms is working properly
        if(size() < 2){
            return;
        }

        //kill useless monoms
        for(int i = 0 ; i < c.size() ; i++){
            if(((Monom)c.get(i)).getCoefficient() == 0 || c.get(i) == null){
                c.removeCell(i);
            }
        }

        if(c.size() != size()){
            throw new Error("error in function sort ; there are " + (c.size() - size())+" useless monoms in the list of the polynom" + this);
        }
        
        int n = size() -2;
        int exchangeindex;
        while(n >= 0){
          exchangeindex = 0;
          for(int j = 0 ; j <= n ; j++){
            if(((Monom)c.get(j)).getPow() <  ((Monom)c.get(j+1)).getPow()){ //that means power at j monom is greater than value at j+1 monom
              c.swap(j, j+1);
              exchangeindex = j;
            }
          }
          n = exchangeindex - 1;
        }
        return;//the list is sorted
    }

    /**
     * @param x the multiplier
     * modify the polynom by multiplying it
     */
    public void times(double x){
        if(x == 1 || size() == 0){
            return;
        }
        if(x == 0){
            for(int i = 0 ; i < c.size() ; i++){
                c.set(i, new Monom(0));
            }
            
            return;
        }

        for(int i = 0 ; i < c.size() ; i++){
            Monom r = new Monom(((Monom)c.get(i)));
            r.setCoefficient(r.getCoefficient()*x);
            c.set(i, r);
        }
    }




    
}