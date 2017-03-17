import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Strings;

@JsonIgnoreProperties({"breed", "age"})
public class Piggy {

    public static final String DEFAULT_NAME = "anonymous";

    @JsonProperty(required = true)
    private String name;

    @JsonProperty
    private int weight;

    public String getName() {
        return Strings.isNullOrEmpty(name) ? DEFAULT_NAME : name;
    }

    public int getWeight() {
        return weight;
    }

}
