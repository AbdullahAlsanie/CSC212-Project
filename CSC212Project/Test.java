public class Test {
    public static void main(String[] args) {
        System.out.println("=== Phase 1: Normal PhotoManager ===");
        PhotoManager pm = new PhotoManager();

        Photo p1 = new Photo("hedgehog.jpg", toTagsLinkedList("animal, hedgehog, apple, grass, green"));
        Photo p2 = new Photo("bear.jpg", toTagsLinkedList("animal, bear, cab, grass, wind"));
        Photo p3 = new Photo("butterfly.jpg", toTagsLinkedList("insect, butterfly, flower, color"));
        Photo p4 = new Photo("panda.jpg", toTagsLinkedList("animal, bear, panda, grass"));

        pm.addPhoto(p1);
        pm.addPhoto(p2);
        pm.addPhoto(p3);
        pm.addPhoto(p4);

        Album a1 = new Album("Bear Album", "bear", pm);
        Album a2 = new Album("Animal and Grass", "animal AND grass", pm);
        Album a3 = new Album("All Photos", "", pm);

        System.out.println("Bear Album:");
        printPhotos(a1.getPhotos());

        System.out.println("Animal AND Grass Album:");
        printPhotos(a2.getPhotos());

        System.out.println("All Photos Album:");
        printPhotos(a3.getPhotos());

        System.out.println("Deleting 'bear.jpg'...");
        pm.deletePhoto("bear.jpg");

        System.out.println("Bear Album after deletion:");
        printPhotos(a1.getPhotos());

        System.out.println("Number of comparisons in a2: " + a2.getNbComps());

        System.out.println("\n=== Phase 2: InvIndexPhotoManager ===");

        InvIndexPhotoManager invPm = new InvIndexPhotoManager();

        invPm.addPhoto(p1);
        invPm.addPhoto(p2);
        invPm.addPhoto(p3);
        invPm.addPhoto(p4);

        Album_inverted_index invA1 = new Album_inverted_index("Bear Album", "bear", invPm);
        Album_inverted_index invA2 = new Album_inverted_index("Animal and Grass", "animal AND grass", invPm);
        Album_inverted_index invA3 = new Album_inverted_index("All Photos", "", invPm);

        System.out.println("Bear Album (Inverted Index):");
        printPhotos(invA1.getPhotos());

        System.out.println("Animal AND Grass Album (Inverted Index):");
        printPhotos(invA2.getPhotos());

        System.out.println("All Photos Album (Inverted Index):");
        printPhotos(invA3.getPhotos());

        System.out.println("Deleting 'bear.jpg' from inverted index...");
        invPm.deletePhoto("bear.jpg");

        System.out.println("Bear Album (Inverted Index) after deletion:");
        printPhotos(invA1.getPhotos());

        System.out.println("Number of comparisons in invA2: " + invA2.getNbComps());
    }

    private static LinkedList<String> toTagsLinkedList(String tags) {
        LinkedList<String> result = new LinkedList<String>();
        String[] tagsArray = tags.split("\\s*,\\s*");
        for (String tag : tagsArray) {
            result.insert(tag);
        }
        return result;
    }

    private static void printPhotos(LinkedList<Photo> photos) {
        if (photos.empty()) {
            System.out.println("  (no photos)");
            return;
        }
        photos.findfirst();
        while (!photos.last()) {
            System.out.println("  " + photos.retrieve().getPath());
            photos.findnext();
        }
        System.out.println("  " + photos.retrieve().getPath());
    }
}
