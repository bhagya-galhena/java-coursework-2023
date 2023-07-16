public class Customer {
    private String first_name;
    private String last_name;
    private int burger_amount;

    public Customer() {
    }

    public Customer(String first_name, String last_name, int burger_amount) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.burger_amount = burger_amount;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public int getBurger_amount() {
        return burger_amount;
    }

    public void setBurger_amount(int burger_amount) {
        this.burger_amount = burger_amount;
    }
}
