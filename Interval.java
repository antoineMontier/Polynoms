public class Interval{
  
  //parameters : 
  
  private double a, b;
  private boolean takeA, takeB;              //take means if the value is inside the interval
  
  //builder : 
  
  public Interval(){
    a = 0;
    b = 0;
    takeA = true;
    takeB = true;
  }
  
  public Interval(double x, double y){
    if(x > y){//in case the arguments are passed in the wrong order
      b = x;
      a = y;
    }else{
      a = x;
      b = y;
    }
    takeA = true;
    takeB = true;
  }
  
  public Interval(double x, double y, boolean tA, boolean tB ){
    if(x > y){//in case the arguments are passed in the wrong order
      b = x;
      a = y;
      takeA = tB;
      takeB = tA;
    }else{
      a = x;
      b = y;
      takeA = tA;
      takeB = tB;
    }
  }
  
  public Interval(Interval i){
    a = i.getInf();
    b = i.getSup();
    takeA = i.infTake();
    takeB = i.supTake();
  }
  
  //acceseurs :
  
  public String toString(){
    if(a == b){                   //1st case: the bounds are equal
      if(takeB && takeA){
        return"{" + a + "}";
      }
        return "{∅}";
    }
    String res = "";
    if(takeA){
      res += "[";
    }else{
      res += "]";
    }
    res += a + " ; " + b;
    if(takeB){
      res += "]";
    }else{
      res += "[";
    }
    return res;
  }
  
  public void setInf(double x){
    if(x > b){
      throw new IllegalArgumentException(x + " is greater than " + b);
    }
    a = x;
  }
  
  public void setInf(double x, boolean tA){
    if(x > b){
      throw new IllegalArgumentException(x + " is greater than " + b);
    }
    a = x;
    takeA = tA;
  }
  
    public void setSup(double x){
     if(x < a){
      throw new IllegalArgumentException(x + " is lower than " + a);
    }
    b = x;
  }
  
  public void setSup(double x, boolean tB){
    if(x < a){
      throw new IllegalArgumentException(x + " is lower than " + a);
    }
    b = x;
    takeB = tB;
  }
  
  public void setInterval(double x, double y){
    if(x > y){
      b = x;
      a = y;
    }else{
      a = x;
      b = y;
    }
  }
    
  public void setInterval(double x, double y, boolean tA, boolean tB){
    if(x > y){
      b = x;
      a = y;
      takeA = tB;
      takeB = tA;
    }else{
      a = x;
      b = y;
      takeA = tA;
      takeB = tB;
    }
  }
  
  public double getInf(){//returns the lower bound of the interval
      return a;
  }
  
  public double getSup(){//returns the upper bound of the interval
    return b;
  }
  
  public boolean infTake(){//returns if the lower bound of the interval is inside the interval
    return takeA;
  }
  
  public boolean supTake(){//returns if the upper bound of the interval is inside the interval
    return takeB;
  }
  
  public boolean empty(){//returns if the interval is empty
    return a == b && takeB == false && takeA == false;
  }
  
  
}
