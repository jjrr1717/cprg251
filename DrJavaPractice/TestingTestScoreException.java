public class TestingTestScoreException()
{
  public static void main (String [] args)
  {
    double[] mathScores = {56,90,67,80,45, -1};
  TestScores mathTest = null;
  
  try{
    mathTest = new TestScores(mathScores);
  }catch(IllegalArgumentException e)
  {
    JOptionPane.showMessageDialog(null, "The score must be between 0 and 100!");
  }
  
  System.out.println(mathTest.getAverageScore());
  }
}