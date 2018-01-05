import java.io.BufferedReader;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Writer;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.List;

public class AbonentToTXT implements Serializaer<Abonent>{
    @Override
    public void serialize(Abonent object, File output) throws IOException {
        Writer whereToWrite = new PrintWriter(output);
        whereToWrite.write(object.toString());
        whereToWrite.flush();
        whereToWrite.close();
    }

    @Override
    public void serializeCollection(List<Abonent> objects, File output) throws IOException {
        Writer whereToWrite = new PrintWriter(output);
        for(Abonent z: objects) {
            whereToWrite.write(z.toString());
            whereToWrite.write("\n");
        }
        whereToWrite.flush();
        whereToWrite.close();
    }
    @Override
    public Abonent deserialize(File input) throws FileNotFoundException {
        Scanner scanner = new Scanner(input, "UTF-8");
        String inputText = scanner.useDelimiter("\\A").next();
        scanner.close();

        Abonent temp =new Abonent();
        Pattern for_name = Pattern.compile("(?<=This is )([a-zA-Z]*)(?=,which)");
        Matcher match = for_name.matcher(inputText);
        match.find();

        String name = match.group().replaceAll("\\s+","");


        Pattern for_year = Pattern.compile("(?<=born in ).{4}");
        match = for_year.matcher(inputText);
        if(!match.find()) {
            throw new IllegalArgumentException("Incorrect name");
        }

        int year = Integer.parseInt(match.group(0));


        Pattern for_month = Pattern.compile("(?<=-)(.*)(?=-)");
        match = for_month.matcher(inputText);
        if(!match.find()) {
            throw new IllegalArgumentException();
        }
        int month = Integer.parseInt(match.group());

        Pattern for_day = Pattern.compile(".{2}(?=$)");
        match = for_day.matcher(inputText);
        if(!match.find()) {
            throw new IllegalArgumentException();
        }
        int day= Integer.parseInt(match.group());

        LocalDate temp_year = LocalDate.of(year, month, day);

        temp.setDateofbirth(temp_year);
        temp.setImya(name);
        return temp;
    }
    public List<Abonent> deserializeCollection(File input) throws FileNotFoundException{
        Scanner scanner = new Scanner(input, "UTF-8");
        String inputText = scanner.useDelimiter("\\A").next();
        Abonent temp =new Abonent();
        List<Abonent> products= new ArrayList<Abonent>();
        for(String s: inputText.split("\n")) {
            if(!s.isEmpty()) {
                Pattern for_name = Pattern.compile("(?<=This is )(.*)(?=,which)");
                Matcher match = for_name.matcher(s);
                match.find();

                String name = match.group().replaceAll("\\s+","");


                Pattern for_year = Pattern.compile("(?<=born in ).{4}");
                match = for_year.matcher(s);
                match.find();
                int year = Integer.parseInt(match.group(0));;


                Pattern for_month = Pattern.compile("(?<=-)(.*)(?=-)");
                match = for_month.matcher(s);
                match.find();
                int month = Integer.parseInt(match.group());

                Pattern for_day = Pattern.compile(".{2}(?=$)");
                match = for_day.matcher(s);
                match.find();
                int day= Integer.parseInt(match.group());

                LocalDate temp_year = LocalDate.of(year, month, day);

                temp.setDateofbirth(temp_year);
                products.add(new Abonent(name,LocalDate.of(year,month,day)));
            }
        }
        scanner.close();
        return products;
    }
}
