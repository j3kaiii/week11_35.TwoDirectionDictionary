package dictionary;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author j3kaiii
 */
public class MindfulDictionary {
    private Map<String, String> dictionary;
    private File f;
    private String file;
    
    public MindfulDictionary(String file) {
        this.file = file;
        dictionary = new HashMap<String, String>();
    }

    public MindfulDictionary() {
        this("");
    }
    
    public boolean load() {
        //грузим файл
        try {
            f = new File(file);
            Scanner reader = new Scanner(f);
            
            //чистим словарь от старых значений, заполняем новыми
            dictionary.clear();
            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                String[] parts = line.split(":");
                dictionary.put(parts[0], parts[1]);
            }
            reader.close();
            return true;
        } catch (FileNotFoundException ex) {
            System.out.println("File not found");
            return false;
        }
    }
    
    public boolean save() { 
        //если файла нет, создаем новый и сохраняемся в него
        
        f = new File(file);
        try {
            f.createNewFile();
        } catch (Exception e) {
            System.out.println("new file");
        }
        try {
            //добавляем слова из файла в наш словарик
            Scanner reader = new Scanner(f);
            while(reader.hasNextLine()) {
                String line = reader.nextLine();
                String[] parts = line.split(":");
                //пропускаем повторы
                if (dictionary.containsKey(parts[0]) || dictionary.containsValue(parts[0])) {
                    break;
                } else {
                    dictionary.put(parts[0], parts[1]);
                }
            }
            
            reader.close();
            //перезаписываем обновленный словарь в файл
            FileWriter fw = new FileWriter(file);
            
            for (Map.Entry<String, String> pair : dictionary.entrySet()) {
                String string1 = pair.getKey() + ":" + pair.getValue();
                fw.write(string1 + "\n");
            }
            fw.close();
            
            return true;
        } catch (IOException e) {
            return false;
        }
        
    }
    
    public void add(String word, String translation) { 
        //добавляем слово в словарь, если такого нет
        if (!dictionary.containsKey(word)) {
            dictionary.put(word, translation);
        }
    }
    
    public String translate(String word) {
        //переводим слово
        if (dictionary.containsKey(word)) {
            return dictionary.get(word);
        } else {
            for (Map.Entry<String, String> pair : dictionary.entrySet()) {
                if (pair.getValue().equals(word)) {
                    return pair.getKey();
                }
            }
        }
        return null;
    }
    
    public void remove(String word) {
        //удаляем слово из словаря
        String rem = "";
        
        if (dictionary.containsKey(word)) {
            dictionary.remove(word);
        } else {
            for (Map.Entry<String, String> pair : dictionary.entrySet()) {
                if (pair.getValue().equals(word)) {
                    rem = pair.getKey();
                }
            }
            dictionary.remove(rem);
        }
        
    }

}
