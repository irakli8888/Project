
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Parser {
    static final Logger LOGGER = LogManager.getLogger(Parser.class);

    public String parseFile(String fileName) throws IOException {
        StringBuilder sb=new StringBuilder();//создаем стрингбилдер
        try{
            List<String>lines= Files.readAllLines(Paths.get(fileName));//заполняем элементы листа строками из файла,
            lines.forEach(line->sb.append(line+"\n"));//путь к которому нам передали в параметре, и, сохраняя переходы строк,
        }catch (Exception e){//добавляем в стрингбилдер sb
            LOGGER.error(e);//ошибки, если они возникают, отправляем в лог
        }return sb.toString();//возвращаем String значение стрингбилдера
    }
}
