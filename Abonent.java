import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Comparator;
import java.util.Collections;

@JsonDeserialize
@JsonIgnoreProperties(ignoreUnknown = true)
@XmlRootElement

public class Abonent implements Comparable<Abonent>,Serializable {
    private String imya;
    private String familiya;
    private boolean pilgy;
    private long nomer_telefona;
    private LocalDate dateofbirth;
    @JsonIgnore
    private final String imya_reg = "([A-Z]){1,1}([a-z]){1,15}";
    @JsonIgnore
    private final String familiya_reg = "([A-Z]){1,1}([a-z]){1,15}";
    @JsonIgnore
    private final String nomer_telefona_reg = "([0-9]){9,9}";

    public void writeObjectToFile(Abonent object) throws IOException {
        FileOutputStream fos = new FileOutputStream("temp.out");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(oos);
    }
    @Override
    public int compareTo(Abonent o) {
        if(this.imya.charAt(0)>o.imya.charAt(0)) {
            return 1;
        }
        else if(this.imya.charAt(0)< o.imya.charAt(0)) {
            return -1;
        }
        return 0;
    }

    Abonent(){
        imya = "";
        familiya = "";
        pilgy = false;
        nomer_telefona = 0;
        dateofbirth = LocalDate.of(1998,04,12);
    }
    Abonent(String imya,String familiya, boolean pilgy, long nomer_telefona, LocalDate d){
        this.imya = imya;
        this.familiya = familiya;
        this.pilgy = pilgy;
        this.nomer_telefona = nomer_telefona;
        this.dateofbirth = d;

    }
    public Abonent (String name , LocalDate dateofbirth) {
        this.imya=name;
        this.dateofbirth=dateofbirth;
    }
    public void setImya(String imya) {
        this.imya = imya;
    }
    public void setFamiliya(String familiya) {
        this.familiya = familiya;
    }
    public void setPilgy(boolean pilgy) {
        this.pilgy = pilgy;
    }
    public void setNomer_telefona(long nomer_telefona) {
        this.nomer_telefona = nomer_telefona;
    }
    public void setDateofbirth(LocalDate d){
        this.dateofbirth = d;
    }

    public String getImya() {
        return imya;
    }
    public String getFamiliya() {
        return familiya;
    }
    public boolean getPilgy(){
        return pilgy;
    }
    public long getNomer_telefona() {
        return nomer_telefona;
    }
    public LocalDate getDateofbirth(){
        return  this.dateofbirth;
    }

    public boolean IfPilgoviy(){
        return this.pilgy;
    }
    public boolean IfPensioner(){
        Period p = Period.between(this.dateofbirth, LocalDate.now());
        return p.getYears() >= 60;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Abonent abonent = (Abonent) o;
        return pilgy == abonent.pilgy &&
                nomer_telefona == abonent.nomer_telefona &&
                Objects.equals(imya, abonent.imya) &&
                Objects.equals(familiya, abonent.familiya) &&
                Objects.equals(dateofbirth, abonent.dateofbirth);
    }
    @Override
    public String toString() {
        return String.format("This is %s,which was born in %s", this.imya, this.dateofbirth.toString());
    }
    @Override
    public int hashCode() {

        return Objects.hash(imya, familiya, pilgy, nomer_telefona, dateofbirth);
    }
    public boolean regular_imya() {
        Pattern pt = Pattern.compile(imya_reg);
        Matcher m = pt.matcher(this.imya);
        return m.matches();
    }
    public boolean regular_familiya() {
        Pattern pt = Pattern.compile(familiya_reg);
        Matcher m = pt.matcher(this.familiya);
        return m.matches();
    }
    public boolean regular_telefon() {
        Pattern pt = Pattern.compile(nomer_telefona_reg);
        Matcher m = pt.matcher(Long.toString(this.nomer_telefona));
        return m.matches();
    }
    public static void main(String args[]){
       /* Abonent A = new Abonent("Vitaly", "Sand",false,03722424, LocalDate.of(1998,12,14));
        A.IfPensioner();*/
    }
}