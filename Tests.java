public class Tests {
    public static void main(String[] args){

    /*LinkedList r = new LinkedList();
    r.addHead(2);
    r.addHead(3);
    r.addHead(4);

    System.out.println(r + " of size "+ r.size());
    System.out.println(r + " of size "+ r.size());*/



    Polynom f = new Polynom();
    f.add(2, 80);
    f.add(4, 40);
    f.add(150, 25);
    f.add(4, 13);

    double c[][] = new double[5][5];


    c[0][0] = 2;
    c[0][1] = 1;
    c[0][2] = -1;
    c[0][3] = 4;
    c[0][4] = -19;

    c[1][0] = -1;
    c[1][1] = -2;
    c[1][2] = 1;
    c[1][3] = 1;
    c[1][4] = 3;

    c[2][0] = 2;
    c[2][1] = 4;
    c[2][2] = 2;
    c[2][3] = 1;
    c[2][4] = -25;

    c[3][0] = -1;
    c[3][1] = 1;
    c[3][2] = -1;
    c[3][3] = -2;
    c[3][4] = -5;

    LinearSystem s = new LinearSystem(c);
    //-9.0666 9.266 -0.399 6.866

    
    System.out.println(s);

    s.scale();

    System.out.println(s);

    System.out.println(s.solutions());




















    f.sort();
    System.out.println("");
    
    System.out.println("");



    /*
    System.out.println("f(x) = " + f + "  f is positive on : " + f.positive());

    System.out.println("f(x) = " + f + "  roots of f : " + f.roots());

    System.out.println("f(x) = " + f + "  f even : " + f.even());
    System.out.println("f(x) = " + f + "  f uneven : " + f.uneven());

    System.out.println("f(x) = " + f + "  integral between - 7 and 15 :"  + f.integral(-7, 15));

    System.out.println("f(x) = " + f + "f'(x)= " + f.derivate());
    System.out.println("f(x) = " + f + "F(x)= " + f.primitive());
    System.out.println("f(x) = " + f + "F'(x)= " + f.primitive().derivate());

    System.out.println("f(x) = " + f + "f^2 = " + f.pow(6));

    System.out.println("f(x) = " + f + "f(2) = " + f.im(2));
    System.out.println("f(x) = " + f + "f(0) = " + f.im(0));

    for(int i = 0 ; i <= f.deg() ; i++){
        System.out.println("coeff with index " + i + " in " + f + " : " + f.getCoefficientWithIndex(i));
    }
    System.out.println(f + " = "+ k + " : " + f.equals(k));
    System.out.println(f + " of size "+ f.size() + " of deg " + f.deg());
    System.out.println(f + "   *   " +k+" = " + f.times(k));
     */







    }
}
