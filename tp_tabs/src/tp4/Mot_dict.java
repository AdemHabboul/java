package tp4;

public class Mot_dict {
	private String Mot;
	private String Definition ;
	public Mot_dict(String m , String d ) {
		Mot=m;
		Definition=d;
	}
	public String getMot() {
		return this.Mot;
	};
	public String getDefinition () {
		return this.Definition ;
	}
	public void setDefinition(String d) {
		this.Definition=d;
	}
	public void setMot(String m) {
		this.Mot=m;
	}
	public boolean synonyme (String a) {
        return this.Mot.equals(a);
    }
}
