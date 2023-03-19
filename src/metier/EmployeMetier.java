package metier;

import dao.IDao;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import model.Employe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Data@AllArgsConstructor@NoArgsConstructor
@Service("metier")
public class EmployeMetier implements IEmployeMetier{

    @Autowired
    @Qualifier("daoVolatile")
    private IDao<Employe,Long> employeDao ;
    @Override
    public Employe calculerSalaire(Long id) throws EmployeException {
        var employe = employeDao.trouverParId(id);
        if(employe == null) throw new EmployeException("L'id de l'employé est incorrecte :: [Employé non trouvé]");
        else {
            int nbr_jrsT = employe.getNbr_jrs_travail();
            int nbr_hrs = employe.getNbr_hr_travail();
            int nbr_jrsA = employe.getNbr_jrs_absence();
            double prixH = employe.getPrix_heure();
            Double salaire = ((nbr_hrs*prixH)*nbr_jrsT*4) ;
            salaire = salaire - (0.025*nbr_jrsA*salaire);


            employe.setSalaire(salaire);

            return employe;
        }
    }
}
