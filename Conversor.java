
public class Conversor {
	
	//Verifica se a entrada respeita as limitações
	public static boolean verificarInput(String input) {
		try {
			String[] vetorValores = input.split("\n");
			String s =vetorValores[0];
			String t =vetorValores[1];
			int k = Integer.valueOf(vetorValores[2]);
			if (s.length()==0 ||s.length()>100) { return false;}
			if (t.length()==0 ||t.length()>100) { return false;}
			if (k<=0 ||k>100) {return false;}
			String charValidos = "qwertyuiopasdfghjklzxcvbnm";
			for(int i=0; i<s.length(); i++) {
				if(charValidos.indexOf(s.charAt(i))==-1) { return false;}
			}
			for(int i=0; i<t.length(); i++) {
				if(charValidos.indexOf(t.charAt(i))==-1) { return false;}
			}
			}
			catch(Exception e) {
			  return false;
			}
		return true;
	}
	
	public static String ConcatERemove (int k, String s, String t) {
		if (s.length()+t.length()<=k) {
			return "SIM";
		}
		String menorString = (s.length()>=t.length())?t:s;
		int indexUltimoCharIgual=-1;
		for(int i = 0; i<menorString.length(); i++) {
			if(s.charAt(i)!=t.charAt(i)) {
				break;
			}
			indexUltimoCharIgual=i;
		}
		int totalRemove = s.length() - (indexUltimoCharIgual+1);
		int totalConcat = t.length() - (indexUltimoCharIgual+1);
		int movimentosSobra = k-(totalConcat+totalRemove);
		String retorno = ((movimentosSobra%2==0)&&(movimentosSobra>=0))?"SIM":"NÃO";
		return retorno;

	}
	
	public static void main(String[] args) {
		String linha1 = "ashley"; //String inicial
		String linha2 = "ash"; //String desejada
		String linha3 = "2"; //número de operações
		String input = linha1+"\n"+linha2+"\n"+linha3;
		if (verificarInput(input)) {
			String[] vetorValores = input.split("\n");
			String s =vetorValores[0];
			String t =vetorValores[1];
			int k = Integer.valueOf(vetorValores[2]);
			System.out.println(ConcatERemove(k, s, t));
		}
		else {
			System.out.println("Inputs inválidos.");
		}
		
	}

}
