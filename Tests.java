public class Tests {
    public static void main(String[] args){

    /*LinkedList r = new LinkedList();
    r.addHead(2);
    r.addHead(3);
    r.addHead(4);

    System.out.println(r + " of size "+ r.size());
    System.out.println(r + " of size "+ r.size());*/



    Polynom f = new Polynom(1, 0);
   // f.add(new Monom(4, 1));
    //f.add(-7, 15);
   // f.add(11, 14);
    //f.add(0);
    f.sort();
   f.times(1.2);

    System.out.println(f + " of size "+ f.size());





    }
}
