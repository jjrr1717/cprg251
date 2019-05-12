public class InvalidTestScore extends Exception
{
  public InvalidTestScore()
  {
    super("Error! Number must be between 0 and 100");
  }
}