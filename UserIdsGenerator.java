package ex05;

public class UserIdsGenerator {
    private Integer lastId = 0;
    private static UserIdsGenerator obj;

    public static UserIdsGenerator getInstance(){
        if (obj == null) {
            obj = new UserIdsGenerator();
        }
        return obj;
    }

    public Integer generateId(){
        lastId++;
        return lastId;
    }
}
