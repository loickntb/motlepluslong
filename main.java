public class main {


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
     * Compare deux tableaux de caracteres
     *
     * @param t1
     * @param t2
     * @return
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
     * Retourne true sur le mot est retrouve dans le dictionnaire
     *
     * @param m
     * @param dico
     * @return boolean
     */
    public static boolean motTrouve(char[] m, char[][] dico) {

        for (int i = 0; i < dico.length; i++) {
            if (eqTabChar(m, dico[i]) == true) {
                return true;
            }
        }
        return false;
    }


    public static void main(String[] args) {

//        char[][] dico = getDictionnaire();
//        char[] str;
//        while(true) {
//            System.out.print("Proposer un mot ou une ligne vide pour passer : ");
//            str = Terminal.lireString().toUpperCase().toCharArray();
//            if(motTrouve(str, dico)==true){
//                System.out.println("Le mot est présent dans le dictionnaire");
//            }
//            else System.out.println("Le mot n'est pas présent dans le dictionnaire");
//        }


    }
}
