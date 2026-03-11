package ex04;
    /** Інтерфейс консольної команди;
     * шаблон Command
     * @author Левковська Марія
     * @version 1.0*/
public interface ConsoleCommand extends Command {
     /** Гаряча клавіша команди
     * @return символ гарячої клавіші*/
    public char getKey();
}
