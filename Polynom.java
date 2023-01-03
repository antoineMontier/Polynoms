public class Polynom {

    private final double PRECISION = 100;
    private final double X_MAX = 100;
    private final double X_MIN = -X_MAX;


    private LinkedList c;//coefficient tab

    /**
     * creates an empty polynom
     */
    public Polynom(){
        c = new LinkedList();
    }

    /**
     * @param m monom to build the polynom
     */
    public Polynom(Monom m){
        c = new LinkedList(m);
    }

    /**
     * @param x coefficient
     */
    public Polynom(double x){
        Monom m = new Monom(x, 0);
        c = new LinkedList(m);
    }

    /**
     * @param x coefficient
     * @param n power
     */
    public Polynom(double x, int n){
        Monom m = new Monom(x, n);
        c = new LinkedList(m);
    }

    /**
     * @param p polynom to copy
     * copy contructor (copy the monom too to avoid bad modifications on another polynom)
     */
    public Polynom(Polynom p){
        c = new LinkedList();
        for(int i = 0 ; i < p.size() ; i++){
            c.addHead(new Monom((Monom)p.get(i)));
        }
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     * returns the polynom with simplifications on writtings /!\ 0 coefficients won't be returned even if the monom does exists
     */
    public String toString(){
        String res = "";
        for(int i = 0 ; i < c.size() ; i++){
            res += c.get(i) + " ";
        }
        if(res == "" || res == " "){
            res = "0";
        }
        return res;
    }

    /**
     * @return the monom number
     */
    public int size(){
        //kill useless monoms
        for(int i = 0 ; i < c.size() ; i++){
            if(((Monom)c.get(i)).getCoefficient() == 0 || c.get(i) == null){
                c.removeCell(i);
            }
        }
        return c.size();
    }

    /**
     * @param m is the monom we want to add
     * adding the monom to the polynom (or increasing the coefficient if it already exists)
     */
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

    /**
     * @param x coefficient
     * adding the double number to the polynom (or increasing the coefficient if it already exists)
     */
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
        //System.out.print("adding " + m);
        c.addTail(m);
    }

    /**
     * @param x coefficient
     * @param n power
     * adding the monom(x, n) to the polynom (or increasing the coefficient if it already exists)
     */
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
     * @param i index
     * @return the monom at the index (following the linkedlist rules)
     */
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


    /**
     * @param m monom to add
     * @return new polynom resulting the addition
     */
    public Polynom plus(Monom m){
        Polynom res = new Polynom(this);
        res.add(m);
        res.sort();
        return res;
    }

    /**
     * @param x double to add
     * @return new polynom resulting the addition
     */
    public Polynom plus(double x){
        Polynom res = new Polynom(this);
        res.add(x);
        res.sort();
        return res;
    }

    /**
     * @param x coefficient
     * @param n power
     * @return new polynom resulting the addition
     */
    public Polynom plus(double x, int n){
        Polynom res = new Polynom(this);
        if(n < 0){
            throw new IllegalArgumentException("the power of a monom must be positif but you entered " + n + " as power");
        }
        res.add(x, n);
        res.sort();
        return res;
    }

    /**
     * @param p polynom to add
     * @return new polynom resulting the addition
     */
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
            return this;
        }
        if(x == 0){
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

    /**
     * @param m monom to multiply
     * @return new polynom resulting the multiplication
     */
    public Polynom times(Monom m){
        Polynom res = new Polynom();
        if((m.getPow() == 0 && m.getCoefficient() == 1 )|| size() == 0){
            return this;
        }
        if(m.getCoefficient() == 0){
            return res;
        }

        for(int i = 0 ; i < c.size() ; i++){

            Monom r = new Monom(((Monom)c.get(i)));//copy of actual monom

            r.setCoefficient(r.getCoefficient()*m.getCoefficient());
            r.setPow(r.getPow() + m.getPow());

            res.add(r);
        }
        res.sort();
        return res;
    }

    /**
     * @param x coefficient
     * @param n power
     * @return new polynom resulting the multiplication
     */
    public Polynom times(double x, int n){
        Polynom res = new Polynom();

        if(n < 0){
            throw new IllegalArgumentException("the power must be positive, you entered " + n);
        }
        if((x == 1 && n == 0)|| size() == 0){
            return this;
        }
        if(x == 0){
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

    /**
     * @param p polynom to multiply
     * @return new polynom resulting the multiplication
     */
    public Polynom times(Polynom p){
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
        Polynom rres = new Polynom();

        for(int i = 0 ; i < p.size() ; i++){
            rres = rres.plus(this.times(p.get(i)));
        }
        rres.sort();
        return rres;
    }


    /**
     * @return the maximal degree of the polynom ; returns 0 if the polynom is empty
     */
    public int deg(){
        int res = 0;
        if(size() == 0){
            return res;
        }
        int k;
        for(int i = 0 ; i < size() ; i++){
            k=((Monom)c.get(i)).getPow();
            if(k > res){
            res = k;
            }

        }
        return res;
    }

    /**
     * @param p polynom to compare
     * @return true if both polynoms are exactly the same, otherwise returns false
     */
    public boolean equals(Polynom p){
        if(p.size() == 0 && size() == 0){
            return true;
        }
        if(p.size() != size()){
            return false;
        }
        p.sort();
        sort();
        for(int i = 0 ; i < size() ; i++){
            if(!p.get(i).equals(get(i))){
                return false;
            }
        }
        return true;
    }

    /**
     * @param n index of the coefficient we're searching for
     * @return coefficient with this index
     */
    public double getCoefficientWithIndex(int n){
        if(n < 0){
            throw new IllegalArgumentException("the power must be positive, you entered " + n);
        }
        if(size() == 0){
            return 0;
        }
        sort();
        for(int i = 0 ; i < size() ; i++){
            if(((Monom)c.get(i)).getPow() == n){
                return ((Monom)c.get(i)).getCoefficient();
            }
        }
        return 0;
    }

    /**
     * @param x abs value we want to test
     * @return image of x
     * usage of Math.pow
     */
    public double im(double x){
        if(size() == 0){
            return 0;
        }
        double res = 0;
        for(int i = 0 ; i < size() ; i++){
            res += get(i).getCoefficient()*Math.pow(x, get(i).getPow());
        }
        return res;
    }

    /**
     * @param n power
     * @return polynome raised to the n power
     */
    public Polynom pow(int n){
        if(n < 0){
            throw new IllegalArgumentException("the power must be positive, you entered " + n);
        }
        if(n == 0){
            return new Polynom(1);
        }
        if(n == 1){
            return this;
        }
        Polynom res = new Polynom(this);
        for(int i = 0 ; i < n-1 ; i++){
            res = res.times(this);
        }
        return res;
    }

    /**
     * @return the derivate of the actual polynom
     */
    public Polynom derivate(){
        Polynom res = new Polynom();
        if(deg() == 0){
            return res;
        }
        for(int  i = 0 ; i< size() ; i++){
            if(get(i).getPow() > 0){
                res.add(get(i).getCoefficient()*get(i).getPow() , get(i).getPow()-1 );
            }
        }
        return res;
    }

    /**
     * @return the primitive of the actual polynom
     */
    public Polynom primitive(){
        Polynom res = new Polynom();
        if(size() == 0){
            return res;
        }
        for(int i = 0 ; i < size() ; i++){
            res.add(get(i).getCoefficient()/(get(i).getPow()+1), get(i).getPow() +1);
        }
        return res;
    }

    /**
     * @param inf inferior value
     * @param sup superior value
     * @return the integral of the current polynom between inf and sup
     */
    public double integral(double inf, double sup){
        if(size() == 0 || inf == sup){
            return 0;
        }
        return primitive().im(sup) - primitive().im(inf);
    }

    /**
     * @return if the function is even f(x) = f(-x)
     * the precision of this function can be improved by increasing PRECISION parameter
     * the precision decrease as the deg() of the polynom increase
     */
    public boolean even(){
        if(size() == 0){
            return true;
        }
        double pas = 2*deg()/PRECISION;//proportionnal to degree of function (because double numbers has limitations)
        boolean res = true;
        for(double x = 0 ; x < X_MAX && res ; x += pas){
            res = res && (Math.abs(im(x) - im(-x)) < pas);
            //System.out.println("even at x = " + x);
        }
        return res;
    }

    /**
     * @return if the function is uneven f(-x) = -f(x)
     * the precision of this function can be improved by increasing PRECISION parameter
     * the precision decrease as the deg() of the polynom increase
     */
    public boolean uneven(){
        if(size() == 0){
            return true;
        }
        double pas = 2*deg()/PRECISION;//proportionnal to degree of function (because double numbers has limitations)
        boolean res = true;
        for(double x = 0 ; x < X_MAX && res ; x += pas){
            res = res && (Math.abs(im(-x) + im(x)) < pas);
            //System.out.println("uneven at x = " + x);
        }
        return res;
    }

    /**
     * @return a list of points located on the polynom graph, all with a 0 y-axis ; (roots)
     */
    public LinkedList roots(){
        LinkedList res = new LinkedList();
        if(size() == 0){
            return res;
        }
        //principal loop
        boolean prevpositiv;
        boolean positiv = im(X_MIN) > 0;
        boolean focus = false;
        double pas = deg()/PRECISION;
        //seach loop
        double s;
        boolean spositiv;
        boolean sprevpositiv;
        boolean finded;


        for(double x = X_MIN ; x < X_MAX ; x += pas){//principal loop

            prevpositiv = positiv;

            positiv = im(x) > 0;

            if(positiv != prevpositiv){//if the function sign changes, the focus searches needs to start
                focus = true;
            }

            if(focus){
                finded = false;
                s = x - pas;
                spositiv = im(s)> 0;
                for(double e = s ; e < x && !finded ; e += pas/PRECISION){
                    sprevpositiv = spositiv;
                    spositiv = im(e) > 0;
                    if(spositiv != sprevpositiv){
                        finded = true;
                        s = e;
                    }
                }
                res.addHead(new Point(s /*- pas/2*PRECISION*/, 0));
                focus = false;
            }
        }
        return res;
    }

    /**
     * @return a an interval list on which the polynom is positive
     */
    public LinkedList positive(){
        LinkedList res = new LinkedList();
        if(size() == 0){
            return res;
        }
        LinkedList roots = roots();
        if(roots.isEmpty()){
            return res;
        }
        LinkedList simpleXroots = new LinkedList();
        for(int i = 0  ; i < roots.size() ; i++){
            simpleXroots.addHead(((Point)roots.get(i)).getAbs());//extract the x values of all the roots
        }
        simpleXroots.sort();
        roots = null;//destroy roots list
        double px;
        boolean positive;
        Interval in = new Interval();
        double x = X_MIN;
        double y = ((double)simpleXroots.get(0));

        //catch the 1 root exception
        if(simpleXroots.size() == 1){
          if(im(x/2 + y/2) > 0){
            res.addHead(new Interval(X_MIN, y));
          }else{
            res.addHead(new Interval(y, X_MAX));
          }
          return res;
        }


        for(int i = 1 ; i < simpleXroots.size(); i++){
          if(im(x/2 + y/2) > 0){

            res.addTail(new Interval(x, y));
          }
          do {
            x = y;
            y = ((double)simpleXroots.get(i));
          } while (im(x/2 + y/2) < 0);

        }
        y = X_MAX;
        if(im(x/2 + y/2) > 0){
          res.addTail(new Interval(x, y));
        }
        return res;
    }

    /**
     * @param l the Linkedlist of points
     * @return a polynom that passes throught every points
     * be aware, every points should have a different x-value
     */
    public static Polynom regression(LinkedList l){
        //empty list exception
        if(l.size() == 0){
            throw new IllegalArgumentException("list of points must be at least composed of 1 elemnt !");
        }

        //assert the list contains only points
        for(int i = 0 ; i < l.size(); i++){
            if(LinkedList.stringCompare(l.get(i).getClass().getSimpleName(), "Point") != 0){
                throw new IllegalArgumentException("list of points must be composed of points but the element "+ l.get(i) + " isn't a point (index : " + i + ") !");
            }
        }

        Polynom res = new Polynom();


        //one element exception
        if(l.size() == 1){
            res.add(((Point)l.get(0)).getOrd());
            return res;
        }

        //assert no points has the same x value
        for(int i = 0 ; i < l.size() -1; i++){
            double search = ((Point)l.get(i)).getAbs();//remove search
            for(int j = i+1 ; j < l.size() ; j++){
                if(((Point)l.get(j)).getAbs() == search){
                    throw new IllegalArgumentException("point " + l.get(i) + " (index : " + i + ") and point " + l.get(j) + " (index : " + j + ") have the same x-value !");
                }
            }
        }
        
        //Here, the list is full of points of different x-values

        double sys[][] = new double[l.size() + 1][l.size() + 1];

        for(int i = 0 ; i < sys.length -1; i++){
            for(int j = 0 ; j < sys[0].length -1 ;j++){     //j deosn't go to the end because we use the end to keep y-axis
                sys[i][j] = Math.pow(((Point)l.get(i)).getAbs(), l.size()-1 -j);
            }
            sys[i][sys[0].length -1] = -((Point)l.get(i)).getOrd();
          }

          LinearSystem ls = new LinearSystem(sys);
          ls.scale();
          LinkedList solutions = ls.solutions();

          if(solutions.size() != l.size()){
            throw new Error("in function regression , the size of solutions list (" + solutions.size() + ") should be the same as the point list size ("+l.size()+")");
          }
          for(int d = 0 ; d < solutions.size() ; d++){
            res.add(((double)solutions.get(d)), solutions.size() -1- d);
          } 
          res.sort();
          return res;
    }


    /**
     * @param t is the tangent point
     * @return a polynom ax + b tangent of the called polynom at x point
     * f(x) = f'(t)(x - t) + f(t) // f'(t)*x - f'(t)*t + f(t)
     */
    public Polynom tangent(double t){
        Polynom res = new Polynom();
        res.add(derivate().im(t), 1);
        res.add(-t*derivate().im(t));
        res.add(im(t));
        return res;
    }
    
    public LinkedList inflexion(){
        return derivate().derivate().roots();
    }
}
