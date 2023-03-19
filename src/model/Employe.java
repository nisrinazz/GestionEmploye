package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employe {
    private Long id_employe ;
    private String nom_employe ;
    private Double salaire ; //à calculer
    private String poste ;

    private Double prix_heure ;
    private Integer nbr_jrs_travail ;
    private Integer nbr_hr_travail ;
    private Integer nbr_jrs_absence ;

    public String toString(){
        var employeStr =  "==================================================         \n";
            employeStr += "=> Employé n° "+ getId_employe()+"                          \n";
            employeStr += "=> Nom de l'employé :"+getNom_employe()+"                 \n";
        employeStr += "--------------------------------------------------         \n";
        employeStr += "=> Nom du poste          :"+getPoste()+"\n";
        employeStr += "=> Nombre de jours du  travail                :"+getNbr_jrs_travail()+" jours \n";
        employeStr += "=> Nombre de heures du  travail                :"+getNbr_hr_travail()+" heures \n";
        employeStr += "=> Prix de l'heure du  travail                :"+getPrix_heure()+" Dh \n";
        employeStr += "=> Nombre de jours d'absence                 :"+getNbr_jrs_absence()+" jours \n";
        employeStr += "--------------------------------------------------         \n";
        employeStr += "=> Salaire                  :"
                +(getSalaire() == 0.0 ? "NON-CALCULE ": getSalaire()+"Dh")+"\n";
        employeStr += "==================================================         \n";

            return employeStr ;
    }

}
