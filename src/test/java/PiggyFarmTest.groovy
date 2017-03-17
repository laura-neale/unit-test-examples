import spock.lang.Specification;

/*
Simple test using spock mocking framework
 */
class PiggyFarmTest extends Specification {

    def pigA = Mock(Piggy)
    def pigB = Mock(Piggy)
    def pigC = Mock(Piggy)

    def "get smallest piggy returns smallest piggy in farm"() {
        given: ""
        pigA.weight >> 0
        pigB.weight >> 1
        pigC.weight >> 2
        def farm = new PiggyFarm([pigA, pigB, pigC])

        when: ""
        def smallestPiggy = farm.smallestPiggy

        then: ""
        smallestPiggy.is(pigA)
    }

}