package model;

public class model {
	    private String fileName = "";
	    private String path;

	    public model(String fileName, String path) {
	        this.fileName = fileName;
	        this.path = path;
	    }

	    public model(){

	    }
	    public String getFileName() {
	        return fileName;
	    }

	    public void setFileName(String fileName) {
	        this.fileName = fileName;
	    }

	    public String getPath() {
	        return path;
	    }

	    @Override
	    public String toString() {
	        return fileName;
	    }
	
}
