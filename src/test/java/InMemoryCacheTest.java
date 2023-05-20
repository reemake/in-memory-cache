import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class InMemoryCacheTest {

    Record record1;
    Record record2;
    Record record3;
    Record record4;
    InMemoryCache inMemoryCache;

    @BeforeEach
    public void setUp() {
        record1 = new Record(123L, "Ivan", 123123.22);
        record2 = new Record(124L, "Andrew", 1443.55);
        record3 = new Record(125L, "Anton", 211224.26);
        record4 = new Record(126L, "Sergey", 44533.68);
        inMemoryCache = new InMemoryCache();
    }

    @Test
    public void addRecordsTest() {
        inMemoryCache.addRecords(record1, record2, record3, record4);
        int expectedValue = 4;
        int actualValue = inMemoryCache
                .getRecordsById()
                .size();
        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void addRecordWithEqualAccountTest() {
        Record recordWithAccountAsRecord1 = new Record(record1.getAccount(), "Maxim", 43424.48);
        inMemoryCache.addRecords(record1, recordWithAccountAsRecord1);
        int expectedValue = 1;
        int actualValue = inMemoryCache
                .getRecordsById()
                .size();
        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void removeRecordsTest() {
        inMemoryCache.addRecords(record1, record2, record3, record4);
        inMemoryCache.removeRecord(record1, record3);
        int expectedValue = 2;
        int actualValue = inMemoryCache
                .getRecordsById()
                .size();
        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void getRecordByAccountTest() {
        inMemoryCache.addRecords(record1);
        Record expectedValue = record1;
        Record actualValue = inMemoryCache
                .getRecordByAccount(record1.getAccount())
                .get(0);
        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void updateRecordTest() {
        inMemoryCache.addRecords(record1);
        inMemoryCache.updateRecord(record1, "Maxim", 12121.12);
        String expectedValue = "Maxim";
        String actualValue = inMemoryCache
                .getRecordByAccount(record1.getAccount())
                .get(0)
                .getName();
        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void getRecordByNameTest() {
        inMemoryCache.addRecords(record1);
        Record expectedValue = record1;
        Record actualValue = inMemoryCache
                .getRecordsByName(record1.getName())
                .get(0);
        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void getRecordsByNameTest() {
        Record recordWithNameAsRecord1 = new Record(130L, record1.getName(), 43424.48);
        inMemoryCache.addRecords(record1, recordWithNameAsRecord1);
        int expectedValue = 2;
        int actualValue = inMemoryCache
                .getRecordsByName(record1.getName())
                .size();
        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void getRecordByValueTest() {
        inMemoryCache.addRecords(record1);
        Record expectedValue = record1;
        Record actualValue = inMemoryCache
                .getRecordsByValue(record1.getValue())
                .get(0);
        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void getRecordsByValueTest() {
        Record recordWithValueAsRecord1 = new Record(130L, "Maxim", record1.getValue());
        inMemoryCache.addRecords(record1, recordWithValueAsRecord1);
        int expectedValue = 2;
        int actualValue = inMemoryCache
                .getRecordsByValue(record1.getValue())
                .size();
        assertEquals(expectedValue, actualValue);
    }
}
