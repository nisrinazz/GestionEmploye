package dao.dbFile;

import dao.IDao;
import model.Employe;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.StringTokenizer;

@Repository("daoFile")
public class EmployeDaoFile implements IDao<Employe,Long> {
    @Override
    public Employe trouverParId(Long id) {
        System.out.println("[DAO - DS File] trouver l'employé n° : "+ id);
        Employe employe=null ;
        try {
            List<String> lines = Files.readAllLines(Paths.get("filebase","employes.txt"));
            lines.remove(0);
             return  lines
                    .stream()
                    .filter(line->!line.equals(null))
                    .map(line->{
                        StringTokenizer stringTokenizer = new StringTokenizer(line,",");

                        Long id_employe = Long.parseLong(stringTokenizer.nextToken());
                        String nom_employe = stringTokenizer.nextToken();
                        Double salaire = Double.parseDouble(stringTokenizer.nextToken());
                        String poste = stringTokenizer.nextToken();
                        Double prixHeure = Double.parseDouble(stringTokenizer.nextToken());
                        Integer nbrJrsT = Integer.parseInt(stringTokenizer.nextToken());
                        Integer nbrHrsT = Integer.parseInt(stringTokenizer.nextToken());
                        Integer nbrJrsAbs = Integer.parseInt(stringTokenizer.nextToken());
                        return new Employe(id_employe,nom_employe,salaire,poste,prixHeure,nbrJrsT,nbrHrsT,nbrJrsAbs);
                    }).filter(emp->emp.getId_employe() == id).findFirst().orElse(null);


        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
       return employe;
    }


}
