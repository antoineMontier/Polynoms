public class Tests {
    public static void main(String[] args){

    /*LinkedList r = new LinkedList();
    r.addHead(2);
    r.addHead(3);
    r.addHead(4);

    System.out.println(r + " of size "+ r.size());
    System.out.println(r + " of size "+ r.size());*/

        

    Polynom f = new Polynom(1);
    f.add(new Monom(4, 1));
    f.add(-7, 15);
    f.add(11, 14);
    f.add(0);
    f.sort();

    System.out.println(f + " of size "+ f.size());

    f.times(-0.1, 5);

    System.out.println(f + " of size "+ f.size());

    for(int i = 0 ; i  <4 ; i++){
        System.out.println("element" + i + " : " + f.get(i));
    }





    }
}
