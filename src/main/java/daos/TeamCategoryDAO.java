package daos;

public class TeamCategoryDAO {
    private int categoryId;
    private String name;

    public TeamCategoryDAO() {
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(short categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
