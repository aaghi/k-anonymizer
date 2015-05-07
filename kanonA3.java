import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;


public class kanonA3 {
	static List<kanonRecord> records = new ArrayList<kanonRecord>();
	static String header;
	static int k = 10;
	public static void main(String[] args){
		try {
			readRecords();
			pass1();
			pass2();
			pass3();
			pass4();
			writeRecords();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void readRecords() throws IOException{
		FileInputStream infile = new FileInputStream("Infectious_Disease_Record.csv");
		Scanner in = new Scanner(infile);
		header = in.nextLine();
		while(in.hasNext()){
			records.add(new kanonRecord(in.nextLine()));
		}
		in.close();
		infile.close();
	}
	public static void writeRecords() throws IOException{
		Hashtable<String,List<kanonRecord>> setTable = makeSets();
		FileWriter outfile = new FileWriter("anonymized.csv");
		FileWriter nonoutfile = new FileWriter("non-kanonymized.csv");
		outfile.write(header+"\n");
		nonoutfile.write(header+"\n");
		for(int i = 0 ; i < records.size(); i++){
			if(setTable.get(records.get(i).getKey()).size() < k){
				nonoutfile.write(records.get(i).toString()+"\n");
			}
			outfile.write(records.get(i).toString()+"\n");
		}
		outfile.close();
		nonoutfile.close();
		nonoutfile.close();
		outfile.close();
	}
	public static void pass1(){
		for(int i = 0 ; i < records.size() ; i++){
			records.get(i).setName("*");
			records.get(i).setTelephone("*");
			String[] dob = records.get(i).getDob().split("-");
			dob[0] = "*";
			records.get(i).setDob(dob[0]+"-"+dob[1]+"-"+dob[2]);
		}
	}
	public static void pass2(){
		Hashtable<String,List<kanonRecord>> setTable = makeSets();
		Iterator<List<kanonRecord>> sets =  setTable.values().iterator();
		while(sets.hasNext()){
			List<kanonRecord> set = sets.next();
			if(set.size()<k){
				for(int j = 0 ; j < set.size(); j++){
					String[] postCode = set.get(j).getPostCode().split(" ");
					set.get(j).setPostCode(postCode[0]+" "+"*");
				}
			}
		}
	}
	public static void pass3(){
		Hashtable<String,List<kanonRecord>> setTable = makeSets();
		Iterator<List<kanonRecord>> sets =  setTable.values().iterator();
		while(sets.hasNext()){
			List<kanonRecord> set = sets.next();
			if(set.size()<k){
				for(int j = 0 ; j < set.size(); j++){
					String[] dob = set.get(j).getDob().split("-");
					dob[1] = "*";
					set.get(j).setDob(dob[0]+"-"+dob[1]+"-"+dob[2]);
				}
			}
		}
	}
	public static void pass4(){
		Hashtable<String,List<kanonRecord>> setTable = makeSets();
		Iterator<List<kanonRecord>> sets =  setTable.values().iterator();
		while(sets.hasNext()){
			List<kanonRecord> set = sets.next();
			if(set.size()<k){
				for(int j = 0 ; j < set.size(); j++){
					String[] dob = set.get(j).getDob().split("-");
					int year = Integer.parseInt(dob[2]);
					year -= year%10;
					set.get(j).setDob(dob[0]+"-"+dob[1]+"-"+year);
				}
			}
		}
	}
	public static Hashtable<String,List<kanonRecord>> makeSets(){
		Hashtable<String,List<kanonRecord>> sets = new Hashtable<String,List<kanonRecord>>();
		for(int i = 0 ; i < records.size() ; i++){
			Object o = sets.get(records.get(i).getKey());
			List<kanonRecord> setRecords;
			if(o == null){
				setRecords = new ArrayList<kanonRecord>();
				sets.put(records.get(i).getKey(), setRecords);
			}else{
				setRecords = (List<kanonRecord>)o;
			}
			setRecords.add(records.get(i));
		}
		return sets;
	}
}
