package edu.escuelaing.arep.app;
import junit.framework.Test;
import junit.framework.TestCase;
import static junit.framework.TestCase.assertTrue;
import junit.framework.TestSuite;
public class AppTest 
    extends TestCase
{
    public AppTest( String testName )
    {
        super( testName );
    }
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }
    public void testApp()
    {
        assertTrue( true );
    }
}