package presentation;

import metier.EmployeException;

public interface IEmployeControlleur {

    void afficherSalaire(Long idEmp) throws EmployeException;
}
