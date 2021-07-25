package lucene;

import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.MMapDirectory;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Paths;

public class IndexReaderDemo {

    public static void main(String[] args) throws IOException {
        Directory directory = new MMapDirectory(Paths.get(URI.create("file:/tmp/index")));

        IndexReader directoryReader = DirectoryReader.open(directory);

        System.out.println(directoryReader.maxDoc());
        System.out.println(directoryReader.numDocs());
    }
}
