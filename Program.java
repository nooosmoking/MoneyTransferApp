package ex05;

public class Program {

    public static void main(String[] args) {
        Menu menu = new Menu();
        boolean isWork = true;
        boolean isDev = false;
        if (args.length>0 && args[0].equals("--profile=dev"))
            isDev = true;
        while(isWork){
            isWork = menu.work(isDev);
        }
    }
}

