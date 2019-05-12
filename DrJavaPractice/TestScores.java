import javax.swing.*;

public class TestScores
{
  
  private double [] theScores;
  
  public TestScores(double... scores) throws InvalidTestScore
  {
    theScores = scores;
    for(int i = 0; i< scores.length; i++)
    {
      
      if(scores[i] < 0 || scores[i] >100)
      {
        throw new InvalidTestScore();
      }
    }
    
  }
  
  public double getAverageScore()
  {
    double sumOfScores = 0;
    for(int i = 0; i<theScores.length; i++)
    {
      sumOfScores += theScores[i];
    }
    
    return sumOfScores/theScores.length;
  }
  
  public static void main(String [] args)
{
    
  double[] mathScores = {56,90,67,80,45, 102};
  TestScores mathTest = null;
  
  try{
    mathTest = new TestScores(mathScores);
    System.out.println(mathTest.getAverageScore());
  }catch(InvalidTestScore | NullPointerException e1)
  {
    JOptionPane.showMessageDialog(null, "The score must be between 0 and 100!");
  }
  
}
}

