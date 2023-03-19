package metier;


import model.Employe;

public interface IEmployeMetier {
    public Employe calculerSalaire(Long id) throws EmployeException;

}
