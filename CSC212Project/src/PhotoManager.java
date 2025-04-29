
public class PhotoManager {
	BST<LinkedList<Photo>>index;
    LinkedList<Photo> allPhotos;
   

    public PhotoManager() {
    	index = new BST<LinkedList<Photo>>(); 
        allPhotos = new LinkedList<Photo>();
    }

   
    public void addPhoto(Photo p) {
        if (Photo_Exist(allPhotos, p)) return; 
        allPhotos.insert(p);
        
        LinkedList<String> tags = p.getTags();
        if (tags.empty()) return; 
        tags.findFirst();

        while (!tags.last()) {
            String curTag = tags.retrieve();
            boolean found = index.findKey(curTag);

            if (!found) {
                LinkedList<Photo> curPhotos = new LinkedList<Photo>();
                curPhotos.insert(p);
                index.insert(curTag, curPhotos);
            } else {
                LinkedList<Photo> curPhotos = index.retrieve();
                curPhotos.insert(p);
            }

            tags.findNext();
        }
        String curTag = tags.retrieve();
        boolean found = index.findKey(curTag);

        if (!found) {
            LinkedList<Photo> curPhotos = new LinkedList<Photo>();
            curPhotos.insert(p);
            index.insert(curTag, curPhotos);
        } else {
            LinkedList<Photo> curPhotos = index.retrieve();
            curPhotos.insert(p);
        }
    }

    public void remove_From_List(LinkedList<Photo> L, Photo p) {
        if (L.empty()) return;

        L.findFirst();
        while (!L.empty() && !L.last()) {
            if (L.retrieve().path.equals(p.path)) {
                L.remove();
            } else {
                L.findNext();
            }
        }

        if (!L.empty() && L.retrieve().path.equals(p.path)) {
            L.remove();
        }
    }

    
    public void deletePhoto(String path) {
    	
        if (allPhotos.empty()) return;
        LinkedList<String>tags=null;
        allPhotos.findFirst();
        while (!allPhotos.last()) {
            if (allPhotos.retrieve().path.equals(path)) {
            	tags=allPhotos.retrieve().tags;
                allPhotos.remove();
                break;
            }
            else
            allPhotos.findNext();
        }

        if (allPhotos.retrieve().path.equals(path)) {
        	tags=allPhotos.retrieve().tags;
            allPhotos.remove();
        }
        if (tags == null || tags.empty()) return;

        Photo ourPhoto = new Photo(path, tags);
        tags.findFirst();

        while (!tags.last()) {
            if (index.findKey(tags.retrieve())) {
                remove_From_List(index.retrieve(), ourPhoto);
                if (index.retrieve().empty()) {
                    index.removeKey(tags.retrieve());
                }
            }
            tags.findNext();
        }

        if (index.findKey(tags.retrieve())) {
            remove_From_List(index.retrieve(), ourPhoto);
            if (index.retrieve().empty()) {
                index.removeKey(tags.retrieve());
            }
        }

    }

   
    public BST<LinkedList<Photo>> getPhotos() {
        return index;
    }

   
    public boolean Photo_Exist(LinkedList<Photo> L, Photo p) {
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
}
