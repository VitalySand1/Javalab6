import java.time.LocalDate;
import java.util.*;
import java.util.Comparator;

public class PhoneBook {
    int countOfPages;
    String color;
    String state;
    List<Abonent> abonent;
    LocalDate yearOfPublishing;

    public PhoneBook() {
        int countOfPages = 0;
        String color = "";
        String state = "";
        List<Abonent> abonent = new ArrayList<Abonent>();
        LocalDate yearOfPublishing = LocalDate.now();
    }

    public PhoneBook(int countOfPages, String color, String state, List<Abonent> abonent, LocalDate yearOfPublishing) {
        this.countOfPages = countOfPages;
        this.color = color;
        this.state = state;
        this.abonent = abonent;
        this.yearOfPublishing = yearOfPublishing;
    }

    public int getCountOfPages() {
        return countOfPages;
    }

    public void setCountOfPages(int countOfPages) {
        this.countOfPages = countOfPages;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public List<Abonent> getAbonent() {
        return abonent;
    }

    public void setAbonent(List<Abonent> abonent) {
        this.abonent = abonent;
    }

    public LocalDate getYearOfPublishing() {
        return yearOfPublishing;
    }

    public void setYearOfPublishing(LocalDate yearOfPublishing) {
        this.yearOfPublishing = yearOfPublishing;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PhoneBook phoneBook = (PhoneBook) o;
        return countOfPages == phoneBook.countOfPages &&
                Objects.equals(color, phoneBook.color) &&
                Objects.equals(state, phoneBook.state) &&
                Objects.equals(abonent, phoneBook.abonent) &&
                Objects.equals(yearOfPublishing, phoneBook.yearOfPublishing);
    }

    @Override
    public int hashCode() {

        return Objects.hash(countOfPages, color, state, abonent, yearOfPublishing);
    }

    public int CountOfAbonents(){
        return abonent.size();
    }
    public int CountOfSameName(){
        int count = 0;
        for(int i=0;i<abonent.size();i++){
            for(int j=i+1;j<abonent.size();j++){
                if(abonent.get(i).getImya().equals(abonent.get(j).getImya())) count++;
            }
        }
        return count;
    }
    //-----------//
    public List<Abonent> sort_Up_alphabet(){
        Collections.sort(abonent, new Comparator<Abonent>() {
            public int compare(Abonent t1, Abonent t2) {
                if (t1.getFamiliya().charAt(0)>t2.getFamiliya().charAt(0))
                    return 1;
                else return -1;
            }
        });
        return abonent;
    }
    public List<Abonent> sort_Down_Alphabet(){
        Collections.reverse(sort_Up_alphabet());
        return abonent;
    }
    public static void main(String args[]){
       /* Abonent A1 = new Abonent("Vitaly", "Sand",false,03722424, LocalDate.of(1998,12,14));
        Abonent A2 = new Abonent("Anton", "Antonw",false,03722424, LocalDate.of(1988,11,10));
        Abonent A3 = new Abonent("Bodya", "BodyaAA",false,03722424, LocalDate.of(1997,5,5));
        List<Abonent> Abonent1 = new ArrayList<>();
        Abonent1.add(A1);
        Abonent1.add(A2);
        Abonent1.add(A3);
        PhoneBook P = new PhoneBook(300,"Blue","Bukovina", Abonent1,LocalDate.of(2017,5,22));
        System.out.print(P.CountOfAbonents());*/
    }
}
