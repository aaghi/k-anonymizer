
public class kanonRecord {

	String name,gender,telephone,postCode,dob,disease;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getPostCode() {
		return postCode;
	}
	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public String getDisease() {
		return disease;
	}
	public void setDisease(String disease) {
		this.disease = disease;
	}
	kanonRecord(String line){
		String[] fields = line.split(",");
		this.name = fields[0];
		this.gender = fields[1];
		this.telephone = fields[2];
		this.postCode  = fields[3];
		this.dob = fields[4];
		this.disease = fields[5];
	}
	public String toString(){
		return name+","+gender+","+telephone+","+postCode+","+dob+","+disease;
	}
	
	public String getKey(){
		return name+","+gender+","+telephone+","+postCode+","+dob;
	}
}
