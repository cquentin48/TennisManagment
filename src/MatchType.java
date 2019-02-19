public enum MatchType {
    BEST_OF_THREE{
        private int matchType = 3;
        private int requirements = 2;

        @Override
        public int getMatchType() {
            return matchType;
        }

        @Override
        public int getRequirements() {
            return requirements;
        }
    },
    BEST_OF_FIVE {
        private int requirements = 3;
        private int matchType = 5;

        @Override
        public int getRequirements() {
            return requirements;
        }

        @Override
        public int getMatchType() {
            return matchType;
        }
    };

    private int matchType;
    private int requirements;


    public int getMatchType() {
        return matchType;
    }

    public int getRequirements() {
        return requirements;
    }
}
