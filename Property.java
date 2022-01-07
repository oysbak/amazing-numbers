package numbers;

import java.util.Arrays;
import java.util.List;

public enum Property {
    EVEN, ODD, BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY, SUNNY, SQUARE, JUMPING, SAD, HAPPY,
    NOT_EVEN, NOT_ODD, NOT_BUZZ, NOT_DUCK, NOT_PALINDROMIC, NOT_GAPFUL, NOT_SPY, NOT_SUNNY,
    NOT_SQUARE, NOT_JUMPING, NOT_SAD, NOT_HAPPY;

    // Checks the param-list of properties whether any are mutually exclusive
    public static String[] anyMutuallyExclusive(List<Property> properties) {
        for (Property property : properties) {
            List<Property> partners = property.getMutuallyExclusivePartners();
            assert partners != null;
            for (Property partner : partners)
                if (properties.contains(partner)) {
                    assert property.getMutuallyExclusivePartners() != null;
                    return new String[]{property.name(), partner.name()};
                }
        }
        return null;
    }

    public static String allPropertiesToString() {
        return Arrays.asList(values()).toString();
    }

    private List<Property> getMutuallyExclusivePartners() {
        switch (this) {
            case EVEN:
                return List.of(ODD, NOT_EVEN);
            case ODD:
                return List.of(EVEN, NOT_ODD);
            case BUZZ:
                return List.of(NOT_BUZZ);
            case DUCK:
                return List.of(SPY, NOT_DUCK);
            case PALINDROMIC:
                return List.of(NOT_PALINDROMIC);
            case GAPFUL:
                return List.of(NOT_GAPFUL);
            case SPY:
                return List.of(DUCK, NOT_SPY);
            case SUNNY:
                return List.of(SQUARE, NOT_SUNNY);
            case SQUARE:
                return List.of(SUNNY, NOT_SQUARE);
            case JUMPING:
                return List.of(NOT_JUMPING);
            case SAD:
                return List.of(HAPPY, NOT_SAD);
            case HAPPY:
                return List.of(SAD, NOT_HAPPY);
            case NOT_EVEN:
                return List.of(NOT_ODD, EVEN);
            case NOT_ODD:
                return List.of(NOT_EVEN, ODD);
            case NOT_BUZZ:
                return List.of(BUZZ);
            case NOT_DUCK:
                return List.of(NOT_SPY, DUCK);
            case NOT_PALINDROMIC:
                return List.of(PALINDROMIC);
            case NOT_GAPFUL:
                return List.of(GAPFUL);
            case NOT_SPY:
                return List.of(NOT_DUCK, SPY);
            case NOT_SUNNY:
                return List.of(NOT_SQUARE, SUNNY);
            case NOT_SQUARE:
                return List.of(NOT_SUNNY, SQUARE);
            case NOT_JUMPING:
                return List.of(JUMPING);
            case NOT_SAD:
                return List.of(NOT_HAPPY, SAD);
            case NOT_HAPPY:
                return List.of(NOT_SAD, HAPPY);
            default:
                return null;
        }
    }

    @Override
    public String toString() {
        return this.name();
    }
}
