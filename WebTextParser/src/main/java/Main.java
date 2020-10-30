import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Main {

    static final Logger LOGGER = LogManager.getLogger(Main.class);//log4J
    private static final Marker INPUT_HISTORY_MARKER = MarkerManager.getMarker("INPUT_HISTORY");//маркер,по которому можно фильровать сообщения

    private static final String PATH_NAME ="data/page.html";//путь, по которому будет храниться html файл
    private static ArrayList<Word>wordList=new ArrayList<>();//тут будут хранится слова
    private static Parser parser = new Parser();
    private static final Pattern LINK =Pattern.compile("http[s]?://.*");//регулярное выражения для самых популярных протоколов соединения

    private static  String user;
    private static  String pass;
    private static  String db_path;

    private static final long FREE_MEMORY_MB= Runtime.getRuntime().freeMemory()/1048576;
    ;
    public static void main(String[] args) throws SQLException {
        try {
        Scanner sc = new Scanner(System.in);
        System.out.println("введите имя пользователя MySql");
        user=sc.nextLine().trim();
        System.out.println("введите пароль");
        pass=sc.nextLine().trim();
        System.out.println("введите порт");
        String port=sc.nextLine().trim();
        db_path="jdbc:mysql://localhost:"+port+"/mymodel?useSSL=false&serverTimezone=UTC";
        Connection connection = DriverManager.getConnection(db_path,user,pass);//подключаемся к базе данных
        Statement statement=connection.createStatement();
        statement.executeUpdate("delete from word");//и удаляем предыдущие записи
        connection.close();
        statement.close();
            System.out.println("Введите адрес сайта: ");
            String url = sc.nextLine();
            Matcher m = LINK.matcher(url);
            if (!m.find()) {
                throw new IllegalArgumentException("неверная ссылка на сайт!"//если ссылка на сайт введена неправильно
                        + "\nпример: https://ru.wikipedia.org/wiki/Java");// то генерируем исключение и заканчиваем работу
            }
            LOGGER.info(INPUT_HISTORY_MARKER,"Пользователь ввел ссылку {}  ", url);//отправляем в лог саму сылку
            readAndSavePage(url);//считываем и сохраняем html файл
            Document doc = Jsoup.parse(parser.parseFile(PATH_NAME));
            wordCount(doc);//считаем уникальные слова и записываем в бд
        }catch (Exception e){
            LOGGER.error(e);//если отловили исключения, записываем в лог
        }
    }

    public static void readAndSavePage(String url) throws Exception {
        Document document = Jsoup.connect(url).get();// получаем html файл
        if (FREE_MEMORY_MB<=Runtime.getRuntime().freeMemory()/1048576) {//если программе не хватает оперативной памяти, кидаем исключение
            throw new Exception("оперативной памяти не хватает на данную операцию");
        }
        FileWriter writer = new FileWriter(PATH_NAME, false);//указываем путь и вторым параметром обозначаем, что
        writer.write(document.toString());// файл будет перезаписываться
        LOGGER.info(INPUT_HISTORY_MARKER,"файл записан по пути {} ", PATH_NAME);//отправляем в лог путь записи
        writer.flush();
        writer.close();
    }

    public static Map<String, Long> wordCount(Document doc) throws SQLException {
        Connection connection = DriverManager.getConnection(db_path, user, pass);//соединяемся с базой данных
        Statement statement = connection.createStatement();
        String wordsArray[] = doc.text().split("[\\.\\!\\?\"\\,;:\\s\\n\\r\\t\\\\\\)\\(»«—/]");//разделяем текст по ключевым символам
        for (int i = 0; i < wordsArray.length; i++) {
            if (!wordsArray[i].equals("")) {
                wordList.add(new Word(wordsArray[i].toUpperCase()));//создаем новое слово, добавляем слово в лист, переводя в верхний регистр
            }
        }
        Map<String, Long> wordWithCount = wordList.stream().collect(Collectors.groupingBy(Word::getValue, Collectors.counting()));//создаем карту
        for (Map.Entry<String, Long> item : wordWithCount.entrySet()) {//со значениями слова и количества его вхождений, используя группировку по словам
            statement.executeUpdate("INSERT word(value, count) VALUES (\"" + item.getKey() + "\"," + item.getValue().intValue() + ")");//перебираем элементы
            System.out.println("Слово: " + item.getKey() + "\nКоличество вхождений: " + item.getValue());//записывая в базу данных и , попутно, выводя в консоль
        }
        LOGGER.info(INPUT_HISTORY_MARKER,"текст обработан и значения внесены в базу данных");//отправляем в лог сообщение о завершении работы
        connection.close();
        statement.close();
        return wordWithCount;
    }
}
