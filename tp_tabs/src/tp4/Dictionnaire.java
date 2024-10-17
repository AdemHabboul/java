package tp4;

public class Dictionnaire {
    private int nb_mots;
    private Mot_dict[] Dict;
    private String Nom;

    public Dictionnaire(int n, String nom) {
        this.nb_mots = 0;
        this.Dict = new Mot_dict[n];
        this.Nom = nom;
    }

    public void Ajouter_Mot(Mot_dict mot) {
        if (nb_mots < Dict.length) {
            Dict[nb_mots] = mot; // Add the word at the correct position
            nb_mots++; // Increment the number of words
            Trier(); // Sort the dictionary after adding a word
        } else {
            System.out.println("Le dictionnaire est plein.");
        }
    }

    public void Trier() {
        for (int i = 1; i < nb_mots; i++) { // Start from the second element
            Mot_dict ch = Dict[i];
            int j = i - 1;
            while (j >= 0 && Dict[j].getMot().compareTo(ch.getMot()) > 0) {
                Dict[j + 1] = Dict[j];
                j = j - 1;
            }
            Dict[j + 1] = ch;
        }
    }

    public void Supprimer_Mot(Mot_dict mot) {
        boolean found = false;
        for (int i = 0; i < nb_mots; i++) {
            if (Dict[i].getMot().equals(mot.getMot())) {
                // Shift elements to the left after deletion
                for (int j = i; j < nb_mots - 1; j++) {
                    Dict[j] = Dict[j + 1];
                }
                nb_mots--; // Decrement word count
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Mot non trouvé.");
        }
    }

    public String Recherche_dicho(String m) {
        int g = 0;
        int d = nb_mots - 1;
        while (g <= d) {
            int middle = (g + d) / 2;
            String motM = Dict[middle].getMot();
            int comp = motM.compareTo(m);
            if (comp == 0) {
                return Dict[middle].getDefinition();
            } else if (comp < 0) {
                g = middle + 1;
            } else {
                d = middle - 1;
            }
        }
        return "Mot non trouvé.";
    }

    public void Lister_dictionnaire() {
        if (nb_mots == 0) {
            System.out.println("Le dictionnaire est vide.");
        } else {
            System.out.println("Dictionnaire :");
            for (int i = 0; i < nb_mots; i++) {
                System.out.println(Dict[i].getMot() + " : " + Dict[i].getDefinition());
            }
        }
    }

    public int Nombre_synonyme(Mot_dict mot) {
        int nb = 0;
        for (int i = 0; i < nb_mots; i++) {
            if (mot.synonyme(Dict[i].getMot())) {
                nb++;
            }
        }
        return nb;
    }

    public static void main(String[] args) {
        Dictionnaire dictionnaire = new Dictionnaire(10, "Mon Dict");
        Mot_dict mot2 = new Mot_dict("adem", "définition d'adem");
        Mot_dict mot1 = new Mot_dict("mot1", "définition de mot1");
        Mot_dict mot3 = new Mot_dict("zebra", "définition de zebra");

        dictionnaire.Ajouter_Mot(mot1);
        dictionnaire.Ajouter_Mot(mot2);
        dictionnaire.Ajouter_Mot(mot3);

        System.out.println(dictionnaire.Recherche_dicho("adem"));
        System.out.println("\nNombre de synonymes de 'adem':");
        System.out.println(dictionnaire.Nombre_synonyme(mot2));

        dictionnaire.Supprimer_Mot(mot2);
        System.out.println("\nContenu du dictionnaire après suppression:");
        dictionnaire.Lister_dictionnaire();
    }
}
