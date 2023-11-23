package ex05;

public class UsersArrayList implements UsersList {
    private int sizeArray = 10;
    User[] usersArray = new User[this.sizeArray];
    private int usersCount = 0;

    @Override
    public void addUser(User user){
        if(this.usersCount == sizeArray){
            User[] newUsersArray = new User[this.sizeArray * 2];
            for (int i = 0; i <this.sizeArray ; i++) {
                newUsersArray[i] = this.usersArray[i];
            }
            this.usersArray = newUsersArray;
            this.sizeArray *= 2;
        }
        usersArray[this.usersCount] = user;
        this.usersCount++;
    };

    @Override
    public User getUserById(Integer id){
        for (int i = 0; i <this.usersCount ; i++) {
            if(this.usersArray[i].getIdentifier().equals(id))
                return this.usersArray[i] ;
        }
        throw new UserNotFoundException("User not found");
    }
    @Override
    public User getUserByIndex(Integer index){
        if (index<0 || index >= this.usersCount || this.usersArray[index] == null)
            throw new UserNotFoundException("Incorrect index of user");
        return this.usersArray[index];
    }
    @Override
    public Integer getUserCount(){
        return this.usersCount;
    }

    @Override
    public String toString(){
        StringBuilder usersString = new StringBuilder();
        for (int i = 0; i < this.usersCount; i++) {
            usersString.append(this.usersArray[i].toString());
            usersString.append("\n");
        }
        return usersString.toString();
    }
}
