package toraylife.mappetextras.modules.scripting.scripts.code;

import toraylife.mappetextras.modules.scripting.scripts.user.IScriptPath;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.stream.Collectors;

public class ScriptPath implements IScriptPath {

    Path path;

    public ScriptPath(Path path) {
        this.path = path;
    }


    @Override
    public Path getPath() {
        return this.path;
    }

    @Override
    public String getFullName() {
        return this.path.getFileName().toString();
    }

    @Override
    public String getName() {
        int dotIndex = this.getFullName().lastIndexOf('.');
        return this.getFullName().substring(0, dotIndex);
    }

    @Override
    public String getExtension() {
        return this.getFullName().substring(this.getFullName().lastIndexOf('.'));
    }

    @Override
    public IScriptPath resolve(String path) {
        return new ScriptPath(this.path.resolve(path));
    }

    @Override
    public IScriptPath resolve(IScriptPath path) {
        return new ScriptPath(this.path.resolve(path.getPath()));
    }

    @Override
    public boolean isFile() {
        return this.path.toFile().isFile();
    }

    @Override
    public boolean isDirectory() {
        return this.path.toFile().isDirectory();
    }

    @Override
    public void write(String content) throws IOException {
        Files.createDirectories(this.path);
        Files.write(this.path, content.getBytes(StandardCharsets.UTF_8));
    }

    @Override
    public void append(String content) throws IOException {
        Files.createDirectories(this.path);
        Files.write(this.path, content.getBytes(StandardCharsets.UTF_8), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
    }

    @Override
    public String read() throws IOException {
        return String.join("\n", Files.readAllLines(this.path, StandardCharsets.UTF_8));
    }

    @Override
    public void clear() throws IOException {
        Files.write(this.path, new byte[0], StandardOpenOption.TRUNCATE_EXISTING);
    }

    @Override
    public void create() throws IOException {
        Files.createDirectories(this.path);
        Files.createFile(this.path);
    }

    @Override
    public void copy(IScriptPath path) throws IOException {
        Files.copy(path.getPath(), this.path);
    }

    @Override
    public void delete() throws IOException {
        Files.delete(this.path);
    }

    @Override
    public void move(IScriptPath path) throws IOException {
        Files.move(this.path, path.getPath());
    }

    @Override
    public List<IScriptPath> list() throws IOException {
        return Files.list(this.path).map(ScriptPath::new).collect(Collectors.toList());
    }
}
