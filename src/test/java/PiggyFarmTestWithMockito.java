import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

/*
simple test using the mockito mock framework
 */
@RunWith(MockitoJUnitRunner.class)
public class PiggyFarmTestWithMockito {

    @Mock
    private Piggy smallPig;
    @Mock
    private Piggy mediumPig;
    @Mock
    private Piggy largePig;

    @Test
    public void givenThreePigsOfDifferentWeights_whenGivingSmallestPigAward_pigWithLowestWeightReceivesAward() {
        when(smallPig.getWeight()).thenReturn(10);
        when(mediumPig.getWeight()).thenReturn(50);
        when(largePig.getWeight()).thenReturn(100);
        PiggyFarm farm = new PiggyFarm(Arrays.asList(smallPig, mediumPig, largePig));

        farm.giveSmallestPigAward();

        verify(smallPig).giveAward();
        verify(mediumPig, times(0)).giveAward();
        verify(largePig, times(0)).giveAward();
    }

}
