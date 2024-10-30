package org.example;
public class Main
{
    public static void main( String[] args )
    {
        DBService dbService = new DBService();
        User user = dbService.getUserByLogin("user5");
        if (user != null) {
            System.out.println(user);
        } else {
            System.out.println("User not found");
        }
        User user2 = new User("new_user", "12345", "new_user@example.com");
        int a = dbService.addUser(user2);
        System.out.println(a);
    }
}
