public class Point{

    //builder :
  
    public Point(){
      abs = 0;
      ord = 0;
    }
  
    public Point(double x, double y){
      abs = x;
      ord = y;
    }
  
  
    //parameters :
  
    public double abs;
    public double ord;
  
    //access :
  
    public double getAbs(){
      return abs;
    }
    public double getOrd(){
      return ord;
    }
    public void setAbs(double a){
      abs = a;
    }
    public void setOrd(double a){
      ord = a;
    }
  
    public String toString(){
      return "(" + abs + ", " + ord + ")" ;
    }
  
    //méthodes :
  
    public void translate(double x, double y){
      abs += x;
      ord += y;
    }
  
    public boolean superposition(Point p){
      return p.getAbs() == abs && p.getOrd() == ord ;
    }
  
    public Point middle(Point p){
      Point npoint = new Point();
      npoint.setAbs((p.getAbs() + abs)/2);
      npoint.setOrd((p.getOrd() + ord)/2);
      return npoint;
    }
  
    public double distance(Point p){
      return Math.sqrt((abs - p.getAbs())*(abs - p.getAbs())+(ord - p.getOrd())*(ord - p.getOrd()));
    }
  
    public static double distance(Point a, Point b){
      return Math.sqrt( (a.getAbs() - b.getAbs())*(a.getAbs() - b.getAbs()) +  (a.getOrd() - b.getOrd())*(a.getOrd() - b.getOrd()) );
    }
  
  }
