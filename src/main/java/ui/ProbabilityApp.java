package ui;

import model.Disease;
import model.Study;
import model.Symptom;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

//Represents the probability application that collects user input and stores study data.
public class ProbabilityApp {

    private static final String filePath = "data/studyData.json";

    ArrayList<Study> studies;
    Scanner scanner;
    JsonWriter jsonWriter;
    JsonReader jsonReader;

    //EFFECTS: creates probability app without any study data in it
    public ProbabilityApp() {
        this.studies = new ArrayList<>();
        this.scanner = new Scanner(System.in);
        this.jsonWriter = new JsonWriter(filePath);
        this.jsonReader = new JsonReader(filePath);
    }

    //EFFECTS: reads persistent study data,
    //         gets user input about whether to add data or check symptoms and then does that until user quits
    //MODIFIES: this
    public void runApp() {
        readStudiesFromFile();
        System.out.println("Welcome to the \"Estimator of Ailment Probability from Symptoms\"");
        boolean wantToContinue = true;
        while (wantToContinue) {
            System.out.println("Would you like to check disease probability or add study data to the program?");
            System.out.println("   Check diseases: press 1");
            System.out.println("   Add study data: press 2");
            System.out.println("delete study data: press 3");
            System.out.println(" See current data: press 4");
            System.out.println("             Quit: press q");
            String choice = scanner.next();
            while (!choice.equals("1") && !choice.equals("2") && !choice.equals("3") && !choice.equals("4")
                    && !choice.equals("q")) {
                System.out.println("You must press 1, 2, 3 , 4 or q");
                choice = scanner.next();
            }
            if (choice.equals("q")) {
                wantToContinue = false;
            } else {
                handleChoice(choice);
            }
        }
    }

    //EFFECTS: gets user input about what to do and checks symptoms or adds study data
    //REQUIRES: String choice is either "1" or "2" or "3"
    //MODIFIES: this
    public void handleChoice(String choice) {
        if (choice.equals("1")) {
            System.out.println("CHECKING DISEASE PROBABILITIES");
            checkSymptoms();
        } else if (choice.equals("2")) {
            System.out.println("ADDING STUDY DATA TO PROGRAM:");
            addStudyData();
        } else if (choice.equals("3")) {
            System.out.println("DELETING STUDY DATA FROM PROGRAM:");
            deleteStudyData();
        } else if (choice.equals("4")) {
            System.out.println("CURRENT DATA:");
            printAddedData();
        }
    }

    //EFFECTS: gets user input of study data to add and then adds it to the probability app
    //MODIFIES: this
    public void addStudyData() {
        System.out.println("How many participants were in this study");
        Study study = new Study(scanner.nextInt());

        System.out.println("Name the first disease in this study");
        String diseaseName = scanner.next();
        System.out.printf("How many participants had %s\n", diseaseName);
        Disease disease = new Disease(diseaseName, scanner.nextInt());
        getSymptomInput(disease);
        study.addDisease(disease);

        while (true) {
            System.out.println("Name the next disease in this study or press \"d\" if no more diseases");
            diseaseName = scanner.next();
            if (diseaseName.equals("d")) {
                break;
            }
            System.out.printf("How many participants had %s\n", diseaseName);
            disease = new Disease(diseaseName, scanner.nextInt());
            getSymptomInput(disease);
            study.addDisease(disease);
        }
        studies.add(study);

        saveStudiesToFile();
    }

    //EFFECTS: save all studies to specified filePath
    //MODIFIES: this
    private void saveStudiesToFile() {
        try {
            jsonWriter.openFile();
        } catch (FileNotFoundException e) {
            System.err.printf("could not open/create file at %s \n", filePath);
        }
        jsonWriter.writeStudiesToFile(studies);
        jsonWriter.closeFile();
    }

    //EFFECTS: read the studies from specified filePath and put into studies field
    //MODIFIES: this
    private void readStudiesFromFile() {
        try {
            studies = jsonReader.readStudiesFromFile();
        } catch (IOException e) {
            System.err.printf("could not read file at %s \n", filePath);
        }
    }

