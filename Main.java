import java.util.*;

static HashMap<String, Member> members = new HashMap<>();
static HashSet<String> books = new HashSet<>();


public static boolean isValidMember(String memberName){
    return members.containsKey(memberName);
}

public static boolean isBookBorrowed(String bookName){
    return books.contains(bookName);
}

public static boolean isBookAvailable(String bookName){
    return Book.searchBook(bookName).isPresent();
}

public static boolean isBookBorrowedByMember(Member member, String bookName){
    return member.borrowedBooks.containsKey(bookName);
}

public static void registerMember(String memberName, String dateOfJoining){
    if(isValidMember(memberName)){
        System.out.println("Member already exists");
        return;
    }
    Member member = new Member(memberName, dateOfJoining);
    members.put(memberName, member);
    System.out.println("Member " + memberName + " has been registered.");
}

public static void cancelMembership(String memberName){
    if(!isValidMember(memberName)){
        System.out.println("Member does not exist");
        return;
    }
    Member member=members.get(memberName);
    if(!member.borrowedBooks.isEmpty()){
        System.out.println("Member " + memberName + " have books to return.\n"+member.borrowedBooks.keySet());
    }
    else if(member.fine!=0){
        System.out.println("Member " + memberName + " has fine amount to pay.\n"+member.fine);
    }
    else {
        member = null;
        members.remove(memberName);
        System.out.println("Member " + memberName + " has been cancelled.");
    }
}

public static void checkOutBook(String memberName, String bookName, String dateOfCheckout){
    if(isValidMember(memberName)){
        if(isBookAvailable(bookName)) {
            if (!isBookBorrowed(bookName)) {
                Member member = members.get(memberName);
                member.borrowedBook(bookName,dateOfCheckout);
                books.add(bookName);
                System.out.println("Book " + bookName + " has been borrowed.");
            } else {
                System.out.println("Book already borrowed.");
            }
        }
        else{
            System.out.println("Library do not have this book");
        }
    }
    else{
        System.out.println("Member does not exist");
    }
}

public static void returnBook(String memberName, String bookName, String dateOfReturned){
    if(isValidMember(memberName)){
        if(isBookAvailable(bookName)){
            if(isBookBorrowed(bookName)){
                Member member = members.get(memberName);
                if(!isBookBorrowedByMember(member,bookName)){
                    System.out.println("Book wasn't borrowed by this member");
                    return;
                }
                String dateOfCheckedOut=member.getCheckedOutDate(bookName);
                member.fine=calculateFine(dateOfCheckedOut, dateOfReturned);
                System.out.println("Fine amount: "+member.fine);
                if(member.fine!=0) {
                    payFine(member);
                }
                System.out.println("Member "+memberName+" has returned the book "+bookName);
                books.remove(bookName);
                member.addToHistory(bookName,dateOfReturned);
                member.removeFromBorrowList(bookName);
            }
            else{
                System.out.println("Book wasn't borrowed");
            }
        }
        else{
            System.out.println("Library do not have this book");
        }
    }
    else{
        System.out.println("Member does not exist");
    }
}

public static int calculateFine(String dateOfCheckedOut, String dateOfReturned){
    int fineAmount=0;
    List<String> months=Arrays.asList(new String[]{"january","february","march","april","may","june","july","august","september","october","november","december"});
    String[] datesOfCheckedOut=dateOfCheckedOut.split(" ");
    String[] datesOfReturned=dateOfReturned.split(" ");
    int day1=Integer.parseInt(datesOfCheckedOut[0]);
    int day2=Integer.parseInt(datesOfReturned[0]);
    int month1= months.indexOf(datesOfCheckedOut[1]);
    int month2= months.indexOf(datesOfReturned[1]);
    int year1=Integer.parseInt(datesOfCheckedOut[2]);
    int year2=Integer.parseInt(datesOfReturned[2]);
    int days=calculateDays(day2,month2,year2)-calculateDays(day1,month1,year1);
    return (days-15>0)?(days-15)*2:0;
}

public static int calculateDays(int day, int month, int year){
    int[] daysOfMonth={31,28,31,30,31,30,31,31,30,31,30,31};
    int days=0;
    for(int i=0;i<year;i++){
        days+=(isLeapYear(i))?366:365;
    }
    for(int i=0;i<month;i++){
        days+=daysOfMonth[i];
        if(i==1 && isLeapYear(year)){
            days+=1;
        }
    }
    days+=day;
    return days;
}

public static boolean isLeapYear(int year){
    return (year%4==0 && year%100!=0 || year%400==0);
}

public static void payFine(Member member){
    Scanner sc=new Scanner(System.in);
    System.out.println("Enter amount: ");
    int amount;
    do{
        amount = sc.nextInt();
        if (amount == member.fine) {
            member.fine = 0;
        } else {
            System.out.println("Enter correct amount: ");
        }
    }while(member.fine!=0);
}

public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    boolean exit=true;
    do {
        System.out.println("\t\tLibrary Management\n1.Register Membership\n2.Cancel Membership\n3.Search Book\n4.Check-out Book\n5.Return Book\n6.Exit");
        String memberName = "";
        String dateOfJoining;
        String bookName;
        String dateOfCheckout;
        String dateOfReturned;
        String[] choice = scanner.nextLine().split(",");
        switch (choice[0]) {
            case "1":
                if (choice.length != 3) {
                    System.out.println("Invalid input");
                } else {
                    memberName = choice[1];
                    dateOfJoining = choice[2];
                    registerMember(memberName, dateOfJoining);
                }
                break;
            case "2":
                if (choice.length != 2) {
                    System.out.println("Invalid input");
                } else {
                    memberName = choice[1];
                    cancelMembership(memberName);
                }
                break;
            case "3":
                if (choice.length != 2) {
                    System.out.println("Invalid input");
                } else {
                    bookName = choice[1];
                    Optional<int[]> index = Book.searchBook(bookName);
                    if(index.isEmpty()){
                        System.out.println("Book not found");
                    }
                    else {
                        int[] rowCol=index.get();
                        System.out.println(bookName + " found at " + rowCol[0]+" "+rowCol[1]);
                    }
                }
                break;
            case "4":
                if (choice.length != 4) {
                    System.out.println("Invalid input");
                } else {
                    memberName = choice[1];
                    bookName = choice[2];
                    dateOfCheckout = choice[3];
                    checkOutBook(memberName, bookName, dateOfCheckout);
                }
                break;
            case "5":
                if (choice.length != 4) {
                    System.out.println("Invalid input");
                } else {
                    memberName = choice[1];
                    bookName = choice[2];
                    dateOfReturned = choice[3];
                    returnBook(memberName, bookName, dateOfReturned);
                }
                break;
            case "6":
                exit = false;
                break;
            default:
                System.out.println("Invalid input");
        }
    } while (exit);
    scanner.close();
    for(Map.Entry<String,Member> entry1:members.entrySet()){
        Member member=entry1.getValue();
        System.out.println("Details of member "+member.name+":");
        System.out.println("Borrowed books: ");
        for(Map.Entry<String, String> entry2:member.borrowedBooks.entrySet()){
            System.out.println(entry2.getKey() + " " + entry2.getValue());
        }
        System.out.println("Returned books: ");
        for(String[] str:member.history){
            System.out.println(str[0]+"-"+str[1]+"-"+str[2]);
        }
    }
}