package lucene;

import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.Sort;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.MMapDirectory;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Paths;

public class IndexSearchDemo {

    public static void main(String[] args) throws IOException {
        Directory directory = new MMapDirectory(Paths.get(URI.create("file:/tmp/index")));

        DirectoryReader directoryReader = DirectoryReader.open(directory);
        IndexSearcher indexSearcher = new IndexSearcher(directoryReader);
        ScoreDoc[] hits = indexSearcher.search(new TermQuery(new Term("fieldName", "text")), 10, Sort.INDEXORDER).scoreDocs;
        for (ScoreDoc scoreDoc : hits) {
            System.out.println(indexSearcher.doc(scoreDoc.doc).get("fieldName"));
        }
    }
}