public class Tests {
    public static void main(String[] args){

    /*LinkedList r = new LinkedList();
    r.addHead(2);
    r.addHead(3);
    r.addHead(4);

    System.out.println(r + " of size "+ r.size());
    System.out.println(r + " of size "+ r.size());*/


    LinkedList ll = new LinkedList();
    ll.addHead(new Point(2, 3));
    ll.addHead(new Point(-1, 4));
    ll.addHead(new Point(-5, 8));
    ll.addHead(new Point(11, 14));
    ll.addHead(new Point(-17, 3));
    ll.addHead(new Point(0, -15));
    ll.addHead(new Point(1, 12));
    ll.addHead(new Point(-3, -9));


    Polynom f = Polynom.regression(ll);
    System.out.println("");
    System.out.println("f(x) = " + f);

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
