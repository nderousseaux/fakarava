package fakarava.ecosystem;

import java.io.File;

import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;

public class Plongeur implements Clock{
    
    //Attribut de Plongeur
    
    private int identifiant;
    private String nom;
    private String labo;
    private String fichierLog;
    private ArrayList<String> toDoList;
    private ArrayList<Cellule> passesObs = new ArrayList<Cellule>();
    private ArrayList<Predateur> predateursObs = new ArrayList<Predateur>();
    private Lagon lagon;
    
 /*********************************************************************************************************************/
   
    //Constructeur de Plongeur
 
    public Plongeur(int identifiant, String nom, String labo, Lagon lagon) {
        this.identifiant = identifiant;
        this.nom = nom;
        this.labo = labo;
        this.toDoList = new ArrayList<String>();
        this.lagon = lagon;
        this.fichierLog = nom+".txt";
        File fc = new File(this.fichierLog);
        try {
            fc.createNewFile();
            FileWriter fw = new FileWriter(fc,false);
            fw.write("Fichier Log du plongeur "+this.identifiant+" plus connue sous le nom de "+this.nom);
            fw.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }
 
/*********************************************************************************************************************/  
    
    //M�thode

    //La m�thode poseEmetteur v�rifie les condition pour pouvoir poser un emetteur sur un pr�dateur
    public void poseEmetteur(Predateur predateur){
        if (predateur.getPoid()>0 && predateur.getCellule().getEstPasse() && predateur.getProprietaireEmetteur()==null){
            this.predateursObs.add(predateur);
            predateur.setProprietaireEmetteur(this);
        }
    }
    //La m�thode poseCamera v�rifie les condition pour pouvoir poser une camera dans une cellule
    public void poseCamera(Cellule cellule){
        if (cellule.getEstPasse() && cellule.getProprietaireCamera()==null){
            this.passesObs.add(cellule);
            cellule.setProprietaireCamera(this);
        }
    }
    //La m�thode deleteEmetteur permet de supprimer un emetteur
    public void deleteEmetteur(Predateur predateur){
        if (predateursObs.contains(predateur)){
            predateursObs.remove(predateur);
            predateur.setProprietaireEmetteur(null);
        }
    }
    //La m�thode deleteCamera permet de supprimer une cam�ra
    public void deleteCamera(Cellule cellule){
        if (passesObs.contains(cellule)){
            passesObs.remove(cellule);
            cellule.setProprietaireCamera(null);
        }
    }
    //La m�thode getId renvoie identifiuant
    public int getId(){
        return this.identifiant;
    }
    //La m�thode getFichierLog renvoie fichierLog
    public String getFichierLog(){
        return this.fichierLog;
    }
    //La m�thode getPosCam renvoie passesObs    Obs=Observation
    public ArrayList<Cellule> getPosCam(){
        return this.passesObs;
    }
    //La m�thode getPreEm renvoie predateursObs   Obs=Observation
    public ArrayList<Predateur> getPredEm(){
        return this.predateursObs;
    }
    //La m�thode addToDoList ajoute a la liste toDoListe une instruction
    public void addToDoList(String instruction){ //"c intPasse" ou "t intPred"
        this.toDoList.add(instruction);
    }
    //La m�thode ticktock permet de gerer les action des plongeurs et g�re aussi les eventuelles errueurs 
    public void ticktock(){
        //v�rifie que la liste toDoliste est differnets de 0
        if (this.toDoList.size()!=0){
            String chaine = this.toDoList.get(0);
            this.toDoList.remove(0);
            String[] chcoupe = chaine.split(" ");
            try {
                //Creation fclog qui est un fichier qui sera remplie 
                File fclog = new File(this.getFichierLog());
                FileWriter fw = new FileWriter(fclog,true);
                fw.write("\n- Action Plongeur :\n");
                switch (chcoupe[0]){
                case "c" :
                    this.poseCamera(lagon.getPasseById(Integer.valueOf(chcoupe[1])));
                    fw.write("  - pose une camera sur la passe numero "+chcoupe[1]+"\n");
                    break;
                case "t" :
                    Poisson poiss = lagon.getPoisson(Integer.valueOf(chcoupe[1]));
                    if (poiss.getType()=="Predateur"){
                        this.poseEmetteur((Predateur)poiss);
                        fw.write("  - pose un transmetteur sur le predateur numero "+chcoupe[1]+"\n");
                    }
                }
                fw.close();
            }
            catch (IOException e){
                System.out.println("Erreur lors du chargement ou �criture du fichier");
                e.printStackTrace();
            }
        }
    }
}
