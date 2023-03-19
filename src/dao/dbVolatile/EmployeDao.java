package dao.dbVolatile;

import dao.IDao;
import model.Employe;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Repository("daoVolatile")
public class EmployeDao implements IDao<Employe,Long> {
    @Override
    public Employe trouverParId(Long id) {
        System.out.println("[DAO - DS volatile] trouver l'employé n° : "+ id);
        return BD_Credit()
                .stream()
                .filter(employe->employe.getId_employe() == id)
                .findFirst()
                .orElse(null);
    }

    static Set<Employe> BD_Credit(){
        return new HashSet<Employe>(Arrays.asList(
                new Employe(1L,"Nisrine",0.0,"posteX",100.0,5,5,2),
                new Employe(2L,"Sanae",0.0,"posteA",200.0,3,6,0),
                new Employe(3L,"Khalid",0.0,"posteC",400.0,5,8,5)

        ));
    }
}
