package fakarava.control;
import fakarava.ecosystem.Cellule;
import fakarava.ecosystem.Lagon;
import fakarava.ecosystem.Plongeur;
import fakarava.ecosystem.Poisson;
import fakarava.ecosystem.Predateur;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


//Classe de gestion des plongeurs
public class GestionnairePlongeurs {
    
    //Atributs
    private static ArrayList<Plongeur> lesPlongeurs = new ArrayList<Plongeur>();
    private static int nextNumero = -1;
    
    //Constrcuteurs
    protected static int addPlongeur(String nom,String labo, Lagon lagon){
        nextNumero++;
        lesPlongeurs.add(new Plongeur(nextNumero,nom,labo, lagon));
        return nextNumero;
    }
    
    //Assesseurs
    protected static int getNbPlongeurs(){
        return lesPlongeurs.size();
    }
    protected static Plongeur getPlongeur(int numPlongeur){
        //Par défaut, le plongeur n'existe pas, jusqu'à preuve du contraire
        Plongeur plongeur = null;

        //On cherche dans la liste des plongeurs si le plongeur existe.
        for (Plongeur plongeuri : lesPlongeurs){
            if(plongeuri.getId()==numPlongeur){
                plongeur = plongeuri;
            }
        }

        //Si il n'existe pas, on renvoie une exception
        if (plongeur == null){
            throw new IllegalArgumentException();
        }
        return plongeur;
    }
    protected static void deletePlongeur(int numero){
        //On cherche le plongeur
        Plongeur plongeur = getPlongeur(numero);

        //On supprime toutes ses caméras
        for (int i = 0;i<plongeur.getPosCam().size();i++){
            plongeur.deleteCamera(plongeur.getPosCam().get(0));
        }
        //On supprime tout ses éméteurs
        for (int i = 0;i<plongeur.getPredEm().size();i++){
            plongeur.deleteEmetteur(plongeur.getPredEm().get(0));
        }

        //On supprime le plongeur
        lesPlongeurs.remove(plongeur);
    }
    
    //Méthodes statiques 
    //Le temps passe pour les plongeurs, a chaque action, on leur crée leur fichier de log
    protected static void tempsPasse(Boolean isDay, Lagon leLagon, int compteur){
        //Pour chaque plongeur on incrémente leur fichier de log
        for (Plongeur plongeur : lesPlongeurs){
            try {
                File fclog = new File(plongeur.getFichierLog());
                FileWriter fw = new FileWriter(fclog,true);

                //On écrit l'entête dans le fichier de log
                fw.write("\n" + 
                "\n" + 
                " ---------------Demi-Jour suivant ("+compteur+")--------------- \n");

                //Si c'est le jour, il peut faire des actions
                if (isDay){
                    plongeur.ticktock();
                }

                //On affiche tout les prédateurs suivis
                if (plongeur.getPredEm().size()!=0){
                    fw.write("\n- Predateur suivi :");
                    for (Predateur predateur : plongeur.getPredEm()){
                        fw.write("\n - "+predateur.toString()+" :\n");
                    }
                }

                //On affiche tout ce qui ce passe dans les passes suivies
                if (plongeur.getPosCam().size()!=0){
                    fw.write("\n- Passe suivie :");

                    //Toutes les actions de chasse
                    for (Cellule passe : plongeur.getPosCam()){
                        fw.write("\n - "+passe.toString()+" :\n");
                        if (passe.getChasse().size()!=0){
                            fw.write("   - Chasse :\n");
                            for (String chaine: passe.getChasse()){
                                fw.write("      "+chaine+"\n");
                            }
                        }

                        //Tout les poissons présent
                        if (leLagon.getPoissonbyCellule(passe).size()!=0){
                            fw.write("   - poisson présent :\n");
                            for (Poisson poisson: leLagon.getPoissonbyCellule(passe)){
                                fw.write("      "+poisson.toString()+"\n");
                            }
                        }
                    }
                }
                fw.close();
            }
            catch (IOException e){
                System.out.println("Erreur lors du chargement ou écriture du fichier");
                e.printStackTrace();
            }
        }
    }
    
}
