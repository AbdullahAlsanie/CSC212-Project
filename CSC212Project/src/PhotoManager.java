
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
 
         allPhotos.findFirst();
         while (!allPhotos.last()) {
             if (allPhotos.retrieve().path.equals(path)) {
                 allPhotos.remove();
                 return;
             }
             allPhotos.findNext();
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
 
         L.findFirst();
         while (!L.last()) {
             if (L.retrieve().path.equals(p.path))
                 return true;
             L.findNext();
         }
 
         if (L.retrieve().path.equals(p.path))
             return true;
 
         return false;
     }