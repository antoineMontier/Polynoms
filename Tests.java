public class Tests {
    public static void main(String[] args){

    /*LinkedList r = new LinkedList();
    r.addHead(2);
    r.addHead(3);
    r.addHead(4);

    System.out.println(r + " of size "+ r.size());
    System.out.println(r + " of size "+ r.size());*/

        

    Polynom f = new Polynom(1);
    Polynom k = new Polynom(7, 11);
    k.add(2, 0);
    k.add(1, 5);
    //f.add(new Monom(4, 1));
    //f.add(-7, 15);
    //f.add(1, 14);
    f.add(0);
    f.sort();

    System.out.println(f + " of size "+ f.size() + " of deg " + f.deg());

    

   // System.out.println(f + "   *   " +k+" = " + f.times(k));

   




    }
}
