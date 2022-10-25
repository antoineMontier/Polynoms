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

    public Polynom(Polynom p){
        c = new LinkedList();
        for(int i = 0 ; i < p.size() ; i++){
            c.addHead(((Monom)p.get(i)));
        }
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
        return c.size();
        /*int r = 0;
        for(int i = 0 ; i < c.size() ;i++){
            if(((Monom)c.get(i)).getCoefficient() != 0){//assert the monom value isn't zero
                r++;
            }
        }
        return r;*/
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

    public Monom get(int i){
        if(i < 0 || i >= size()){
            throw new IllegalArgumentException("you can't get the "+i+"e monom of the polynom" + this);
        }
        this.sort();
        return ((Monom)c.get(i));
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


    public Polynom plus(Monom m){
        Polynom res = new Polynom(this);
        res.add(m);
        res.sort();
        return res;
    }
    
    public Polynom plus(double x){
        Polynom res = new Polynom(this);
        res.add(x);
        res.sort();
        return res;
    }
    
    public Polynom plus(double x, int n){
        Polynom res = new Polynom(this);
        if(n < 0){
            throw new IllegalArgumentException("the power of a monom must be positif but you entered " + n + " as power");
        }
        res.add(x, n);     
        res.sort();
        return res;
    }
   
    public Polynom plus(Polynom p){
        Polynom res = new Polynom(this);
        for(int i = 0 ; i < p.size() ; i++){
            Monom m = new Monom(((Monom)p.get(i)));
            res.add(m.getCoefficient(), m.getPow());
        }
        res.sort();
        return res;
    }




    /**
     * @param x the multiplier
     * modify the polynom by multiplying it
     */
    public Polynom times(double x){
        Polynom res = new Polynom();
        if(x == 1 || size() == 0){
            return res;
        }
        if(x == 0){
            for(int i = 0 ; i < c.size() ; i++){
                c.set(i, new Monom(0));
            }
            
            return res;
        }

        for(int i = 0 ; i < c.size() ; i++){
            Monom r = new Monom(((Monom)c.get(i)));
            r.setCoefficient(r.getCoefficient()*x);
            res.add(r);
        }
        res.sort();
        return res;
    }

    public Polynom times(Monom m){
        Polynom res = new Polynom();
        if((m.getPow() == 0 && m.getCoefficient() == 1 )|| size() == 0){
            return res;
        }
        if(m.getCoefficient() == 0){
            for(int i = 0 ; i < c.size() ; i++){
                c.set(i, new Monom(0));
            }
            res.sort();
            return res;
        }

        for(int i = 0 ; i < c.size() ; i++){

            Monom r = new Monom(((Monom)c.get(i)));//copy of actual monom

            r.setCoefficient(r.getCoefficient()*m.getCoefficient());
            r.setPow(r.getPow() + m.getPow());

            res.add(r);
        }
        return res;
    }

    public Polynom times(double x, int n){
        Polynom res = new Polynom();

        if(n < 0){
            throw new IllegalArgumentException("the power must be positive, you entered " + n);
        }
        if((x == 1 && n == 0)|| size() == 0){
            return res;
        }
        if(x == 0){
            for(int i = 0 ; i < c.size() ; i++){
                c.set(i, new Monom(0));
            }
            
            return res;
        }

        for(int i = 0 ; i < c.size() ; i++){
            Monom r = new Monom(((Monom)c.get(i)));
            r.setCoefficient(r.getCoefficient()*x);
            r.setPow(r.getPow() + n);
            res.add(r);
        }
        res.sort();
        return res;
    }

    public Polynom times(Polynom p){
        Polynom res = new Polynom(this);
        if(p.size() == 0 || size() == 0){
            return new Polynom();//0-polynom if one of the two polynoms is null
        }
        if(p.size() == 1){
            Monom m = new Monom(((Monom)p.get(0)));//using the previous methode if a polynom is a monom
            return times(m);
        }
        if(size() == 1){
            Monom m = new Monom(((Monom)get(0)));//using the previous methode if a polynom is a monom
            return  p.times(m);
        }

        //iterative way to multiply two polynoms :

        for(int i = 0 ; i < p.size() ; i++){
            res = res.plus(res.times(p.get(i)));
        }
        res.sort();
        return res;
    }








    
}