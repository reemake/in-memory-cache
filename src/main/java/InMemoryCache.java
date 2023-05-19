import java.util.*;

// TODO: 19.05.2023 javadocs
public class InMemoryCache {
    private Map<Long, Record> recordsById; // Хранение записей по полю account
    private Map<String, List<Record>> recordsByName; // Хранение записей по полю name
    private Map<Double, List<Record>> recordsByValue; // Хранение записей по полю value

    public InMemoryCache() {
        recordsById = new HashMap<>();
        recordsByName = new HashMap<>();
        recordsByValue = new HashMap<>();
    }

    // TODO: 19.05.2023 конструктор с переменным числом аргументов
    public void addRecord(Record record) {
        recordsById.put(record.getAccount(), record);

        List<Record> recordsWithName = recordsByName.getOrDefault(record.getName(), new LinkedList<>());
        recordsWithName.add(record);
        recordsByName.put(record.getName(), recordsWithName);

        List<Record> recordsWithValue = recordsByValue.getOrDefault(record.getValue(), new LinkedList<>());
        recordsWithValue.add(record);
        recordsByValue.put(record.getValue(), recordsWithValue);
    }

    public void removeRecord(Record record) {
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

    public void updateRecord(Record oldRecord, String name, Double value) {
        removeRecord(oldRecord);
        Record newRecord = new Record(oldRecord.getAccount(), name, value);
        addRecord(newRecord);
    }

    public List<Record> getRecordsByAccount(long account) {
        List<Record> result = new LinkedList<>();
        Record record = recordsById.get(account);
        if (record != null) {
            result.add(record);
        }
        return result;
    }

    public List<Record> getRecordsByName(String name) {
        return recordsByName.getOrDefault(name, new LinkedList<>());
    }

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

    // TODO: 19.05.2023 unit tests
    public static void main(String[] args) {
        Record record1 = new Record(123L, "Ivan", 123123.22);
        Record record2 = new Record(124L, "Andrew", 1443.55);
        Record record3 = new Record(125L, "Anton", 211224.26);
        Record record4 = new Record(126L, "Nikita", 44533.68);

        InMemoryCache inMemoryCache = new InMemoryCache();
        inMemoryCache.addRecord(record1);
//        inMemoryCache.addRecord(record2);
//        inMemoryCache.addRecord(record3);
//        inMemoryCache.addRecord(record4);

        System.out.println(inMemoryCache);

        inMemoryCache.updateRecord(record1, "Sergey", 32323.22);
        System.out.println(inMemoryCache);
    }
}
