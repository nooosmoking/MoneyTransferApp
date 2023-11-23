package ex05;

public class MakeTransfer implements Command{
    @Override
    public void run(){
        System.out.println("Enter a sender ID, a recipient ID, and a transfer amount");
        String answerStr = Menu.in.nextLine().trim();
        String[] answers = answerStr.split(" ");
        if(answers.length == 3){
            try {
                Menu.service.makeTransaction(Integer.parseInt(answers[0]), Integer.parseInt(answers[1]), Integer.parseInt(answers[2]) );
                System.out.println("The transfer is completed");
            } catch (RuntimeException ex) {
                System.out.println("Incorrect arguments!");
            }
        } else {
            System.out.println("You should write a sender ID, a recipient ID, and a transfer amount!");
        }


    }
}
