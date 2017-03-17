import spock.lang.Specification;

/*
Simple test using spock mocking framework
 */
class PiggyFarmTest extends Specification {

    //pigs can only be constructed by deserializing a string
    //but this unit test shouldn't be concerned with whether the deserialization works
    //so mock pigs are used instead of real ones
    def smallPig = Mock(Piggy)
    def mediumPig = Mock(Piggy)
    def largePig = Mock(Piggy)

    def "test smallest pig award is given correctly for pigs with different weights"() {
        given: "three pigs of different weights"
        smallPig.weight >> 10
        mediumPig.weight >> 50
        largePig.weight >> 100
        def farm = new PiggyFarm([smallPig, mediumPig, largePig])

        when: "giving the smallest pig award"
        farm.giveSmallestPigAward()

        then: "the pig with the lowest weight is given an award, and no other pig is given an award"
        //giveAward() has no testable effects, so this uses interaction to test it
        1 * smallPig.giveAward()
        0 * mediumPig.giveAward()
        0 * largePig.giveAward()
    }

}