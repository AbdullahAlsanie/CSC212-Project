
public class PhotoManager {
    LinkedList<Photo> allPhotos;

    public PhotoManager() {
        allPhotos = new LinkedList<Photo>();
    }

    public void addPhoto(Photo p) {
        if (PhotoExists(allPhotos, p)) return;
        allPhotos.insert(p);
    }

    public void deletePhoto(String path) {
        if (allPhotos.empty()) return;

        allPhotos.findfirst();
        while (!allPhotos.last()) {
            if (allPhotos.retrieve().path.equals(path)) {
                allPhotos.remove();
                return;
            }
            allPhotos.findnext();
        }

        if (allPhotos.retrieve().path.equals(path)) {
            allPhotos.remove();
        }
    }

    public LinkedList<Photo> getPhotos() {
        return allPhotos;
    }

    public boolean PhotoExists(LinkedList<Photo> L, Photo p) {
        if (L.empty()) return false;

        L.findfirst();
        while (!L.last()) {
            if (L.retrieve().path.equals(p.path))
                return true;
            L.findnext();
        }

        return L.retrieve().path.equals(p.path);
    }
}
