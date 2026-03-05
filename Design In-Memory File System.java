// Design a data structure that simulates
// an in-memory file system.

// Implement the FileSystem class:

// FileSystem() Initializes the object of
// the system.
// List<String> ls(String path)
// If path is a file path, returns a list 
// that only contains this file's name.
// If path is a directory path, returns
// the list of file and directory names in
// this directory.
// The answer should in lexicographic order.
// void mkdir(String path) Makes a new directory
// according to the given path. The given
// directory path does not exist. If the
// middle directories in the path do not 
// exist, you should create them as well.
// void addContentToFile(String filePath,
// String content)
// If filePath does not exist, creates 
// that file containing given content.
// If filePath already exists, appends 
// the given content to original content.
// String readContentFromFile(String filePath)
// Returns the content in the file at filePath.

// FileSystem fileSystem = new FileSystem();
// fileSystem.ls("/");                         // return []
// fileSystem.mkdir("/a/b/c");
// fileSystem.addContentToFile("/a/b/c/d", "hello");
// fileSystem.ls("/");                         // return ["a"]
// fileSystem.readContentFromFile("/a/b/c/d"); // return "hello"

// 1 <= path.length, filePath.length <= 100
// path and filePath are absolute paths which
// begin with '/' and do not end with '/' except
// that the path is just "/".
// You can assume that all directory names and
// file names only contain lowercase letters,
// and the same names will not exist in the
// same directory.
// You can assume that all operations will 
// be passed valid parameters, and users
// will not attempt to retrieve file content
// or list a directory or file that does not
// exist.
// You can assume that the parent directory
// for the file in addContentToFile will exist.
// 1 <= content.length <= 50
// At most 300 calls will be made to ls,
// mkdir, addContentToFile, and readContentFromFile.

class FileSystem {
/**
 * Each Node represents either a directory or a file.
 * - If isFile == false, it's a directory; its children map stores files/directories inside it.
 * - If isFile == true, it is a file; content stores its file content.
 */
    private class Node {
        boolean isFile;
        StringBuilder content;
        Map<String, Node> children; 
// Mapping of child name → Node (file or directory)
        public Node() {
            isFile = false;          
            // By default, a new node is a directory
            content = new StringBuilder ();            
            // Only files have content; directories have empty string
            children = new HashMap<>(); 
            // Initialize empty map of children
        }
    }
    // Root node represents the "/" directory
    private Node root;

    public FileSystem() {
        root = new Node();  // Initialize root as empty directory
    }

/**
 * List the files/directories at the given path.
 * - If path points to a file, return list containing only the file name.
 * - If path points to a directory, return the names of all children, sorted lexicographically.
 */
    public List<String> ls(String path) {
// Traverse the path to reach the final node
        Node current = traversePath(path);
        List<String> result = new ArrayList<>();

        if (current.isFile) {
// If the path is a file, return only the file name
            String[] parts = path.split("/");
            result.add(parts[parts.length - 1]); 
// Last part of path = file name
        } else {
// If the path is a directory, return all children names
            result.addAll(current.children.keySet());
            Collections.sort(result);  
// Problem requires lexicographical order
        }

        return result;
    }

/**
* Create directories along the given path.
* - If intermediate directories do not exist, create them.
* - We do not create files here; only directories.
*/
    public void mkdir(String path) {
        traversePath(path, true);  
// Create missing directories along the path
    }

/**
* Add content to a file.
* - If the file does not exist, create it.
* - If the file already exists, append the content.
*/
    public void addContentToFile(String filePath, String content) {
        Node fileNode = traversePath(filePath, true); 
        // Create missing nodes
        fileNode.isFile = true;                       
        // Mark the last node as file
        fileNode.content.append(content);                 
        // Append content
    }

/**
 * Read content from a file.
 * - Assumes file exists (as per problem constraints).
 */
    public String readContentFromFile(String filePath) {
        Node fileNode = traversePath(filePath);     
// Traverse to the file node
        return fileNode.content;                 
// Return its content
    }
/**
 * Helper function to traverse a path and return the corresponding node.
 * - By default, does not create missing nodes.
 */
    private Node traversePath(String path) {
        return traversePath(path, false);
    }
/**
 * Overloaded helper: traverse path with optional creation of missing nodes.
 * @param path The absolute path (e.g., "/a/b/c")
 * @param createIfMissing If true, missing directories/files along the path are created
 */
    private Node traversePath(String path, boolean createIfMissing) {
// Split path by "/" to get each part of the path
        String[] parts = path.split("/");
// Start at root directory "/"
        Node current = root; 
// Traverse each part of the path
        for (int i = 1; i < parts.length; i++) { 
// Skip first empty string before first "/"
            String part = parts[i];
// If the child does not exist and we are allowed to create it
            if (!current.children.containsKey(part)) {
                if (createIfMissing) {
                current.children.put(part, new Node()); 
// Create new directory/file
                } else {
// Safe because problem guarantees valid paths; otherwise we could throw exception
                    current.children.putIfAbsent(part, new Node());
                }
            }

// Move to the next node
            current = current.children.get(part);
        }

// Return the node representing the final directory or file
        return current;
    }
}

// Time :O(N+KlogK)
// Space :O(K)

// N = depth of the path (number of parts in the path, i.e., P in previous explanation).
// K = number of items in the directory being listed (children size).