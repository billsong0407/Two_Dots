/**
 * Author: Bill Song
 * Revised: March.15th, 2020
 * 
 * Description: Testing all of the modules
 */

package src;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
    TestBoardT.class,
    TestEndByMoves.class,
    TestEndByTime.class
})

public class AllTests
{   
}
