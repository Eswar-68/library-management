import java.util.HashMap;
import java.util.Optional;

public class Book {
    static String[][] books={
            {"to kill a mocking bird","1984","pride and prejudice","the great gatsby","moby dick"},
            {"war and peace","the catcher in the rye","the hobbit","crime and punishment","brave new world"},
            {"the brothers karamazov","jane eyre","the odyssey","the divine comedy","the picture of dorian gray"},
            {"wuthering heights","fahrenheit 451","les miserables","lord of the rings","anna karenina"},
            {"the count of monte cristo","ulysses","the iliad","a tale of two cities","don quixote"}
    };
    static HashMap<String, int[]> bookMap=new HashMap<>();
    static{
        for(int i=0;i<books.length;i++){
            for(int j=0;j<books[i].length;j++){
                bookMap.put(books[i][j],new int[]{i,j});
            }
        }
    }
    public static Optional<int[]> searchBook(String bookName){
        bookName=bookName.strip();
        if(bookMap.containsKey(bookName)){
            return Optional.of(bookMap.get(bookName));
        }
        return Optional.empty();
    }
}
