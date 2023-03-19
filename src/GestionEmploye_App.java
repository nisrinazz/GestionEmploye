import dao.IDao;
import dao.dbFile.EmployeDaoFile;
import dao.dbVolatile.EmployeDao;
import metier.EmployeException;
import metier.EmployeMetier;
import metier.IEmployeMetier;
import model.Employe;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import presentation.EmployeControlleur;
import presentation.IEmployeControlleur;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.Properties;
import java.util.Scanner;

public class GestionEmploye_App {
    static IEmployeControlleur controlleur1 ;

    static Scanner clavier = new Scanner(System.in);

    private static boolean estUnNombre(String nisrineInput){
        try{Integer.parseInt(nisrineInput); return true ;}
        catch(Exception e){ return false ;}
    }

    public static void test1(){
        //initialisation
        var metier = new EmployeMetier();
        var dao = new EmployeDaoFile();
        var controlleur = new EmployeControlleur();
        //injection de dépendances
        metier.setEmployeDao(dao);
        controlleur.setService(metier);

        String reponse = "" ;
        do {
            System.out.println("=> [Test1] Calcule du salaire de l'employé \n");
            try{
                String input = "";
                while(true){
                    System.out.println("=>Entrer l'id de l'employé");
                    input = clavier.nextLine();
                    if(estUnNombre(input)) break;
                    System.out.println("Entrée non valide !");
                }
                long id = Long.parseLong(input);
                controlleur.afficherSalaire(id);
            }
            catch (Exception e){
                System.out.println(e.getMessage());
            }
            System.out.println("Vous voulez quittez (oui/non) ?  "); reponse = clavier.nextLine();
        }while(!reponse.equalsIgnoreCase("oui"));
        System.out.println("Au revoir ^-^");
    }

    public static void test2() throws Exception {
       String daoClass ;
       String serviceClass ;
       String controllerClass ;
        Properties properties = new Properties();
        ClassLoader cl = Thread.currentThread().getContextClassLoader();
        InputStream propertiesFile = cl.getResourceAsStream("config.properties");
        if(propertiesFile == null) throw new Exception("fichier config introuvable !");
       else {
          try  {
                properties.load(propertiesFile);
                daoClass = properties.getProperty("DAO");
                serviceClass = properties.getProperty("SERVICE");
                controllerClass = properties.getProperty("CONTROLLEUR");
                propertiesFile.close();
            }catch (IOException e){
              throw new Exception("Problème de chargement des propriétés du fichier config");
          }
          finally {
              properties.clear();
          }

        }
       try{
           Class cDao = Class.forName(daoClass);
           Class cMetier= Class.forName(serviceClass);
           Class cControlleur = Class.forName(controllerClass);

           var dao = (IDao<Employe,Long>)cDao.getDeclaredConstructor().newInstance();
           var metier = (IEmployeMetier) cMetier.getDeclaredConstructor().newInstance();
           controlleur1 = (IEmployeControlleur)cControlleur.getDeclaredConstructor().newInstance();

           Method setDao = cMetier.getMethod("setEmployeDao",IDao.class);
           setDao.invoke(metier,dao);

           Method setMetier = cControlleur.getMethod("setService",IEmployeMetier.class);
           setMetier.invoke(controlleur1,metier);

           controlleur1.afficherSalaire(3L);
       }catch(Exception e) { e.printStackTrace();}

       }

       public static void test3() throws EmployeException{
           ApplicationContext context = new ClassPathXmlApplicationContext("spring-ioc.xml");
           controlleur1 = (IEmployeControlleur) context.getBean("controlleur");
           controlleur1.afficherSalaire(1L);
       }


       public static void test4() throws EmployeException {
           ApplicationContext context = new AnnotationConfigApplicationContext("dao","metier","presentation");
           //on peut scanner carrement à partir d'un package root qui englobe le tout (dao , metier , presentation)
           controlleur1 = (IEmployeControlleur) context.getBean(IEmployeControlleur.class);
           controlleur1.afficherSalaire(1L);
       }


    public static void main(String[] args) throws Exception {
    //    test1();
     //   test2();
       //   test3();
          test4();

    }
}
