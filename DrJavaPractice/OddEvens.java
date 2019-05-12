public class OddEvens{
  public static void main (String [] args){
    
    oddEven(5,10);

}
  
 public static void oddEven(int low, int high)
{
 if(low > high)
 {
  return;
 }
 else
 {
  if(low % 2 == 0)
  {
   System.out.println(low + " is even");
  }
  else
  {
   System.out.println(low+" is odd");
  }
  oddEven(low + 1, high);
 }
}

}

      
    
  