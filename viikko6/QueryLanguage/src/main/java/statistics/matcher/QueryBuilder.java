package statistics.matcher;

public class QueryBuilder {
    private Matcher matcher;
    
    
    public QueryBuilder() {
        this.matcher = new All();
    }
    
    public QueryBuilder playsIn(String team) {
        this.matcher = new And(new PlaysIn(team), this.matcher);
        return this;
    }
    
    public QueryBuilder hasAtLeast(int value, String category) {
        this.matcher = new And(new HasAtLeast(value, category), this.matcher);
        return this;
    }
    
    public QueryBuilder hasFewerThan(int value, String category) {
        this.matcher = new And(new HasFewerThan(value, category), this.matcher);
        return this;
    }
    
    public QueryBuilder oneOf(Matcher first, Matcher second) {
        this.matcher = new Or(first, second);
        return this;
    }
    
    public Matcher build() {
        Matcher returnValue = this.matcher;
        this.matcher = new All();
        return returnValue;
    }
}
