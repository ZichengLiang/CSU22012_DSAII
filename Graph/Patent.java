package Graph;

public class Patent implements Comparable {
    private String patentId;    // e.g., "US1234567A"
    private String title;
    private int issueDate;   //e.g., 2000, 2004 etc.

    public Patent(String patentId, String title, int issueDate) {
        this.patentId = patentId;
        this.title = title;
        this.issueDate = issueDate;
    }

    public String getPatentId() {
        return patentId;
    }

    public String getTitle() {
        return title;
    }

    public int getIssueDate() {
        return issueDate;
    }

    @Override
    public String toString() {
        return String.format("%s (%s) - issued on %d", patentId, title, issueDate);
    }

    @Override
    public int compareTo(Object o) {
        return this.title.compareTo(((Patent) o).getTitle());
//        if(this.title>((Patent)o).title)
//            return 1;
//        else if (this.title<((Patent)o).title) {
//            return -1;
//        } else {
//            return 0;
//        }
    }
}
