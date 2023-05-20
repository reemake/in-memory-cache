import java.util.*;

/**
 * Класс для организации in memory кэша
 * @author Nikita Safonov
 */
public class InMemoryCache {

    /** Хранение записей по полю account */
    private Map<Long, Record> recordsById;

    /** Хранение записей по полю name */
    private Map<String, List<Record>> recordsByName;

    /** Хранение записей по полю value */
    private Map<Double, List<Record>> recordsByValue;

    public InMemoryCache() {
        recordsById = new TreeMap<>();
        recordsByName = new TreeMap<>();
        recordsByValue = new TreeMap<>();
    }

    /**
     * Метод для добавления записи (записей)
     * @param records запись (записи) для добавления
     */
    public void addRecords(Record... records) {
        for (Record record: records) {
            if (recordsById.containsKey(record.getAccount())) {
                System.out.println("Аккаунт с id = " + record.getAccount() + " уже существует!");
                continue;
            }
            recordsById.put(record.getAccount(), record);

            List<Record> recordsWithName = recordsByName.getOrDefault(record.getName(), new LinkedList<>());
            recordsWithName.add(record);
            recordsByName.put(record.getName(), recordsWithName);

            List<Record> recordsWithValue = recordsByValue.getOrDefault(record.getValue(), new LinkedList<>());
            recordsWithValue.add(record);
            recordsByValue.put(record.getValue(), recordsWithValue);
        }
    }

    /**
     * Метод для удаления записи (записей)
     * @param records запись (записи) для удаления
     */
    public void removeRecord(Record... records) {
        for (Record record: records) {
            recordsById.remove(record.getAccount());

            List<Record> recordsWithName = recordsByName.get(record.getName());
            if (recordsWithName != null) {
                recordsWithName.remove(record);
                if (recordsWithName.isEmpty()) {
                    recordsByName.remove(record.getName());
                }
            }

            List<Record> recordsWithValue = recordsByValue.get(record.getValue());
            if (recordsWithValue != null) {
                recordsWithValue.remove(record);
                if (recordsWithValue.isEmpty()) {
                    recordsByValue.remove(record.getValue());
                }
            }
        }
    }

    /**
     * Метод для обновления записи
     * @param oldRecord обновляемая запись
     * @param name новое значение name
     * @param value новое значение value
     */
    public void updateRecord(Record oldRecord, String name, Double value) {
        removeRecord(oldRecord);
        Record newRecord = new Record(oldRecord.getAccount(), name, value);
        addRecords(newRecord);
    }

    /**
     * Метод получения полного набора записи по полю account
     * @param account значение поля account
     * @return список с единственной записью при успешном поиске, иначе пустой список
     */
    public List<Record> getRecordByAccount(long account) {
        List<Record> result = new LinkedList<>();
        Record record = recordsById.get(account);
        if (record != null) {
            result.add(record);
        }
        return result;
    }

    /**
     * Метод получения полного набора записей по полю name
     * @param name значение поля name
     * @return список с записями при успешном поиске, иначе пустой список
     */
    public List<Record> getRecordsByName(String name) {
        return recordsByName.getOrDefault(name, new LinkedList<>());
    }

    /**
     * Метод получения полного набора записей по полю value
     * @param value значение поля value
     * @return список с записями при успешном поиске, иначе пустой список
     */
    public List<Record> getRecordsByValue(double value) {
        return recordsByValue.getOrDefault(value, new LinkedList<>());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InMemoryCache that = (InMemoryCache) o;
        return recordsById.equals(that.recordsById) && recordsByName.equals(that.recordsByName) && recordsByValue.equals(that.recordsByValue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(recordsById, recordsByName, recordsByValue);
    }

    @Override
    public String toString() {
        return "InMemoryCache{" +
                "recordsById=" + recordsById +
                ", recordsByName=" + recordsByName +
                ", recordsByValue=" + recordsByValue +
                '}';
    }

    public Map<Long, Record> getRecordsById() {
        return recordsById;
    }

    public Map<String, List<Record>> getRecordsByName() {
        return recordsByName;
    }

    public Map<Double, List<Record>> getRecordsByValue() {
        return recordsByValue;
    }
}
