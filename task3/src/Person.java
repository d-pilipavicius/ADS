public class Person {
    private String name;
    private final int availableSum;
    private Person[] friends;
    private boolean mark;
    public Person(String name, int availableSum, Person[] friends) {
        this.name = name;
        this.availableSum = availableSum;
        this.friends = friends;
        mark = false;
    }
    public Person(String name, int availableSum, Person friend) {
        this.name = name;
        this.availableSum = availableSum;
        friends = new Person[1];
        friends[0] = friend;
        mark = false;
    }
    public Person(String name, int availableSum) {
        this.name = name;
        this.availableSum = availableSum;
        mark = false;
    }
    public int getFriendCount() {
        return (friends == null) ? 0 : friends.length;
    }
    public int getAvailableSum() {
        return availableSum;
    }
    public String getName() { return name; }
    public void setMarked(boolean mark) { this.mark = mark; }
    public Person getFriend(int id) { return (id >= getFriendCount()) ? null : friends[id]; }
    public boolean isMarked() { return mark; }
    public void insertFriend(Person friend) {
        if(friend == this)
            return;
        if(friends == null)
        {
            friends = new Person[1];
            friends[0] = friend;
            return;
        }
        for(int i = 0; i < friends.length; ++i) {
            if(friends[i] == friend)
                return;
        }
        Person[] tempFriends = friends;
        friends = new Person[friends.length+1];
        System.arraycopy(tempFriends, 0, friends, 0, tempFriends.length);
        friends[tempFriends.length] = friend;
    }
}
