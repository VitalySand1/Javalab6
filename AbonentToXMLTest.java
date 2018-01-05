import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.*;

public class AbonentToXMLTest {
    Abonent Abonent1,Abonent2,Abonent3;
    List<Abonent> abonents;
    @BeforeTest
    public void start() {

        Abonent1= new Abonent("Vitaly", LocalDate.of(1998,5,28));
        Abonent2= new Abonent("Bodya",LocalDate.of(1998, 9, 1));
        Abonent3= new Abonent("Kirill",LocalDate.of(1960,1,18));
        abonents= new ArrayList<Abonent>();
        abonents.add(Abonent1);
        abonents.add(Abonent2);
        abonents.add(Abonent3);
    }
    @DataProvider
    public Object[][] dataProviderForSerializationOfOneObject(){
        return new Object[][] {{Abonent1},{Abonent2},{Abonent3}};
    }
    @DataProvider
    public Object[][] dataProviderForSerializationOfList(){
        return new Object[][] {{abonents}};
    }
    @Test(dataProvider = "dataProviderForSerializationOfOneObject")
    public void serializationtoXML(Abonent p) throws JsonGenerationException, JsonMappingException, IOException {
        new AbonentToXML().serialize(p, new File("in2.xml"));
        Assert.assertEquals(new AbonentToXML().deserialize(new File("in2.xml")), p);
    }
    @Test(dataProvider = "dataProviderForSerializationOfList")
    public void serializationOfXML(List<Abonent> p) throws JsonGenerationException, JsonMappingException, IOException {
        new AbonentToXML().serializeCollection(p, new File("in2.xml"));
        Assert.assertEquals(new AbonentToXML().deserializeCollection(new File("in2.xml")), p);
    }
}