public class Expense {
    private int id;
    private double amount;
    private String category;
    private String date;
    private String description;

    public Expense(int id, double amount, String category, String date, String description) {
        this.id = id;
        this.amount = amount;
        this.category = category;
        this.date = date;
        this.description = description;
    }

	public double getAmount() {
		
		return this.amount;
	}

	public String getCategory() {
		
		return this.category;
	}

	public String getDate() {
		
		return this.date;
	}

	public String getDescription() {
		
		return this.description;
	}

	public int getId() {
		
		return this.id;
	}

    // Getters and Setters
}
