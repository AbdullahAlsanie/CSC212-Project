
public class Photo {

    String path;
    LinkedList<String> tags;

    public Photo(String path, LinkedList<String> tags) {
        this.path = path;
        if (tags != null) {
            this.tags = tags;
        } else {
            this.tags = new LinkedList<String>();
        } 
    }

    public String getPath() {
        return path;
    }

    public LinkedList<String> getTags() {
        return tags;
    }
}
