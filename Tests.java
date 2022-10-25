public class Tests {
    public static void main(String[] args){

    /*LinkedList r = new LinkedList();
    r.addHead(2);
    r.addHead(3);
    r.addHead(4);

    System.out.println(r + " of size "+ r.size());
    System.out.println(r + " of size "+ r.size());*/

        

    Polynom f = new Polynom(00);
    Polynom k = new Polynom(7, 11);
    k.add(2, 4);
    k.add(1, 5);
    f.add(new Monom(7, 11));
    f.add(1, 1);
    f.add(1, 14);
    f.add(0);
    f.sort();

    System.out.println("f(x) = " + f + "f'(x)= " + f.derivate());

    System.out.println("f(x) = " + f + "F(x)= " + f.primitive());
    
    System.out.println("f(x) = " + f + "F'(x)= " + f.primitive().derivate());



    /*
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
