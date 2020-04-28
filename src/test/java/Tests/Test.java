package test.java.Tests;

import org.testng.Reporter;
import test.java.Commons.*;

public class Test extends Init {


    @org.testng.annotations.Test
    public void cannotDoUnitTasks()
    {

        login.cannotDoUnitTasksFunctionality();

        Reporter.log("Test run successfully!!!");

    }



}