    //EFFECTS: gets User input of symptom data for a disease and then adds it to the disease
    //MODIFIES: disease
    public void getSymptomInput(Disease disease) {
        System.out.printf("Name the first symptom of %s\n", disease.getName());
        String symptomName = scanner.next();
        System.out.printf("How many participants who had %s had %s?\n", disease.getName(), symptomName);
        int affected = scanner.nextInt();
        disease.addSymptom(new Symptom(symptomName, affected));

        while (true) {
            System.out.printf("Name another symptom of %s or press \"d\" if no more symptoms\n", disease.getName());
            symptomName = scanner.next();
            if (symptomName.equals("d")) {
                break;
            }
            System.out.printf("How many participants who had %s had %s?\n", disease.getName(), symptomName);
            affected = scanner.nextInt();
            disease.addSymptom(new Symptom(symptomName, affected));
        }
    }

    //EFFECTS: gets user input about a study to delete from studies and deletes it. Does nothing if studies is empty.
    //MODIFIES: this
    private void deleteStudyData() {
        if (studies.size() == 0) {
            System.out.println("No study data to delete, must add a study first");
            return;
        }

        ArrayList<String> possibleChoices = printStudiesToDelete();
        String choice = scanner.next();
        while (!possibleChoices.contains(choice)) {
            System.out.println("You must select a study number");
            choice = scanner.next();
        }
        studies.remove(Integer.valueOf(choice) - 1);
        saveStudiesToFile();
    }

    //EFFECTS: prints out info on studies that can be deleted and returns Strings for choices of what to delete
    private ArrayList<String> printStudiesToDelete() {
        System.out.println("Which Study would you like to delete??");
        int i = 1;
        ArrayList<String> possibleChoices = new ArrayList<>();
        for (Study study : studies) {
            System.out.printf("press %d -> delete study #%d (", i, i);
            for (String diseaseName: study.getAllDiseaseNames()) {
                System.out.print(diseaseName);
                System.out.print(" ");
            }
            System.out.println(")");
            possibleChoices.add(String.valueOf(i));
            i++;
        }
        return possibleChoices;
    }


    //EFFECTS: gets user input of symptoms the user has and then outputs probability of different ailments
    //MODIFIES: this
    //REQUIRES: At least one Study in studies
    public void checkSymptoms() {
        ArrayList<String> symptomNames = new ArrayList<>();
        ArrayList<String> posSymptomNames = new ArrayList<>();
        for (Study study: studies) {
            symptomNames.addAll(study.getAllSymptoms());
        }
        Symptom.filterUniqueSymptomNames(symptomNames);

        for (String symptomName : symptomNames) {
            System.out.printf("Do you have %s?\t(y/n)\n", symptomName);
            String choice = scanner.next();
            while (!choice.equals("y") && !choice.equals("n")) {
                System.out.println("You must press y or n");
                choice = scanner.next();
            }
            if (choice.equals("y")) {
                posSymptomNames.add(symptomName);
            }
        }

        for (Study study: studies) {
            study.findAllProbs(posSymptomNames);
        }

        printDiseaseProbs();
    }

    //EFFECTS: prints to console all the probabilities of different disease symptoms
    private void printAddedData() {
        for (Study study : studies) {
            for (Disease disease : study.getDiseases()) {
                System.out.printf("\t%s\tP(%s) = %d / %d\n",disease.getName(),disease.getName(),
                        disease.getAffected(), study.getSampleSize());
                for (Symptom symptom : disease.getSymptoms()) {
                    System.out.printf("\t\tP(%s | %s) = %d / %d\n", symptom.getName(),
                            disease.getName(), symptom.getAffected(), disease.getAffected());
                }
            }
        }
    }

    //EFFECTS: prints to console the probability of having each disease
    //REQUIRES: findAllProbs() has already been called on each study
    private void printDiseaseProbs() {
        for (Study study : studies) {
            for (Disease disease : study.getDiseases()) {
                System.out.printf("\tP(%s) = %f \n",disease.getName(),disease.getProb());
            }
        }
    }

}
