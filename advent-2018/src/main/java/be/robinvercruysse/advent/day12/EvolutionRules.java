package be.robinvercruysse.advent.day12;

import java.util.HashMap;
import java.util.Map;

public class EvolutionRules {
    private Map<Rule, Boolean> rules = new HashMap<>();

    public RuleBuilder when(boolean... input) {
        return new RuleBuilder(input);
    }

    public boolean evolves(boolean... input) {
        Boolean value = rules.get(new Rule(input));
        return value != null ? value : false;
    }

    public class RuleBuilder {
        private boolean[] input;

        public RuleBuilder(boolean... input) {
            this.input = input;
        }

        public void then(boolean output) {
            rules.put(new Rule(input), output);
        }
    }
}
