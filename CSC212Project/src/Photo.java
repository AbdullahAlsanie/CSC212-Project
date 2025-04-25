
public class Photo {
	
	String path;
	LinkedList<String> tags;
	
	//Constructor
	
	public Photo(String path, LinkedList<String> tags) {
	// Return the full file name (the path) of the photo. A photo is uniquely identified by its path.
		
		this.path = path;
		this.tags = tags;
	}
	
	public String getPath() {
	// Return all tags associated with the photo
		
		return path;
	}
	
	
	public LinkedList<String> getTags(){
	
		return tags;
	}

}
