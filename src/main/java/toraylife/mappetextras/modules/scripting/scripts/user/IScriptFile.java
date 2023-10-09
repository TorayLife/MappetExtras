package toraylife.mappetextras.modules.scripting.scripts.user;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public interface IScriptFile {

    /**
     * Returns the java Path object.
     *
     * <p>See link below for more information.</p>
     *
     * <pre>{@code
     *    "https://docs.oracle.com/javase/8/docs/api/java/nio/file/Path.html"
     *  }</pre>
     */
    Path getPath();

    /**
     * Returns full name of the file or directory.
     *
     * <pre>{@code
     *    function main(c)
     *    {
     *       var files = mappet.createPath("./configs/test.txt");
     *       c.send(files.getFullName()); // returns "test.txt"
     *    }
     *  }</pre>
     */
    String getFullName();

    /**
     * Returns name of the file or directory.
     *
     * <pre>{@code
     *    function main(c)
     *    {
     *       var files = mappet.createPath("./configs/test.txt");
     *       c.send(files.getName()); // returns "test"
     *    }
     *  }</pre>
     */
    String getName();

    /**
     * Returns extension of the file.
     *
     * <pre>{@code
     *    function main(c)
     *    {
     *       var files = mappet.createPath("./configs/test.txt");
     *       c.send(files.getExtension()); // returns ".txt"
     *    }
     *  }</pre>
     */
    String getExtension();

    /**
     * Returns new {@link IScriptFile} with combined path.
     *
     * <pre>{@code
     *    function main(c)
     *    {
     *       var files = mappet.createPath("./configs"); // "./configs"
     *       var testFile = files.resolve("test.txt"); // "./configs/test.txt"
     *    }
     *  }</pre>
     */
    IScriptFile resolve(String path);

    /**
     * Returns true if the path is a file.
     *
     * <pre>{@code
     *    function main(c)
     *    {
     *       var file = mappet.createPath("./configs/test.txt");
     *       if (file.isFile()) {
     *           c.send("This is a file with " + file.getExtension() + " extension.");
     *       }
     *    }
     *  }</pre>
     */
    boolean isFile();

    /**
     * Returns true if the path is a directory.
     *
     * <pre>{@code
     *    function main(c)
     *    {
     *       var dir = mappet.createPath("./configs");
     *       if (dir.isDirectory()) {
     *           c.send("This is a directory!");
     *       }
     *    }
     *  }</pre>
     */
    boolean isDirectory();

    /**
     * Writes content to the file.
     *
     * <pre>{@code
     *    function main(c)
     *    {
     *       var file = mappet.getWorldDir().resolve("test.txt");
     *       file.write("Hello World!");
     *    }
     *  }</pre>
     *
     * <p>Throws {@link IOException} If an I/O error occurs during the operation.</p>
     */
    void write(String content) throws IOException;

    /**
     * Adds content to the file.
     *
     * <pre>{@code
     *    function main(c)
     *    {
     *       var file = mappet.getWorldDir().resolve("test.txt");
     *       file.write("Hello World!");
     *       file.append("\nNew line!");
     *    }
     *  }</pre>
     *
     * <p>Throws {@link IOException} If an I/O error occurs during the operation.</p>
     */
    void append(String content) throws IOException;

    /**
     * Returns content from the file.
     *
     * <pre>{@code
     *    function main(c)
     *    {
     *       var file = mappet.getWorldDir().resolve("test.txt");
     *       c.send(file.read());
     *    }
     *  }</pre>
     *
     * <p>Throws {@link IOException} If an I/O error occurs during the operation.</p>
     */
    String read() throws IOException;

    /**
     * Clear content from the file.
     *
     * <pre>{@code
     *    function main(c)
     *    {
     *       var file = mappet.getWorldDir().resolve("test.txt");
     *       file.clear();
     *    }
     *  }</pre>
     *
     * <p>Throws {@link IOException} If an I/O error occurs during the operation.</p>
     */
    void clear() throws IOException;

    /**
     * Creates a new file.
     *
     * <pre>{@code
     *    function main(c)
     *    {
     *       var file = mappet.getWorldDir().resolve("test.txt");
     *       file.create();
     *    }
     *  }</pre>
     *
     * <p>Throws {@link IOException} If an I/O error occurs during the operation.</p>
     */
    void create() throws IOException;

    /**
     * Copies a file or a directory to new path.
     *
     * <pre>{@code
     *    function main(c)
     *    {
     *       var file = mappet.getWorldDir().resolve("test.txt");
     *       file.copy(mappet.getWorldDir().resolve("someFolder/test.txt"));
     *    }
     *  }</pre>
     *
     * <p>Throws {@link IOException} If an I/O error occurs during the operation.</p>
     */
    void copy(IScriptFile path) throws IOException;

    /**
     * Deletes a file or a directory.
     *
     * <pre>{@code
     *    function main(c)
     *    {
     *       var file = mappet.getWorldDir().resolve("test.txt");
     *       file.delete();
     *    }
     *  }</pre>
     *
     * <p>Throws {@link IOException} If an I/O error occurs during the operation.</p>
     */
    void delete() throws IOException;

    /**
     * Moves a file or a directory to new path.
     *
     * <p>This also acts like rename method.</p>
     *
     * <pre>{@code
     *    function main(c)
     *    {
     *       var file = mappet.getWorldDir().resolve("test.txt");
     *       file.move(mappet.getWorldDir().resolve("test1.txt"));
     *    }
     *  }</pre>
     *
     * <p>Throws {@link IOException} If an I/O error occurs during the operation.</p>
     */
    void move(IScriptFile path) throws IOException;

    /**
     * Returns a list of contents of the directory.
     *
     * <pre>{@code
     *    function main(c)
     *    {
     *       var file = mappet.getWorldDir().resolve("mappet");
     *       Java.from(file.list()).forEach(function(f) {
     *          c.send(f.getName());
     *       });
     *    }
     *  }</pre>
     *
     * <p>Throws {@link IOException} If an I/O error occurs during the operation.</p>
     */
    List<IScriptFile> list() throws IOException;
}
