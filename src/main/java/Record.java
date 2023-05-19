import java.util.Objects;

// TODO: 19.05.2023 javadocs 
public class Record {
    private Long account;
    private String name;
    private Double value;

    public Record(Long account, String name, Double value) {
        this.account = account;
        this.name = name;
        this.value = value;
    }

    public Long getAccount() {
        return account;
    }

    public void setAccount(Long account) {
        this.account = account;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Record record = (Record) o;
        return account.equals(record.account) && name.equals(record.name) && value.equals(record.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(account, name, value);
    }

    @Override
    public String toString() {
        return "Record{" +
                "account=" + account +
                ", name='" + name + '\'' +
                ", value=" + value +
                '}';
    }
}
