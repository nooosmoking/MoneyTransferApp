package ex05;

public class AddUser implements Command {
    @Override
    public void run() {
        System.out.println("Enter a user name and a balance");
        String answerStr = Menu.in.nextLine().trim();
        String[] answers = answerStr.split(" ");
        if(answers.length == 2 && answers[0].matches("[^0-9]+")){
            try {
                int balance = Integer.parseInt(answers[1]);
                int id = Menu.service.addUser(answers[0], balance);
                System.out.println("User with id = " + id +" is added");
            } catch (RuntimeException ex) {
                System.out.println("Incorrect balance!");
            }
        } else {
            System.out.println("You should write name and balance!");
        }
    }
}
