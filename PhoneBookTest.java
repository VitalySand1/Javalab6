import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PhoneBookTest {
    Abonent A1, A2, A3;
    PhoneBook P1,P2;
    @BeforeTest
    public void start() {
        A1 = new Abonent("Vitaly", "Sand",false,03722424, LocalDate.of(1998,12,14));
        A2 = new Abonent("Anton", "Antonw",false,03722424, LocalDate.of(1988,11,10));
        A3 = new Abonent("Bodya", "BodyaAA",false,03722424, LocalDate.of(1997,5,5));
        List<Abonent> Abonent1 = new ArrayList<>();
        Abonent1.add(A1);
        Abonent1.add(A2);
        Abonent1.add(A3);
        List<Abonent> Abonent2 = new ArrayList<>();
        Abonent2.add(A1);
        Abonent2.add(A1);
        Abonent2.add(A2);
        Abonent2.add(A1);
        Abonent2.add(A3);
        P1 = new PhoneBook(300,"Blue","Bukovina", Abonent1,LocalDate.of(2017,5,22));
        P2 = new PhoneBook(300,"Blue","Bukovina", Abonent2,LocalDate.of(2017,5,22));

    }
    @DataProvider
    public Object[][] countProvider(){
        return new Object[][] {{P1,3},{P2,5}};
    }
    @Test(dataProvider="countProvider")
    public void countTest(PhoneBook p, int a) {
        Assert.assertEquals(p.CountOfAbonents(),a);
    }
    @DataProvider
    public Object[][] countNameTestProvider(){
        return new Object[][] {{P1,0},{P2,3}};
    }
    @Test(dataProvider="countNameTestProvider")
    public void countNameTest(PhoneBook p, int a) {
        Assert.assertEquals(p.CountOfSameName(),a);
    }
    @DataProvider
    public Object[][] upSortTest(){
        List<Abonent> abonent = new ArrayList<>();
        abonent.add(A2);
        abonent.add(A3);
        abonent.add(A1);
        return new Object[][] {{P1,abonent}};
    }
    @Test(dataProvider="upSortTest")
    public void testUpSort(PhoneBook p, ArrayList<Abonent> a) {
        Assert.assertEquals(p.sort_Up_alphabet(),a);
    }
    @DataProvider
    public Object[][] downSortTest(){
        List<Abonent> abonent = new ArrayList<>();
        abonent.add(A1);
        abonent.add(A3);
        abonent.add(A2);
        return new Object[][] {{P1,abonent}};
    }
    @Test(dataProvider="downSortTest")
    public void testDownSort(PhoneBook p, ArrayList<Abonent> a) {
        Assert.assertEquals(p.sort_Down_Alphabet(),a);
    }
}