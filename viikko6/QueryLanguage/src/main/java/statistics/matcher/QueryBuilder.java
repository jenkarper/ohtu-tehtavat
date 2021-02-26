package statistics.matcher;

public class QueryBuilder {
    private Matcher[] matcherSet;
    
    
    public QueryBuilder() {
        this.matcherSet = new Matcher[]{new All()};
    }
    
    public QueryBuilder playsIn(String team) {
        addMatcher(new PlaysIn(team));
        return this;
    }
    
    public QueryBuilder hasAtLeast(int value, String category) {
        addMatcher(new HasAtLeast(value, category));
        return this;
    }
    
    public QueryBuilder hasFewerThan(int value, String category) {
        addMatcher(new HasFewerThan(value, category));
        return this;
    }
    
    public Matcher build() {
        return new And(matcherSet);
    }
    
    private void addMatcher(Matcher matcher) {
        Matcher[] newMatchers = new Matcher[this.matcherSet.length + 1];
        for (int i = 0; i < this.matcherSet.length; i++) {
            newMatchers[i] = this.matcherSet[i];
        }
        newMatchers[this.matcherSet.length] = matcher;
        this.matcherSet = newMatchers;
    }
}
