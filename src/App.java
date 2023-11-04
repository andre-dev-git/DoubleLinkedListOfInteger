public class App {
    public static void main(String[] args) throws Exception {
        DoubledLinkedListOfInteger list = new DoubledLinkedListOfInteger();
        for (int i = 1; i <= 5000; i++) {
            list.add(i);
        }
    }
}
