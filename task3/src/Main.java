import java.io.*;

public class Main {
    public static int rand(int min, int max) {
        int temp = (int)((max-min+1)*Math.random());
        return min+temp%(max-min+1);
    }

    public static String[] returnNames(String fileName) {
        String[] results = new String[100];
        try {
            int counter = 0;
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String temp = reader.readLine();
            while(temp != null) {
                if(counter >= results.length) {
                    String[] tempArr = results.clone();
                    results = new String[results.length+100];
                    System.arraycopy(tempArr, 0, results, 0, counter);
                }
                results[counter] = temp;
                temp = reader.readLine();
                counter++;
            }
            String[] tempArr = results.clone();
            results = new String[counter];
            System.arraycopy(tempArr, 0, results, 0, counter);
            reader.close();
        } catch (IOException ex) {
            System.out.println("THE PROVIDED FILE \""+fileName+"\" DOES NOT EXIST. PLEASE CREATE A FILE WITH RANDOM NAMES.");
            System.exit(1);
        }
        return results;
    }

    public static void printRelationship(Person[] people) {
        for(int i = 0; i < people.length; ++i) {
            System.out.print((i+1)+": "+people[i].getName()+" (balance - "+people[i].getAvailableSum()+"€) -");
            for(int j = 0; j < people[i].getFriendCount(); ++j) {
                System.out.print(" "+people[i].getFriend(j).getName());
                System.out.print(((j < people[i].getFriendCount()-1) ? "," : ".\n"));
            }
        }
    }

    public static Person[] pathToItself(Person person, int requiredMoney) {
        for(int i = 0; i < person.getFriendCount(); ++i) {
            Person tempP = person.getFriend(i);
            if(requiredMoney-tempP.getAvailableSum() <= 0)
                continue;
            Person[] tempList = pathToItself(person, requiredMoney-tempP.getAvailableSum(), tempP);
            if(tempList != null)
                return tempList;
        }
        return null;
    }

    private static Person[] pathToItself(Person person, int requiredMoney, Person current) {
        if(requiredMoney-current.getAvailableSum() <= 0)
            return null;
        current.setMarked(true);
        for(int i = 0; i < current.getFriendCount(); ++i) {
            if(current.getFriend(i) == person) {
                return new Person[]{current};
            }
        }
        for(int i = 0; i < current.getFriendCount(); ++i) {
            Person tempP = current.getFriend(i);
            if(tempP.isMarked())
                continue;
            Person[] tempList = pathToItself(person, requiredMoney-current.getAvailableSum(), tempP);
            if(tempList != null) {
                Person[] tempList1 = new Person[tempList.length+1];
                System.arraycopy(tempList, 0, tempList1, 1, tempList.length);
                tempList1[0] = tempP;
                return tempList1;
            }
        }
        current.setMarked(false);
        return null;
    }

    public static void resetMarks(Person[] people) {
        for(Person person : people)
            person.setMarked(false);
    }

    public static void main(String[] args) {
	if(args.length != 2) {
		System.out.println("THE PROGRAM WAS STARTED INCORRECTLY. ENTER THE PARAMETERS CORRECTLY (instructions in readMe.md).");
	}
	int peopleCount = 0;
	try {
        	peopleCount = Integer.parseInt(args[1]);
	} catch(Exception ex) {
		System.out.println("THE PEOPLE_COUNT PARAMETER WAS ENTERED INCORRECTLY.");
		System.exit(1);
	}
        final int borrowedMoney = 1000;
        Person[] people = new Person[peopleCount];
        String[] names = returnNames(args[0]);

        for(int i = 0; i < peopleCount; ++i) {
            int temp = rand(0, names.length-1);
            while(names[temp] == null)
                temp = rand(0, names.length-1);
            people[i] = new Person(names[temp], rand(0, 100));
            names[temp] = null;
        }

        for(int i = 0; i < peopleCount; ++i) {
            int temp = rand(1, (int) Math.ceil(Math.sqrt(peopleCount)));
            for(int j = 0; j < temp; ++j) {
                int temp2 = rand(0, peopleCount-1);
                while(temp2 == i)
                    temp2 = rand(0, peopleCount-1);
                people[i].insertFriend(people[temp2]);
            }
        }

        printRelationship(people);
        System.out.println();

        for(int i = 0; i < peopleCount; ++i) {
            Person[] list = pathToItself(people[i], borrowedMoney);
            if(list == null) {
                System.out.println(people[i].getName() + " (" + (i + 1) + ") IS NOT ABLE TO BACKTRACK TO THEMSELVES.\n");
            }
            else {
                System.out.println(people[i].getName()+" ("+(i+1)+") IS ABLE TO BACKTRACK THEMSELVES THIS WAY: ");
                System.out.println(people[i].getName()+" ->");
                Person lastPerson = null;
                for(int j = 0; j < list.length; ++j) {
                    if(lastPerson == list[j])
                        continue;
                    System.out.println(list[j].getName()+" ->");
                    lastPerson = list[j];
                }
                System.out.println(people[i].getName()+".\n");
            }
        }
    }
}
