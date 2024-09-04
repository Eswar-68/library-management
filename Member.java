import java.util.ArrayList;
import java.util.HashMap;

public class Member {
    String name;
    String doj;
    int fine;
    HashMap<String, String> borrowedBooks=new HashMap<>();
    ArrayList<String[]> history=new ArrayList<>();
    public Member(String name, String doj) {
        this.name = name;
        this.doj = doj;
    }
    public void borrowedBook(String bookName, String dateOfCheckedOut) {
        borrowedBooks.put(bookName,dateOfCheckedOut);
    }
    public String getCheckedOutDate(String bookName){
        return borrowedBooks.get(bookName);
    }
    public void addToHistory(String bookName, String dateOfReturned){
        history.add(new String[]{bookName,borrowedBooks.get(bookName),dateOfReturned});
    }
    public void removeFromBorrowList(String bookName){
        borrowedBooks.remove(bookName);
    }
}
