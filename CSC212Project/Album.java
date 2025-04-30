
public class Album {
    String name;
    String condition;
    PhotoManager manager;
    int totalnbcomp = 0;

    public Album(String name, String condition, PhotoManager manager) {
        this.name = name;
        this.condition = condition;
        this.manager = manager;
    }

    public String getName() {
        return name;
    }

    public String getCondition() {
        return condition;
    }

    public PhotoManager getManager() {
        return manager;
    }

    public boolean Tag_Exist_in_photo(LinkedList<String> L, String tag) {
        if (L.empty()) return false;
        L.findfirst();
        while (!L.last()) {
            totalnbcomp++;
            if (L.retrieve().equals(tag)) {
                return true;
            }
            L.findnext();
        }
        totalnbcomp++;
        return L.retrieve().equals(tag);
    }

    public boolean subset(String[] A, LinkedList<String> B) {
        if (A.length == 0) return true;
        if (B.empty()) return false;
        for (int i = 0; i < A.length; i++) {
            if (!Tag_Exist_in_photo(B, A[i])) {
                return false;
            }
        }
        return true;
    }

    public LinkedList<Photo> getPhotos() {
        totalnbcomp = 0; 
        LinkedList<Photo> allPhotos = manager.getPhotos();
        LinkedList<Photo> res = new LinkedList<Photo>();
        if (allPhotos.empty()) return res;
        if (condition == null) return res;
        if (condition.equals("")) return allPhotos;
        String[] a = condition.split("AND");
        for (int i = 0; i < a.length; i++) {
            a[i] = a[i].trim();
        }

        allPhotos.findfirst();
        while (!allPhotos.last()) {
            if (subset(a, allPhotos.retrieve().getTags())) {
                res.insert(allPhotos.retrieve());
            }
            allPhotos.findnext();
        }

        if (subset(a, allPhotos.retrieve().getTags())) {
            res.insert(allPhotos.retrieve());
        }

        return res;
    }

    public int getNbComps() {
        return totalnbcomp;
    }
}
