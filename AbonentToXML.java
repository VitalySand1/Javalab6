import java.beans.Encoder;
import java.beans.Expression;
import java.beans.PersistenceDelegate;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Writer;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;



public class AbonentToXML implements Serializaer<Abonent>{
    @Override
    public void serialize(Abonent object, File output) throws FileNotFoundException {
        XMLEncoder encoder=null;
        encoder=new XMLEncoder(new BufferedOutputStream(new FileOutputStream(output)));
        encoder.setPersistenceDelegate(LocalDate.class,
                new PersistenceDelegate() {
                    @Override
                    protected Expression instantiate(Object obj, Encoder encdr) {
                        LocalDate localDate = (LocalDate) obj;
                        return new Expression(localDate,
                                LocalDate.class,
                                "of",
                                new Object[] {localDate.getYear(), localDate.getMonth(), localDate.getDayOfMonth()});
                    }
                });
        encoder.writeObject(object);
        encoder.close();
    }
    @Override
    public void serializeCollection(List<Abonent> objects, File output) throws FileNotFoundException {
        XMLEncoder encoder=null;
        encoder=new XMLEncoder(new BufferedOutputStream(new FileOutputStream(output)));
        encoder.setPersistenceDelegate(LocalDate.class,
                new PersistenceDelegate() {
                    @Override
                    protected Expression instantiate(Object obj, Encoder encdr) {
                        LocalDate localDate = (LocalDate) obj;
                        return new Expression(localDate,
                                LocalDate.class,
                                "of",
                                new Object[] {localDate.getYear(), localDate.getMonth(), localDate.getDayOfMonth()});
                    }
                });
        encoder.writeObject(objects);
        encoder.close();
    }
    @Override
    public  Abonent deserialize(File input) throws FileNotFoundException {
        XMLDecoder decoder=null;
        decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream(input)));
        Abonent p = (Abonent) decoder.readObject();
        decoder.close();
        return p;
    }
    @Override
    public List<Abonent> deserializeCollection(File input) throws FileNotFoundException{
        XMLDecoder decoder=null;
        decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream(input)));
        List<Abonent> p = (List<Abonent>) decoder.readObject();
        decoder.close();
        return p;
    }
}
