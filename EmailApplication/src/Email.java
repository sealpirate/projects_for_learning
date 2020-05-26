import java.util.Scanner;

public class Email {
    private String firstName;
    private String lastName;
    private String password;
    private String department;
    private int mailboxCapacity = 500;
    private String alternateMail;
    private int defaultPasswordLength = 8;
    private String email;
    private String companySuffix = "company.com";

    //создание почтового адреса из имени и фамилии, задание отдела, создание пароля и т.д.
    
    public Email(String firstName, String lastName){
        this.firstName = firstName;
        this.lastName = lastName;
        this.department = setDepartment();
        this.password = getPassword(defaultPasswordLength);
        System.out.println("your password is " + this.password);
        email = firstName.toLowerCase() + "." + lastName.toLowerCase() +"@" +
                department.toLowerCase() + "." + companySuffix;
    }

    private String setDepartment() {
        System.out.print("Enter the department\n1 for Sales\n2 for Development\n3 for " +
                "Accounting\n0 for none \n");
        Scanner in = new Scanner(System.in);
        int choiceOfDep = in.nextInt();
        switch (choiceOfDep) {
            case (1):
                return "Sale";
            case (2):
                return "Development";
            case (3):
                return "Accounting";
            default:
                return "";
        }
    }
//создание пароля заданной длины из случайного набора символов
    private String getPassword(int length){
        String passwordSet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!;()*";
        char[] password = new char[length];
        int rand;
        for (int i = 0; i < length ; i++) {
           rand = (int)(Math.random()*passwordSet.length());
           password[i] = passwordSet.charAt(rand);
        }
        return new String(password);
    }

    public void setMailboxCapacity(int capacity){
       this.mailboxCapacity = capacity;
    }

    public void setAlternateMail(String alternateMail){
        this.alternateMail = alternateMail;
    }

    public void changePassword(String password){
        this.password = password;
    }

    public int getMailboxCapacity(){
        return mailboxCapacity;
    }

    public String getAlternateMail(){
        return alternateMail;
    }

    public String getPassword(){
        return password;
    }

    public String showInfo(){
        return "DISPLAY NAME: " + firstName + " " + lastName +
                " COMPANY EMAIL: " + email +
                " MAILBOX CAPACITY: " + mailboxCapacity + "mb";
    }
}
