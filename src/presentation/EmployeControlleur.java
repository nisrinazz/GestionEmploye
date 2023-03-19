package presentation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import metier.EmployeException;
import metier.IEmployeMetier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

@Data@AllArgsConstructor@NoArgsConstructor
@Controller("controlleur")
public class EmployeControlleur implements IEmployeControlleur{

    @Autowired
    @Qualifier("metier")
    private IEmployeMetier service ;
    @Override
    public void afficherSalaire(Long idEmp) throws EmployeException {
        var employe = service.calculerSalaire(idEmp);
        System.out.println(employe);
    }
}
