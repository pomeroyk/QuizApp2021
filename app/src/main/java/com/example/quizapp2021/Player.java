package com.example.quizapp2021;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Player implements Comparable<Player>{
    private String name;
    private int score;

    public Player(String n, int s)
    {
        name = n; score = s;
    }
    public Player(){
        // Important!! This is needed for models that will be used with Firebase Realtime Database
        // Default constructor required for calls to DataSnapshot.getValue
    }
    public String toString() {
        return "Name: " + name + " \tScore: " + score;
    }
    public int getScore(){ return score;}

    public String getName(){ return name;}

    @Override
    public int compareTo(Player p) {
        return this.getScore()- ((Player)p).getScore();
    }
    public boolean equals(Player p){
        return this.compareTo(p)==0 && this.name.equals(p.getName());
    }



}
