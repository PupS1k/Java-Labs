import java.util.Map;
import java.util.HashMap;
import java.lang.NullPointerException;

public class Faculty {
    public static void main(String[] args) {

        int size = 3;
        String[] teachers = {"Бойко", "Маталыцкий", "Петревич"};
        String[] course = {"Алгебра", "Геометрия", "Программирование"};


        Map map = new HashMap<String, String>();
        while (true) {
            String[] mirror_course = {"Алгебра", "Геометрия", "Программирование"};
            String[] mirror_teachers = {"Бойко", "Маталыцкий", "Петревич"};
            Student name_std = new Student();
            String name = name_std.inputName();
            if (name.equals("exit")) break;
            String full_name;
            Teacher tch = new Teacher();
            if (!tch.torS(name, teachers)) {

                System.out.println("Вы авторизовались как студент");
                full_name = name_std.inputInfo();

                System.out.println("Преподаватель   -   Курс");
                System.out.println("--------------------------");
                for (int i = 0; i < size; i++) {
                    System.out.println(teachers[i] + "    -   " + course[i]);
                }
                System.out.println("--------------------------");

                Course std = new Course();


                std.courseStudent(size, teachers, course, mirror_course, name, map, full_name);
                teachers = mirror_teachers;
                course = mirror_course;

            } else {
                try {
                    int amount = tch.teacherIndex(teachers, name);
                    System.out.println("\nКурс: " + course[amount] + "\nПреподаватель: " + teachers[amount]);
                    String str_map = (String) map.get(course[amount]);
                    String[] stud_c = str_map.split(" ");
                    int size_stud = stud_c.length;
                    for (int k = 0; k < size_stud; k++) {
                        if (stud_c[k].equals("null")) {
                            System.arraycopy(stud_c, k + 1, stud_c, k, size_stud - 1 - k);
                            size_stud--;
                        }
                    }
                    if (stud_c[size_stud - 1].equals("null")) size_stud--;


                    System.out.println("Студенты, которые записались на ваш курс:");
                    for (int i = 0; i < size_stud; i++)
                        System.out.println("\t" + stud_c[i]);
                    tch.archiveCourse(course[amount]);
                    int size_mirror = size_stud;
                    tch.inputValue(stud_c, size_stud, size_mirror);
                } catch (NullPointerException e) {
                    System.out.println("\tНикто не записался на ваш курс");
                }
            }
        }
    }
}
