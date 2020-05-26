public class EmailApp {
    public static void main(String[] args){
        Email email1 = new Email("Jane", "Shepard");
        email1.setAlternateMail("janeshapard@gmail.com");
        System.out.println(email1.showInfo());
    }
}
