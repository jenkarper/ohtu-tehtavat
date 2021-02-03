
package ohtu;

public class Player implements Comparable<Player> {
    private String name;
    private String nationality;
    private String team;
    private int goals;
    private int assists;

    public String getNationality() {
        return nationality;
    }
    
    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public int getGoals() {
        return goals;
    }

    public void setGoals(int goals) {
        this.goals = goals;
    }

    public int getAssists() {
        return assists;
    }

    public void setAssists(int assists) {
        this.assists = assists;
    }
    
    public int getPoints() {
        return goals + assists;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return String.format("%-20s",name) + " " + team + " " + String.format("%2d",goals) + " + " 
                + String.format("%2d",assists) + " = " + getPoints();
    }
    
    @Override
    public int compareTo(Player t) {
        return t.getPoints()-this.getPoints();
    }
      
}
