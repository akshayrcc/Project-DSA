package com.cybertron.contests;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

interface OnlineAccount {
	int basePrice = 120;
	int regularMoviePrice = 45;
	int exclusiveMoviePrice = 80;
}
class Account implements OnlineAccount, Comparable<Account> {

    int noOfRegularMovies, noOfExclusiveMovies;
    String ownerName;

    // 1) Add a parameterized constructor that initializes the attributes noOfRegularMovies and noOfExclusiveMovies.
    public Account(String ownerName, int noOfRegularMovies, int noOfExclusiveMovies) {
        this.ownerName = ownerName;
        this.noOfRegularMovies = noOfRegularMovies;
        this.noOfExclusiveMovies = noOfExclusiveMovies;
    }

    // 2. This method returns the monthly cost for the account.
    public int monthlyCost() {
        return basePrice + (noOfRegularMovies * regularMoviePrice) + (noOfExclusiveMovies * exclusiveMoviePrice);
    }

    // 3. Override the compareTo method of the Comparable interface such that two accounts can be compared based on their monthly cost.
    public int compareTo(Account o) {
        if (this.monthlyCost() > o.monthlyCost()) { 
            return 1; 
        } 
        else if (this.monthlyCost() < o.monthlyCost()) { 
            return -1; 
        } 
        else { 
            return 0; 
        } 
    }
    
    // 4. Returns "Owner is [ownerName] and monthly cost is [monthlyCost] USD."
    public String toString() {
        return "Owner is "+ this.ownerName + " and monthly cost is "+ this.monthlyCost() +" USD.";
    }
}