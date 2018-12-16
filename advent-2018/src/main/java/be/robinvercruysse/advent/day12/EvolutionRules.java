package be.robinvercruysse.advent.day12;

import java.util.HashMap;
import java.util.Map;

public class EvolutionRules {
    private Map<boolean[], Boolean> rules = new HashMap<>();

    public RuleBuilder when(boolean[] input) {
        return new RuleBuilder(input);
    }

    public boolean evolves(boolean[] input) {
        return rules.get(input);
    }

    public class RuleBuilder {
        private boolean[] input;

        public RuleBuilder(boolean[] input) {
            this.input = input;
        }

        public void then(boolean output) {
            rules.put(input, output);
        }
    }
}
