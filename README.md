# Project ЗАДАНИЕ
Имеется входная строка с адресом web-страницы. Приложение должно скачать
web-страницу, сохранить ее на жесткий диск. Затем текстовый контент, который есть
на ней (который видит пользователь на странице браузера), разбивать на слова и
подсчитывать количество нахождений на странице каждого уникального слова.
Разделителями слов считать следующие символы: {' ', ',', '.', '!', '?','"', ';', ':', '[', ']', '(', ')', '\n',
'\r', '\t'}
 
 
 ============================================================================
 в приложении использованы технологии: JDBC для работы с базой данных MySQl, log4j2 для логгирования(логи пишутся в файлы .\logs\error.log & .\logs\info.log), Jsoup для 
 парсинга html  страниц, JUnit для тестов классов приложения; для разделения слов добавил дополнительные символы для удобства
 ============================================================================
 1.для работы программы необходимо сделать дамп базы данных:
  1.1 скрипт базы данных лежит в папке ./database/newScript.sql
  1.2 открыть консоль cmd и пройти до исполняемоего файла( команда cd) mysql(обычно, его расположение по умолчанию: C:\Program Files\MySQL\MySQL Server 8.0\bin\mysql")
    и выполнить команду mysqldump -u USER -pPASSWORD DATABASE > dumpname.sql(пароль не отделять от символа -p), если дамп не получился, то открыть MySQl Workbench 
    (File->open sql script->newScript.sql) и выполнить его нажав на значок с молнией. Скрипт не содержит значений, в нем переданна только структура для объекта
   
 2. программу можно запустить через main метод(путь WebTextParser/src/main/java/Main.java), открыв ее в любом из компиляторов, либо через .jar файл(путь           
    WebTextParser/out/artifacts/WebTextParser.jar; при таком запуске 
    в консоль не выводятся данные, но в бд записываются)
 
 3. при запуске у вас попросят ваш логин,пароль и хост на котором запускается MySQl Server(3306 обычно)
 
 4. после заполнения логина, пароля и хоста вам нужно будет ввести ссылку на сайт (пример: https://en.wikipedia.org/wiki/Main_Page)
 
 5. информация о ходе работы программы записывается в файл .\logs\info.log, об ошибках в .\logs\error.log
