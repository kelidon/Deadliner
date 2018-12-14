package Deadliner;

import Deadliner.Class;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by Kelidon on 14.11.2018.
 */


public class Week {
    Class[][] classes;

    public Week() {
    }

    public Week(Class[][] classes) {
        this.classes = classes;
    }

    public void fill(String subjectSFiller) {
        classes = new Class[Main.NUMBER_OF_CLASSES][Main.NUMBER_OF_CLASSES];
        for (int i = 0; i < Main.NUMBER_OF_CLASSES; i++)
            for (int j = 0; j < Main.NUMBER_OF_CLASSES; j++) {
                classes[i][j] = new Class();
                classes[i][j].setSubject(subjectSFiller);
            }
    }

    Week(File timetable) {
        try {
            Scanner scanner = new Scanner(timetable);
            String temp;
            classes = new Class[Main.NUMBER_OF_CLASSES][Main.NUMBER_OF_CLASSES];
            int indexClasses = 0, indexDays = 0;
            temp = scanner.nextLine();
            while (scanner.hasNext()) {
                if (!temp.equals("&")) {
                    while (!temp.equals("&")) {
                        if (!temp.equals("_")) {
                            classes[indexDays][indexClasses] = new Class();
                            classes[indexDays][indexClasses].setFromString(temp);
                            if(indexClasses<4)
                                classes[indexDays][indexClasses].setTime(Main.CLASSES_TIME[indexClasses]);
                            else if(classes[indexDays][indexClasses].getClassType().equals("str"))
                                classes[indexDays][indexClasses].setTime(Main.CLASSES_TIME[5]);
                            else if(classes[indexDays][indexClasses].getClassType().equals("litklub"))
                                classes[indexDays][indexClasses].setTime(Main.CLASSES_TIME[4]);
                        } else {
                            classes[indexDays][indexClasses] = new Class();
                        }
                        indexClasses++;
                        temp = scanner.nextLine();
                    }
                } else {
                    indexDays++;
                    indexClasses = 0;
                    temp = scanner.nextLine();
                }
            }
        }
        catch(FileNotFoundException exc){}
        catch(IllegalArgumentException exc){}

    }
}