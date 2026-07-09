public class SingletonDesignPattern {
    public static void main(String[] args) {
        DatabaseConnection connectionOne = DatabaseConnection.getInstance();
        connectionOne.connect();
        connectionOne.query("SELECT * FROM users");

        DatabaseConnection connectionTwo = DatabaseConnection.getInstance();
        connectionTwo.query("SELECT * FROM orders");

        System.out.println(connectionOne);
        System.out.println(connectionTwo);
        System.out.println(connectionOne == connectionTwo);
    }
}

class DatabaseConnection {
    private static DatabaseConnection instance;
    private String url;
    private boolean connected;

    private DatabaseConnection() {
        this.url = "jdbc:mysql://localhost:3306/learning";
        this.connected = false;
    }

    public static DatabaseConnection getInstance() {
        if (instance == null) {
            instance = new DatabaseConnection();
        }
        return instance;
    }

    public void connect() {
        if (!connected) {
            connected = true;
            System.out.println("Connected to database.");
        }
    }

    public void query(String sql) {
        if (!connected) {
            connect();
        }
        System.out.println("Executing query: " + sql);
    }

    @Override
    public String toString() {
        return "DatabaseConnection{" +
                "url='" + url + "'" +
                ", connected=" + connected +
                "}";
    }
}
