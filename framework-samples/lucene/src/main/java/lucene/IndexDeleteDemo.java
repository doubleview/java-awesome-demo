package lucene;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.MMapDirectory;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Paths;

public class IndexDeleteDemo {

    public static void main(String[] args) throws IOException {
        Analyzer analyzer = new StandardAnalyzer();
        Directory directory = new MMapDirectory(Paths.get(URI.create("file:/tmp/index")));

        IndexWriterConfig config = new IndexWriterConfig(analyzer);
        IndexWriter indexWriter = new IndexWriter(directory, config);

        indexWriter.deleteAll();
        indexWriter.close();
    }
}
