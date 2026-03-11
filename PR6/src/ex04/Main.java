package ex04;
    /** Запуск програми;
     * містить реалізацію статичного методу main()
     * @author Левковська Марія
     * @version 1.0
     * @see Main#main */
public class Main {
    /** Виконується при запуску програми;
     * викликає метод {@linkplain Application#run()}
     * @param args параметри запуску*/
    public static void main(String[] args){
        Application app = Application.getInstance();
        app.run();
    }
}
