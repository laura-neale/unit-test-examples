import java.util.List;
import java.util.NoSuchElementException;

public class PiggyFarm {

    private List<Piggy> piggies;

    public PiggyFarm(List<Piggy> piggies) {
        this.piggies = piggies;
    }

    public void giveSmallestPigAward() {
        Piggy smallestPiggy = getSmallestPiggy();
        smallestPiggy.giveAward();
    }

    private Piggy getSmallestPiggy() {
        return piggies.stream()
                .reduce((a,b) -> getSmallerPiggy(a, b))
                .orElseThrow(() -> new NoSuchElementException("No piggies found"));
    }

    private Piggy getSmallerPiggy(Piggy a, Piggy b) {
        if (a.getWeight() < b.getWeight()) {
            return a;
        } else {
            return b;
        }
    }
}
