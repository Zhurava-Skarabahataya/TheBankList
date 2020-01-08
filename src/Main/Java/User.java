public class User {
    String name;
    String surName;
    int userID;
    int accountID;
    int account;

    public User(int userID, String name, String surName, int accountID, int account){
        this.userID = userID;
        this.name = name;
        this.surName = surName;
        this.accountID = accountID;
        this.account  =account;
    }
}
