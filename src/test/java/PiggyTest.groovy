import com.fasterxml.jackson.core.JsonParseException
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException
import spock.lang.Specification
import spock.lang.Title
import spock.lang.Unroll

import static groovy.test.GroovyAssert.shouldFail

@Title("Tests deserialization of a piggy")
class PiggyTest extends Specification {

    def mapper = new ObjectMapper()

    @Unroll
    "test deserializing valid piggy names"() {
        given: "a valid json with name #name"
        def json = "{\"name\": ${inputName}}"

        when: "deserializing a piggy"
        def piggy = mapper.readValue(json, Piggy.class)

        then: "a piggy is created with name #name"
        piggy.name == output

        where:
        inputName               | output
        "\"sir pigglesworth\""  | "sir pigglesworth"
        "\"\""                  | Piggy.DEFAULT_NAME
        "null"                  | Piggy.DEFAULT_NAME
    }

    @Unroll
    "piggy weight is extracted correctly"() {
        given: "a valid json with weight #weight"
        def json =  """ {
                "name": null,
                "weight": $weight
            } """

        when: "deserializing a piggy"
        def piggy = mapper.readValue(json, Piggy.class)

        then: "a piggy is created with weight #weight"
        piggy.weight == weight

        where:
        weight | _
        -1     | _
        0      | _
        100    | _
    }

    def "ignorable properties are ignored"() {
        given: "a valid json with the breed and age fields"
        def json =  """ {
                "name": null,
                "breed": "potbellied pig",
                "age": 7
            } """

        expect: "a piggy is created without error"
        mapper.readValue(json, Piggy.class)
    }

    @Unroll
    "invalid json throws expection"() {
        expect: "a json with #scenario throws #exception when trying to create a piggy"
        shouldFail(exception) {
            mapper.readValue(json, Piggy.class)
        }

        where:
        scenario             |   json                                       |   exception
        "no name"            |   """ { "weight":140) """                    |   JsonParseException
        "uknown properties"  |   """ { "name":null, "gender":"f"} """       |   UnrecognizedPropertyException
        "missing bracket"    |   """ { "name":null """                      |   JsonParseException
    }

}
