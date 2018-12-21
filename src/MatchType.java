public enum MatchType {
    BEST_OF_THREE{
        private int matchType = 3;
        private int requirements = 2;

        @Override
        public int getMatchType() {
            return matchType;
        }

        @Override
        public void setMatchType(int matchType) {
            this.matchType = matchType;
        }

        @Override
        public int getRequirements() {
            return requirements;
        }

        @Override
        public void setRequirements(int requirements) {
            this.requirements = requirements;
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
        public void setRequirements(int requirements) {
            this.requirements = requirements;
        }

        @Override
        public int getMatchType() {
            return matchType;
        }

        @Override
        public void setMatchType(int matchType) {
            this.matchType = matchType;
        }
    };
    private int matchType;
    private int requirements;

    MatchType() {
    }

    public int getMatchType() {
        return matchType;
    }

    public void setMatchType(int matchType) {
        this.matchType = matchType;
    }

    public int getRequirements() {
        return requirements;
    }

    public void setRequirements(int requirements) {
        this.requirements = requirements;
    }
}
