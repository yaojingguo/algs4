package yao;

import org.junit.Test;

public class HarvesterTest {
    
    @Test
    public void testHarvest() {
        Harvester.harvest("a*", "aab");
        Harvester.harvest("a*", "aabxaab");
    }

}
