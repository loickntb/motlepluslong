package src.main;
import src.Terminal;

public class game {


    public static char[] donnerTirage() {
        double[] frequences = {
                0.084641116045612,
                0.097550346533667,
                0.134264256131715,
                0.175931501299866,
                0.339787563705305,
                0.352412789645191,
                0.366356662429924,
                0.37898710353533,
                0.454338255487703,
                0.458247915903501,
                0.461526843221907,
                0.517821090574744,
                0.547529673593674,
                0.620002807215,
                0.677444355180383,
                0.70566493929644,
                0.71299279223788,
                0.781799811286431,
                0.855650833407438,
                0.922800098125308,
                0.974199670376239,
                0.986838818540602,
                0.98881203071521,
                0.993095137557963,
                0.998288765757996,
                1};
        char[] res = new char[10];
        for (int i = 0; i < res.length; i++) {
            double ausort = Math.random();
            int j = 0;
            while (frequences[j] < ausort)
                j++;
            res[i] = (char) ('A' + j);
        }
        java.util.Arrays.sort(res);
        return res;
    }

    /**
     * Retourne un tableau de tableau de caractères ou chaque ligne
     * correspond à un mot du fichier dico_nfa031.txt
     */
    public static char[][] getDictionnaire() {
        char[][] res;
        char[][] temp = new char[500000][];
        int nbMots = 0;
        try {
            java.io.FileReader fr = new java.io.FileReader("dico_nfa031.txt");
            java.io.BufferedReader br = new java.io.BufferedReader(fr);
            String ligne = br.readLine();
            while (ligne != null) {
                temp[nbMots] = ligne.trim().toCharArray();
                nbMots++;
                ligne = br.readLine();
            }
            br.close();
        } catch (java.io.FileNotFoundException e) {
            throw new RuntimeException("Fichier dico_nfa031.txt non trouvé");
        } catch (java.io.IOException e) {
            throw new RuntimeException("Problème à la lecture du fichier");
        }
        res = new char[nbMots][];
        for (int i = 0; i < nbMots; i = i + 1) {
            res[i] = temp[i];
        }
        return res;
    }

    /**
     * Affiche le contenu d'un tableau de char sans
     * espace
     */
    public static void AfficheMot(char[] mot) {
        for (int i = 0; i < mot.length; i++) {
            System.out.print(mot[i]);
        }
        System.out.println();
    }

    /**
     * Compare deux tableaux de caractères
     *
     * @param t1 (required)
     * @param t2 (required)
     * @return boolean
     */

    public static boolean eqTabChar(char[] t1, char[] t2) {
        if (t1.length != t2.length)
            return false;
        for (int i = 0; i < t1.length; i++) {
            if (t1[i] != t2[i]) {
                return false;
            }
        }
        return true;
    }

    /**
     * Compare une chaine de caractères
     * et un tableau de char
     *
     * @param s1 (required)
     * @param t2 (required)
     * @return boolean
     */
    public static boolean eqStringTabChar(String s1, char[] t2){
        if(s1.length() != t2.length)
            return false;
        for(int i=0;i<s1.length(); i++){
            if(s1.charAt(i)!=t2[i]){
                return false;
            }
        }
        return true;
    }

    /**
     * Retourne true sur le mot est retrouvé dans le dictionnaire
     *
     * @param t1 (required)
     * @param dico (required)
     * @return boolean
     */
    public static boolean motTrouve(char[] t1, char[][] dico) {

        for (int i = 0; i < dico.length; i++) {
            if (eqTabChar(t1, dico[i])) {
                return true;
            }
        }
        return false;
    }

    /**
     * Retourne true si une lettre du tirage n'est
     *  pas utilisée plus de fois qu'elle n'apparait
     * @param jeu
     * @param tirage
     * @return
     */
    public static boolean lettreCompteur(char[] jeu, char[] tirage){
        for (int i = 0; i < tirage.length; i++) {
            char c = tirage[i];
            if (c != ' ') {
                int tirageCount = 0;
                int jeuCount = 0;
                for (int j = 0; j < tirage.length; j++) {
                    if (tirage[j] == c) {
                        tirageCount++;
                    }
                }
                for (int j = 0; j < jeu.length; j++) {
                    if (jeu[j] == c) {
                        jeuCount++;
                    }
                }
                if (jeuCount > tirageCount) {
                    return false;
                }
            }
        }
        return true;
    }
    /**
     * Retourne true sur chaque lettre de jeu
     * est présent dans le tirage
     * @param jeu (required)
     * @param tirage (required)
     * @return boolean
     */
    public static boolean compareLettre(char[] jeu,char[] tirage) {
        for (int i = 0; i < jeu.length; i++) {
            char c = jeu[i];
            boolean match = false;
            for (int j = 0; j < tirage.length; j++) {
                if (tirage[j] == c) {
                    match = true;
                    break;
                }
            }
            if (!match) {
               return false;
            }
        }
        return true;
    }


    public static void main(String[] args) {
        char[][] dico = getDictionnaire();
        char[] jeu;
        char[] tirage = donnerTirage();

        System.out.println("Tirage:" + new String(tirage));
        System.out.print("Proposez un mot (ou une ligne vide pour passer) : ");
        jeu = Terminal.lireString().toUpperCase().toCharArray();
        if( jeu.length>25) throw new IllegalArgumentException("Votre entrée dépasse la limite autorisée de 25 lettres");
        if(!lettreCompteur(jeu,tirage)) System.out.println("** Erreur : Ce mot contient des lettres qui apparaissent plus que dans le tirage ! **");
        else if(compareLettre(jeu,tirage)){
            System.out.println(jeu);
            for (int i = 0; i < jeu.length; i++) {
                char lettre = jeu[i];
                for (int j = 0; j < tirage.length; j++) {
                    if (tirage[j] == lettre) {
                        System.out.print(j);
                        break;
                    }
                }
            }
            System.out.print("\n");
            for (int i = 0; i < tirage.length; i++){
                System.out.print(i);
            }
            System.out.print("\n");
            System.out.println(tirage);

            if(motTrouve(jeu,dico)) System.out.println("Votre mot fait "+ jeu.length + " lettre");
            else System.out.println("Votre mot n'est pas présent dans le dictionnaire");


        }else if(!compareLettre(jeu,tirage)){
            System.out.print("** Erreur : Ce mot contient des lettres qui ne sont pas présentes dans le tirage ! **");
        }


    }
}
