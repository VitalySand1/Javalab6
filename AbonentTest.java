import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.LocalDate;

import static org.testng.AssertJUnit.assertEquals;

public class AbonentTest {
    @Test
    public void testIfPilgoviy(){
        Abonent A = new Abonent("Vitaly", "Sand",false,03722424, LocalDate.of(1998,12,14));
        Assert.assertEquals(A.IfPilgoviy(),false);
    }

    @Test
    public void testIfPensioner(){
        Abonent A = new Abonent("Vitaly", "Sand",false,03722424, LocalDate.of(1998,12,14));
        Assert.assertEquals(A.IfPensioner(),false);
    }
    @DataProvider
    public Object[][] regularImyaProvider(){
        Abonent A = new Abonent("Vitaly", "Sand",false,03722424, LocalDate.of(1998,12,14));
        Abonent A1 = new Abonent("vitaly", "sand",false,03722424, LocalDate.of(1998,12,14));
        return new Object [][] {{A,true},{A1,false}};
    }
    @Test(dataProvider = "regularImyaProvider")
    public void testNameRg(Abonent a, boolean s) {
        assertEquals(a.regular_imya(),s);
    }
    @DataProvider
    public Object[][] regularFamiliyaProvider(){
        Abonent A = new Abonent("Vitaly", "Sand",false,03722424, LocalDate.of(1998,12,14));
        Abonent A1 = new Abonent("vitaly", "sand",false,03722424, LocalDate.of(1998,12,14));
        return new Object [][] {{A,true},{A1,false}};
    }
    @Test(dataProvider = "regularFamiliyaProvider")
    public void testFamiliyaRg(Abonent a, boolean s) {
        assertEquals(a.regular_familiya(),s);
    }
    @DataProvider
    public Object[][] regularNomerProvider(){
        Abonent A = new Abonent("Vitaly", "Sand",false,380314433, LocalDate.of(1998,12,14));
        Abonent A1 = new Abonent("Vitaly", "Sand",false,13436433, LocalDate.of(1998,12,14));
        return new Object[][] {{A,true},{A1,false}};
    }
    @Test(dataProvider = "regularNomerProvider")
    public void testNomerRg(Abonent a, boolean s){
        assertEquals(a.regular_telefon(),s);
    }
}