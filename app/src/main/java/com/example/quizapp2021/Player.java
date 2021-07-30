package com.example.quizapp2021;

public class Player implements Comparable<Player>{
    private String name;
    private int score;

    public Player(String n, int s)
    {
        name = n; score = s;
    }

    public int getScore(){ return score;}

    public String getName(){ return name;}

    @Override
    public int compareTo(Player p) {
        return this.getScore()- p.getScore();
    }
    public boolean equals(Player p){
        return this.compareTo(p)==0 && this.name.equals(p.getName());
    }
}
